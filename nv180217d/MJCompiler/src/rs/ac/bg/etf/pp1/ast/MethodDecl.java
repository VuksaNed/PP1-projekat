// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class MethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethodTypeName MethodTypeName;
    private MethodDeclFormPars MethodDeclFormPars;
    private VarDeclMore VarDeclMore;
    private MoreStatement MoreStatement;

    public MethodDecl (MethodTypeName MethodTypeName, MethodDeclFormPars MethodDeclFormPars, VarDeclMore VarDeclMore, MoreStatement MoreStatement) {
        this.MethodTypeName=MethodTypeName;
        if(MethodTypeName!=null) MethodTypeName.setParent(this);
        this.MethodDeclFormPars=MethodDeclFormPars;
        if(MethodDeclFormPars!=null) MethodDeclFormPars.setParent(this);
        this.VarDeclMore=VarDeclMore;
        if(VarDeclMore!=null) VarDeclMore.setParent(this);
        this.MoreStatement=MoreStatement;
        if(MoreStatement!=null) MoreStatement.setParent(this);
    }

    public MethodTypeName getMethodTypeName() {
        return MethodTypeName;
    }

    public void setMethodTypeName(MethodTypeName MethodTypeName) {
        this.MethodTypeName=MethodTypeName;
    }

    public MethodDeclFormPars getMethodDeclFormPars() {
        return MethodDeclFormPars;
    }

    public void setMethodDeclFormPars(MethodDeclFormPars MethodDeclFormPars) {
        this.MethodDeclFormPars=MethodDeclFormPars;
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
        if(MethodTypeName!=null) MethodTypeName.accept(visitor);
        if(MethodDeclFormPars!=null) MethodDeclFormPars.accept(visitor);
        if(VarDeclMore!=null) VarDeclMore.accept(visitor);
        if(MoreStatement!=null) MoreStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodTypeName!=null) MethodTypeName.traverseTopDown(visitor);
        if(MethodDeclFormPars!=null) MethodDeclFormPars.traverseTopDown(visitor);
        if(VarDeclMore!=null) VarDeclMore.traverseTopDown(visitor);
        if(MoreStatement!=null) MoreStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodTypeName!=null) MethodTypeName.traverseBottomUp(visitor);
        if(MethodDeclFormPars!=null) MethodDeclFormPars.traverseBottomUp(visitor);
        if(VarDeclMore!=null) VarDeclMore.traverseBottomUp(visitor);
        if(MoreStatement!=null) MoreStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecl(\n");

        if(MethodTypeName!=null)
            buffer.append(MethodTypeName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclFormPars!=null)
            buffer.append(MethodDeclFormPars.toString("  "+tab));
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
        buffer.append(") [MethodDecl]");
        return buffer.toString();
    }
}
