// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ClassExtends extends ClassExtend {

    private ClassExtendCheckError ClassExtendCheckError;

    public ClassExtends (ClassExtendCheckError ClassExtendCheckError) {
        this.ClassExtendCheckError=ClassExtendCheckError;
        if(ClassExtendCheckError!=null) ClassExtendCheckError.setParent(this);
    }

    public ClassExtendCheckError getClassExtendCheckError() {
        return ClassExtendCheckError;
    }

    public void setClassExtendCheckError(ClassExtendCheckError ClassExtendCheckError) {
        this.ClassExtendCheckError=ClassExtendCheckError;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassExtendCheckError!=null) ClassExtendCheckError.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassExtendCheckError!=null) ClassExtendCheckError.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassExtendCheckError!=null) ClassExtendCheckError.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassExtends(\n");

        if(ClassExtendCheckError!=null)
            buffer.append(ClassExtendCheckError.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassExtends]");
        return buffer.toString();
    }
}
