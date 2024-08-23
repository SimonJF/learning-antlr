package ast;

public class Print extends Statement {
    private Expr printee;
    public Print(Expr printee) {
        this.printee = printee;
    }
    public Expr getPrintee() {
        return this.printee;
    }
}
