package duke.util;

// import duke packages
import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class Ui {
    private String logo;
    private String line;

    public Ui(String logo, String line) {
        this.logo = logo;
        this.line = line;
    }

    public Ui() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.line = "===========================";
    }

    public void showLoadingError() {
        System.out.println(line);
        System.out.println("oh no.. i seem to have trouble loading the file");
        System.out.println(line);
    }

    public void printList(TaskList cmdList) {
        System.out.println(line);
        for (int i = 0; i < cmdList.getLength(); i++) {
            System.out.println((i + 1) + "." + cmdList.get(i));
        }
        System.out.println(line);
    }

    public void printTaskDone(TaskList cmdList, int i) {
        System.out.println(line);
        System.out.println("congrats on completing this task! :");
        System.out.println("  " + cmdList.get(i));
        System.out.println(line);
    }

    public void printTaskAdded(TaskList cmdList, int i) {
        System.out.println(line);
        System.out.println("noted! ive added this task:");
        System.out.println("  " + cmdList.get(i));
        System.out.println("now you have " + cmdList.getLength() + " tasks in your list");
        System.out.println(line);
    }

    public void printTaskDeleted(TaskList cmdList, int i) {
        System.out.println(line);
        System.out.println("ok, ive deleted this task :");
        System.out.println("  " + cmdList.get(i));
        System.out.println("now you have " + (cmdList.getLength() - 1) + " tasks in your list");
        System.out.println(line);
    }

    public void sayHello() {
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("hello! i'm calico ☺");
        System.out.println("how can i assist you?");
        System.out.println(line);
    }

    public void showDukeError(DukeException e) {
        System.out.println(line);
        System.out.println(e);
        System.out.println(line);
    }

    public void sayGoodbye() {
        System.out.println(line);
        System.out.println("bye friend!");
        System.out.println(line);
    }

    /**
     * Displays successful query of matching tasks.
     *
     * @param results Successful matches.
     */
    public void showMatchingTasks(ArrayList<Task> results) {
        System.out.println(line);
        System.out.println("ive found some matches for your search query:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + "." + results.get(i));
        }
        System.out.println(line);
    }

    /**
     * Displays no matches found
     */
    public void showNoMatch() {
        System.out.println(line);
        System.out.println("im sorry i couldn't find any matches :(");
        System.out.println(line);
    }
}
