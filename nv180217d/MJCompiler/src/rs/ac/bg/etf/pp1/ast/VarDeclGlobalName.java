// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class VarDeclGlobalName implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String varName;
    private VarDeclBrace VarDeclBrace;

    public VarDeclGlobalName (String varName, VarDeclBrace VarDeclBrace) {
        this.varName=varName;
        this.VarDeclBrace=VarDeclBrace;
        if(VarDeclBrace!=null) VarDeclBrace.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public VarDeclBrace getVarDeclBrace() {
        return VarDeclBrace;
    }

    public void setVarDeclBrace(VarDeclBrace VarDeclBrace) {
        this.VarDeclBrace=VarDeclBrace;
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
        if(VarDeclBrace!=null) VarDeclBrace.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclBrace!=null) VarDeclBrace.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclBrace!=null) VarDeclBrace.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclGlobalName(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(VarDeclBrace!=null)
            buffer.append(VarDeclBrace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclGlobalName]");
        return buffer.toString();
    }
}
