package duke;

import duke.Duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    protected static final String friendGreeting = "(*^_^*) Friend says: \n";
    private static final String logo
            = " _____       _                    _        \n"
            + "|  ___|  __  _  ___   _,____     | |       \n"
            + "| |_  |/  _|| |/ _ \\ |  __  |____| |       \n"
            + "| __| | /   | |  __/ | / \\  |  __  |       \n"
            + "|_|   |_|   |_|\\____ |_|  |_|______|       \n";

    private Duke duke;

    public Ui(Duke duke) {
        this.duke = duke;
        this.sc = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        System.out.println("Hi there! Start chatting with your new \n" + logo);
        System.out.println("What would you like to do today?");
    }

    public void showGoodbyeMessage() {
        System.out.println(friendGreeting + "See you again, my friend!");
    }

    public void showLoadingError() {
        System.out.println("File not found");
    }

    public void showListLoad() {
        System.out.println("These are your existing tasks!");
        System.out.println(duke.getTasks().printList());
    }

    public void showList() {
        System.out.println(friendGreeting + "Your to-do list has the following tasks: \n");
        System.out.println(duke.getTasks().printList());
    }

    public void showRemoveTask(String removed) {
        System.out.println(friendGreeting + "removed the following task from your to-do list: \n" + removed);
        System.out.println("Now you have " + duke.getTasks().getList().size() + " tasks in the list.");
    }

    public void showAddTask() {
        int listSize = duke.getTasks().getList().size();
        System.out.println(friendGreeting + "added: " + duke.getTasks().getList().get(listSize - 1).toString() + " to your to-do list!");
        System.out.println("Now you have " + listSize + " tasks in the list.");
    }

    public void showSearchResults(ArrayList<Task> resultsArray) {
        System.out.println(friendGreeting + "Here are the matching tasks in your list:");
        int listSize = resultsArray.size();
        for (int i = 0; i < listSize; i++) {
            Task currTask = resultsArray.get(i);
            int index = i + 1;
            System.out.println(index + "." + currTask.toString());
        }
    }

    public void showNoSearchResults() {
        System.out.println(friendGreeting + "There are no tasks that match your search.");
    }

    public void saveList() {
        System.out.println("Saving file");
    }

    public String getUserCommand() {
        String message = "e";
        message = sc.nextLine();
        return message;
    }
}
