package ast;

public class Id extends Expr {
    private String id;

    public Id(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
