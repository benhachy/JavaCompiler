package fr.ensimag.deca;

import java.io.File;
import org.apache.log4j.Logger;

/**
 * Main class for the command-line Deca compiler.
 *
 * @author gl03
 * @date 01/01/2022
 */
public class DecacMain {
    private static Logger LOG = Logger.getLogger(DecacMain.class);
    
    public static void main(String[] args) {
        // example log4j message.
        LOG.info("Decac compiler started");
        boolean error = false;
        final CompilerOptions options = new CompilerOptions();
        try {
            options.parseArgs(args);
        } catch (CLIException e) {
            System.err.println("Error during option parsing:\n"
                    + e.getMessage());
            options.displayUsage();
            System.exit(1);
        }
        if (options.getPrintBanner()) {
            //throw new UnsupportedOperationException("decac -b not yet implemented");
            System.out.println("----- Equipe gl03 -----");
        }
        if (options.getSourceFiles().isEmpty()) {
            //throw new UnsupportedOperationException("decac without argument not yet implemented");
        }
        if (options.getParallel()) {
            Thread[] threads = new Thread[options.getSourceFiles().size()];
            DecacCompiler[] compilers = new DecacCompiler[options.getSourceFiles().size()];
            int i = 0;
            for (File source : options.getSourceFiles()) {
                compilers[i] = new DecacCompiler(options, source);
                threads[i] = new Thread(compilers[i]);
                threads[i].start();
                System.out.println("Thread #"+i);
                i++;
            }
            int j=0;
            for (j =0 ; j <  i ; j++) {
                try{
                    threads[j].join();
                }catch(Exception ex)
                {
                    System.out.println("Exception has "+"been caught" + ex);
                }
                j++;
            }

        } else {
            for (File source : options.getSourceFiles()) {
                DecacCompiler compiler = new DecacCompiler(options, source);
                if(compiler.compile()) {
                    error = true;
                }
            }
            System.exit(error ? 1 : 0);
        }
        
    }
}
