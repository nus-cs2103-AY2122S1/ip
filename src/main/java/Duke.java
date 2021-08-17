import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public Duke() {}
    private static boolean loopStatus;
    private static int listCount = 0;
    private static Task[] dukeList = new Task[100];
    private static String greetText = "Hello I'm Duke\nWhat can I do for you?\n";
    private static String exitText = "Bye. Hope to see you again soon!";
    private static String addedText = "Got it. I've added this task:\n";
    public void greet() {
        System.out.print(greetText);
    }

    public void exit() {

        System.out.println(exitText);
    }

    public void showList() {
        boolean isEmpty = false;
        StringBuilder showListText = new StringBuilder("Here are the tasks in your list:");
        String emptyListText = "Oops! Looks like you have no tasks in your list!";
        for (Task item : dukeList) {
            if (item != null) {
                isEmpty = true;
                showListText.append("\n").append(item.getNumber()).append(".").append(item.toString());
            }
        }
        if (!isEmpty) {
            System.out.println(emptyListText);
        } else {
            System.out.println(showListText);
        }
    }

    public void markDone(String i) {
        int itemNumber = Integer.parseInt(i);
        String message = "Oops! You may have incorrectly entered a number. Try again!";
        for (Task item : dukeList) {
            if (item != null && (item.getNumber() == itemNumber)) {
                item.markAsDone();
                message = ("Nice! I've marked this task as done:\n"
                                        + " " + item.toString());
            }
        }
        System.out.println(message);
    }

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
        dukeList[listCount] = newDL;
        listCount++;
        System.out.println(addedText + newDL.toString() + "\nNow you have " + listCount + " tasks in the list");
    }

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
        dukeList[listCount] = newEV;
        listCount++;
        System.out.println(addedText + newEV.toString() + "\nNow you have " + listCount + " tasks in the list");
    }

    public void addTodo(String input) throws DukeException {
        // First check if the user has only input the one word "todo".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description! Try again :-)");
        }
        //If "todo" is entered with more words, use the information.
        String[] information = input.split(" ", 2);
        Todo newTD = new Todo(information[1]);
        dukeList[listCount] = newTD;
        listCount++;
        System.out.println(addedText + newTD.toString() + "\nNow you have " + listCount + " tasks in the list");
    }

    public void start() {
        loopStatus = true;
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
                    markDone(input.substring(input.length() - 1));
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
