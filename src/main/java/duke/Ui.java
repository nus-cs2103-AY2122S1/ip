package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    protected Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showStartup() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";

        String greeting = "Hello! I'm Duke.\n" + "What can I do for you? :)";

        String usage = "Usage:\n"
                + "list                                         - show current tasks\n"
                + "todo [Description]                           - add todo\n"
                + "deadline [Description] /by [yyyy-mm-dd]      - add deadline\n"
                + "event [Description] /at [yyyy-mm-dd HH:mm]   - add event\n"
                + "done [Task Number]                           - mark task as done\n"
                + "bye                                          - say goodbye\n";
        System.out.println(logo + greeting + "\n"
                + usage);
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("_______________________________________________________\n");
    }

    public void showLoadingError() {
        System.out.println("I can't locate the file!\n");
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void printTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks!\n");
        } else {
            System.out.println("Here are your tasks!\n" + taskList.toString());
        }
    }

    public void showSearchResult(String filter, TaskList taskList) {
        TaskList searchResult = taskList.findTasks(filter);
        if (searchResult.isEmpty()) {
            System.out.println("Sorry, there are no tasks that match your search!");
        } else {
            System.out.println("Here are the matching tasks in your list:\n"
                    + searchResult.toString());
        }
    }

    public void taskAdded(Task task) {
        System.out.println("Okay! I've added this task:\n    " + task.toString());
    }

    public void showTaskListSize(TaskList taskList){
        System.out.printf("You have %d task(s) in the list.\n\n", taskList.getSize());
    }

    public void taskDeleted(Task task) {
        System.out.println("Okay! I've removed this task:\n    " + task.toString());
    }

    public void taskMarked(Task task) {
        System.out.println("Okay! This task has been marked done:");
        System.out.println("  " + task.toString() + "\n");
    }

    public void showGoodbye() {
        System.out.println("Bye bye!\n");
        scanner.close();
    }


}
