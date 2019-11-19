package maa.myfishing.data.models;

public enum TypeOfOvernight {
    HOTEL, CAMPER, TENT, WITHOUT;

    @Override
    public String toString() {
        return this.name().substring(0, 1) + this.name().substring(1).toLowerCase();
    }
}
