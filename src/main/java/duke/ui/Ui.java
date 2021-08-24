package duke.ui;

import duke.exception.LoadingException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner sc;

    public Ui() {
        //User input.
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);
        //Start Prompt.
        System.out.println(getPattern("Hello! I'm Duke\n   What can I do for you?"));
    }

    /**
     * Returns the formatted string output.
     *
     * @param task The possible task string representations.
     * @return The desirable output string related to task.
     */
    public String getOutputFrame(String task, int count) {
        String title = "   Got it. I've added this task:\n   ";
        String middle = "  " + task + "\n   ";
        String end = "Now you have " + count + " tasks in the list.";
        return title + middle + end;
    }

    /**
     * Returns formatted string output.
     *
     * @param r The input string.
     * @return Formatted string.
     */
   public String getPattern(String r) {
        StringBuilder result = new StringBuilder();
        StringBuilder curr = new StringBuilder();
        String empty = "   ";
        curr.append("*".repeat(80));
        String out = empty + curr + "\n" + empty + r + "\n" + empty + curr + "\n";
        result.append(out);
        return result.toString();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("   " + "*".repeat(80));
    }

    public void showError(String message) {
        System.out.println("   " + message);
    }

    /**
     * Returns all strings stored with indexing.
     *
     * @return All user's stored strings.
     */
    public void showList(TaskList tasks, int count) {
        StringBuilder curr = new StringBuilder();
        String end = "\n   ";
        String begin = "   Here are the tasks in your list:\n   ";
        curr.append(begin);
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                end = "";
            }
            String out = ((i + 1)) + "." + tasks.elementToString(i) + end;
            curr.append(out);
        }
        System.out.println(curr);
    }

    public void showDelete(Task shouldDelete, TaskList tasks) {
        String title = "   Noted. I've removed this task: \n";
        String out = "     " + shouldDelete.toString() + "\n   ";
        String end = "Now you have " + tasks.getSize() + " tasks in the list.";
        System.out.println(title + out + end);
    }

    public void showTasks(String task, int count) {
        System.out.println(getOutputFrame(task, count));
    }

    public void showLoadingError() {
        LoadingException e = new LoadingException();
        System.out.println(e.getMessage());
    }

    public void showDone(String stringForm) {
        String title = "   Nice! I've marked this task as done: \n";
        String out = "     " + stringForm;
        System.out.println(title + out);
    }

    public void showBye() {
        System.out.println("   " + "Bye, see you soon. ^-^");
    }
}
