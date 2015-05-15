package boxparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Element;

import boxparse.exception.UnableToParseException;
import boxparse.interpretation.Interpretation;
import boxparse.interpretation.Referent;
import boxparse.interpretation.Token;
import boxparse.interpretation.drs.AnaphoricPronoun;
import boxparse.interpretation.drs.BasicDRS;
import boxparse.interpretation.drs.DRS;
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

/**
 * A parser for the DRS part of Boxer's XML.
 * 
 * @author Eugenio Ribeiro
 */
public class DRSParser {
	/**
	 * The XML element corresponding to the main DRS.
	 */
	private final Element mainDRS;
	
	/**
	 * The tokens extracted from Boxer's XML.
	 */
	private final HashMap<String, Token> tokens;
	
	/**
	 * The referents present in the XML.
	 */
	private final HashMap<String, Referent> referents;

	/**
	 * Parses an XML element corresponding to a DRS.
	 * 
	 * @param drs The XML element.
	 * @return The corresponding DRS.
	 * @throws UnableToParseException if the DRS type is invalid.
	 */
	private DRS parseDRS(Element drs) throws UnableToParseException {
		switch(drs.getNodeName()) {
		case "drs":
			return parseBasicDRS(drs);
		case "merge":
			return parseMergeDRS(drs);
		case "smerge":
			return parseMergeDRS(drs);
		case "alfa":
			return parseAlfaDRS(drs);
		default:
			throw new UnableToParseException("Invalid DRS type");
		}
	}

	/**
	 * Parses a basic DRS element.
	 * 
	 * @param drs The DRS element.
	 * @return The basic DRS.
	 * @throws UnableToParseException if the DRS is invalid.
	 */
	private DRS parseBasicDRS(Element drs) throws UnableToParseException {
		List<Element> children = NodeFilter.getInstance().selectElements(drs.getChildNodes());
		
		List<Condition> conditions = new ArrayList<Condition>();
		
		for(Element e : children) {
			if(e.getNodeName().equals("dr")) {
				parseReferent(e);
			}
			else {
				conditions.add(parseCondition(e));
			}
		}
		
		return new BasicDRS(conditions);
	}
	
	/**
	 * Parses a merged DRS element.
	 * 
	 * @param drs The DRS element.
	 * @return The merged DRS.
	 * @throws UnableToParseException if the merge is invalid.
	 */
	private DRS parseMergeDRS(Element drs) throws UnableToParseException {
		List<Element> children = NodeFilter.getInstance().selectElements(drs.getChildNodes());
		
		if(children.size() != 2) {
			throw new UnableToParseException("MergeDRS with invalid number of components");
		}
		
		return new MergeDRS(parseDRS(children.get(0)), parseDRS(children.get(1)));
	}
	
	/**
	 * Parses an alfa DRS element.
	 * 
	 * @param drs The DRS element.
	 * @return The alfa DRS.
	 * @throws UnableToParseException if the alfa is invalid.
	 */
	private DRS parseAlfaDRS(Element drs) throws UnableToParseException {
		List<Element> children = NodeFilter.getInstance().selectElements(drs.getChildNodes());
		
		if(children.size() != 2) {
			throw new UnableToParseException("AlfaDRS with invalid number of components");
		}
		
		String type = drs.getAttribute("type");
		
		switch(type) {
		case "pro":
			return new AnaphoricPronoun(parseDRS(children.get(0)), parseDRS(children.get(1)));
		case "def":
			return new DefiniteDescription(parseDRS(children.get(0)), parseDRS(children.get(1)));
		case "nam":
			return new ProperName(parseDRS(children.get(0)), parseDRS(children.get(1)));
		case "ref":
			return new ReflexivePronoun(parseDRS(children.get(0)), parseDRS(children.get(1)));
		case "dei":
			return new DeicticPronoun(parseDRS(children.get(0)), parseDRS(children.get(1)));
		default:
			throw new UnableToParseException("Invalid AlfaDRS type");	
		}	
	}
	
	/**
	 * Parses a referent definition.
	 * 
	 * @param referent The referent element.
	 */
	private void parseReferent(Element referent) {
		String id = referent.getAttribute("name");
		
		Element index = (Element) referent.getElementsByTagName("index").item(0);
		
		referents.put(id, index != null ? new Referent(id, index.getTextContent()) : new Referent(id));		
	}
	
	/**
	 * Parses a condition element.
	 *  
	 * @param condition The condition element.
	 * @return The condition.
	 * @throws UnableToParseException if the condition is invalid.
	 */
	private Condition parseCondition(Element condition) throws UnableToParseException {
		String type = condition.getNodeName();
		
		switch(type) {
		// Basic conditions
		case "pred":
			return parsePredicate(condition);
		case "rel":
			return parseRelation(condition);
		case "named":
			return parseNamed(condition);
		case "timex":
			return parseTime(condition);
		case "card":
			return parseCardinal(condition);
		case "eq":
			return parseEquality(condition);
		// Complex conditions
		case "or":
			return parseDisjunction(condition);
		case "imp":
			return parseImplication(condition);
		case "not":
			return parseNegation(condition);
		case "nec":
			throw new UnableToParseException("A wild nec appeared. What is this?");
		case "pos":
			throw new UnableToParseException("A wild pos appeared. What is this?");
		case "whq":
			return parseWhQuestion(condition);
		case "prop":
			return parseProposition(condition);
		default:
			throw new UnableToParseException("Invalid condition type");
		}

	}

	/**
	 * Parses a predicate element.
	 * 
	 * @param predicate The predicate element.
	 * @return The predicate.
	 */
	private Predicate parsePredicate(Element predicate) {
		String referent = predicate.getAttribute("arg");
		Predicate p = new Predicate(referent, predicate.getAttribute("symbol"), predicate.getAttribute("type"), Integer.parseInt(predicate.getAttribute("sense")));
		referents.get(referent).addReference(p);
		return p;
	}
	
	/**
	 * Parses a relation element.
	 * 
	 * @param relation The relation element.
	 * @return The relation.
	 */
	private Relation parseRelation(Element relation) {
		String ref1 = relation.getAttribute("arg1");
		String ref2 = relation.getAttribute("arg2");
		Relation r = new Relation(ref1, ref2, relation.getAttribute("symbol"), Integer.parseInt(relation.getAttribute("sense")));
		referents.get(ref1).addReference(r);
		referents.get(ref2).addReference(r);
		return r;
	}
	
	private NamedEntity parseNamed(Element named) {
		String referent = named.getAttribute("arg");
		NamedEntity ne = new NamedEntity(referent, named.getAttribute("symbol"), named.getAttribute("type"));
		referents.get(referent).addReference(ne);
		return ne;
	}
	
	private TimeExpression parseTime(Element timex) {
		// TODO Auto-generated method stub
		System.out.println("A wild Timex appeared!");
		return null;
	}
	
	private CardinalExpression parseCardinal(Element cardex) {
		// TODO Auto-generated method stub
		System.out.println("A wild Cardex appeared!");
		return null;
	}
	
	private Equality parseEquality(Element equality) {
		// TODO Auto-generated method stub
		System.out.println("A wild Equality appeared!");
		return null;
	}
	
	private Disjunction parseDisjunction(Element or) throws UnableToParseException {
		List<Element> children = NodeFilter.getInstance().selectElements(or.getChildNodes());
		
		if(children.size() != 2) {
			throw new UnableToParseException("Disjunction with invalid number of components");
		}
		
		return new Disjunction(parseDRS(children.get(0)), parseDRS(children.get(1)));
	}
	
	private Implication parseImplication(Element implication) throws UnableToParseException {
		List<Element> children = NodeFilter.getInstance().selectElements(implication.getChildNodes());
		
		if(children.size() != 2) {
			throw new UnableToParseException("Implication with invalid number of components");
		}
		
		return new Implication(parseDRS(children.get(0)), parseDRS(children.get(1)));
	}
	
	private Negation parseNegation(Element negation) throws UnableToParseException {
		List<Element> children = NodeFilter.getInstance().selectElements(negation.getChildNodes());
		
		if(children.size() != 1) {
			throw new UnableToParseException("Negation with invalid number of components");
		}
		
		return new Negation(parseDRS(children.get(0)));
	}
	
	private WhQuestion parseWhQuestion(Element whq) {
		// TODO Auto-generated method stub
		System.out.println("A wild Wh-Question appeared!");
		return null;
	}
	
	private Proposition parseProposition(Element proposition) throws UnableToParseException {
		NodeFilter filterer = NodeFilter.getInstance();
		List<Element> children = filterer.removeIndexElements(filterer.selectElements(proposition.getChildNodes()));
		
		if(children.size() != 1) {
			throw new UnableToParseException("Proposition with invalid number of components");
		}
		
		String referent = proposition.getAttribute("argument");
		Proposition p = new Proposition(referent, parseDRS(children.get(0)));
		referents.get(referent).addReference(p);
		return p;
	}

	/**
	 * Creates a new DRS parser.
	 * 
	 * @param drs The XML element corresponding to the main DRS.
	 * @param tokens The tokens extracted from the XML.
	 */
	public DRSParser(Element mainDRS, HashMap<String, Token> tokens) {
		this.mainDRS = mainDRS;
		this.tokens = tokens;
		this.referents = new HashMap<String, Referent>();
	}
	
	/**
	 * Parses the main DRS element.
	 * 
	 * @return The interpretation.
	 * @throws UnableToParseException if the element could not be parsed correctly.
	 */
	public Interpretation parse() throws UnableToParseException {
		return new Interpretation(tokens, referents, parseDRS(mainDRS));
	}
	
}
