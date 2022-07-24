// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class FormalParamListComma extends FormalParamList {

    private FormParsSingle FormParsSingle;

    public FormalParamListComma (FormParsSingle FormParsSingle) {
        this.FormParsSingle=FormParsSingle;
        if(FormParsSingle!=null) FormParsSingle.setParent(this);
    }

    public FormParsSingle getFormParsSingle() {
        return FormParsSingle;
    }

    public void setFormParsSingle(FormParsSingle FormParsSingle) {
        this.FormParsSingle=FormParsSingle;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsSingle!=null) FormParsSingle.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsSingle!=null) FormParsSingle.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsSingle!=null) FormParsSingle.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamListComma(\n");

        if(FormParsSingle!=null)
            buffer.append(FormParsSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamListComma]");
        return buffer.toString();
    }
}
