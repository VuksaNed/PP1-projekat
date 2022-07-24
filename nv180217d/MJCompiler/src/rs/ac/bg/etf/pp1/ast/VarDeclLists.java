// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class VarDeclLists extends VarDeclList {

    private VarDeclLocallName VarDeclLocallName;
    private VarDeclList VarDeclList;

    public VarDeclLists (VarDeclLocallName VarDeclLocallName, VarDeclList VarDeclList) {
        this.VarDeclLocallName=VarDeclLocallName;
        if(VarDeclLocallName!=null) VarDeclLocallName.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public VarDeclLocallName getVarDeclLocallName() {
        return VarDeclLocallName;
    }

    public void setVarDeclLocallName(VarDeclLocallName VarDeclLocallName) {
        this.VarDeclLocallName=VarDeclLocallName;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclLocallName!=null) VarDeclLocallName.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclLocallName!=null) VarDeclLocallName.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclLocallName!=null) VarDeclLocallName.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclLists(\n");

        if(VarDeclLocallName!=null)
            buffer.append(VarDeclLocallName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclLists]");
        return buffer.toString();
    }
}
