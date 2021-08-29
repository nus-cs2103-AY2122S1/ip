package duke;

import duke.tasks.Task;

public class Ui {

    private static String DIVIDER = "_______________________________________________\n";

    public Ui(){}

    public void startMessage() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(DIVIDER
                + "\nHello!\n"
                + LOGO
                + "\nI'm Duke! How can I help you?\n"
                + DIVIDER
        );
    }

    public void byeMessage() {
        System.out.println("Bye! See you next time!\n");
    }

    public void addedMessage(TaskList taskList, Task task) {
        System.out.println("Got it. I've added this task:\n"
                + "\t"
                + task.toString()
                +"\n"
                + "Now you have "
                + taskList.size()
                +" tasks in your list\n"
                + DIVIDER
        );
    }

    public void deleteMessage(TaskList taskList, Task task) {
        System.out.println("Noted. I've removed this task:\n"
                + "\t"
                + task.toString()
                + "\n"
                + "Now you have "
                + taskList.size()
                + " tasks in your list\n"
                + DIVIDER
        );
    }

    public void doneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: \n"
                + "\t"
                + task.toString()
                + "\n"
                + DIVIDER
        );
    }

    public void listTasks(TaskList taskList) {
        System.out.println(taskList.toString()
            + DIVIDER
        );
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void loadErrorMessage() {
        System.out.println("File could not be read.\n"
        + DIVIDER);
    }

    public void errorMessage(DukeException error) {
        System.out.println(error.getMessage()
                + DIVIDER
        );
    }
}