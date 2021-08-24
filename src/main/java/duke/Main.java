package duke;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
