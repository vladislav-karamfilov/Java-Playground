package it.vkaramfilov;

import it.vkaramfilov.librarysystem.*;

import java.io.*;

public class LibrarySystemDemo {
    private static final String MEDIAS_INPUT_FILE_PATH = "in.txt";
    private static final String MEDIAS_OUTPUT_FILE_PATH = "out.txt";
    private static final String TEST_AUTHOR_NAME = "Test author";
    private static final String TEST_LIBRARY_NAME = "Test library";

    private static Library library = new Library(TEST_LIBRARY_NAME);

    public static void main(String[] args) {
        System.out.println("Library System Demo");
        try {
            readMedias(MEDIAS_INPUT_FILE_PATH);

            writeRentedMedias(MEDIAS_OUTPUT_FILE_PATH);

            writeNotRentedMedias(MEDIAS_OUTPUT_FILE_PATH);

            writeMediasByAuthor(MEDIAS_OUTPUT_FILE_PATH, TEST_AUTHOR_NAME);

            System.out.println("Operations completed successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readMedias(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        try {
            String currentLine = reader.readLine();
            while (currentLine != null) {
                // Gets the media info in format "Title | Author | Type | Release year | (optional) Rented/Not rented"
                String[] mediaInfo = currentLine.split("\\|");

                addMediaToLibrary(mediaInfo[0], mediaInfo[1], mediaInfo[2], mediaInfo[3], mediaInfo.length == 5 ? mediaInfo[4] : null);

                currentLine = reader.readLine();
            }
        } finally {
            reader.close();
        }
    }

    private static void addMediaToLibrary(String title, String author, String type, String releaseYear, String isRented) {
        IAuthor mediaAuthor = new Author(author);
        MediaType mediaType = MediaType.parse(type);
        int mediaReleaseYear = Integer.parseInt(releaseYear.trim());
        MediaState mediaState = MediaState.parse(isRented != null ? isRented : "Not rented");

        IMedia media = new Media(mediaType, mediaAuthor, title, mediaReleaseYear, mediaState);

        library.addMedia(media);
    }

    private static void writeRentedMedias(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
        try {
            writer.println("---Rented medias in library \"" + library.getName() + "\"---");
            for (IMedia media : library.getRentedMedias()) {
                writer.println(media);
            }
        } finally {
            writer.close();
        }
    }

    private static void writeNotRentedMedias(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
        try {
            writer.println("---Not rented medias in library \"" + library.getName() + "\"---");
            for (IMedia media : library.getNotRentedMedias()) {
                writer.println(media);
            }
        } finally {
            writer.close();
        }
    }

    private static void writeMediasByAuthor(String fileName, String authorName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
        try {
            writer.println("---Medias with author \"" + authorName + "\" in library \"" + library.getName() + "\"---");
            Iterable<IMedia> authorMedias = library.getMediasByAuthor(authorName);
            if (authorMedias == null) {
                writer.println("There are no books with author \"" + authorName + "\"");
            } else {
                for (IMedia media : authorMedias) {
                    writer.println(media);
                }
            }
        } finally {
            writer.close();
        }
    }
}
