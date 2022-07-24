// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class FormParam extends FormPars {

    private FormParsSinglePom FormParsSinglePom;

    public FormParam (FormParsSinglePom FormParsSinglePom) {
        this.FormParsSinglePom=FormParsSinglePom;
        if(FormParsSinglePom!=null) FormParsSinglePom.setParent(this);
    }

    public FormParsSinglePom getFormParsSinglePom() {
        return FormParsSinglePom;
    }

    public void setFormParsSinglePom(FormParsSinglePom FormParsSinglePom) {
        this.FormParsSinglePom=FormParsSinglePom;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsSinglePom!=null) FormParsSinglePom.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsSinglePom!=null) FormParsSinglePom.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsSinglePom!=null) FormParsSinglePom.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParam(\n");

        if(FormParsSinglePom!=null)
            buffer.append(FormParsSinglePom.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParam]");
        return buffer.toString();
    }
}
