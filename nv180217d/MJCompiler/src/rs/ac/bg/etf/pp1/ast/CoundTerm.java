// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class CoundTerm implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private CoundTermMore CoundTermMore;

    public CoundTerm (CoundTermMore CoundTermMore) {
        this.CoundTermMore=CoundTermMore;
        if(CoundTermMore!=null) CoundTermMore.setParent(this);
    }

    public CoundTermMore getCoundTermMore() {
        return CoundTermMore;
    }

    public void setCoundTermMore(CoundTermMore CoundTermMore) {
        this.CoundTermMore=CoundTermMore;
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
        if(CoundTermMore!=null) CoundTermMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CoundTermMore!=null) CoundTermMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CoundTermMore!=null) CoundTermMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CoundTerm(\n");

        if(CoundTermMore!=null)
            buffer.append(CoundTermMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CoundTerm]");
        return buffer.toString();
    }
}
