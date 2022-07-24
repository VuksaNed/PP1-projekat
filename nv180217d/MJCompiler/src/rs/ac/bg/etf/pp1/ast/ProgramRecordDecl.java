// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ProgramRecordDecl extends ProgPar {

    private ProgPar ProgPar;
    private RecordDecl RecordDecl;

    public ProgramRecordDecl (ProgPar ProgPar, RecordDecl RecordDecl) {
        this.ProgPar=ProgPar;
        if(ProgPar!=null) ProgPar.setParent(this);
        this.RecordDecl=RecordDecl;
        if(RecordDecl!=null) RecordDecl.setParent(this);
    }

    public ProgPar getProgPar() {
        return ProgPar;
    }

    public void setProgPar(ProgPar ProgPar) {
        this.ProgPar=ProgPar;
    }

    public RecordDecl getRecordDecl() {
        return RecordDecl;
    }

    public void setRecordDecl(RecordDecl RecordDecl) {
        this.RecordDecl=RecordDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgPar!=null) ProgPar.accept(visitor);
        if(RecordDecl!=null) RecordDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgPar!=null) ProgPar.traverseTopDown(visitor);
        if(RecordDecl!=null) RecordDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgPar!=null) ProgPar.traverseBottomUp(visitor);
        if(RecordDecl!=null) RecordDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProgramRecordDecl(\n");

        if(ProgPar!=null)
            buffer.append(ProgPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(RecordDecl!=null)
            buffer.append(RecordDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramRecordDecl]");
        return buffer.toString();
    }
}
