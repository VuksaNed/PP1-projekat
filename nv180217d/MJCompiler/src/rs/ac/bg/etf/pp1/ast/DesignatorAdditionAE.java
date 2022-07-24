// generated with ast extension for cup
// version 0.8
// 26/0/2022 15:17:51


package rs.ac.bg.etf.pp1.ast;

public class DesignatorAdditionAE extends DesignatorAddition {

    private Assignop Assignop;
    private DesignatorAdditionExprSemi DesignatorAdditionExprSemi;

    public DesignatorAdditionAE (Assignop Assignop, DesignatorAdditionExprSemi DesignatorAdditionExprSemi) {
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.DesignatorAdditionExprSemi=DesignatorAdditionExprSemi;
        if(DesignatorAdditionExprSemi!=null) DesignatorAdditionExprSemi.setParent(this);
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public DesignatorAdditionExprSemi getDesignatorAdditionExprSemi() {
        return DesignatorAdditionExprSemi;
    }

    public void setDesignatorAdditionExprSemi(DesignatorAdditionExprSemi DesignatorAdditionExprSemi) {
        this.DesignatorAdditionExprSemi=DesignatorAdditionExprSemi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Assignop!=null) Assignop.accept(visitor);
        if(DesignatorAdditionExprSemi!=null) DesignatorAdditionExprSemi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(DesignatorAdditionExprSemi!=null) DesignatorAdditionExprSemi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(DesignatorAdditionExprSemi!=null) DesignatorAdditionExprSemi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorAdditionAE(\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorAdditionExprSemi!=null)
            buffer.append(DesignatorAdditionExprSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorAdditionAE]");
        return buffer.toString();
    }
}
