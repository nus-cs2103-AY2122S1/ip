package duke.ui;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;
import duke.task.Task;

public class Ui {

    private static final String line =
            "------------------------------------------------------------------------------" +
            "-------------------------------";

    private String wrap(String response) {
        return line
                + "\n\n"
                + "\t" + response
                + "\n\n"
                + line
                + "\n";
    }

    public Ui() {}

    public void greeting(String savedTasks) {
        System.out.println(wrap("Hello! I'm Duke!\n\n"
                + "\t" + savedTasks + "\n\n"
                + "\tWhat can I do for you?")
        );
    }

    public void farewell() {
        System.out.println(wrap("Bye. Hope to see you again soon!"));
    }

    public void reply(String response) {
        System.out.println(wrap(response));
    }

    public void doneMsg(Task task) {
        System.out.println(wrap("Nice! I've marked this task as done:\n"
                + "\t\t" + task.toString()));
    }

    public void deleteMsg(Task task, int taskCount) {
        System.out.println(wrap("Noted. I've removed this task: \n" +
                "\t\t" + task.toString() + "\n" +
                "\tNow you have " + taskCount + " tasks in the list."));
    }

    public void addTaskMsg(Task task, int taskCount) {
        System.out.println(wrap("Got it. I've added this task:\n" +
                "\t\t" + task.toString() + "\n" +
                "\t" + "Now you have " + taskCount + " tasks in the list."));
    }

    public void invalidInput(InvalidInputException e) {
        System.out.println(wrap(e.toString()));
    }

    public void invalidInstruction(InvalidInstructionException e) {
        System.out.println(wrap(e.toString()));
    }

    public void printException(Exception e) {
        System.out.println(wrap(e.toString()));
    }
}
