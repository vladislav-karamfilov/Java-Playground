package it.vkaramfilov.librarysystem;

public interface IMedia {
    MediaType getType();

    IAuthor getAuthor();

    String getTitle();

    int getReleaseYear();

    MediaState getState();

    void setState(MediaState state);
}
