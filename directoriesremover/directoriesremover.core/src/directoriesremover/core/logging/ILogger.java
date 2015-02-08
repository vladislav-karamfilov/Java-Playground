package directoriesremover.core.logging;

import java.io.IOException;

public interface ILogger {
    void log(String message) throws IOException;
}
