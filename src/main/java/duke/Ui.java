package duke;

import java.util.Scanner;

/**
 * Class for user interactions
 */
public class Ui {

    private Scanner scanner;
    
    public Ui() {
        this.scanner = new Scanner(System.in);

    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showTaskList(TaskList taskList) {
        System.out.println(taskList.toString());
    }

    public void showGreetMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void echo(String t) {
        System.out.println("-----------------------------------------\n" +
                String.format("%s\n", t) +
                "-----------------------------------------\n");
    }

    public void showExitMessage() {
        String exitMessage =
                "-----------------------------------------\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "-----------------------------------------\n";
        System.out.println(exitMessage);
    }

    public void showAddMessage(Task newTask, TaskList newList) {
        System.out.println("-----------------------------------------\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                String.format("Now you have %s tasks in the list.\n", newList.getLength()) +
                "-----------------------------------------\n");
    }

    public void showMarkDoneMessage(Task markedTask) {
        System.out.println("-----------------------------------------\n" +
                "Nice! I've marked this task as done:\n" +
                markedTask.toString() +
                "-----------------------------------------\n");
    }

    public void showDeleteMessage(Task deletedTask, TaskList newList) {
        System.out.println("-----------------------------------------\n" +
                "Noted. I've removed this task:\n" +
                deletedTask.toString() +
                String.format("Now you have %s tasks in the list.\n", newList.getLength()) +
                "-----------------------------------------\n");
    }
}
