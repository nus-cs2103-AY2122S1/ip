package duke.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static final String LINE = "\t************************************************************";
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String errorMessage) {
        System.out.println("\t" + errorMessage);
    }

    public void showWelcome() {
        System.out.println("Hello from");
        System.out.println(LOGO);
        showLine();
        print("\033[3m*booting up......*\033[0m", "I'm the Hui Zhuan Bot v2.0!", "What do you want me to do?");
        showLine();
    }

    public String readCommand() throws IOException {
        return READER.readLine();
    }

    public void cleanup() throws IOException {
        READER.close();
    }

    public void print(String...input) {
        for (String out : input) {
            System.out.println("\t" + out);
        }
    }
}
