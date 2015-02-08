package it.vkaramfilov.librarysystem;

public enum MediaState {
    RENTED("Rented"),
    NOT_RENTED("Not rented");

    private String value;

    private MediaState(String value) {
        this.setValue(value);
    }

    public static MediaState parse(String mediaStateAsString) {
        if (mediaStateAsString == null) {
            throw new IllegalArgumentException("Cannot parse null value to MediaState enumeration value!");
        }

        String trimmedMediaStateString = mediaStateAsString.trim();
        for (MediaState mediaState : MediaState.values()) {
            if (trimmedMediaStateString.equalsIgnoreCase(mediaState.getValue())) {
                return mediaState;
            }
        }

        throw new IllegalArgumentException("There is no media state with provided string representation!");
    }

    public String getValue() {
        return this.value;
    }

    private void setValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Media state enumeration value cannot be null!");
        }

        this.value = value;
    }
}
