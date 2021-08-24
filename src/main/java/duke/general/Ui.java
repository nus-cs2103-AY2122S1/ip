package duke.general;

import duke.error.DukeException;
import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner textInput = new Scanner(System.in);
    private boolean isLoop = true;

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
        return this.isLoop;
    }

    public void setLoop() {
        this.isLoop = false;
    }

}
