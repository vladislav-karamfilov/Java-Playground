package it.vkaramfilov.librarysystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Library implements ILibrary {
    private static final int NAME_MIN_LENGTH = 2;

    private String name;
    private Collection<IMedia> medias;
    private Map<String, IAuthor> authors;

    public Library(String name) {
        this(name, new ArrayList<>());
    }

    public Library(String name, Collection<IMedia> medias) {
        this.setName(name);
        this.setMedias(medias);
        this.authors = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Library name cannot be null!");
        }

        String trimmedName = name.trim();
        if (trimmedName.length() < NAME_MIN_LENGTH) {
            throw new IllegalArgumentException("Library name cannot be less than " + NAME_MIN_LENGTH + " characters long!");
        }

        this.name = trimmedName;
    }

    public void addMedia(IMedia media) {
        if (media == null) {
            throw new IllegalArgumentException("Library media cannot be null!");
        }

        this.addMediaToAuthor(media);
        this.medias.add(media);
    }

    public Iterable<IMedia> getRentedMedias() {
        Collection<IMedia> rentedMedias = new ArrayList<>();

        for (IMedia media : this.medias) {
            if (media.getState() == MediaState.RENTED) {
                rentedMedias.add(media);
            }
        }

        return rentedMedias;
    }

    public Iterable<IMedia> getNotRentedMedias() {
        Collection<IMedia> notRentedMedias = new ArrayList<>();

        for (IMedia media : this.medias) {
            if (media.getState() == MediaState.NOT_RENTED) {
                notRentedMedias.add(media);
            }
        }

        return notRentedMedias;
    }

    public Iterable<IMedia> getMediasByAuthor(String authorName) {
        if (this.authors.containsKey(authorName)) {
            return this.authors.get(authorName).getMedias();
        }

        return null;
    }

    private void setMedias(Collection<IMedia> medias) {
        if (medias == null) {
            throw new IllegalArgumentException("Library medias cannot be null!");
        }

        this.medias = medias;
    }

    private void addMediaToAuthor(IMedia media) {
        if (!this.authors.containsKey(media.getAuthor().getName())) {
            this.authors.put(media.getAuthor().getName(), media.getAuthor());
        }

        this.authors.get(media.getAuthor().getName()).addMedia(media);
    }
}
