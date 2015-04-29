package boxparse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

public class BoxerParser {
	
	private Element getXMLDocument(String filename) throws UnableToParseException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
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
	
	private List<Node> selectElements(NodeList nodes) {
		List<Node> elements = new ArrayList<Node>();
		
		for(int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				elements.add(node);
			}
		}
		
		return elements;
	}

	public Interpretation parse(String filename) throws UnableToParseException {
		
		NodeList xdrss = getXMLDocument(filename).getElementsByTagName("xdrs");
		
		if(xdrss.getLength() == 0) {
			throw new UnableToParseException("DRS definition not found");
		}
		
		Node xdrs = xdrss.item(0); // TODO: Edit here for multiple XDRSs
		

		List<Node> mainChildren = selectElements(xdrs.getChildNodes());
		
		if(mainChildren.size() != 4 || !mainChildren.get(0).getNodeName().equals("words")
				|| !mainChildren.get(1).getNodeName().equals("postags")
				|| !mainChildren.get(2).getNodeName().equals("netags")
				|| !Arrays.asList("drs", "merge", "smerge", "alfa").contains(mainChildren.get(3).getNodeName())) {
			throw new UnableToParseException("Invalid DRS definition");
		}
		
		Node words = mainChildren.get(0);
		Node pos = mainChildren.get(1);
		Node ne = mainChildren.get(2);
		Node drs = mainChildren.get(3);
		



			

		Interpretation interpretation = new Interpretation();
		return interpretation;
	}
	
	public static void main(String[] args) throws UnableToParseException {
		BoxerParser parser = new BoxerParser();
		
		parser.parse("/Users/ge/Downloads/candc-1.00/test.xml");
	}

}
