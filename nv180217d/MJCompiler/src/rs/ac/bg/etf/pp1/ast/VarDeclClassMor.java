// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class VarDeclClassMor extends VarDeclClassMore {

    private VarDeclClassMore VarDeclClassMore;
    private VarDeclClass VarDeclClass;

    public VarDeclClassMor (VarDeclClassMore VarDeclClassMore, VarDeclClass VarDeclClass) {
        this.VarDeclClassMore=VarDeclClassMore;
        if(VarDeclClassMore!=null) VarDeclClassMore.setParent(this);
        this.VarDeclClass=VarDeclClass;
        if(VarDeclClass!=null) VarDeclClass.setParent(this);
    }

    public VarDeclClassMore getVarDeclClassMore() {
        return VarDeclClassMore;
    }

    public void setVarDeclClassMore(VarDeclClassMore VarDeclClassMore) {
        this.VarDeclClassMore=VarDeclClassMore;
    }

    public VarDeclClass getVarDeclClass() {
        return VarDeclClass;
    }

    public void setVarDeclClass(VarDeclClass VarDeclClass) {
        this.VarDeclClass=VarDeclClass;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclClassMore!=null) VarDeclClassMore.accept(visitor);
        if(VarDeclClass!=null) VarDeclClass.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclClassMore!=null) VarDeclClassMore.traverseTopDown(visitor);
        if(VarDeclClass!=null) VarDeclClass.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclClassMore!=null) VarDeclClassMore.traverseBottomUp(visitor);
        if(VarDeclClass!=null) VarDeclClass.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclClassMor(\n");

        if(VarDeclClassMore!=null)
            buffer.append(VarDeclClassMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclClass!=null)
            buffer.append(VarDeclClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclClassMor]");
        return buffer.toString();
    }
}
