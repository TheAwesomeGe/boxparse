package boxparse.interpretation.drs;

import boxparse.interpretation.drs.condition.CardinalExpression;
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

public interface DRSVisitor {
	
	public void processDRS(BasicDRS drs);
	
	public void processMergeDRS(MergeDRS drs);
	
	public void processAnaphoricPronoun(AnaphoricPronoun pronoun);
	
	public void processDescription(DefiniteDescription description);
	
	public void processProperName(ProperName name);
	
	public void processReflexivePronoun(ReflexivePronoun pronoun);
	
	public void processDeicticPronoun(DeicticPronoun pronoun);
	
	public void processPredicate(Predicate predicate);
	
	public void processRelation(Relation relation);
	
	public void processNamedEntity(NamedEntity entity);
	
	public void processTimex(TimeExpression timex);
	
	public void processCardinal(CardinalExpression cardinal);
	
	public void processEquality(Equality equality);

	public void processDisjunction(Disjunction disjunction);

	public void processImplication(Implication implication);
	
	public void processNegation(Negation negation);

	public void processWhQuestion(WhQuestion question);

	public void processProposition(Proposition proposition);
}
