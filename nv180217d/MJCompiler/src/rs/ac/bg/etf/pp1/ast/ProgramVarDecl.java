// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ProgramVarDecl extends ProgPar {

    private ProgPar ProgPar;
    private GlobalVarDecl GlobalVarDecl;

    public ProgramVarDecl (ProgPar ProgPar, GlobalVarDecl GlobalVarDecl) {
        this.ProgPar=ProgPar;
        if(ProgPar!=null) ProgPar.setParent(this);
        this.GlobalVarDecl=GlobalVarDecl;
        if(GlobalVarDecl!=null) GlobalVarDecl.setParent(this);
    }

    public ProgPar getProgPar() {
        return ProgPar;
    }

    public void setProgPar(ProgPar ProgPar) {
        this.ProgPar=ProgPar;
    }

    public GlobalVarDecl getGlobalVarDecl() {
        return GlobalVarDecl;
    }

    public void setGlobalVarDecl(GlobalVarDecl GlobalVarDecl) {
        this.GlobalVarDecl=GlobalVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgPar!=null) ProgPar.accept(visitor);
        if(GlobalVarDecl!=null) GlobalVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgPar!=null) ProgPar.traverseTopDown(visitor);
        if(GlobalVarDecl!=null) GlobalVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgPar!=null) ProgPar.traverseBottomUp(visitor);
        if(GlobalVarDecl!=null) GlobalVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramVarDecl(\n");

        if(ProgPar!=null)
            buffer.append(ProgPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalVarDecl!=null)
            buffer.append(GlobalVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramVarDecl]");
        return buffer.toString();
    }
}
