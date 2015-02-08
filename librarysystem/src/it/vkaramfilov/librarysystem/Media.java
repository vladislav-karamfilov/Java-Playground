package it.vkaramfilov.librarysystem;

public class Media implements IMedia {
    private static final int TITLE_MIN_LENGTH = 2;

    private MediaType type;
    private IAuthor author;
    private String title;
    private int releaseYear;
    private MediaState state;

    public Media(MediaType type, IAuthor author, String title, int releaseYear) {
        this(type, author, title, releaseYear, MediaState.NOT_RENTED);
    }

    public Media(MediaType type, IAuthor author, String title, int releaseYear, MediaState state) {
        this.setType(type);
        this.setAuthor(author);
        this.setTitle(title);
        this.setReleaseYear(releaseYear);
        this.setState(state);
    }

    public MediaType getType() {
        return this.type;
    }

    private void setType(MediaType type) {
        if (type == null) {
            throw new IllegalArgumentException("Media's type cannot be null!");
        }

        this.type = type;
    }

    public IAuthor getAuthor() {
        return this.author;
    }

    private void setAuthor(IAuthor author) {
        if (author == null) {
            throw new IllegalArgumentException("Media's author cannot be null!");
        }

        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    private void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Media's title cannot be null!");
        }

        String trimmedTitle = title.trim();
        if (trimmedTitle.length() < TITLE_MIN_LENGTH) {
            throw new IllegalArgumentException("Media's title cannot be less than " + TITLE_MIN_LENGTH + " characters long!");
        }

        this.title = trimmedTitle;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    private void setReleaseYear(int releaseYear) {
        if (releaseYear < 0) {
            throw new IllegalArgumentException("Media's release year cannot be negative!");
        }

        this.releaseYear = releaseYear;
    }

    public MediaState getState() {
        return this.state;
    }

    public void setState(MediaState state) {
        if (state == null) {
            throw new IllegalArgumentException("Media's state cannot be null!");
        }

        this.state = state;
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();

        output.append("Title: " + this.getTitle() + System.lineSeparator());
        output.append("Author: " + this.getAuthor().getName() + System.lineSeparator());
        output.append("Type: " + this.getType().getValue() + System.lineSeparator());
        output.append("Release year: " + this.getReleaseYear() + System.lineSeparator());
        output.append("State: " + this.getState().getValue() + System.lineSeparator());

        return output.toString();
    }

    //// Can be used when storing medias with the same title is not allowed (using HashSet<Media> in the Library class).
//    @Override
//    public boolean equals(Object other) {
//        try {
//            Media otherMedia = (Media) other;
//            return this.getTitle() == otherMedia.getTitle();
//        } catch (ClassCastException ex) {
//            return false;
//        }
//    }
//
//    @Override
//    public int hashCode() {
//        return this.getTitle().hashCode();
//    }
}
