package directoriesremover.core;

import directoriesremover.core.logging.ILogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirectoriesRemover implements IDirectoriesRemover {
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss.SSS";

    private String rootDirectoryPath;
    private ILogger logger;

    public DirectoriesRemover(String rootDirectoryPath) throws FileNotFoundException {
        this(rootDirectoryPath, null);
    }

    public DirectoriesRemover(String rootDirectoryPath, ILogger logger) throws FileNotFoundException {
        this.setRootDirectoryPath(rootDirectoryPath);
        this.setLogger(logger);
    }

    public void remove(Iterable<String> directoryNames) throws IOException {
        Files.walkFileTree(Paths.get(this.rootDirectoryPath), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                String currentDirectoryName = path.getFileName().toString();
                for (String directoryName : directoryNames) {
                    if (directoryName.equals(currentDirectoryName)) {
                        deleteAndLog(path.toFile());

                        return FileVisitResult.SKIP_SUBTREE;
                    }
                }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path path, IOException e) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void deleteAndLog(File file) throws IOException {
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles == null) {
                if (this.logger != null) {
                    String currentDateTime = new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());
                    this.logger.log(String.format("%s - couldn't get sub files of '%s'", currentDateTime, file));
                }
            } else {
                for (File subFile : subFiles) {
                    deleteAndLog(subFile);
                }
            }
        }

        boolean deleted = file.delete();

        if (this.logger != null) {
            String currentDateTime = new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());
            String message = String.format(
                    "%s - %s '%s'",
                    currentDateTime,
                    deleted ? "successfully deleted" : "couldn't delete",
                    file);
            this.logger.log(message);
        }
    }

    private void setRootDirectoryPath(String rootDirectoryPath) throws FileNotFoundException {
        File rootDirectory = new File(rootDirectoryPath);
        if (!rootDirectory.exists()) {
            throw new FileNotFoundException("Root directory not found!");
        }

        if (!rootDirectory.isDirectory()) {
            throw new InvalidParameterException("The provided path is not a directory path!");
        }

        this.rootDirectoryPath = rootDirectoryPath;
    }

    private void setLogger(ILogger logger) {
        this.logger = logger;
    }
}
