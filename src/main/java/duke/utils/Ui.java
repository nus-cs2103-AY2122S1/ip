package duke.utils;

import java.util.Scanner;

public class Ui {
    private static final String GREETING = "Hi, I'm Duke, your personal assistant!\n";
    private static final String FAREWELL =  "Bye from Duke!";

    public void printTasks(TaskList taskList) {
        System.out.println(taskList);
    }

    public void greetUser() {
        System.out.println(GREETING);
    }

    public void farewellToUser() {
        System.out.println(FAREWELL);
    }

    public void displayError(Exception e) {
        System.out.println(e.getMessage());
    }

    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}
