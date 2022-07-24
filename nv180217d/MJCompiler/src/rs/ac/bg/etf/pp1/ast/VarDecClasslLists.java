// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class VarDecClasslLists extends VarDecClasslList {

    private VarDeclClassName VarDeclClassName;
    private VarDecClasslList VarDecClasslList;

    public VarDecClasslLists (VarDeclClassName VarDeclClassName, VarDecClasslList VarDecClasslList) {
        this.VarDeclClassName=VarDeclClassName;
        if(VarDeclClassName!=null) VarDeclClassName.setParent(this);
        this.VarDecClasslList=VarDecClasslList;
        if(VarDecClasslList!=null) VarDecClasslList.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclClassName!=null) VarDeclClassName.accept(visitor);
        if(VarDecClasslList!=null) VarDecClasslList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclClassName!=null) VarDeclClassName.traverseTopDown(visitor);
        if(VarDecClasslList!=null) VarDecClasslList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclClassName!=null) VarDeclClassName.traverseBottomUp(visitor);
        if(VarDecClasslList!=null) VarDecClasslList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecClasslLists(\n");

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
        buffer.append(") [VarDecClasslLists]");
        return buffer.toString();
    }
}
