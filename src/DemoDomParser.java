import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class DemoDomParser {

    private static final String VNEXPRESS_TAM_SU = "https://vnexpress.net/rss/tam-su.rss";
    private static final String VNEXPRESS_TAG_NAME = "item";

    public static void main(String[] args) {
        ArrayList<ItemVN> list = readXML();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    private static ArrayList<ItemVN> readXML(){
        ArrayList<ItemVN> readAll = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(VNEXPRESS_TAM_SU);
            Element element = document.getDocumentElement();
            System.out.println(element.getTagName());
            NodeList listChannel = element.getElementsByTagName(VNEXPRESS_TAG_NAME); // lấy ra các thẻ con
            for (int i = 0; i < listChannel.getLength(); i++) {
                Node node = listChannel.item(i);
//                System.out.println(node.getNodeName());
                NodeList nodeList = node.getChildNodes();
                // tạo vòng lặp để lấy ra các thẻ con trong item
                ItemVN itemVN = new ItemVN();
                for (int j = 0; j < nodeList.getLength(); j++) {
                    Node childNode = nodeList.item(j);
                    if (childNode.getNodeType() != Node.ELEMENT_NODE){
                        continue;
                    }
                    //đảm bảo phải là ELEMENT_NODE
//                        System.out.println(childNode.getNodeName() + " + " + childNode.getNodeType());
//                        System.out.println(childNode.getTextContent());
                    switch (childNode.getNodeName()){
                        case ItemVN.TITLE_TAG:
                            itemVN.setTitle(childNode.getTextContent());
                            break;
                        case ItemVN.DESCRIPTION_TAG:
                            itemVN.setDescription(childNode.getTextContent());
                            break;
                        case ItemVN.PUB_DATE_TAG:
                            itemVN.setPubDate(childNode.getTextContent());
                            break;
                        case ItemVN.LINK_TAG:
                            itemVN.setLink(childNode.getTextContent());
                            break;
                        case ItemVN.GUID_TAG:
                            itemVN.setGuid(childNode.getTextContent());
                            break;
                        case ItemVN.COMMENTS_TAG:
                            itemVN.setComments(Integer.parseInt(childNode.getTextContent()));
                            break;
                    }
                }
                readAll.add(itemVN);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return readAll;
    }
}
