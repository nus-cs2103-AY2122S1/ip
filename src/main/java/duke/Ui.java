package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Ui {
    private final BufferedReader in;
    private final PrintWriter out;

    /**
     * Handles user interaction. I/O dependencies are exposed for injection in tests.
     * @param in source of user input.
     * @param out output to respond to user input.
     */
    public Ui(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Displays default Duke startup banner.
     */
    public void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.println("Hello from\n" + logo);
        out.println("Hello! I'm Duke.Duke");
        out.println("What can I do for you?");
        out.flush();
    }

    /**
     * Prints tasks found from find query
     * @param tasks tasks found by taskList
     */
    public void printFind(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads next user input.
     * @return user input in string format.
     * @throws IOException issue found in reading file.
     */
    public String getNextCommand() throws IOException {
        return this.in.readLine();
    }
}
