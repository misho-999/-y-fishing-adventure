package maa.myfishing.web.models;

import maa.myfishing.data.models.Destination;

import java.util.List;

public class UserInfoModel {
    private List<DestinationDetailsViewModel> destinations;

    public UserInfoModel() {
    }

    public List<DestinationDetailsViewModel> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationDetailsViewModel> destinations) {
        this.destinations = destinations;
    }
}
