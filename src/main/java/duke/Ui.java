package duke;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine() + " ";
    }

    public void close() {
        this.sc.close();
    }
}
