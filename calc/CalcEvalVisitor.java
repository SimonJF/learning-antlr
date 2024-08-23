import java.util.LinkedHashMap;
import java.util.Map;

public class CalcEvalVisitor extends CalcBaseVisitor<Integer> {

    Map<String, Integer> memory = new LinkedHashMap<>();

    // <expr> NEWLINE
    @Override
    public Integer visitPrintExpr(CalcParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    // ID = <expr> NEWLINE
    @Override
    public Integer visitAssign(CalcParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitParens(CalcParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    // expr op expr
    @Override
    public Integer visitMulDiv(CalcParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        switch (ctx.op.getType()) {
            case CalcParser.MUL: return (left * right);
            case CalcParser.DIV: return (left / right);
        }
        throw new RuntimeException("invalid op");
    }
    public Integer visitAddSub(CalcParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        switch (ctx.op.getType()) {
            case CalcParser.ADD: return (left * right);
            case CalcParser.SUB: return (left / right);
        }
        throw new RuntimeException("invalid op");
    }

    @Override
    public Integer visitId(CalcParser.IdContext ctx) {
        return memory.get(ctx.getText());
    }

    @Override
    public Integer visitInt(CalcParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }
}
