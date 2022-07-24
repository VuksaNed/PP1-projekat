// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ConditionMor extends ConditionMore {

    private ConditionMore ConditionMore;
    private CoundTerm CoundTerm;

    public ConditionMor (ConditionMore ConditionMore, CoundTerm CoundTerm) {
        this.ConditionMore=ConditionMore;
        if(ConditionMore!=null) ConditionMore.setParent(this);
        this.CoundTerm=CoundTerm;
        if(CoundTerm!=null) CoundTerm.setParent(this);
    }

    public ConditionMore getConditionMore() {
        return ConditionMore;
    }

    public void setConditionMore(ConditionMore ConditionMore) {
        this.ConditionMore=ConditionMore;
    }

    public CoundTerm getCoundTerm() {
        return CoundTerm;
    }

    public void setCoundTerm(CoundTerm CoundTerm) {
        this.CoundTerm=CoundTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionMore!=null) ConditionMore.accept(visitor);
        if(CoundTerm!=null) CoundTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionMore!=null) ConditionMore.traverseTopDown(visitor);
        if(CoundTerm!=null) CoundTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionMore!=null) ConditionMore.traverseBottomUp(visitor);
        if(CoundTerm!=null) CoundTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConditionMor(\n");

        if(ConditionMore!=null)
            buffer.append(ConditionMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CoundTerm!=null)
            buffer.append(CoundTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConditionMor]");
        return buffer.toString();
    }
}
