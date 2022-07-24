// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ActPars implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ActParsExpr ActParsExpr;

    public ActPars (ActParsExpr ActParsExpr) {
        this.ActParsExpr=ActParsExpr;
        if(ActParsExpr!=null) ActParsExpr.setParent(this);
    }

    public ActParsExpr getActParsExpr() {
        return ActParsExpr;
    }

    public void setActParsExpr(ActParsExpr ActParsExpr) {
        this.ActParsExpr=ActParsExpr;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParsExpr!=null) ActParsExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsExpr!=null) ActParsExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsExpr!=null) ActParsExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActPars(\n");

        if(ActParsExpr!=null)
            buffer.append(ActParsExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActPars]");
        return buffer.toString();
    }
}
