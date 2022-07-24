package rs.ac.bg.etf.pp1;

import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	private boolean imareturn = false;
	private Obj retobj = null;
	private Obj pristupnizu = null;
	private Stack<Integer> doadresa = new Stack<Integer>();
	private Stack<Integer> relop = new Stack<Integer>();
	private Stack<Integer> adrforfix = new Stack<Integer>();
	private Stack<Integer> breakadr = new Stack<Integer>();
	private Stack<Integer> addfix = new Stack<Integer>();
	private Stack<Integer> orfix = new Stack<Integer>();
	private int dowhilestart = 0;
	private int add = 0;
	private int or = 0;

	public int getMainPc() {
		return mainPc;
	}

	public void visit(StatementPrintNoNum print) {

		if (print.getExpr().struct == TabExtend.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else {
			if (print.getExpr().struct == TabExtend.charType) {
				Code.loadConst(1);
				Code.put(Code.bprint);
			} else {
				Code.loadConst(5);
				Code.put(Code.print);
			}
		}

	}

	public void visit(StatementPrintNum print) {

		int wid = print.getN2();

		if (print.getExpr().struct == TabExtend.intType) {
			Code.loadConst(wid);
			Code.put(Code.print);
		} else {
			if (print.getExpr().struct == TabExtend.charType) {
				Code.loadConst(wid);
				Code.put(Code.bprint);
			} else {
				Code.loadConst(wid);
				Code.put(Code.print);
			}
		}

	}

	public void visit(StatementRead sr) {
		Code.put(Code.read);
		Code.store(sr.getDesignator().obj);
	}

	private int dohvatiConstValue(Const c) {
		int ret = -1;
		if (c.struct.getKind() == Struct.Int) {
			ret = ((NumConst) c).getNum();
		} else {
			if (c.struct.getKind() == Struct.Char) {
				ret = ((CharConst) c).getCh().charAt(1);
			} else {
				if (((BoolConst) c).getBo().equals("true")) {
					ret = 1;
				} else {
					ret = 0;
				}
			}
		}

		return ret;
	}

	
	public void visit(FactorConst fc) {

		if (pristupnizu != null) {
			if (fc.getParent().getParent().getParent().getClass() != FactorNewBrack.class) {
				Code.load(pristupnizu);
				pristupnizu = null;
			}
			pristupnizu = null;
		}
	
		Obj con = TabExtend.insert(Obj.Con, "$", fc.struct);
		con.setLevel(0);

		con.setAdr(dohvatiConstValue(fc.getConst()));

		Code.load(con);

		if (fc.getParent().getParent().getClass() == MinusExpr.class) {
			Code.put(Code.neg);
		}
		
	}

	public void visit(DesignatorName dname) {
		if (dname.obj.getKind() == Obj.Con) {
			Code.load(dname.obj);
		} else {

			if (dname.obj.getKind() == Obj.Meth) {

//				Obj functionObj = dname.obj;
//				int offset = functionObj.getAdr() - Code.pc;
//				Code.put(Code.call);
//
//				Code.put2(offset);

				// nista

			} else {
				SyntaxNode parent = dname.getParent();

				if (DesignatorAdditionActPars.class != parent.getClass()
						&& DesignatorAdditionNoActPars.class != parent.getClass()
						&& DesignatorAdditionPlusPlus.class != parent.getClass()
						&& DesignatorAdditionMinusMinus.class != parent.getClass()) {

					if (parent.getClass() != DesignatorMoreSingle.class) {

						if (parent.getClass() != DesignatorMoreIdent.class) {
							Code.load(dname.obj);
							Code.put(Code.getfield);
						} else {
							parent = parent.getParent().getParent();
							if (parent.getClass() != FactorVar.class) {
								if (dname.obj.getType().getKind() == Struct.Array) {
									pristupnizu = dname.obj;
								}
							} else {
								Code.load(dname.obj);
							}

						}
					} else {
						if (parent.getParent().getClass() != Designator.class) {
							if (dname.obj.getKind() != Obj.Fld)
								Code.load(dname.obj);
						} else {

							SyntaxNode parentOfParent;
							parentOfParent = parent.getParent().getParent();

							if (parentOfParent.getClass() != DesignatorStatement.class) {
								if (dname.obj.getType().getKind() != Struct.Class) {
									if (pristupnizu != null) {
										Code.load(pristupnizu);
										pristupnizu = null;
									}
									Code.load(dname.obj);
								}
							} else {

								if (((DesignatorStatement) (parentOfParent)).getDesignatorAddition()
										.getClass() != DesignatorAdditionAE.class) {

									Code.load(dname.obj);
								}
							}
						}
					}
				}
			}
		}
	}

	public void visit(MethodTypeName mtn) {

		if ("main".equalsIgnoreCase(mtn.getMethName())) {
			mainPc = Code.pc;
		}

		if (mtn.obj.getType().getKind() != Struct.None) {
			imareturn = false;
		} else {
			imareturn = true;
		}

		mtn.obj.setAdr(Code.pc);

		SyntaxNode methodNode = mtn.getParent();

		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);

		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);

		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());

	}

	public void visit(MethodDecl md) {
		if (!imareturn) {
			Code.put(Code.trap);
		}
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(StatementReturnExpr e) {
		imareturn = true;
	}

	public void visit(DesignatorAdditionAE da) {
		Obj obj = ((DesignatorStatement) da.getParent()).getDesignator().obj;
		Code.store(obj);
	}

	public void visit(DesignatorStatement ds) {

		if (ds.getDesignator().obj.getKind() == Obj.Meth) {
			Obj functionObj = ds.getDesignator().obj;
			int offset = functionObj.getAdr() - Code.pc;
			
			Code.put(Code.call);

			Code.put2(offset);
		} else {
			if (ds.getDesignatorAddition().getClass() == DesignatorAdditionPlusPlus.class) {
				Code.put(Code.add);
				Code.store(ds.getDesignator().getDesignatorMore().obj);
			} else {
				if (ds.getDesignatorAddition().getClass() == DesignatorAdditionMinusMinus.class) {
					Code.put(Code.sub);
					Code.store(ds.getDesignator().getDesignatorMore().obj);
				}
			}
		}

	}

	public void visit(FactorVar ds) {

		if (ds.getDesignator().obj.getKind() == Obj.Elem) {
			Code.load(ds.getDesignator().obj);
		}
	}

	public void visit(FactorVarFunAct ds) {

		if (ds.getDesignator().obj.getKind() == Obj.Meth) {
			Obj functionObj = ds.getDesignator().obj;
			int offset = functionObj.getAdr() - Code.pc;
			
			if (ds.getDesignator().getDesignatorMore().getClass() == DesignatorMoreSingle.class) {

				if (((DesignatorMoreSingle)(ds.getDesignator().getDesignatorMore())).getDesignatorName().getVarName().equals("ord")) {
					
				} else {
					if (((DesignatorMoreSingle)(ds.getDesignator().getDesignatorMore())).getDesignatorName().getVarName().equals("len")) {
						Code.put(Code.arraylength);
					} else {
						Code.put(Code.call);

						Code.put2(offset);
					}
				}
				
			} else {
				Code.put(Code.call);

				Code.put2(offset);
			}
			
		}

	}

	public void visit(FactorVarFunNoAct ds) {

		if (ds.getDesignator().obj.getKind() == Obj.Meth) {
			Obj functionObj = ds.getDesignator().obj;
			int offset = functionObj.getAdr() - Code.pc;
			if (ds.getDesignator().getDesignatorMore().getClass() == DesignatorMoreSingle.class) {

				if (((DesignatorMoreSingle)(ds.getDesignator().getDesignatorMore())).getDesignatorName().getVarName().equals("ord")) {
					
				} else {
					Code.put(Code.call);

					Code.put2(offset);
				}
				
			} else {
				Code.put(Code.call);

				Code.put2(offset);
			}
		}
	}

	public void visit(MoreExp me) {
		if (me.getAddop().getClass() == AddopPlus.class) {
			Code.put(Code.add);
		} else {
			if (me.getAddop().getClass() == AddopMinus.class) {
				Code.put(Code.sub);
			} else {
				// nista greksa!!
			}
		}
	}

	public void visit(TermMulop tm) {
		if (tm.getMulop().getClass() == MulopMul.class) {
			Code.put(Code.mul);
		} else {
			if (tm.getMulop().getClass() == MulopDiv.class) {
				Code.put(Code.div);
			} else {
				if (tm.getMulop().getClass() == MulopMod.class) {
					Code.put(Code.rem);
				} else {
					// nista greksa!!
				}
			}
		}
	}

	public void visit(DesignatorAdditionPlusPlus pp) {
		
		if (((DesignatorStatement)pp.getParent()).getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(((DesignatorStatement)pp.getParent()).getDesignator().obj);
		} else {
			if (((DesignatorStatement)pp.getParent()).getDesignator().obj.getKind() == Obj.Fld) {
				Code.put(Code.dup);
				Code.load(((DesignatorStatement)pp.getParent()).getDesignator().obj);
			}
		}
		
		Obj con = TabExtend.insert(Obj.Con, "$", new Struct(Struct.Int));
		con.setLevel(0);

		con.setAdr(1);

		Code.load(con);
	}

	public void visit(DesignatorAdditionMinusMinus pp) {
		
		if (((DesignatorStatement)pp.getParent()).getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(((DesignatorStatement)pp.getParent()).getDesignator().obj);
		} else {
			if (((DesignatorStatement)pp.getParent()).getDesignator().obj.getKind() == Obj.Fld) {
				Code.put(Code.dup);
				Code.load(((DesignatorStatement)pp.getParent()).getDesignator().obj);
			}
		}
		
		Obj con = TabExtend.insert(Obj.Con, "$", new Struct(Struct.Int));
		con.setLevel(0);

		con.setAdr(1);

		Code.load(con);
	}

	public void visit(FactorNewArray fna) {
		if (fna.struct.getKind() == Struct.Int) {
			Code.put(Code.newarray);
			Code.put(1);
		} else {
			if (fna.struct.getKind() == Struct.Char) {
				Code.put(Code.newarray);
				Code.put(0);
			} else {
				Code.put(Code.newarray);
				Code.put(1);
			}
		}
	}

	public void visit(FactorNewNoArray fnna) {
		Code.put(Code.new_);
		int size = fnna.struct.getNumberOfFields() * 4;
		Code.put2(size);
		Code.put(Code.dup);
		Code.loadConst(0);
		Code.put(Code.putfield);
		Code.put2(0);
	}

	public void visit(DesignatorMoreExpr dme) {
		if (pristupnizu != null) {
			Code.load(pristupnizu);
			pristupnizu = null;
		}

	}

	public void visit(Designator d) {
		//pristupnizu = null;
	}

	public void visit(StatementBreak sb) {
		Code.putJump(0);
		breakadr.push(Code.pc - 2);
	}

	public void visit(StatementContinue sc) {
		Code.putJump(dowhilestart);
	}

	public void visit(Do d) {
		doadresa.push(Code.pc);
		dowhilestart = Code.pc;
	}

	public void visit(StatementDoWhile sdw) {
		Code.putJump(doadresa.pop());
		Code.fixup(adrforfix.pop());

		while (!breakadr.empty())
			Code.fixup(breakadr.pop());

		if (add > 0) {
			while (add != 0) {
				Code.fixup(addfix.pop());
				add--;

			}
		}
	}

	public void visit(CoundFactExprMore cfem) {

		SyntaxNode parent2 = cfem.getParent().getParent();
		SyntaxNode parent3 = parent2.getParent().getParent();

		if (parent2.getClass() == CoundTermMor.class) {
			Code.putFalseJump(relop.pop(), 0);
			addfix.push(Code.pc - 2);
			add++; // na kraj ifa ili sledece
		} else {
			if (parent3.getClass() == ConditionMor.class) {
				Code.putFalseJump(Code.inverse[relop.pop()], 0);
				orfix.push(Code.pc - 2);
				or++; // na kraj ifa ili sledece
				if (add>0) {
					while(add>0) {
						add--;
						Code.fixup(addfix.pop());
					}
				}
			} else {
				Code.putFalseJump(relop.pop(), 0);
				adrforfix.push(Code.pc - 2);
			}
		}

	}
	
	public void visit(CoundFactExprOne cfem) {

		SyntaxNode parent2 = cfem.getParent().getParent();
		SyntaxNode parent3 = parent2.getParent().getParent();

		if (parent2.getClass() == CoundTermMor.class) {
			Code.put(Code.const_1);
			Code.putFalseJump(Code.eq, 0);
			addfix.push(Code.pc - 2);
			add++; // na kraj ifa ili sledece
		} else {
			if (parent3.getClass() == ConditionMor.class) {
				Code.put(Code.const_1);
				Code.putFalseJump(Code.inverse[Code.eq], 0);
				orfix.push(Code.pc - 2);
				or++; // na kraj ifa ili sledece
				if (add>0) {
					while(add>0) {
						add--;
						Code.fixup(addfix.pop());
					}
				}
			} else {
				Code.put(Code.const_1);
				Code.putFalseJump(Code.eq, 0);
				adrforfix.push(Code.pc - 2);
			}
		}

	}

	public void visit(EqualEqual c) {
		relop.push(Code.eq);
	}

	public void visit(NotEqual c) {
		relop.push(Code.ne);
	}

	public void visit(Greater c) {
		relop.push(Code.gt);
	}

	public void visit(GreaterEqual c) {
		relop.push(Code.ge);
	}

	public void visit(Less c) {
		relop.push(Code.lt);
	}

	public void visit(LessEqual c) {
		relop.push(Code.le);
	}

	public void visit(UnmatchedIf ui) {

		if (add > 0) {
			Code.fixup(adrforfix.pop());/////////////

			while (add != 0) {
				Code.fixup(addfix.pop());
				add--;

			}
		} else {

			if (or > 0) {
//				Code.fixup(adrforfix.pop());/////////////
//
//				while (or != 0) {
//					Code.fixup(orfix.pop());
//					or--;
//				}
			} else {

				Code.fixup(adrforfix.pop());
			}
		}

	}

	public void visit(UnmatchedIfElse ui) {
		Code.fixup(adrforfix.pop());
	}

	public void visit(StatementIf ui) {
		Code.fixup(adrforfix.pop());
	}

	public void visit(Else e) {
		Code.putJump(0);
		int pom = Code.pc - 2;
		if (add > 0) {
			Code.fixup(adrforfix.pop());//////////

			while (add != 0) {
				Code.fixup(addfix.pop());
				add--;

			}
			adrforfix.push(pom);
		} else {
			if (or > 0) {
//				Code.fixup(adrforfix.pop());/////////////
//
//				while (or != 0) {
//					Code.fixup(orfix.pop());
//					or--;
//				}
//				adrforfix.push(pom);
			} else {
				Code.fixup(adrforfix.pop());
				adrforfix.push(pom);
			}
		}
	}

	public void visit(ConditionMor cm) {
//		Code.putFalseJump(relop.pop(), 0);
//		adrforfix.push(Code.pc - 2);
	}

	public void visit(NoConditionMore ncm) {
//		Code.putFalseJump(relop.pop(), 0);
//		adrforfix.push(Code.pc - 2);
	}

	
	public void visit(Condition c) {
		if (or > 0) {
			while (or != 0) {
				Code.fixup(orfix.pop());
				or--;
			}
		}
	}
	
}







//
//
//sum:
//
//Code.load(NizObj);
//Code.put(Code.dup);
//Code.put(Code.arraylength);
//Code.loadConst(1);
//Code.put(Code.sub);
//Code.put(Code.dup_x1);
//Code.put(Code.aload);
//
//int adrWhile = Code.pc;
//Code.put(Code.dup_x1);
//Code.put(Code.pop);
//Code.put(Code.dup);
//Code.loadConst(0);
//Code.putFalseJump(Code.gt,0);
//int endWhile = Code.pc-2;
//
//Code.loadConst(1);
//Code.put(Code.sub);
//Code.load(NizObj);
//Code.put(Code.dup_x1);
//Code.put(Code.pop);
//Code.put(Code.dup_x2);
//Code.put(Code.aload);
//
//Code.put(Code.add);
//
//Code.putJump(adrWhile);
//Code.fixup(endWhile);
//
//Code.put(Code.pop);

//test/program.obj -debug

// comp
//test/program.mj test/program.obj
