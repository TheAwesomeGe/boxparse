package boxparse;

import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Element;

import boxparse.exception.UnableToParseException;
import boxparse.interpretation.Interpretation;
import boxparse.interpretation.Token;
import boxparse.interpretation.drs.AnaphoricPronoun;
import boxparse.interpretation.drs.DRS;
import boxparse.interpretation.drs.DefiniteDescription;
import boxparse.interpretation.drs.DeicticPronoun;
import boxparse.interpretation.drs.MergeDRS;
import boxparse.interpretation.drs.ProperName;
import boxparse.interpretation.drs.ReflexivePronoun;

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

	private DRS parseBasicDRS(Element drs) {
		System.out.println("YAY, a Basic DRS!");
		return null;
	}
	
	/**
	 * Parses a merged DRS element.
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
	 * Creates a new DRS parser.
	 * 
	 * @param drs The XML element corresponding to the main DRS.
	 * @param tokens The tokens extracted from the XML.
	 */
	public DRSParser(Element mainDRS, HashMap<String, Token> tokens) {
		this.mainDRS = mainDRS;
		this.tokens = tokens;
	}
	
	/**
	 * Parses the main DRS element.
	 * 
	 * @return The interpretation.
	 * @throws UnableToParseException if the element could not be parsed correctly.
	 */
	public Interpretation parse() throws UnableToParseException {
		return new Interpretation(tokens, parseDRS(mainDRS));
	}
	
}
