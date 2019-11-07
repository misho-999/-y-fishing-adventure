package maa.myfishing.data.models;

public enum SpoolSize {
    onеThousand(1000), twoThousand(2000), threeThousand(3000), fourThousand(4000);

    private final int id;

    SpoolSize(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
