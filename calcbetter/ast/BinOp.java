package ast;

public class BinOp extends Expr {
    private Op op;
    private Expr e1;
    private Expr e2;

    public BinOp(Op op, Expr e1, Expr e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    public Op getOp() {
        return op;
    }

    public Expr getE1() {
        return e1;
    }

    public Expr getE2() {
        return e2;
    }
}