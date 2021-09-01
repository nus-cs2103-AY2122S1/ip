package duke;

import java.util.Iterator;
import java.util.List;

public class Ui {
    public Ui() {
        System.out.println("Hey there, I'm Duke! Nice to meet you!");
        System.out.println("I'm a bot build by SpdPnd98, let me know how I can help you!");
        System.out.println("Here are some things I can do now:");
        System.out.println("1. Bye");
        System.out.println("2. List");
        System.out.println("3. Find <search term>");
    }

    protected void showLoadingFile() {
        System.out.println("Loading your commands...");
    }

    protected void showChatting() {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("How can I help you?");
        System.out.print("User: ");
    }

    protected void showNotSupported() {
        System.out.println("This action is not supported yet. Contact the devs for more information.");
    }

    protected void showAddTaskSuccessful(Task task) {
        System.out.println("Got it. Added the following task");
        System.out.println(task);
    }

    protected void showNothingAdded() {
        System.out.println("You haven't asked me to store anything yet... Good! :)");
    }

    protected void showAllTasks(List memory) {
        System.out.println("Here are the tasks in the list:");
        Iterator<Task> itr = memory.iterator();
        for (int i = 1; i <= memory.size(); i++) {
            System.out.println(i + ". " + itr.next());
        }
    }

    protected void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    protected void showDeleted(Task task) {
        System.out.println("Noted. I have removed this task.");
        System.out.println("  " + task);
    }

    protected void showFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
