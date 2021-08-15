import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class CustomerHandler extends DefaultHandler {
    private ArrayList<ItemVN> listVN = new ArrayList<>();
    private static final String VNEXPRESS_TAG_NAME = "item";
    private ItemVN itemCurrentVN;

    private boolean isItem;
    private boolean isTitle;
    private boolean isDescription;
    private boolean isPubDate;
    private boolean isLink;
    private boolean isGuid;
    private boolean isComments;

    public ArrayList<ItemVN> getListItemVN (){
        return listVN;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("< " + qName + " >");
        if (qName.equals(VNEXPRESS_TAG_NAME)){
            itemCurrentVN = new ItemVN();
            isItem = true;
        }

        if (isItem){
            switch (qName){
                case ItemVN.TITLE_TAG:
                    isTitle = true;
                    break;
                case ItemVN.DESCRIPTION_TAG:
                    isDescription = true;
                    break;
                case ItemVN.PUB_DATE_TAG:
                    isPubDate = true;
                    break;
                case ItemVN.LINK_TAG:
                    isLink = true;
                    break;
                case ItemVN.GUID_TAG:
                    isGuid = true;
                    break;
                case ItemVN.COMMENTS_TAG:
                    isComments = true;
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("</ " + qName + " >");
        switch (qName){
            case VNEXPRESS_TAG_NAME:
                listVN.add(itemCurrentVN);
                isItem = false;
                break;
            case ItemVN.TITLE_TAG:
                isTitle = false;
                break;
            case ItemVN.DESCRIPTION_TAG:
                isDescription = false;
                break;
            case ItemVN.PUB_DATE_TAG:
                isPubDate = false;
                break;
            case ItemVN.LINK_TAG:
                isLink = false;
                break;
            case ItemVN.GUID_TAG:
                isGuid = false;
                break;
            case ItemVN.COMMENTS_TAG:
                isComments = false;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        super.characters(ch, start, length);
        String value = new String(ch,start,length);
//        System.out.println(value);
        if (isTitle){
            itemCurrentVN.setTitle(value);
        } else if (isDescription){
            itemCurrentVN.setDescription(value);
        }else if (isPubDate){
            itemCurrentVN.setPubDate(value);
        }else if (isLink){
            itemCurrentVN.setLink(value);
        }else if (isGuid){
            itemCurrentVN.setGuid(value);
        }else if (isComments){
            itemCurrentVN.setComments(Integer.parseInt(value));
        }
    }

}
