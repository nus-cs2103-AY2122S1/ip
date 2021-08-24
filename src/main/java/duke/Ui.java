package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private Parser parser;

    Ui() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        printLine();
        scanner = new Scanner(System.in);
        parser = new Parser();
    }

    public boolean nextLine() {
        String input = scanner.nextLine();
        return parser.parse(input);
    }

    public void showLoadingError() {
        System.out.println("    Error loading oops");
    }

    public void addTaskList(TaskList list) {
        parser.addTaskList(list);
    }
    private void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void addStorage(Storage storage) {
        parser.addStorage(storage);
    }
}

