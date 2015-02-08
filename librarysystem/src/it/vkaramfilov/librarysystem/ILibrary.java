package it.vkaramfilov.librarysystem;

public interface ILibrary {
    String getName();

    void addMedia(IMedia media);

    Iterable<IMedia> getRentedMedias();

    Iterable<IMedia> getNotRentedMedias();

    Iterable<IMedia> getMediasByAuthor(String authorName);
}
