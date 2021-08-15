import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

public class DemoSaxParser {
    private static final String VNEXPRESS_TAM_SU = "https://vnexpress.net/rss/tam-su.rss";

    public static void main(String[] args) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            CustomerHandler customerHandler = new CustomerHandler();
            saxParser.parse(VNEXPRESS_TAM_SU,customerHandler);
            ArrayList<ItemVN> vnArrayList = customerHandler.getListItemVN();
            for (ItemVN itemVN:
                 vnArrayList) {
                System.out.println(itemVN.toString());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
