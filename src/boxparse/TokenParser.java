package boxparse;

import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import boxparse.interpretation.Token;

/**
 * A parser for the token section of Boxer's XML.
 * 
 * @author Eugenio Ribeiro
 */
public class TokenParser {

	/**
	 * Generates the token map based on the words and POS and named entity tags.
	 * 
	 * @param words The element containing the words.
	 * @param pos The element containing the POS tags.
	 * @param ne The element containing the named entity tags.
	 * @return The token map.
	 */
	public HashMap<String, Token> parse(Element words, Element pos, Element ne) {
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
}
