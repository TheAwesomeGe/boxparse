package boxparse;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import boxparse.exception.UnableToParseException;
import boxparse.interpretation.Interpretation;
import boxparse.interpretation.Token;

/**
 * BoxerParser reads and parses the XML output of Boxer, generating an interpretation in Java.
 * 
 * @author Eugenio Ribeiro
 */
public class BoxerParser {
	
	/**
	 * Gets the document element of the XML file.
	 * 
	 * @param filename The name of the XML file.
	 * @return The document element.
	 * @throws UnableToParseException if the file could not be parsed.
	 */
	private Element getXMLDocument(String filename) throws UnableToParseException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false); // Disables auto validation
		} catch (ParserConfigurationException e) {
			throw new UnableToParseException(e.getMessage());
		}

		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new UnableToParseException(e.getMessage());
		}
		
		Document dom;
		try {
			dom = builder.parse(filename);
		} catch (SAXException | IOException e) {
			throw new UnableToParseException(e.getMessage());
		}
		
		return dom.getDocumentElement();
	}
	
	/**
	 * Generates the token map based on the words and POS and named entity tags.
	 * 
	 * @param words The element containing the words.
	 * @param pos The element containing the POS tags.
	 * @param ne The element containing the named entity tags.
	 * @return The token map.
	 */
	private HashMap<String, Token> generateTokens(Element words, Element pos, Element ne) {
		HashMap<String, Token> tokens = new HashMap<String, Token>();
		
		NodeList wordElements = words.getElementsByTagName("word");
		for(int i = 0; i < wordElements.getLength(); i++) {
			Element word = (Element) wordElements.item(i);
			
			String id = word.getAttribute("xml:id");
			
			tokens.put(id, new Token(id, word.getTextContent()));
		}
		
		NodeList posElements = pos.getElementsByTagName("postag");
		for(int i = 0; i < posElements.getLength(); i++) {
			Element postag = (Element) posElements.item(i);
			
			String id = postag.getAttribute("index");
			
			tokens.get(id).setPOSTag(postag.getTextContent());
		}
		
		NodeList neElements = ne.getElementsByTagName("netag");
		for(int i = 0; i < neElements.getLength(); i++) {
			Element netag = (Element) neElements.item(i);
			
			String id = netag.getAttribute("index");
			
			tokens.get(id).setNETag(netag.getTextContent());
		}
		
		return tokens;
	}

	/**
	 * Generates an interpretation based on the multiple XML sections.
	 * 
	 * @param words The element containing the words.
	 * @param pos The element containing the POS tags.
	 * @param ne The element containing the named entity tags.
	 * @param drs The element containing the main DRS.
	 * @return The interpretation.
	 * @throws UnableToParseException if the main DRS could not be parsed.
	 */
	private Interpretation generateInterpretation(Element words, Element pos, Element ne, Element drs) throws UnableToParseException {
		return (new DRSParser(drs, generateTokens(words, pos, ne))).parse();
	}

	/**
	 * Parses a Boxer XML file and generates an interpretation in Java.
	 * 
	 * @param filename The name of the XML file.
	 * @return The interpretation.
	 * @throws UnableToParseException if the file could not be parsed.
	 */
	public Interpretation parse(String filename) throws UnableToParseException {
		
		NodeList xdrss = getXMLDocument(filename).getElementsByTagName("xdrs");
		
		if(xdrss.getLength() == 0) {
			throw new UnableToParseException("DRS definition not found");
		}
		
		Node xdrs = xdrss.item(0); // TODO: Edit here for multiple XDRSs

		List<Element> mainChildren = NodeFilter.getInstance().selectElements(xdrs.getChildNodes());
		
		if(mainChildren.size() != 4 || !mainChildren.get(0).getNodeName().equals("words")
				|| !mainChildren.get(1).getNodeName().equals("postags")
				|| !mainChildren.get(2).getNodeName().equals("netags")
				|| !Arrays.asList("drs", "merge", "smerge", "alfa").contains(mainChildren.get(3).getNodeName())) {
			throw new UnableToParseException("Invalid DRS definition");
		}
		
		Element words = mainChildren.get(0);
		Element pos = mainChildren.get(1);
		Element ne = mainChildren.get(2);
		Element drs = mainChildren.get(3);
		
		return generateInterpretation(words, pos, ne, drs);
	}
	

	public static void main(String[] args) throws UnableToParseException {
		BoxerParser parser = new BoxerParser();
		
		parser.parse("/Users/ge/Downloads/candc-1.00/test.xml");
	}

}
