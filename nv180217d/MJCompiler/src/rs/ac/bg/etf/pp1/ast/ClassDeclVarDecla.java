// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclVarDecla extends ClassDeclVarDecl {

    private ClassDeclVarDecl ClassDeclVarDecl;
    private ClassDeclVarDeclMore ClassDeclVarDeclMore;

    public ClassDeclVarDecla (ClassDeclVarDecl ClassDeclVarDecl, ClassDeclVarDeclMore ClassDeclVarDeclMore) {
        this.ClassDeclVarDecl=ClassDeclVarDecl;
        if(ClassDeclVarDecl!=null) ClassDeclVarDecl.setParent(this);
        this.ClassDeclVarDeclMore=ClassDeclVarDeclMore;
        if(ClassDeclVarDeclMore!=null) ClassDeclVarDeclMore.setParent(this);
    }

    public ClassDeclVarDecl getClassDeclVarDecl() {
        return ClassDeclVarDecl;
    }

    public void setClassDeclVarDecl(ClassDeclVarDecl ClassDeclVarDecl) {
        this.ClassDeclVarDecl=ClassDeclVarDecl;
    }

    public ClassDeclVarDeclMore getClassDeclVarDeclMore() {
        return ClassDeclVarDeclMore;
    }

    public void setClassDeclVarDeclMore(ClassDeclVarDeclMore ClassDeclVarDeclMore) {
        this.ClassDeclVarDeclMore=ClassDeclVarDeclMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassDeclVarDecl!=null) ClassDeclVarDecl.accept(visitor);
        if(ClassDeclVarDeclMore!=null) ClassDeclVarDeclMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassDeclVarDecl!=null) ClassDeclVarDecl.traverseTopDown(visitor);
        if(ClassDeclVarDeclMore!=null) ClassDeclVarDeclMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassDeclVarDecl!=null) ClassDeclVarDecl.traverseBottomUp(visitor);
        if(ClassDeclVarDeclMore!=null) ClassDeclVarDeclMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclVarDecla(\n");

        if(ClassDeclVarDecl!=null)
            buffer.append(ClassDeclVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDeclVarDeclMore!=null)
            buffer.append(ClassDeclVarDeclMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclVarDecla]");
        return buffer.toString();
    }
}
