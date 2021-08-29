package Duke;

import java.io.*;
import java.util.Scanner;

public class Ui {
    private final BufferedReader in;
    private final PrintWriter out;

    public Ui(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new BufferedReader(
                new InputStreamReader(in));
        this.out = new PrintWriter(out);
    }

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

    public String getNextCommand() throws IOException {
        return this.in.readLine();
    }
}
