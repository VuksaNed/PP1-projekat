// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclaration extends ConstDecl {

    private ConstPar ConstPar;

    public ConstDeclaration (ConstPar ConstPar) {
        this.ConstPar=ConstPar;
        if(ConstPar!=null) ConstPar.setParent(this);
    }

    public ConstPar getConstPar() {
        return ConstPar;
    }

    public void setConstPar(ConstPar ConstPar) {
        this.ConstPar=ConstPar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstPar!=null) ConstPar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstPar!=null) ConstPar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstPar!=null) ConstPar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclaration(\n");

        if(ConstPar!=null)
            buffer.append(ConstPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclaration]");
        return buffer.toString();
    }
}
