// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class NoVarDeclClassMore extends VarDeclClassMore {

    public NoVarDeclClassMore () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoVarDeclClassMore(\n");

        buffer.append(tab);
        buffer.append(") [NoVarDeclClassMore]");
        return buffer.toString();
    }
}
