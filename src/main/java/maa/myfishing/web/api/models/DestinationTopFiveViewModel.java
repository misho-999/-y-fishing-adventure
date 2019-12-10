package maa.myfishing.web.api.models;

public class DestinationTopFiveViewModel {

    private String creator;
    private String imgUrl;
    private String townName;
    private int fishingsCount;

    public DestinationTopFiveViewModel() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getFishingsCount() {
        return fishingsCount;
    }

    public void setFishingsCount(int fishingsCount) {
        this.fishingsCount = fishingsCount;
    }
}
