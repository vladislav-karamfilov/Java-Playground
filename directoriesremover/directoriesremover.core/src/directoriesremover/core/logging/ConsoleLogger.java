package directoriesremover.core.logging;

public class ConsoleLogger implements ILogger {
    final public void log(String message) {
        System.out.println(message);
    }
}
