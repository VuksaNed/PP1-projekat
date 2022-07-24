package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	int nVars;

	int printCallCount = 0;
	int varDeclCount = 0;
	Obj currentMethod = null;
	boolean errorDetected = false;
	Struct currentType = null;
	int setadresconst = 0;
	boolean array = false;
	Obj currentclass = null;
	int numformal = 0;
	int fildnumber = 0;
	int scopenumber = 0;
	boolean errorconst = false;
	Obj designatorObj = null;
	boolean isdesignator = false;
	int actparsnumber = 0;
	boolean isdowhile = false;
	boolean relopcrit = false;
	boolean conditiongreska = false;
	ArrayList<Struct> listaactpars = new ArrayList<Struct>();
	Obj des = null;
	boolean ismain = false;
	boolean stampa = false;
	ArrayList<String> listaformalnih = new ArrayList<>();
	int lokalnepromenljive = 0;
	int golablnepromenljive = 0;
	int klasapromenljive = 0;

	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public boolean passed() {
		return !errorDetected;
	}

	public void visit(StatementPrintNum print) {
		printCallCount++;

		int pomocna = print.getExpr().struct.getKind();

		if ((pomocna != Struct.Bool) && (pomocna != Struct.Char) && (pomocna != Struct.Int)) {
			report_error("GRESKA: Expr mora biti tipa bool, char ili int", print);
		}

	}

	public void visit(StatementPrintNoNum print) {
		printCallCount++;

		int pomocna = print.getExpr().struct.getKind();

		if ((pomocna != Struct.Bool) && (pomocna != Struct.Char) && (pomocna != Struct.Int)) {
			report_error("GRESKA: Expr mora biti tipa bool, char ili int", print);
		}

	}

	public void visit(ProgName progName) {
		progName.obj = TabExtend.insert(Obj.Prog, progName.getProgName(), TabExtend.noType);
		TabExtend.openScope();
	}

	public void visit(Program program) {
		nVars = TabExtend.currentScope.getnVars();

		TabExtend.chainLocalSymbols(program.getProgName().obj);
		TabExtend.closeScope();
		if (!ismain) {
			report_error("GRESKA: Nije deklarisna main metoda", program);
		}
		if (lokalnepromenljive > 256) {
			report_error("GRESKA: Nije moguce koristiti vise od 256 lokalnih promenljivi", null);
		}
		if (golablnepromenljive > 65536) {
			report_error("GRESKA: Nije moguce koristiti vise od 65536 globalnih promenljivi", null);
		}
	}

	public void visit(ConstAsign constAsign) {
		if (TabExtend.currentScope.findSymbol(constAsign.getVarName()) != null) {
			report_error("GRESKA: Vec u opsegu postoji simbol sa imenom " + constAsign.getVarName(), constAsign);
		} else {
			if (constAsign.getConst().struct == currentType) {
				constAsign.obj = TabExtend.insert(Obj.Con, constAsign.getVarName(), currentType);
				constAsign.obj.setAdr(setadresconst);
			} else {
				report_error("GRESKA: Konstanta " + constAsign.getVarName() + " ima nekompatiblini tip i vrednost", constAsign);
			}
		}

	}

	public void visit(NumConst numConst) {
		numConst.struct = TabExtend.intType;
		setadresconst = numConst.getNum();
	}

	public void visit(CharConst charConst) {
		charConst.struct = TabExtend.charType;
		setadresconst = (int) (charConst.getCh().charAt(1));
	}

	public void visit(BoolConst boolConst) {
		boolConst.struct = TabExtend.boolType;
		if (boolConst.getBo().equals("true"))
			setadresconst = 1;
		else
			setadresconst = 0;
	}

	public void visit(VarDeclGlobalName varDeclGlobalName) {
		if (TabExtend.currentScope.findSymbol(varDeclGlobalName.getVarName()) != null) {
			report_error("GRESKA: Vec u opsegu postoji simbol sa imenom " + varDeclGlobalName.getVarName(), varDeclGlobalName);

		} else {
			//report_info("Globalna promenljiva: " + varDeclGlobalName.getVarName(), varDeclGlobalName);
			golablnepromenljive++;
			Obj obj;
			if (array)
				if (currentclass == null)
					obj = TabExtend.insert(Obj.Var, varDeclGlobalName.getVarName(),
							new Struct(Struct.Array, currentType));
				else
					obj = TabExtend.insert(Obj.Fld, varDeclGlobalName.getVarName(),
							new Struct(Struct.Array, currentType));
			else if (currentclass == null)
				obj = TabExtend.insert(Obj.Var, varDeclGlobalName.getVarName(), currentType);
			else
				obj = TabExtend.insert(Obj.Fld, varDeclGlobalName.getVarName(), currentType);
			obj.setLevel(scopenumber);
			if (currentclass != null) {
				fildnumber++;
			}
		}

	}
	
	public void visit(VarDeclLocallName varDeclGlobalName) {
		if (TabExtend.currentScope.findSymbol(varDeclGlobalName.getVarName()) != null) {
			report_error("GRESKA: Vec u opsegu postoji simbol sa imenom " + varDeclGlobalName.getVarName(), varDeclGlobalName);

		} else {
			//report_info("Lokalna promenljiva: " + varDeclGlobalName.getVarName(), varDeclGlobalName);
			lokalnepromenljive++;
			Obj obj;
			if (array)
				if (currentclass == null)
					obj = TabExtend.insert(Obj.Var, varDeclGlobalName.getVarName(),
							new Struct(Struct.Array, currentType));
				else
					obj = TabExtend.insert(Obj.Fld, varDeclGlobalName.getVarName(),
							new Struct(Struct.Array, currentType));
			else if (currentclass == null)
				obj = TabExtend.insert(Obj.Var, varDeclGlobalName.getVarName(), currentType);
			else
				obj = TabExtend.insert(Obj.Fld, varDeclGlobalName.getVarName(), currentType);
			obj.setLevel(scopenumber);
			if (currentclass != null) {
				fildnumber++;
			}
		}

	}
	
	public void visit(VarDeclClassName varDeclGlobalName) {
		if (TabExtend.currentScope.findSymbol(varDeclGlobalName.getVarName()) != null) {
			report_error("GRESKA: Vec u opsegu postoji simbol sa imenom " + varDeclGlobalName.getVarName(), varDeclGlobalName);

		} else {
			//log.info("Lokalna promenljiva: " + varDeclGlobalName.getVarName());
			// nije lokalna promenljiva?
			Obj obj;
			
			if (array)
				if (currentclass == null)
					obj = TabExtend.insert(Obj.Var, varDeclGlobalName.getVarName(),
							new Struct(Struct.Array, currentType));
				else
					obj = TabExtend.insert(Obj.Fld, varDeclGlobalName.getVarName(),
							new Struct(Struct.Array, currentType));
			else if (currentclass == null)
				obj = TabExtend.insert(Obj.Var, varDeclGlobalName.getVarName(), currentType);
			else
				obj = TabExtend.insert(Obj.Fld, varDeclGlobalName.getVarName(), currentType);
			obj.setLevel(scopenumber);
			if (currentclass != null) {
				fildnumber++;
			}
		}

	}

	public void visit(VarDeclBraces varDeclBraces) {
		array = true;
	}

	public void visit(NoVarDeclBraces noVarDeclBraces) {
		array = false;
	}

	public void visit(RecordName recordName) {
		Obj record = TabExtend.find(recordName.getRecordName());
		if (record == TabExtend.noObj) {
			currentclass = recordName.obj = TabExtend.insert(Obj.Type, recordName.getRecordName(), new Struct(Struct.Class));
			currentclass.setFpPos(1);
		} else {
			currentclass = record;
			report_error("GRESKA: Vec postoji tip deklarisan " + recordName.getRecordName() + " ovim tipom", recordName);
		}
		fildnumber = 0;
		TabExtend.openScope();
		scopenumber++;

	}

	public void visit(RecordDecl recordDecl) {
		TabExtend.chainLocalSymbols(currentclass.getType());
		TabExtend.closeScope();
		scopenumber--;
		currentclass = null;
	}

	public void visit(Type type) {
		Obj typeNode = TabExtend.find(type.getTypeName());
		if (typeNode == TabExtend.noObj) {
			report_error("GRESKA: Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", type);
			currentType = type.struct = TabExtend.noType;
		} else {
			if (Obj.Type == typeNode.getKind()) {
				currentType = type.struct = typeNode.getType();
			} else {
				report_error("GRESKA: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				currentType = type.struct = TabExtend.noType;
			}
		}
	}

	public void visit(TypeV typeV) {
		typeV.struct = TabExtend.noType;
	}

	public void visit(TypeT typeT) {
		typeT.struct = typeT.getType().struct;
	}

	public void visit(MethodTypeName methodTypeName) {
		Obj obj = TabExtend.find(methodTypeName.getMethName());
		if (obj == TabExtend.noObj) {
			currentMethod = TabExtend.insert(Obj.Meth, methodTypeName.getMethName(),
					methodTypeName.getTypeVoid().struct);
			methodTypeName.obj = currentMethod;
			if (methodTypeName.getMethName().equals("main")) {
				ismain = true;
				if (methodTypeName.getTypeVoid().struct.getKind() != Struct.None) {
					report_error("GRESKA: main metoda mora biti void ", methodTypeName);
				}
			}
		} else
			report_error("GRESKA: Vec je deklarisan metoda sa ovim nazivom " + methodTypeName.getMethName(), methodTypeName);
		TabExtend.openScope();
		scopenumber++;

		if (currentclass != null) {
			Obj pom = TabExtend.insert(Obj.Var, "this", currentclass.getType());
			pom.setLevel(scopenumber);
			//stampa = false;
		} else {
			//stampa = true;
		}

		//report_info("Obradjuje se funkcija " + methodTypeName.getMethName(), methodTypeName);
	}

	public void visit(MethodDecl methoddecl) {
		TabExtend.chainLocalSymbols(currentMethod);
		currentMethod.setLevel(numformal);
		TabExtend.closeScope();
		scopenumber--;
		currentMethod = null;
		numformal = 0;
		listaformalnih.clear();
	}

	public void visit(NoBraceFormParsSingle fps) {
		if (TabExtend.currentScope.findSymbol(fps.getVarName()) != null) {
			report_error("GRESKA: Vec je deklarisan formalni parametar sa ovim nazivom " + fps.getVarName(), fps);
		} else {

			Obj obj = TabExtend.insert(Obj.Var, fps.getVarName(), currentType);
			obj.setLevel(scopenumber);
			numformal++;
			obj.setFpPos(numformal);	
			listaformalnih.add(fps.getVarName());
		}
	}

	public void visit(FormParsSingl fps) {
		if (TabExtend.currentScope.findSymbol(fps.getVarName()) != null) {
			report_error("GRESKA: Vec je deklarisan formalni parametar sa ovim nazivom " + fps.getVarName(), fps);
		} else {
			Obj obj =TabExtend.insert(Obj.Var, fps.getVarName(), new Struct(Struct.Array, currentType));
			obj.setLevel(scopenumber);
			numformal++;
			obj.setFpPos(numformal);
			listaformalnih.add(fps.getVarName());
		}
	}

	public void visit(ClassName className) {
		Obj obj = TabExtend.find(className.getClasName());
		if (obj == TabExtend.noObj) {
			currentclass = className.obj = TabExtend.insert(Obj.Type, className.getClasName(),
					new Struct(Struct.Class));
			className.obj = currentclass;
		} else {
			currentclass = obj;
			report_error("GRESKA: Vec je deklarisan sa ovakvim imenom " + className.getClasName(), className);
		}
		fildnumber = 0;
		TabExtend.openScope();
		scopenumber++;

	}

	public void visit(ClassDecl classDecl) {
		TabExtend.chainLocalSymbols(currentclass.getType());
		currentclass.getType().setMembers(TabExtend.currentScope.getLocals());
		TabExtend.closeScope();
		scopenumber--;
		if (klasapromenljive > 65536) {
			report_error("GRESKA: Nije moguce koristiti vise od 65536 globalnih promenljivi", null);
		}
		klasapromenljive = 0;
		currentclass = null;
	}

	public void visit(ClassExtendCheckNoError ex) {
		Obj obj = TabExtend.find(ex.getType().getTypeName());

		if ((obj.getType().getKind() != Struct.Class) || (obj.getFpPos() == 1)) {
			report_error("GRESKA: Klasa se ne moze naslediti iz " + ex.getType().getTypeName(), ex);
		} else {
			// nesto
		}

	}

	public void visit(ConstructorName cn) {
		if (!cn.getConstructorName().equals(currentclass.getName())) {
			report_error("GRESKA: Klasa mora imati konstruktor sa istim imenom kao i klasa " + cn.getConstructorName(), cn);
			errorconst = true;
		} else {
			/*
			 * currentMethod = TabExtend.insert(Obj.Meth, cn.getConstructorName(),
			 * currentclass.getType()); cn.obj = currentMethod;
			 * 
			 * TabExtend.openScope(); scopenumber++;
			 * 
			 * if (currentclass != null) { Obj pom = TabExtend.insert(Obj.Var, "this",
			 * currentclass.getType()); pom.setLevel(scopenumber); numformal++; }
			 * 
			 * report_info("Obradjuje se funkcija " + cn.getConstructorName(), cn);
			 */
		}
	}

	public void visit(ConstructorDecl cn) {
		/*
		 * if (!errorconst) { TabExtend.chainLocalSymbols(currentMethod);
		 * currentMethod.setLevel(numformal); TabExtend.closeScope(); scopenumber--;
		 * currentMethod = null; numformal = 0; } errorconst = false;
		 */
	}

	public void visit(DesignatorName dname) {
		
		Obj obj = TabExtend.find(dname.getVarName());
		if (obj == TabExtend.noObj) {
			if (dname.getParent().getClass() == DesignatorMoreIdent.class) {
				Obj objpom = ((DesignatorMoreIdent)(dname.getParent())).getDesignatorMore().obj;
				
				for (Obj tren : objpom.getType().getMembers()) {
					if (tren.getName().equals(dname.getVarName())) {
						obj = tren;
					}
				}
				
			}
			
		}
		dname.obj = obj;
		if ((obj.getType().getKind() == Struct.Class) || 
				((obj.getType().getKind() == Struct.Array)
						&&(obj.getType().getElemType().getKind()==Struct.Class))) {
			report_info("Koristi se promenljiva tipa record ili klase " + dname.getVarName(), dname);
		} 
		String pomstring = dname.getVarName();
		if (obj.getKind() == Obj.Var) {
			Obj o = TabExtend.currentScope.findSymbol(pomstring);
			if (o !=null) {
				boolean nije = false;
				for (String s: listaformalnih)				
					if (s.equals(pomstring)) {
							nije = true;
						}
				if (!nije) {
					report_info("Pristupa se lokalnoj promenljivi " + pomstring, dname);
				}
			} else {
				report_info("Pristupa se globalnoj promenljivi " + pomstring, dname);
			}
		}
		
	}

	public void visit(Designator d) {
		if ((d.getDesignatorMore().obj != null)&&(d.getDesignatorMore().obj.getKind() == Obj.Meth)) {
			designatorObj = d.getDesignatorMore().obj;
			report_info("Poziva se globalna funkcija " + ((DesignatorMoreSingle)d.getDesignatorMore()).getDesignatorName().getVarName(), d);
		}
		
		d.obj = d.getDesignatorMore().obj;
		
	}

	public void visit(DesignatorAdditionAE ae) {

		if (((DesignatorStatement) ae.getParent()).getDesignator().getDesignatorMore().obj != null) {
			int pomocna = ((DesignatorStatement) ae.getParent()).getDesignator().getDesignatorMore().obj.getKind();
			int pomocnatip = ((DesignatorStatement) ae.getParent()).getDesignator().getDesignatorMore().obj.getType()
					.getKind();

			if ((pomocna != Obj.Var) && (pomocna != Obj.Elem) && (pomocna != Obj.Fld)) {
				report_error("GRESKA: Designator ne oznacava promeljivu, elemnt niza ili polje unutar objekta", ae);
			} else if (ae.getDesignatorAdditionExprSemi().struct == null
					|| pomocnatip != ae.getDesignatorAdditionExprSemi().struct.getKind()) {
				report_error("GRESKA: Designator i expr nisu istog tipa", ae);
			}
		}

	}

	public void visit(DesignatorMoreIdent dm) {
		Obj obj = dm.getDesignatorMore().obj;

		if (obj == TabExtend.noObj) {
			report_error("GRESKA: Ne postoji promenljiva " + obj.getName(), dm);
		} else {
			if (obj.getType().getKind() != Struct.Class) {
				report_error("GRESKA: Promenljiva " + obj.getName() + " ne pretstavlja klasu", dm);
			} else {
				Obj pom = null;
				if ((currentclass!=null)&&(obj.getType() != currentclass.getType())) {
					for (Obj tren : obj.getType().getMembers()) {
						if (tren.getName().equals(dm.getDesignatorName().getVarName())) {
							pom = tren;
						}
					}
				} else {
					// gledamo polja nase klase
					if (currentclass!=null) {
					pom = TabExtend.currentScope.getOuter().findSymbol(dm.getDesignatorName().getVarName());
					} else {
						pom = TabExtend.find(obj.getName());
						Collection<Obj> kolekcija = pom.getType().getMembers();
						pom = null;
						for (Obj oo:kolekcija) {
							if (oo.getName().equals(dm.getDesignatorName().getVarName())) {
								pom = oo;
							}
						}
						
					}
				}

				if (pom == null) {
					pom = TabExtend.noObj;
				}

				if (pom == TabExtend.noObj) {
					report_error("GRESKA: Klasa nema polje ili metodu: " + dm.getDesignatorName().getVarName(),dm);
				}

				dm.obj = pom;
			}
		}

	}

	public void visit(DesignatorMoreExpr dme) {

		Obj obj = dme.getDesignatorMore().obj;

		if (obj.getType().getKind() != Struct.Array) {
			report_error("GRESKA: Ime ne oznacava niz " + obj.getName(), dme);
			dme.obj = TabExtend.noObj;
		} else {
			report_info("Pristupa se nizu " + obj.getName(), dme);
			dme.obj = new Obj(Obj.Elem, "Elem" + obj.getName(), obj.getType().getElemType());
		}

		if (dme.getExpr().struct.getKind() != Struct.Int) {
			report_error("GRESKA: Pristupanje elementu niza mora biti preko inta", dme);
			dme.obj = TabExtend.noObj;
		}

	}

	public void visit(DesignatorMoreSingle dms) {
		Obj obj = TabExtend.find(dms.getDesignatorName().getVarName());
		int pomocna = obj.getKind();
		if (obj == TabExtend.noObj) {
			report_error("GRESKA: Ime nije deklarisano " + dms.getDesignatorName().getVarName(), dms);
		} else {
			if ((pomocna != Obj.Var) && (pomocna != Obj.Elem) && (pomocna != Obj.Fld) && (pomocna != Obj.Con)&& (pomocna != Obj.Meth)) { 
				report_error("GRESKA: Ime ne predstavlja promenljivu, element niza ili polje klase " + dms.getDesignatorName().getVarName(), dms);
			} else {
				String ime = dms.getDesignatorName().getVarName();
				for (String s: listaformalnih)				
					if (s.equals(ime)) {
							report_info("Pristupa se formalnom argumentu funkcije: " + ime, dms);
						}
			}
		}

		dms.obj = obj;

	}

	public void visit(FactorConst fc) {
		fc.struct = fc.getConst().struct;
	}

	public void visit(FactorParen fp) {
		fp.struct = fp.getExpr().struct;
	}

	public void visit(FactorNewNoArray fn) {
		fn.struct = fn.getType().struct;
		
		if (fn.getType().struct.getKind() != Struct.Class) {
			report_error("GRESKA: Pri generisanju new mora biti tip klase", fn);
		}
	}

	public void visit(FactorNewArray fn) {
//		public void visit(FactorNewBrack f) {
//			if (f.getExpr().struct.getKind() != Struct.Int) {
//				report_error("Greska unutar zagrad mora biti int", f);
//			}
//		}
		
//		if (fn.getType().struct.getKind() != Struct.Class) {
//			report_error("Greska pri generisanju new mora biti tip klase", fn);
//		}
		
		fn.struct = new Struct(Struct.Array, fn.getType().struct);
	}

	public void visit(FactorNewBrack f) {
		if (f.getExpr().struct.getKind() != Struct.Int) {
			report_error("GRESKA: Unutar zagrad mora biti int", f);
		}
	}

	public void visit(FactorVar fv) {
		fv.struct = fv.getDesignator().getDesignatorMore().obj.getType();
	}

	public void visit(FactorVarFunNoAct fv) {
		Obj obj = fv.getDesignator().getDesignatorMore().obj;
		if (obj.getKind() != Obj.Meth) {
			report_error("GRESKA: Pozvana funkcija nije metoda ili funkcija", fv);
		} else {
			if (obj.getLevel() != 0) {
				report_error("GRESKA: Pozvana funkcija ima parametre", fv);
			}
		}
		fv.struct = obj.getType();
	}

	public void visit(FactorVarFunAct fv) {
		Obj obj = fv.getDesignator().getDesignatorMore().obj;
		if (obj.getKind() != Obj.Meth) {
			report_error("GRESKA: Pozvana funkcija nije metoda ili funkcija", fv);
		} else {

		}
		fv.struct = obj.getType();
	}

	public void visit(ActParsExprMore actp) {
		actparsnumber++;
		listaactpars.add(actp.getExpr().struct);
	}

	public void visit(NoActParsExpr actp) {
		actparsnumber++;
		listaactpars.add(0, actp.getExpr().struct);
	}

	public void visit(ActPars a) {
		if (designatorObj.getLevel() != actparsnumber) {
			report_error("GRESKA: Unet pogresna broj parametara", a);
		} else {
			Collection<Obj> o = designatorObj.getLocalSymbols();
			boolean greska = false;
			for (Obj pom : o) {
				if (pom.getFpPos() != 0)
					if (!pom.getType().equals(listaactpars.get(pom.getFpPos() - 1))) {
						greska = true;
					}
			}

			if (greska)
				report_error("GRESKA: Uneti parametri nisu istog tipa kao stvarni parametri funkcije", a);

		}

		actparsnumber = 0;
		listaactpars.clear();
	}

	public void visit(NoMulopTerm t) {
		t.struct = t.getFactor().struct;
	}

	public void visit(TermMulop t) {
		if ((t.getFactor().struct.getKind() == Struct.Int) && (t.getTerm().struct.getKind() == Struct.Int)) {
			t.struct = t.getFactor().struct;
		} else {
			report_error("GRESKA: Operacije *, / i % moguce je samo izmedju tipa int ", t);
		}
	}

	public void visit(MinusExpr e) {

		if (e.getTerm().struct.getKind() != Struct.Int) {
			report_error("GRESKA: Minus je moguce raditi samo na podacima tipa int ", e);
		}

		e.struct = e.getTerm().struct;
	}

	public void visit(PlusExpr e) {
		e.struct = e.getTerm().struct;
	}

	public void visit(MoreExp e) {
		if ((e.getExpr().struct.getKind() == Struct.Int) && (e.getTerm().struct.getKind() == Struct.Int)) {
			e.struct = e.getExpr().struct;
		} else {
			report_error("GRESKA: Operacije + i - moguce je samo izmedju tipa int ", e);
		}
	}

	public void visit(DesignatorAdditionExprSem de) {
		de.struct = de.getExpr().struct;
	}

	public void visit(DesignatorAdditionPlusPlus dp) {
		int pomocna = ((DesignatorStatement) dp.getParent()).getDesignator().getDesignatorMore().obj.getKind();
		int pomocnatip = ((DesignatorStatement) dp.getParent()).getDesignator().getDesignatorMore().obj.getType()
				.getKind();

		if ((pomocna != Obj.Var) && (pomocna != Obj.Elem) && (pomocna != Obj.Fld)) {
			report_error("GRESKA: Designator ne oznacava promeljivu, elemnt niza ili polje unutar objekta", dp);
		} else if (pomocnatip != Struct.Int) {
			report_error("GRESKA: Designator nije tipa int ", dp.getParent());
		}

	}

	public void visit(DesignatorAdditionMinusMinus dp) {
		int pomocna = ((DesignatorStatement) dp.getParent()).getDesignator().getDesignatorMore().obj.getKind();
		int pomocnatip = ((DesignatorStatement) dp.getParent()).getDesignator().getDesignatorMore().obj.getType()
				.getKind();

		if ((pomocna != Obj.Var) && (pomocna != Obj.Elem) && (pomocna != Obj.Fld)) {
			report_error("GRESKA: Designator ne oznacava promeljivu, elemnt niza ili polje unutar objekta", dp);
		} else if (pomocnatip != Struct.Int) {
			report_error("GRESKA: Designator nije tipa int ", dp.getParent());
		}

	}

	public void visit(DesignatorAdditionActPars dp) {
		int pomocna = ((DesignatorStatement) dp.getParent()).getDesignator().getDesignatorMore().obj.getKind();
		// int pomocnatip =
		// ((DesignatorStatement)dp.getParent()).getDesignator().getDesignatorMore().obj.getType().getKind();

		if ((pomocna != Obj.Meth)) {
			report_error(
					"GRESKA: Designator mora oznacavati nestaticku metodu unutrasnje klase ili globalnu funkciju glavnog programa",
					dp);
		} else {

		}

	}

	public void visit(Do d) {
		isdowhile = true;
	}

	public void visit(While w) {
		isdowhile = false;
	}

	public void visit(StatementBreak b) {
		if (!isdowhile) {
			report_error("GRESKA: Break naredba se moze koristiti samo unutar do while petlje", b.getParent());
		}
	}

	public void visit(StatementContinue b) {
		if (!isdowhile) {
			report_error("GRESKA: Continue naredba se moze koristiti samo unutar do while petlje", b.getParent());
		}
	}

	public void visit(StatementRead r) {
		int pomocna = r.getDesignator().getDesignatorMore().obj.getKind();
		int pomocnatip = r.getDesignator().getDesignatorMore().obj.getType().getKind();

		if ((pomocna != Obj.Var) && (pomocna != Obj.Elem) && (pomocna != Obj.Fld)) {
			report_error("GRESKA: Designator ne oznacava promeljivu, elemnt niza ili polje unutar objekta", r);
		} else if ((pomocnatip != Struct.Bool) && (pomocnatip != Struct.Char) && (pomocnatip != Struct.Int)) {
			report_error("GRESKA: Designator mora biti tipa bool, char ili int", r);
		}

	}

	public void visit(StatementReturnNoExpr st) {
		if (currentMethod == null) {
			report_error("GRESKA: Return se mora pozvati unutar metode", st);
		} else {
			if (currentMethod.getType().getKind() != Struct.None) {
				report_error("GRESKA: Metoda ima povratan tip i ne moze se pozvati prazan return", st);
			}
		}
	}

	public void visit(StatementReturnExpr st) {
		if (currentMethod == null) {
			report_error("GRESKA: Return se mora pozvati unutar metode", st);
		} else {
			if (currentMethod.getType() != st.getExpr().struct) {
				report_error("GRESKA: Tip metode i povratni tip moraju biti isti", st);
			}
		}
	}

	public void visit(EqualEqual e) {
		relopcrit = false;
	}

	public void visit(NotEqual e) {
		relopcrit = false;
	}

	public void visit(Greater e) {
		relopcrit = true;
	}

	public void visit(GreaterEqual e) {
		relopcrit = true;
	}

	public void visit(Less e) {
		relopcrit = true;
	}

	public void visit(LessEqual e) {
		relopcrit = true;
	}

	public void visit(CoundFactExprOne c) {
		if (c.getExpr().struct.getKind() != Struct.Bool) {
			report_error("GRESKA: Expr mora biti bool", c);
			conditiongreska = true;
		}

	}

	public void visit(CoundFactExprMore c) {
		if (!c.getExpr().struct.equals(c.getExpr1().struct)) {
			report_error("GRESKA: Expr moraju biti kombatibilni", c);
			conditiongreska = true;
		}

		if ((c.getExpr().struct.getKind() == Struct.Array) || (c.getExpr().struct.getKind() == Struct.Class)) {
			if (relopcrit) {
				report_error("GRESKA: Uz klasni i nizovni tip se mogu koristiti samo == i !=", c);
				conditiongreska = true;
			}
		}
	}

	public void visit(Condition c) {
		if (conditiongreska) {
			report_error("GRESKA: Tip uslovnog izraza mora biti bool", c);
		}
	}

	public void visit(ClassVarDecl a) {
		klasapromenljive++;
	}
	
	public void visit(MethodDeclFormPar p) {
		currentMethod.setLevel(numformal);
	}

}
