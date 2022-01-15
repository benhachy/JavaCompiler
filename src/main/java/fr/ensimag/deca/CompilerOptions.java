package fr.ensimag.deca;

import static org.mockito.ArgumentMatchers.anyString;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import fr.ensimag.ima.pseudocode.Register;

/**
 * User-specified options influencing the compilation.
 *
 * @author gl03
 * @date 01/01/2022
 */
public class CompilerOptions {
    public static final int QUIET = 0;
    public static final int INFO  = 1;
    public static final int DEBUG = 2;
    public static final int TRACE = 3;
    public int getDebug() {
        return debug;
    }

    public boolean getParallel() {
        return parallel;
    }

    public boolean getPrintBanner() {
        return printBanner;
    }

    public boolean getVerification() {
        return verification;
    }
    public boolean getParse() {
        return parse;
    }
    
    public List<File> getSourceFiles() {
        return Collections.unmodifiableList(sourceFiles);
    }

    private int debug = 0;
    private boolean parallel = false;
    private boolean printBanner = false;
    private boolean verification = false;
    private boolean parse = false;
    private List<File> sourceFiles = new ArrayList<File>();

    
    public void parseArgs(String[] args) throws CLIException {
        // A FAIRE : parcourir args pour positionner les options correctement.
        Logger logger = Logger.getRootLogger();
        // map command-line debug option to log4j's level.
        switch (getDebug()) {
        case QUIET: break; // keep default
        case INFO:
            logger.setLevel(Level.INFO); break;
        case DEBUG:
            logger.setLevel(Level.DEBUG); break;
        case TRACE:
            logger.setLevel(Level.TRACE); break;
        default:
            logger.setLevel(Level.ALL); break;
        }
        logger.info("Application-wide trace level set to " + logger.getLevel());

        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side effect!!!
        if (assertsEnabled) {
            logger.info("Java assertions enabled");
        } else {
            logger.info("Java assertions disabled");
        }
        int i =0;
        int posArgs = 0;
        char lastArgument=' ';
        for (String s : args)
        {
            if(s.charAt(0)=='-')
            {
                i ++;
                switch(s.charAt(1))
                {
                    case 'b':
                       printBanner= true;
                        break;
                    case 'v':
                        verification= true;
                        break;
                    case 'P':
                        parallel= true;
                        break;
                    case 'p':
                        parse = true;
                    case 'r':
                        break;
                }
            }else{
                if(lastArgument=='r'){
                    if(s.charAt(0) >= '0' && s.charAt(0) <= '9'){
                        try{
                            int nbRegistres = Integer.parseInt(s);
                            if(nbRegistres>16||nbRegistres<4){
                                throw new UnsupportedOperationException("-r can only take values between 4 and 16");
                            }else{
                                //let know the compiler how many registers it can use 
                                System.out.println("we will only use "+nbRegistres+" registers");
                                Register.nbRegistres = nbRegistres;
                            }
                        }catch (NumberFormatException ex){
                            throw new UnsupportedOperationException("-r can only take values between 4 and 16");
                        }
                    }else{
                        throw new UnsupportedOperationException("-r can only take values between 4 and 16");
                    }
                }else{
                    sourceFiles.add(new File(args[posArgs]));
                }
            }
            if(s.length()>1){
                if(s.charAt(0)=='-'){
                    lastArgument = s.charAt(1);
                }else{
                    lastArgument = ' ';
                }
            }else{
                lastArgument = ' ';
            }
            posArgs++;
        }
    }

    protected void displayUsage() {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
