// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ClassName ClassName;
    private ClassExtend ClassExtend;
    private ClassDeclVarDecl ClassDeclVarDecl;
    private ClassDeclBody ClassDeclBody;

    public ClassDecl (ClassName ClassName, ClassExtend ClassExtend, ClassDeclVarDecl ClassDeclVarDecl, ClassDeclBody ClassDeclBody) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.ClassExtend=ClassExtend;
        if(ClassExtend!=null) ClassExtend.setParent(this);
        this.ClassDeclVarDecl=ClassDeclVarDecl;
        if(ClassDeclVarDecl!=null) ClassDeclVarDecl.setParent(this);
        this.ClassDeclBody=ClassDeclBody;
        if(ClassDeclBody!=null) ClassDeclBody.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public ClassExtend getClassExtend() {
        return ClassExtend;
    }

    public void setClassExtend(ClassExtend ClassExtend) {
        this.ClassExtend=ClassExtend;
    }

    public ClassDeclVarDecl getClassDeclVarDecl() {
        return ClassDeclVarDecl;
    }

    public void setClassDeclVarDecl(ClassDeclVarDecl ClassDeclVarDecl) {
        this.ClassDeclVarDecl=ClassDeclVarDecl;
    }

    public ClassDeclBody getClassDeclBody() {
        return ClassDeclBody;
    }

    public void setClassDeclBody(ClassDeclBody ClassDeclBody) {
        this.ClassDeclBody=ClassDeclBody;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassName!=null) ClassName.accept(visitor);
        if(ClassExtend!=null) ClassExtend.accept(visitor);
        if(ClassDeclVarDecl!=null) ClassDeclVarDecl.accept(visitor);
        if(ClassDeclBody!=null) ClassDeclBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(ClassExtend!=null) ClassExtend.traverseTopDown(visitor);
        if(ClassDeclVarDecl!=null) ClassDeclVarDecl.traverseTopDown(visitor);
        if(ClassDeclBody!=null) ClassDeclBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(ClassExtend!=null) ClassExtend.traverseBottomUp(visitor);
        if(ClassDeclVarDecl!=null) ClassDeclVarDecl.traverseBottomUp(visitor);
        if(ClassDeclBody!=null) ClassDeclBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassExtend!=null)
            buffer.append(ClassExtend.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDeclVarDecl!=null)
            buffer.append(ClassDeclVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDeclBody!=null)
            buffer.append(ClassDeclBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
