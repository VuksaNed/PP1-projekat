// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ActParsExprMore extends ActParsExpr {

    private ActParsExpr ActParsExpr;
    private Expr Expr;

    public ActParsExprMore (ActParsExpr ActParsExpr, Expr Expr) {
        this.ActParsExpr=ActParsExpr;
        if(ActParsExpr!=null) ActParsExpr.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ActParsExpr getActParsExpr() {
        return ActParsExpr;
    }

    public void setActParsExpr(ActParsExpr ActParsExpr) {
        this.ActParsExpr=ActParsExpr;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParsExpr!=null) ActParsExpr.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsExpr!=null) ActParsExpr.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsExpr!=null) ActParsExpr.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActParsExprMore(\n");

        if(ActParsExpr!=null)
            buffer.append(ActParsExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActParsExprMore]");
        return buffer.toString();
    }
}
