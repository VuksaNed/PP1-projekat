// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class GlobalDeclarations extends GlobalDecl {

    private GlobalVarDeclComm GlobalVarDeclComm;
    private GlobalDecl GlobalDecl;

    public GlobalDeclarations (GlobalVarDeclComm GlobalVarDeclComm, GlobalDecl GlobalDecl) {
        this.GlobalVarDeclComm=GlobalVarDeclComm;
        if(GlobalVarDeclComm!=null) GlobalVarDeclComm.setParent(this);
        this.GlobalDecl=GlobalDecl;
        if(GlobalDecl!=null) GlobalDecl.setParent(this);
    }

    public GlobalVarDeclComm getGlobalVarDeclComm() {
        return GlobalVarDeclComm;
    }

    public void setGlobalVarDeclComm(GlobalVarDeclComm GlobalVarDeclComm) {
        this.GlobalVarDeclComm=GlobalVarDeclComm;
    }

    public GlobalDecl getGlobalDecl() {
        return GlobalDecl;
    }

    public void setGlobalDecl(GlobalDecl GlobalDecl) {
        this.GlobalDecl=GlobalDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalVarDeclComm!=null) GlobalVarDeclComm.accept(visitor);
        if(GlobalDecl!=null) GlobalDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarDeclComm!=null) GlobalVarDeclComm.traverseTopDown(visitor);
        if(GlobalDecl!=null) GlobalDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarDeclComm!=null) GlobalVarDeclComm.traverseBottomUp(visitor);
        if(GlobalDecl!=null) GlobalDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalDeclarations(\n");

        if(GlobalVarDeclComm!=null)
            buffer.append(GlobalVarDeclComm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalDecl!=null)
            buffer.append(GlobalDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalDeclarations]");
        return buffer.toString();
    }
}
