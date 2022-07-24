// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class RecordDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private RecordName RecordName;
    private VarDeclClassMore VarDeclClassMore;

    public RecordDecl (RecordName RecordName, VarDeclClassMore VarDeclClassMore) {
        this.RecordName=RecordName;
        if(RecordName!=null) RecordName.setParent(this);
        this.VarDeclClassMore=VarDeclClassMore;
        if(VarDeclClassMore!=null) VarDeclClassMore.setParent(this);
    }

    public RecordName getRecordName() {
        return RecordName;
    }

    public void setRecordName(RecordName RecordName) {
        this.RecordName=RecordName;
    }

    public VarDeclClassMore getVarDeclClassMore() {
        return VarDeclClassMore;
    }

    public void setVarDeclClassMore(VarDeclClassMore VarDeclClassMore) {
        this.VarDeclClassMore=VarDeclClassMore;
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
        if(RecordName!=null) RecordName.accept(visitor);
        if(VarDeclClassMore!=null) VarDeclClassMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(RecordName!=null) RecordName.traverseTopDown(visitor);
        if(VarDeclClassMore!=null) VarDeclClassMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(RecordName!=null) RecordName.traverseBottomUp(visitor);
        if(VarDeclClassMore!=null) VarDeclClassMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RecordDecl(\n");

        if(RecordName!=null)
            buffer.append(RecordName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclClassMore!=null)
            buffer.append(VarDeclClassMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [RecordDecl]");
        return buffer.toString();
    }
}
