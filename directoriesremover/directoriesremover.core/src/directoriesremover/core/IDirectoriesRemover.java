package directoriesremover.core;

import java.io.IOException;

public interface IDirectoriesRemover {
    void remove(Iterable<String> directoryNames) throws IOException;
}
