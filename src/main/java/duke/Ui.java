package duke;

import java.util.Scanner;

public class Ui {
    Scanner scan;
    private String str;
    private static TaskList listOfTasks;
    private static Storage storage;

    public Ui(TaskList t, Storage s) {
        scan = new Scanner(System.in);
        this.listOfTasks = t;
        this.storage = s;
    }

    public Ui() {
        str = "There has been a loading error";
    }

    public String input() {
        if (scan.hasNextLine()) {
            String str = scan.nextLine();
            return str;
        }
        return "";
    }

    public String showLoadingError() {
        return this.str;
    }
}
