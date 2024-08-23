import ast.Program;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) {
            inputFile = args[0];
        }
        InputStream is = System.in;
        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream input = new ANTLRInputStream(is);
        CalcBetterLexer lexer = new CalcBetterLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcBetterParser parser = new CalcBetterParser(tokens);

        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));
        Program p = (new GenCalcAST()).genAST(tree);
        new Eval().evalProgram(p);
    }
}
