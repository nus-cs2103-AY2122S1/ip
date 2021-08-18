import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *  This is my attempt at Duke.
 * @author Zachary Lau --> I have sought feedback and suggestions from Charlton Tan, Wilbur Fong and Jia Yuan.
 *
 */

public class Duke {
    public Duke() {}

    private static int listCount = 0;
    private static ArrayList<Task> dukeList = new ArrayList<>();
    private static String addedText = "Got it. I've added this task:\n";

    /**
     * Handles the greeting or opening message that is shown to the user before inputs are read.
     */
    public void greet() {
        String greetText = "Hello I'm Duke\nWhat can I do for you?\n";
        System.out.print(greetText);
    }

    /**
     * Handles the exiting or halting of Duke when the user has given the appropriate input.
     */
    public void exit() {
        String exitText = "Bye. Hope to see you again soon!";
        System.out.println(exitText);
    }

    /**
     * Shows all Tasks in the list that the user has given to Duke to store.
     * Tasks are ordered from least recent to most recent. If no tasks have been given to Duke,
     * the appropriate message is shown.
     */
    public void showList() {
        String showListText = "Here are the tasks in your list:";
        String emptyListText = "☹ Oops! Looks like you have no tasks in your list!";
        for (int i = 0; i < dukeList.size(); i++) {
            showListText += "\n" + (i + 1) + "." + dukeList.get(i).toString();
        }
        if (dukeList.isEmpty()) {
            System.out.println(emptyListText);
        } else {
            System.out.println(showListText);
        }
    }

    /**
     * Updates the marking of a certain Task as 'done'.
     * @param input The entire String that the user has input i.e. "done 2".
     * @throws DukeException If an incorrect input is entered.
     */
    public void markDone(String input) throws DukeException{
        int itemNumber;
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the task number you wish to mark as done! Try again :-)");
        }
        String numberInput = input.split(" ", 2)[1];
        try {
            itemNumber = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            throw new DukeException("☹ You may have entered something incorrectly. Try adding a number behind 'done'!");
        }
        String message = "☹ Oops! I cannot seem to find that task number. Try again!";
        if (dukeList.isEmpty()) {
            message = "☹ Oops! Your list is empty! Try adding a Task first!";
        } else if (itemNumber <= dukeList.size()){
            Task targetItem = dukeList.get(itemNumber - 1);
            message = "Nice! I've marked this task as done:\n" + " " + targetItem.toString();
        }
        System.out.println(message);
    }

    /**
     * Adds a Deadline to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "deadline Whatever /by Whenever".
     * @throws DukeException If an incorrect input is entered.
     */
    public void addDeadline(String input) throws DukeException{
        // First check if the user has only input the one word "deadline".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description and the deadline date! Try again :-)");
        }
        // If "deadline" is entered with more words, check information.
        String[] information = input.split(" ", 2);
        String [] description = information[1].split("/by ", 2);

        //In the case where date is not entered.
        if (description.length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the deadline date! Try again :-)");
        }
        Deadline newDL = new Deadline(description[0], description[1]);
        dukeList.add(newDL);
        listCount++;
        System.out.println(addedText + newDL.toString() + "\nNow you have " + listCount + " tasks in the list");
    }

    /**
     * Adds an Event to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "event Here /at There".
     * @throws DukeException If an incorrect input is entered.
     */
    public void addEvent(String input) throws DukeException{
        // First check if the user has only input the one word "event".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description and the event location! Try again :-)");
        }
        // If "event" is entered with more words, check information.
        String[] information = input.split(" ", 2);
        String [] description = information[1].split("/at ", 2);

        //In the case where location is not entered.
        if (description.length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the event location! Try again :-)");
        }
        Event newEV = new Event(description[0], description[1]);
        dukeList.add(newEV);
        listCount++;
        System.out.println(addedText + newEV.toString() + "\nNow you have " + listCount + " tasks in the list");
    }

    /**
     * Adds a Todo to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "todo This task".
     * @throws DukeException If an incorrect input is entered.
     */
    public void addTodo(String input) throws DukeException {
        // First check if the user has only input the one word "todo".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description! Try again :-)");
        }
        //If "todo" is entered with more words, use the information.
        String[] information = input.split(" ", 2);
        Todo newTD = new Todo(information[1]);
        dukeList.add(newTD);
        listCount++;
        System.out.println(addedText + newTD.toString() + "\nNow you have " + listCount + " tasks in the list");
    }

    /**
     * Deletes a specific task that Duke has stored.
     * @param input The entire String that the user has input i.e. "delete 2".
     * @throws DukeException If an incorrect input is entered.
     */
    public void delete(String input) throws DukeException{
        int itemNumber;
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the number of the task you wish to delete! Try again :-)");
        }
        String numberInput = input.split(" ", 2)[1];
        try {
            itemNumber = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            throw new DukeException("You may have entered something incorrectly. Try adding a number behind 'delete'!");
        }
        String message = "☹ Oops! You may have incorrectly entered a number. Try again!";
        if (itemNumber > dukeList.size()) {
            throw new DukeException("☹ Oops! Looks like you are trying to delete something that is not in your list! Try again!");
        }
        Task removed = dukeList.remove(itemNumber - 1);
        System.out.println("Noted. I've removed this task:\n" + removed.toString() + "\nNow you have " + dukeList.size()
                                + " tasks in the list");
    }

    /**
     * Calls the appropriate methods depending on what the user has input.
     */
    public void start() {
        boolean loopStatus = true;
        greet();
        Scanner scanner = new Scanner(System.in);
        while (loopStatus) {
            String input = scanner.nextLine().toLowerCase();
            String firstWord = input.split(" ", 2)[0];
            switch (firstWord) {
                case "bye":
                    loopStatus = false;
                    exit();
                    break;
                case "list":
                    showList();
                    break;
                case "deadline":
                    try {
                        addDeadline(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        delete(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        addEvent(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "todo":
                    try {
                        addTodo(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "done":
                    try {
                        markDone(input);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    //If no cases above are entered, Duke will not understand the command and prompt the user.
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
