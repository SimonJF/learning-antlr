import java.util.LinkedHashMap;
import java.util.Map;
import ast.*;

public class Eval {

    private Map<String, Integer> memory = new LinkedHashMap<>();

    public int evalExpr(Expr e) {
        if (e instanceof IntLit) {
            return ((IntLit) e).getValue();
        } else if (e instanceof Id) {
            return memory.get(((Id) e).getId());
        } else if (e instanceof BinOp) {
            BinOp binop = (BinOp) e;
            int v1 = evalExpr(binop.getE1());
            int v2 = evalExpr(binop.getE2());
            return switch (binop.getOp()) {
                case ADD -> v1 + v2;
                case SUB -> v1 - v2;
                case MUL -> v1 * v2;
                case DIV -> v1 / v2;
            };
        }
        throw new RuntimeException("non-exhaustive expression evaluation: " + e.toString());
    }

    public void evalStatement(Statement s) {
        if (s instanceof Assign) {
            Assign assign = (Assign) s;
            int result = evalExpr(assign.getAsignee());
            memory.put(assign.getId(), result);
        } else if (s instanceof ast.Print) {
            Print p = (Print) s;
            int v = evalExpr(p.getPrintee());
            System.out.println(v);
        } else {
            throw new RuntimeException("non-exhaustive statement evaluation: " + s.toString());
        }
    }

    public void evalProgram(Program p) {
        for (Statement s : p.getStatements()) {
            evalStatement(s);
        }
    }
}
