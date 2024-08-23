import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import ast.*;

import java.util.List;

public class GenCalcAST {

    public class GenStatement extends CalcBetterBaseVisitor<Statement> {
        private Expr genExpr(ParseTree t) {
            return new GenExpr().visit(t);
        }

        @Override
        public Statement visitPrintExpr(CalcBetterParser.PrintExprContext ctx) {
            return new Print(genExpr(ctx.expr()));
        }

        @Override
        public Statement visitAssign(CalcBetterParser.AssignContext ctx) {
            String id = ctx.ID().getText();
            return new Assign(id, genExpr(ctx.expr()));
        }

    }

    public class GenExpr extends CalcBetterBaseVisitor<Expr> {

        @Override
        public Expr visitParens(CalcBetterParser.ParensContext ctx) {
            return visit(ctx.expr());
        }

        @Override
        public Expr visitMulDiv(CalcBetterParser.MulDivContext ctx) {
            Op op;
            if (ctx.op.getType() == CalcBetterParser.MUL) {
                op = Op.MUL;
            } else {
                op = Op.DIV;
            }
            Expr e1 = visit(ctx.expr(0));
            Expr e2 = visit(ctx.expr(1));
            return new BinOp(op, e1, e2);
        }

        @Override
        public Expr visitAddSub(CalcBetterParser.AddSubContext ctx) {
            Op op;
            if (ctx.op.getType() == CalcBetterParser.ADD) {
                op = Op.ADD;
            } else {
                op = Op.SUB;
            }
            Expr e1 = visit(ctx.expr(0));
            Expr e2 = visit(ctx.expr(1));
            return new BinOp(op, e1, e2);
        }

        @Override
        public Expr visitId(CalcBetterParser.IdContext ctx) {
            return new Id(ctx.getText());
        }


        @Override
        public Expr visitInt(CalcBetterParser.IntContext ctx) {
            return new IntLit(Integer.valueOf(ctx.getText()));
        }
    }

    public Program genAST(ParseTree tree) {
        assert(tree != null);
        if (tree instanceof CalcBetterParser.ProgContext) {
            List<Statement> statements = ((CalcBetterParser.ProgContext) tree)
                    .stat()
                    .stream()
                    .map((CalcBetterParser.StatContext s) -> (new GenStatement()).visit(s))
                    .toList();
            Program prog = new Program(statements);
            return (new Program(statements));
        } else {
            throw new RuntimeException("expected a Program node");
        }
    }
}
