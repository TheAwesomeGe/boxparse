package boxparse.interpretation.printer;

import boxparse.interpretation.Interpretation;
import boxparse.interpretation.Referent;
import boxparse.interpretation.Token;
import boxparse.interpretation.drs.AnaphoricPronoun;
import boxparse.interpretation.drs.BasicDRS;
import boxparse.interpretation.drs.DRSVisitor;
import boxparse.interpretation.drs.DefiniteDescription;
import boxparse.interpretation.drs.DeicticPronoun;
import boxparse.interpretation.drs.MergeDRS;
import boxparse.interpretation.drs.ProperName;
import boxparse.interpretation.drs.ReflexivePronoun;
import boxparse.interpretation.drs.condition.CardinalExpression;
import boxparse.interpretation.drs.condition.Condition;
import boxparse.interpretation.drs.condition.Disjunction;
import boxparse.interpretation.drs.condition.Equality;
import boxparse.interpretation.drs.condition.Implication;
import boxparse.interpretation.drs.condition.NamedEntity;
import boxparse.interpretation.drs.condition.Negation;
import boxparse.interpretation.drs.condition.Predicate;
import boxparse.interpretation.drs.condition.Proposition;
import boxparse.interpretation.drs.condition.Relation;
import boxparse.interpretation.drs.condition.TimeExpression;
import boxparse.interpretation.drs.condition.WhQuestion;

public class XMLPrinter implements DRSVisitor {
	
	private int indent;

	
	private String generateIndent() {
		String ind = "";
		for(int i = 0; i < indent; i++) {
			ind += "  ";
		}
		return ind;
	}
	
	private void printTokens(Interpretation interpretation) {
		System.out.println(generateIndent() + "<tokens>");
		indent++;
		
		for(Token t : interpretation.getTokens().values()) {
			System.out.println(generateIndent() + "<token id=\"" + t.getID() + "\" word=\"" + t.getWord() + "\" pos=\"" + t.getPOSTag() + "\" ne=\"" + t.getNETag() + "\" />" );
		}
		
		indent--;
		System.out.println(generateIndent() + "</tokens>");
	}
	
	private void printReferents(Interpretation interpretation) {
		System.out.println(generateIndent() + "<referents>");
		indent++;

		for(Referent r : interpretation.getReferents().values()) {
			System.out.println(generateIndent() + "<referent id=\"" + r.getID() + "\" token=\"" + r.getTokenID() + "\" />" );
		}
		
		indent--;
		System.out.println(generateIndent() + "</referents>");
	}
	
	
	public XMLPrinter() {
		indent = 0;
	}
	
	public void print(Interpretation interpretation) {
		System.out.println("<interpretation>");
		indent++;
		
		printTokens(interpretation);
		printReferents(interpretation);
		
		interpretation.getDRS().accept(this);
		
		indent--;
		System.out.println("</interpretation>");
	}
	
	@Override
	public void processDRS(BasicDRS drs) {
		System.out.println(generateIndent() + "<drs>");
		indent++;
		
		for(Condition c : drs.getConditions()) {
			c.accept(this);
		}
		
		indent--;
		System.out.println(generateIndent() + "</drs>");
	}
	
	@Override
	public void processMergeDRS(MergeDRS drs) {
		System.out.println(generateIndent() + "<merge>");
		indent++;
		
		drs.getFirstDRS().accept(this);
		drs.getSecondDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</merge>");
	}
	
	@Override
	public void processAnaphoricPronoun(AnaphoricPronoun pronoun) {
		System.out.println(generateIndent() + "<anaphoric>");
		indent++;
		
		pronoun.getFirstDRS().accept(this);
		pronoun.getSecondDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</anaphoric>");
	}

	@Override
	public void processDescription(DefiniteDescription description) {
		System.out.println(generateIndent() + "<description>");
		indent++;
		
		description.getFirstDRS().accept(this);
		description.getSecondDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</description>");
	}

	@Override
	public void processProperName(ProperName name) {
		System.out.println(generateIndent() + "<propername>");
		indent++;
		
		name.getFirstDRS().accept(this);
		name.getSecondDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</propername>");
	}

	@Override
	public void processReflexivePronoun(ReflexivePronoun pronoun) {
		System.out.println(generateIndent() + "<reflexive>");
		indent++;
		
		pronoun.getFirstDRS().accept(this);
		pronoun.getSecondDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</reflexive>");	
	}

	@Override
	public void processDeicticPronoun(DeicticPronoun pronoun) {
		System.out.println(generateIndent() + "<deictic>");
		indent++;
		
		pronoun.getFirstDRS().accept(this);
		pronoun.getSecondDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</deictic>");
	}
	
	@Override
	public void processPredicate(Predicate predicate) {
		System.out.println(generateIndent() + "<predicate arg=\"" + predicate.getReferent() + "\" symbol=\""
				+ predicate.getSymbol() + "\" pos=\"" + predicate.getPOS() + "\" sense=" + predicate.getSense() + " />" );	
	}
	
	@Override
	public void processRelation(Relation relation) {
		System.out.println(generateIndent() + "<relation arg1=\"" + relation.getFirstReferent() + "\" arg2=\""
				+ relation.getSecondReferent() + "\" symbol=\"" + relation.getSymbol() + "\" sense=" + relation.getSense() + " />" );	
	}
	
	@Override
	public void processNamedEntity(NamedEntity entity) {
		System.out.println(generateIndent() + "<entity arg=\"" + entity.getReferent() + "\" symbol=\"" + entity.getSymbol() + "\" type=\"" + entity.getType() + "\" />" );
	}
	
	@Override
	public void processTimex(TimeExpression timex) {
		System.out.println(generateIndent() + "<timex>");
		indent++;
		
		// TODO Complete this stuff
		
		indent--;
		System.out.println(generateIndent() + "</timex>");	
	}
	
	@Override
	public void processCardinal(CardinalExpression cardinal) {
		System.out.println(generateIndent() + "<cardinal>");
		indent++;
		
		// TODO Complete this stuff
		
		indent--;
		System.out.println(generateIndent() + "</cardinal>");
		
	}
	
	@Override
	public void processEquality(Equality equality) {
		System.out.println(generateIndent() + "<equality>");
		indent++;
		
		// TODO Complete this stuff
		
		indent--;
		System.out.println(generateIndent() + "</equality>");
	}
	
	@Override
	public void processDisjunction(Disjunction disjunction) {
		System.out.println(generateIndent() + "<or>");
		indent++;
		
		disjunction.getFirstDRS().accept(this);
		disjunction.getSecondDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</or>");	
	}
	
	@Override
	public void processImplication(Implication implication) {
		System.out.println(generateIndent() + "<implication>");
		indent++;
		
		implication.getFirstDRS().accept(this);
		implication.getSecondDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</implication>");
	}
	
	@Override
	public void processNegation(Negation negation) {
		System.out.println(generateIndent() + "<not>");
		indent++;
		
		negation.getDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</not>");	
	}

	@Override
	public void processWhQuestion(WhQuestion question) {
		System.out.println(generateIndent() + "<whq>");
		indent++;
		
		// TODO Complete this stuff
		
		indent--;
		System.out.println(generateIndent() + "</whq>");		
	}

	@Override
	public void processProposition(Proposition proposition) {
		System.out.println(generateIndent() + "<proposition arg=\"" + proposition.getReferent() + "\">");
		indent++;
		
		proposition.getDRS().accept(this);
		
		indent--;
		System.out.println(generateIndent() + "</proposition>");
		
	}

}
