package duke;
import java.util.Scanner;

public class Ui {
    Scanner scanner;
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void init() {
        this.stdout("Hello from\n" + logo);
    }

    public String readInput() {
        return scanner.nextLine().strip();
    }

    public void stderr(String e) {
        System.err.println(e);
    }
    public void stdout(String s) {
        System.out.println(s);
    }
}