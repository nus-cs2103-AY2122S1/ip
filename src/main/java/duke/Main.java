package duke;

import java.io.IOException;

/**
 * Entry point of the Duke application.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
