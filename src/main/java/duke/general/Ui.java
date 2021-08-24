package duke.general;

import duke.error.DukeException;
import duke.general.Tasklist;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner textInput = new Scanner(System.in);
    private boolean loop = true;

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public String readInput() {
        return textInput.nextLine();
    }

    public void addResponse(Task t, int size) {
        t.addResponse(size);
    }

    public void doneResponse(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    public void deleteResponse(Task t, Tasklist list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in your list.");
    }

    public void byeResponse() {
        System.out.println("Bye bye. Duke going to sleep now.");
    }

    /**
     * Shows the users what tasks in the tasklist matches their keyword.
     * If there are no tasks in the list, informs the users of not finding any matches
     *
     * @param list List of tasks that have matches
     */
    public void findResponse(ArrayList<Task> list) {
        if (list.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= list.size(); i++) {
                Task task = list.get(i - 1);
                System.out.println(i + "." + task.toString());
            }
        } else {
            System.out.println("No matching tasks have been found :(");
        }
    }

    public void showList(Tasklist t) {
        try {
            if (t.size() == 0) {
                throw new DukeException("The list is empty!!");
            }
            System.out.println("Here are your tasks:");
            for (int i = 1; i <= t.size(); i++) {
                Task task = t.get(i - 1);
                System.out.println(i + "." + task.toString());
            }
        } catch (DukeException e) {
            System.out.println(e.toString().split(" ", 2)[1]);
        }
    }

    public boolean getLoop() {
        return this.loop;
    }

    public void stopLoop() {
        this.loop = false;
    }

}
