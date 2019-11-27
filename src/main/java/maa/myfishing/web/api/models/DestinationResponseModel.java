package maa.myfishing.web.api.models;

public class DestinationResponseModel {
    private String creator;
    private String id;
    private String imgUrl;
    private String townName;
    private int fishingsCount;

    public DestinationResponseModel() {
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
