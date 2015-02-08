package directoriesremover.consoleclient;

import directoriesremover.core.DirectoriesRemover;
import directoriesremover.core.IDirectoriesRemover;
import directoriesremover.core.logging.FileLogger;
import directoriesremover.core.logging.ILogger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Main {
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss.SSS";
    private static final String ROOT_DIRECTORY_PATH = "";
    private static final String[] DIRECTORIES_TO_DELETE = new String[]{""};

    public static void main(String[] args) throws IOException {
        ILogger logger = new FileLogger("./log.txt");
        IDirectoriesRemover directoriesRemover = new DirectoriesRemover(ROOT_DIRECTORY_PATH, logger);

        long start = System.nanoTime();
        directoriesRemover.remove(Arrays.asList(DIRECTORIES_TO_DELETE));
        long end = System.nanoTime();

        double elapsed = (double) (end - start) / 1000000000;
        String currentDateTime = new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());
        System.out.format("Removing directories finished at %s and took %f s.", currentDateTime, elapsed);
    }
}
