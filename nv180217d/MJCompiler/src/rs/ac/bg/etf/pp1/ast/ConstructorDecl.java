// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ConstructorDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConstructorName ConstructorName;
    private VarDeclMore VarDeclMore;
    private MoreStatement MoreStatement;

    public ConstructorDecl (ConstructorName ConstructorName, VarDeclMore VarDeclMore, MoreStatement MoreStatement) {
        this.ConstructorName=ConstructorName;
        if(ConstructorName!=null) ConstructorName.setParent(this);
        this.VarDeclMore=VarDeclMore;
        if(VarDeclMore!=null) VarDeclMore.setParent(this);
        this.MoreStatement=MoreStatement;
        if(MoreStatement!=null) MoreStatement.setParent(this);
    }

    public ConstructorName getConstructorName() {
        return ConstructorName;
    }

    public void setConstructorName(ConstructorName ConstructorName) {
        this.ConstructorName=ConstructorName;
    }

    public VarDeclMore getVarDeclMore() {
        return VarDeclMore;
    }

    public void setVarDeclMore(VarDeclMore VarDeclMore) {
        this.VarDeclMore=VarDeclMore;
    }

    public MoreStatement getMoreStatement() {
        return MoreStatement;
    }

    public void setMoreStatement(MoreStatement MoreStatement) {
        this.MoreStatement=MoreStatement;
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
        if(ConstructorName!=null) ConstructorName.accept(visitor);
        if(VarDeclMore!=null) VarDeclMore.accept(visitor);
        if(MoreStatement!=null) MoreStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorName!=null) ConstructorName.traverseTopDown(visitor);
        if(VarDeclMore!=null) VarDeclMore.traverseTopDown(visitor);
        if(MoreStatement!=null) MoreStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorName!=null) ConstructorName.traverseBottomUp(visitor);
        if(VarDeclMore!=null) VarDeclMore.traverseBottomUp(visitor);
        if(MoreStatement!=null) MoreStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstructorDecl(\n");

        if(ConstructorName!=null)
            buffer.append(ConstructorName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclMore!=null)
            buffer.append(VarDeclMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreStatement!=null)
            buffer.append(MoreStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstructorDecl]");
        return buffer.toString();
    }
}
