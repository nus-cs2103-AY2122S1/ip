package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void showWelcome() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke :)\nWhat can I do for you?");
        showLine();
    }

    public void showLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public String readCommand() throws IOException {
        return reader.readLine();
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showExit() {
        System.out.println("Bye. See you again soon! :)");
    }
}
