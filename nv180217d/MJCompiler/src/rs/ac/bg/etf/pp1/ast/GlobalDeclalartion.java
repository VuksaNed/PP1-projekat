// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class GlobalDeclalartion extends GlobalDecl {

    private GlobalVarDeclSem GlobalVarDeclSem;

    public GlobalDeclalartion (GlobalVarDeclSem GlobalVarDeclSem) {
        this.GlobalVarDeclSem=GlobalVarDeclSem;
        if(GlobalVarDeclSem!=null) GlobalVarDeclSem.setParent(this);
    }

    public GlobalVarDeclSem getGlobalVarDeclSem() {
        return GlobalVarDeclSem;
    }

    public void setGlobalVarDeclSem(GlobalVarDeclSem GlobalVarDeclSem) {
        this.GlobalVarDeclSem=GlobalVarDeclSem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalVarDeclSem!=null) GlobalVarDeclSem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarDeclSem!=null) GlobalVarDeclSem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarDeclSem!=null) GlobalVarDeclSem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalDeclalartion(\n");

        if(GlobalVarDeclSem!=null)
            buffer.append(GlobalVarDeclSem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalDeclalartion]");
        return buffer.toString();
    }
}
