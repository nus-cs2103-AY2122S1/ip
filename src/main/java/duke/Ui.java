package duke;

import duke.task.Task;

public class Ui {

    public Ui() {}

    /**
     * Prints out farewell message.
     */
    public void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Prints out greeting message.
     */
    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
    }

    /**
     * Prints a message to confirm that Duke has read the given file.
     */
    public void fileConfirmation() {
        System.out.println("I have received your file! Added tasks!\n");
    }

    /**
     * Prints a message to warn users that Duke cannot find the given file.
     */
    public void fileNotFoundMsg() {
        System.out.println("I am unable to find your file. " +
                "Check that your 'duketest' file exists," +
                " or that your 'data' folder exists.");
    }

    /**
     * Prints a message to warn users of an unknown error.
     */
    public void ioErrorMsg() {
        System.out.println("Something went wrong!");
    }

    /**
     * Prints a message to confirm that a task has been added to the list.
     *
     * @param task The description of the task.
     * @param size The size of the list.
     */
    public void taskAddedMsg(String task, int size) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + task);
        System.out.println("    Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a message to confirm that a task has been deleted from the list.
     *
     * @param desc The description of the task.
     * @param size The size of the list.
     */
    public void taskDeleteMsg(String desc, int size) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + desc);
        System.out.println("    Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a message to confirm that a task has been marked as done.
     */
    public void taskDoneConfirmation() {
        System.out.println("    Nice! I've marked this task as done:");
    }

    /**
     * Prints various error messages for various error events.
     *
     * @param i The case number.
     * @return The error message relevant to the problem.
     */
    public String taskErrorMsg(int i) {
        switch (i) {
        case 1:
            return "   ☹ OOPS!!! The description of a todo cannot be empty.";
        case 2:
            return "   ☹ OOPS!!! The description of a deadline cannot be empty.";
        case 3:
            return "   ☹ OOPS!!! The description of an event cannot be empty.";
        case 4:
            return "   ☹ OOPS!!! index out of bounds";
        default:
            return "   ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    /**
     * Prints out all the tasks in the list.
     *
     * @param storageList The list storing all the tasks.
     */
    public void displayListContents(StorageList storageList) {
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < storageList.size(); i++) {
            int num = i + 1;
            Task task = storageList.get(i);
            System.out.println("        " + num + "." + task.toString());
        }
    }
}
