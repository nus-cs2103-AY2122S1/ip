import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    protected TaskManager taskManager = new TaskManager();

    public void run() {
        taskManager.run();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
