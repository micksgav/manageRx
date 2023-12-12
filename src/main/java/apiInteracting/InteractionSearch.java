import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InteractionSearch {

	public static void main(String[] args) throws Exception {
        String[][] result = getInteractions("vyvanse");
        for (String[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
	
	public static String[][] getInteractions(String drug) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("drugInteractions/" + drug + ".xml"));

        NodeList commentNodes = doc.getElementsByTagName("comment");
        List<String> components = new ArrayList<>();
        for (int i = 0; i < commentNodes.getLength(); i++) {
            String comment = commentNodes.item(i).getTextContent();
            int start = comment.indexOf("to ")+3;
            String search = comment.substring(start);
            int end = search.indexOf(" (");
            String name = search.substring(0, end);
            components.add(name);
        }

        NodeList interactionPairNodes = doc.getElementsByTagName("interactionPair");
        List<String[]> interactions = new ArrayList<>();
        for (int i = 0; i < interactionPairNodes.getLength(); i++) {
            Node interactionPairNode = interactionPairNodes.item(i);
            NodeList interactionConceptNodes = ((Element) interactionPairNode).getElementsByTagName("interactionConcept");
            String drug1 = ((Element) interactionConceptNodes.item(0)).getElementsByTagName("name").item(0).getTextContent();
            String drug2 = ((Element) interactionConceptNodes.item(1)).getElementsByTagName("name").item(0).getTextContent();
            String description = ((Element) interactionPairNode).getElementsByTagName("description").item(0).getTextContent();

            
            boolean firstDrug = true;
            for (int j = 0; j < components.size(); j++) {
                if (drug1.compareTo(components.get(j)) == 0) {
                    firstDrug = false;
                    break;
                }
            }
            
            String interactingDrug = "";
            
            if (firstDrug) {
            	interactingDrug = drug1;
            } else {
            	interactingDrug = drug2;
            }
            
            interactions.add(new String[]{interactingDrug, description});
        }

        String[][] result = new String[interactions.size()][2];
        for (int i = 0; i < interactions.size(); i++) {
            result[i] = interactions.get(i);
        }
        
        return result;
	}


}
