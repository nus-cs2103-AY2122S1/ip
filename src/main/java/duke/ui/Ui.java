package duke.ui;

import duke.commands.Task;
import duke.data.TaskList;

public class Ui {
    private static final String BORDER = "____________________________________________________________";
    private static final String INTRO = "Hello from\n"
                                        +  " ____        _        \n"
                                        + "|  _ \\ _   _| | _____ \n"
                                        + "| | | | | | | |/ / _ \\\n"
                                        + "| |_| | |_| |   <  __/\n"
                                        + "|____/ \\__,_|_|\\_\\___|\n"
                                        + "Im Duke\nWhat can I do for you?";

    public void PrintIntro() {
        System.out.println(INTRO);
    }

    public void PrintMessage(String message) {
        System.out.println(BORDER);
        System.out.println(message);
        System.out.println(BORDER);
    }

    public void PrintList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(BORDER);
        for (int i = 1; i <= taskList.size(); i++) {
            Task thisTask = taskList.get(i-1);
            String toPrint = String.format("%d. %s", i, thisTask);
            System.out.println(toPrint);
        }
        System.out.println(BORDER);
    }

    public void PrintSpecialTasks(TaskList taskList) {
        String message = taskList.get(taskList.size() - 1).toString();
        int total = taskList.size();
        String newMsg = String.
                format("Got it, I've added this task:\n  %s\nNow you have a total of %d tasks in the list.",
                message, total);
        PrintMessage(newMsg);
    }

    public void PrintDelete(Task deleted, TaskList taskList) {
        String message = deleted.toString();
        int total = taskList.size();
        String newMsg = String.
                format("Noted. I've removed this task:\n  %s\nNow you have a total of %d tasks in the list.",
                        message, total);
        PrintMessage(newMsg);
    }

    public void PrintFind(TaskList matchedTasks) {
        System.out.println(BORDER);
        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 1; i <= matchedTasks.size(); i++) {
            Task thisTask = matchedTasks.get(i-1);
            String toPrint = String.format("%d. %s", i, thisTask);
            System.out.println(toPrint);
        }
        System.out.println(BORDER);
    }
}
