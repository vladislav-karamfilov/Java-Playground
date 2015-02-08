package it.vkaramfilov.librarysystem;

public enum MediaType {
    BOOK("Book"),
    MAGAZINE("Magazine"),
    AUDIO_CD("Audio CD"),
    CD_ROM("CD-ROM"),
    COMPACT_CASSETTE("Compact cassette"),
    VIDEOCASSETTE("Videocassette");

    private String value;

    private MediaType(String value) {
        this.setValue(value);
    }

    public static MediaType parse(String mediaTypeAsString) {
        if (mediaTypeAsString == null) {
            throw new IllegalArgumentException("Cannot parse null value to MediaType enumeration value!");
        }

        String trimmedMediaTypeString = mediaTypeAsString.trim();
        for (MediaType mediaType : MediaType.values()) {
            if (trimmedMediaTypeString.equalsIgnoreCase(mediaType.getValue())) {
                return mediaType;
            }
        }

        throw new IllegalArgumentException("There is no media type with provided string representation!");
    }

    public String getValue() {
        return this.value;
    }

    private void setValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Media type enumeration value cannot be null!");
        }

        this.value = value;
    }
}
