package it.vkaramfilov.librarysystem;

import java.util.Collection;
import java.util.HashSet;

public class Author extends Person implements IAuthor {
    private Collection<IMedia> medias;

    public Author(String name) {
        this(name, new HashSet<IMedia>());
    }

    public Author(String name, Collection<IMedia> medias) {
        super(name);
        this.setMedias(medias);
    }

    public Collection<IMedia> getMedias() {
        return this.medias;
    }

    private void setMedias(Collection<IMedia> medias) {
        if (medias == null) {
            throw new IllegalArgumentException("Author's medias cannot be null!");
        }

        this.medias = medias;
    }

    public void addMedia(IMedia media) {
        if (media == null) {
            throw new IllegalArgumentException("Cannot add media with null value for author's media!");
        }

        this.getMedias().add(media);
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        output.append("Author name: " + this.getName() + " with medias: ");

        if (this.getMedias().isEmpty()) {
            output.append("no medias.");
        }

        output.append(System.lineSeparator());
        for (IMedia media : this.getMedias()) {
            output.append(media + System.lineSeparator());
        }

        return output.toString();
    }

    @Override
    public boolean equals(Object other) {
        try {
            Author otherAuthor = (Author) other;
            return this.getName() == otherAuthor.getName();
        } catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}
