package ast;

public class Assign extends Statement {
    private String id;
    private Expr asignee;

    public Assign(String id, Expr asignee) {
        this.id = id;
        this.asignee = asignee;
    }
    public String getId() {
        return id;
    }
    public Expr getAsignee() {
        return asignee;
    }
}