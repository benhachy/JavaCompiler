package fr.ensimag.deca;

import fr.ensimag.deca.context.BooleanType;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ClassType;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.EnvironmentExp.DoubleDefException;
import fr.ensimag.deca.context.FloatType;
import fr.ensimag.deca.context.IntType;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.StringType;
import fr.ensimag.deca.context.TypeDefinition;
import fr.ensimag.deca.context.VoidType;
import fr.ensimag.deca.syntax.DecaLexer;
import fr.ensimag.deca.syntax.DecaParser;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.deca.tree.AbstractProgram;
import fr.ensimag.deca.tree.LocationException;
import fr.ensimag.ima.pseudocode.AbstractLine;
import fr.ensimag.ima.pseudocode.IMAProgram;
import fr.ensimag.ima.pseudocode.Instruction;
import fr.ensimag.ima.pseudocode.Label;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.log4j.Logger;

/**
 * Decac compiler instance.
 *
 * This class is to be instantiated once per source file to be compiled. It
 * contains the meta-data used for compiling (source file name, compilation
 * options) and the necessary utilities for compilation (symbol tables, abstract
 * representation of target file, ...).
 *
 * It contains several objects specialized for different tasks. Delegate methods
 * are used to simplify the code of the caller (e.g. call
 * compiler.addInstruction() instead of compiler.getProgram().addInstruction()).
 *
 * @author gl03
 * @date 01/01/2022
 */
public class DecacCompiler implements Runnable {
    private static final Logger LOG = Logger.getLogger(DecacCompiler.class);

    /**
     * Portable newline character.
     */
    private static final String nl = System.getProperty("line.separator", "\n");
    private HashMap<SymbolTable.Symbol, TypeDefinition> envTypes;
    private HashMap<SymbolTable.Symbol, EnvironmentExp> envExprs;
    private HashMap<SymbolTable.Symbol, Integer> posLB;
    private Label returnLabel;

    public DecacCompiler(CompilerOptions compilerOptions, File source) {
        super();
        this.compilerOptions = compilerOptions;
        this.source = source;
        envTypes = new HashMap<SymbolTable.Symbol, TypeDefinition>();
        envExprs = new HashMap<SymbolTable.Symbol, EnvironmentExp>();
        posLB = new HashMap<SymbolTable.Symbol, Integer>();
        this.initiate();
    }

    public Label getReturnLabel() {
        return returnLabel;
    }

    public void setReturnLabel(Label label) {
        this.returnLabel = label;
    }

    public void addIndexParam(Symbol symbol, int index) {
        posLB.put(symbol, index);
    }

    public void cleanParam() {
        // idk
    }

    public int getIndexParam(Symbol symbol) {
        return posLB.get(symbol);
    }

    /**
     * Source file associated with this compiler instance.
     */
    public File getSource() {
        return source;
    }

    private void initiate() {
        SymbolTable.Symbol s = SymbolTable.creerSymbol("int");
        SymbolTable.Symbol s1 = SymbolTable.creerSymbol("float");
        SymbolTable.Symbol s2 = SymbolTable.creerSymbol("boolean");
        SymbolTable.Symbol s3 = SymbolTable.creerSymbol("void");
        SymbolTable.Symbol s4 = SymbolTable.creerSymbol("string");
        envTypes.put(s, new TypeDefinition(new IntType(s), null));
        envTypes.put(s1, new TypeDefinition(new FloatType(s1), null));
        envTypes.put(s2, new TypeDefinition(new BooleanType(s2), null));
        envTypes.put(s3, new TypeDefinition(new VoidType(s3), null));
        envTypes.put(s4, new TypeDefinition(new StringType(s4), null));
        // définir la class Object
        SymbolTable.Symbol o = SymbolTable.creerSymbol("Object");
        SymbolTable.Symbol sup = SymbolTable.creerSymbol("0");
        ClassType superObject = new ClassType(sup, null, null);
        ClassType objectClass = new ClassType(o, null, superObject.getDefinition());
        ClassDefinition classeDef = objectClass.getDefinition();
        classeDef.setNumberOfFields(0);
        classeDef.setNumberOfMethods(1);
        envTypes.put(o, classeDef);
        Signature sign = new Signature();
        sign.add(objectClass);
        SymbolTable.Symbol e = SymbolTable.creerSymbol("equals");
        MethodDefinition equal = new MethodDefinition(new BooleanType(e), null, sign, 1);
        EnvironmentExp envObjet = new EnvironmentExp(null);
        envObjet.update(e, equal);
        // envExprs.put(e,envObjet);
        envExprs.put(o, envObjet);
    }

    public void declare(Symbol name, TypeDefinition def) throws DoubleDefException {
        if (envTypes.containsKey(name)) {
            throw new DoubleDefException("La class " + name.getName() + " est déjà définie");
        }
        envTypes.put(name, def);
    }

    public void setEvn(Symbol s, EnvironmentExp def) {
        if (envExprs.containsKey(s)) {
            envExprs.replace(s, def);
        } else {
            envExprs.put(s, def);
        }
    }

    public int getNumberOfClass() {
        return envTypes.size() - 5;
    }

    // public int getIndexMethod(Symbol classe, Symbol method) {
    // return envExprs.get(classe).contains(method);
    // }

    public int getNumberOfMethods() {
        envExprs.size();
        int nbMethods = 0;
        for (Symbol s : envTypes.keySet()) {
            if (envTypes.get(s).isClass()) {
                nbMethods += ((ClassDefinition) envTypes.get(s)).getNumberOfMethods();
            }
        }
        return nbMethods;
    }

    public void update(Symbol name, TypeDefinition def) {
        if (envTypes.containsKey(name)) {
            envTypes.replace(name, def);
        }
    }

    public TypeDefinition get(Symbol key) {
        if (envTypes.containsKey(key)) {
            return envTypes.get(key);
        }
        return null;
    }

    public TypeDefinition getDefinition(Symbol key) {
        if (envTypes.containsKey(key)) {
            return envTypes.get(key);
        }
        return null;
    }

    public ClassDefinition getClass(Symbol key) {
        if (envTypes.containsKey(key)) {
            try {
                return (ClassDefinition) envTypes.get(key);
            } catch (ClassCastException e) {
                throw new DecacInternalError(" Une erreur lors du casting");
            }

        }
        return null;
    }

    public EnvironmentExp getEnv(Symbol s) {
        if (envExprs.containsKey(s)) {
            return envExprs.get(s);
        }
        return null;
    }

    /**
     * Compilation options (e.g. when to stop compilation, number of registers
     * to use, ...).
     */
    public CompilerOptions getCompilerOptions() {
        return compilerOptions;
    }

    /**
     * @see
     *      fr.ensimag.ima.pseudocode.IMAProgram#add(fr.ensimag.ima.pseudocode.AbstractLine)
     */
    public void add(AbstractLine line) {
        program.add(line);
    }

    /**
     * @see fr.ensimag.ima.pseudocode.IMAProgram#addComment(java.lang.String)
     */
    public void addComment(String comment) {
        program.addComment(comment);
    }

    /**
     * @see
     *      fr.ensimag.ima.pseudocode.IMAProgram#addLabel(fr.ensimag.ima.pseudocode.Label)
     */
    public void addLabel(Label label) {
        program.addLabel(label);
    }

    /**
     * @see
     *      fr.ensimag.ima.pseudocode.IMAProgram#addInstruction(fr.ensimag.ima.pseudocode.Instruction)
     */
    public void addInstruction(Instruction instruction) {
        program.addInstruction(instruction);
    }

    /**
     * @see
     *      fr.ensimag.ima.pseudocode.IMAProgram#addInstruction(fr.ensimag.ima.pseudocode.Instruction,
     *      java.lang.String)
     */
    public void addInstruction(Instruction instruction, String comment) {
        program.addInstruction(instruction, comment);
    }

    /**
     * @see
     *      fr.ensimag.ima.pseudocode.IMAProgram#display()
     */
    public String displayIMAProgram() {
        return program.display();
    }

    private final CompilerOptions compilerOptions;
    private final File source;
    /**
     * The main program. Every instruction generated will eventually end up here.
     */
    private final IMAProgram program = new IMAProgram();

    /**
     * Run the compiler (parse source file, generate code)
     *
     * @return true on error
     */
    public boolean compile() {

        String sourceFile = source.getAbsolutePath();
        String[] result = sourceFile.split("[.]");
        String destFile = "";
        for (int i = 0; i < result.length - 1; i++) {
            destFile += result[i] + ".";
        }
        destFile += "ass";

        // A FAIRE: calculer le nom du fichier .ass à partir du nom du

        // A FAIRE: fichier .deca.
        PrintStream err = System.err;
        PrintStream out = System.out;
        LOG.debug("Compiling file " + sourceFile + " to assembly file " + destFile);
        try {
            return doCompile(sourceFile, destFile, out, err);
        } catch (LocationException e) {
            e.display(err);
            return true;
        } catch (DecacFatalError e) {
            err.println(e.getMessage());
            return true;
        } catch (StackOverflowError e) {
            LOG.debug("stack overflow", e);
            err.println("Stack overflow while compiling file " + sourceFile + ".");
            return true;
        } catch (Exception e) {
            LOG.fatal("Exception raised while compiling file " + sourceFile
                    + ":", e);
            err.println("Internal compiler error while compiling file " + sourceFile + ", sorry.");
            return true;
        } catch (AssertionError e) {
            LOG.fatal("Assertion failed while compiling file " + sourceFile
                    + ":", e);
            err.println("Internal compiler error while compiling file " + sourceFile + ", sorry.");
            return true;
        }
    }

    /**
     * Internal function that does the job of compiling (i.e. calling lexer,
     * verification and code generation).
     *
     * @param sourceName name of the source (deca) file
     * @param destName   name of the destination (assembly) file
     * @param out        stream to use for standard output (output of decac -p)
     * @param err        stream to use to display compilation errors
     *
     * @return true on error
     */
    private boolean doCompile(String sourceName, String destName,
            PrintStream out, PrintStream err)
            throws DecacFatalError, LocationException {
        AbstractProgram prog = doLexingAndParsing(sourceName, err);

        if (prog == null) {
            LOG.info("Parsing failed");
            return true;
        } else {
            assert (prog.checkAllLocations());

            if (compilerOptions.getParse()) {
                prog.decompile(new IndentPrintStream(System.out));
            } else {

                prog.verifyProgram(this);

                if (!compilerOptions.getVerification()) {

                    assert (prog.checkAllDecorations());
                    addComment("start main program");
                    prog.codeGenProgram(this);
                    addComment("end main program");

                    LOG.debug("Generated assembly code:" + nl + program.display());
                    LOG.info("Output file assembly file is: " + destName);

                    FileOutputStream fstream = null;
                    try {
                        fstream = new FileOutputStream(destName);
                    } catch (FileNotFoundException e) {
                        throw new DecacFatalError("Failed to open output file: " + e.getLocalizedMessage());
                    }

                    LOG.info("Writing assembler file ...");

                    program.display(new PrintStream(fstream));
                    LOG.info("Compilation of " + sourceName + " successful.");
                }
            }
            return false;
        }
    }

    /**
     * Build and call the lexer and parser to build the primitive abstract
     * syntax tree.
     *
     * @param sourceName Name of the file to parse
     * @param err        Stream to send error messages to
     * @return the abstract syntax tree
     * @throws DecacFatalError    When an error prevented opening the source file
     * @throws DecacInternalError When an inconsistency was detected in the
     *                            compiler.
     * @throws LocationException  When a compilation error (incorrect program)
     *                            occurs.
     */
    protected AbstractProgram doLexingAndParsing(String sourceName, PrintStream err)
            throws DecacFatalError, DecacInternalError {
        DecaLexer lex;
        try {
            lex = new DecaLexer(CharStreams.fromFileName(sourceName));
        } catch (IOException ex) {
            throw new DecacFatalError("Failed to open input file: " + ex.getLocalizedMessage());
        }
        lex.setDecacCompiler(this);
        CommonTokenStream tokens = new CommonTokenStream(lex);
        DecaParser parser = new DecaParser(tokens);
        parser.setDecacCompiler(this);
        return parser.parseProgramAndManageErrors(err);
    }

    @Override
    public void run() {
        compile();
    }

}
