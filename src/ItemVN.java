public class ItemVN {
    public static final String TITLE_TAG = "title";
    public static final String DESCRIPTION_TAG = "description";
    public static final String PUB_DATE_TAG = "pubDate";
    public static final String LINK_TAG = "link";
    public static final String GUID_TAG = "guid";
    public static final String COMMENTS_TAG = "slash:comments";

    private String title;
    private String description;
    private String pubDate;
    private String link;
    private String guid;
    private int comments;

    @Override
    public String toString() {
        return "ItemVN{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", link='" + link + '\'' +
                ", guid='" + guid + '\'' +
                ", comments=" + comments +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
