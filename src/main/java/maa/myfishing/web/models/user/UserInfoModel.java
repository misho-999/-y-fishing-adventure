package maa.myfishing.web.models.user;

import maa.myfishing.web.models.destination.DestinationDetailsViewModel;

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
