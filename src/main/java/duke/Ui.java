package duke;

import duke.task.Task;

public class Ui {

    public Ui() {}

    public void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
    }

    public void fileConfirmation() {
        System.out.println("I have received your file! Added tasks!\n");
    }

    public void fileNotFoundMsg() {
        System.out.println("I am unable to find your file. " +
                "Check that your 'duketest' file exists," +
                " or that your 'data' folder exists.");
    }

    public void ioErrorMsg() {
        System.out.println("Something went wrong!");
    }

    public void taskAddedMsg(String task, int size) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + task);
        System.out.println("    Now you have " + size + " tasks in the list.");
    }

    public void taskDeleteMsg(String desc, int size) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + desc);
        System.out.println("    Now you have " + size + " tasks in the list.");
    }

    public void taskDoneConfirmation() {
        System.out.println("    Nice! I've marked this task as done:");
    }

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

    public void displayListContents(StorageList storageList) {
        System.out.println("    Here are the tasks in your list:");

        for (int i = 0; i < storageList.size(); i++) {
            int num = i + 1;
            Task task = storageList.get(i);
            System.out.println("        " + num + "." + task.toString());
        }
    }
}
