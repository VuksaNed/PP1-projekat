// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class GlobalVarDeclSemi extends GlobalVarDeclSem {

    private VarDeclGlobalName VarDeclGlobalName;

    public GlobalVarDeclSemi (VarDeclGlobalName VarDeclGlobalName) {
        this.VarDeclGlobalName=VarDeclGlobalName;
        if(VarDeclGlobalName!=null) VarDeclGlobalName.setParent(this);
    }

    public VarDeclGlobalName getVarDeclGlobalName() {
        return VarDeclGlobalName;
    }

    public void setVarDeclGlobalName(VarDeclGlobalName VarDeclGlobalName) {
        this.VarDeclGlobalName=VarDeclGlobalName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclGlobalName!=null) VarDeclGlobalName.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclGlobalName!=null) VarDeclGlobalName.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclGlobalName!=null) VarDeclGlobalName.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalVarDeclSemi(\n");

        if(VarDeclGlobalName!=null)
            buffer.append(VarDeclGlobalName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalVarDeclSemi]");
        return buffer.toString();
    }
}
