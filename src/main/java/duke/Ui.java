package duke;

import java.util.ArrayList;

public class Ui {
    final private String INTRO = "Hello! I'm Duke\n" +
            "What can I do for you today?";

    final private String OUTRO = "Bye. Hope to see you again soon!";

    public void showIntroMessage() {
        System.out.println(INTRO);
    }

    public void showGoodbyeMessage() {
        System.out.println(OUTRO);
    }

    public void showDoneMessage(Task currentTask) {
        System.out.println("Nice! I've marked this task as done:" + "\n" + currentTask.toString());
    }

    public void showListMessage(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task currentTask = list.get(i);
            System.out.println((i + 1) + "." + currentTask.toString());
        }
        System.out.println("You have a total of " + list.size() + " tasks");
    }

    public void showTaskMessage(Task currentTask, ArrayList<Task> list) {
        System.out.format("Got it. I've added this task:\n" + currentTask.toString() + "\n"
                + "Now you have %d tasks in this list.\n", list.size());
    }

    public void showDeleteMessage(Task currentTask, ArrayList<Task> list) {
        System.out.format("Noted. I've removed this task:\n" + currentTask.toString() + "\n"
                + "Now you have %d tasks in the list\n", list.size());
    }

    public void showUnknownMessage() {
        System.out.println("Sorry, unknown command!");
    }

    public void showLoadingError() {
        System.out.println("Your file seems to have issues loading.");
    }

}
