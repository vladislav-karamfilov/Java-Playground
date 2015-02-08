package it.vkaramfilov.librarysystem;

import java.util.Collection;

public interface IAuthor {
    String getName();

    Collection<IMedia> getMedias();

    void addMedia(IMedia media);
}
