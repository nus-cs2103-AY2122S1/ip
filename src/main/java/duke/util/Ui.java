package duke.util;

import duke.task.Task;
import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    private static final String LINE = "----------------------------------------------------";
    private static final String WELCOME_MESSAGE= "Hello! I'm Duke " +
            "What can I do for you?\n\n" + LINE + "-----------------------------\n" +
            "|\tPlease enter one of the following commands:                                  |\n" +
            "|\t1. todo <description> (eg. todo paint)                                       |\n" +
            "|\t2. deadline <description> /by <date> (e.g deadline submit hw /by 2020-01-01) |\n" +
            "|\t3. event <description> /at <date> (e.g event party /at 2020-01-01)           |\n" +
            "|\t4. list - see list of tasks added                                            |\n" +
            "|\t5. delete <task number> (e.g delete 1) - delete a task from list             |\n" +
            "|\t6. done <task number> (e.g done 1) - mark a task in list as done             |\n" +
            "|\t7. bye - exit duke                                                           |\n" +
            LINE + "-----------------------------\n";

    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String showTaskAdded(Task task, int listLength) {
        String output = String.format("added: " + task.toString()
                + "\nNow you have %s tasks in your list" , listLength);
        System.out.println(output);
        return output;
    }

    public String showTaskDeleted(Task task, int listLength) {
        String output = String.format("Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have %s tasks in your list\n" , listLength);
        System.out.println(output);
        return output;
    }

    public String showTaskDone(Task task) {
        String output = "Nice! I've marked this task as done:\n"
                + task.toString();
        System.out.println(output);
        return output;
    }

    public void showTask(Task task, int listPosition) {
        System.out.printf("\t%s." + task.toString() + "%n", listPosition);
    }

    public void showBye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void printError(String message) {
        System.out.println(message);
    }


}
