package receipe002.thegenius.kyungjoon.receipelist003;

/**
 * Created by kyungjoon on 17. 10. 18.
 */

public class ListViewItem {
    private String image1;
    private String url;
    private String descStr;

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }


    public void setDesc(String desc) {
        descStr = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return this.descStr;
    }
}




