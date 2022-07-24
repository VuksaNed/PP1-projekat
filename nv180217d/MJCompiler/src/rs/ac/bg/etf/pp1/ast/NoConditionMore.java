// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class NoConditionMore extends ConditionMore {

    private CoundTerm CoundTerm;

    public NoConditionMore (CoundTerm CoundTerm) {
        this.CoundTerm=CoundTerm;
        if(CoundTerm!=null) CoundTerm.setParent(this);
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
        if(CoundTerm!=null) CoundTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CoundTerm!=null) CoundTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CoundTerm!=null) CoundTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoConditionMore(\n");

        if(CoundTerm!=null)
            buffer.append(CoundTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NoConditionMore]");
        return buffer.toString();
    }
}
