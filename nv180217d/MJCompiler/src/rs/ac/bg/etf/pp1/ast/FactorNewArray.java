// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class FactorNewArray extends Factor {

    private Type Type;
    private FactorNewBrack FactorNewBrack;

    public FactorNewArray (Type Type, FactorNewBrack FactorNewBrack) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.FactorNewBrack=FactorNewBrack;
        if(FactorNewBrack!=null) FactorNewBrack.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public FactorNewBrack getFactorNewBrack() {
        return FactorNewBrack;
    }

    public void setFactorNewBrack(FactorNewBrack FactorNewBrack) {
        this.FactorNewBrack=FactorNewBrack;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(FactorNewBrack!=null) FactorNewBrack.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FactorNewBrack!=null) FactorNewBrack.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FactorNewBrack!=null) FactorNewBrack.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorNewArray(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorNewBrack!=null)
            buffer.append(FactorNewBrack.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorNewArray]");
        return buffer.toString();
    }
}
