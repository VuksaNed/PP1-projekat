// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class NoCoundTermMore extends CoundTermMore {

    private CoundFact CoundFact;

    public NoCoundTermMore (CoundFact CoundFact) {
        this.CoundFact=CoundFact;
        if(CoundFact!=null) CoundFact.setParent(this);
    }

    public CoundFact getCoundFact() {
        return CoundFact;
    }

    public void setCoundFact(CoundFact CoundFact) {
        this.CoundFact=CoundFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CoundFact!=null) CoundFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CoundFact!=null) CoundFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CoundFact!=null) CoundFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoCoundTermMore(\n");

        if(CoundFact!=null)
            buffer.append(CoundFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NoCoundTermMore]");
        return buffer.toString();
    }
}
