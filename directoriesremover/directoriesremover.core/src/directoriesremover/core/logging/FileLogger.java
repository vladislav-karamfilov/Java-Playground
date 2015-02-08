package directoriesremover.core.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileLogger implements ILogger {
    private String filePath;

    public FileLogger(String filePath) throws IOException {
        this.setFilePath(filePath);
    }

    final public void log(String message) throws IOException {
        try (Writer fileStream = new FileWriter(this.filePath, true)) {
            fileStream.append(message).append(System.lineSeparator());
        }
    }

    public void setFilePath(String filePath) throws IOException {
        File file = new File(filePath);

        if (file.exists() && !file.canWrite()) {
            throw new IOException("File to log is not writable!");
        }

        this.filePath = filePath;
    }
}
