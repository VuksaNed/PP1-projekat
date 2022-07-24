// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class VarDeclClass implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private VarDeclClassName VarDeclClassName;
    private VarDecClasslList VarDecClasslList;

    public VarDeclClass (Type Type, VarDeclClassName VarDeclClassName, VarDecClasslList VarDecClasslList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclClassName=VarDeclClassName;
        if(VarDeclClassName!=null) VarDeclClassName.setParent(this);
        this.VarDecClasslList=VarDecClasslList;
        if(VarDecClasslList!=null) VarDecClasslList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclClassName getVarDeclClassName() {
        return VarDeclClassName;
    }

    public void setVarDeclClassName(VarDeclClassName VarDeclClassName) {
        this.VarDeclClassName=VarDeclClassName;
    }

    public VarDecClasslList getVarDecClasslList() {
        return VarDecClasslList;
    }

    public void setVarDecClasslList(VarDecClasslList VarDecClasslList) {
        this.VarDecClasslList=VarDecClasslList;
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
        if(Type!=null) Type.accept(visitor);
        if(VarDeclClassName!=null) VarDeclClassName.accept(visitor);
        if(VarDecClasslList!=null) VarDecClasslList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclClassName!=null) VarDeclClassName.traverseTopDown(visitor);
        if(VarDecClasslList!=null) VarDecClasslList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclClassName!=null) VarDeclClassName.traverseBottomUp(visitor);
        if(VarDecClasslList!=null) VarDecClasslList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclClass(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclClassName!=null)
            buffer.append(VarDeclClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecClasslList!=null)
            buffer.append(VarDecClasslList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclClass]");
        return buffer.toString();
    }
}
