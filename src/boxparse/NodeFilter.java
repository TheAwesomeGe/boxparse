package boxparse;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The NodeFilter singleton filters node lists to obtain only nodes of a given type.
 *  
 * @author Eugenio Ribeiro
 */
public class NodeFilter {
	/**
	 * The singleton instance.
	 */
	private static NodeFilter instance;
	
	/**
	 * Gets the singleton instance, creating one on the first time.
	 * 
	 * @return The singleton instance.
	 */
	public static NodeFilter getInstance() {
		if(instance == null) {
			instance = new NodeFilter();
		}
		
		return instance;
	}

	
	/**
	 * Selects only the element nodes of a node list. 
	 * 
	 * @param nodes The node list.
	 * @return A list with the element nodes.
	 */
	public List<Element> selectElements(NodeList nodes) {
		List<Element> elements = new ArrayList<Element>();
		
		for(int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				elements.add((Element) node);
			}
		}
		
		return elements;
	}
}
