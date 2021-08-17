import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public Duke() {}
    private static int listCount = 0;
    private static Task[] dukeList = new Task[100];
    private static String greetText = "Hello I'm Duke\nWhat can I do for you?\n";
    private static String exitText = "Bye. Hope to see you again soon!";
    private static String addedText = "Got it. I've added this task:\n";


    private static String listLengthText = "\nNow you have " + listCount + " tasks in the list";
    public void greet() {
        System.out.print(greetText);
    }

    public void addToList(String input) {
        dukeList[listCount] = new Task(input);
        listCount++;
        System.out.println("added: " + input);
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
                                        + item.getStatusIcon() + " " + item.getDescription());
            }
        }
        System.out.println(message);
    }

    public void addDeadline(String description, String by){
        Deadline newDL = new Deadline(description, by);
        dukeList[listCount] = newDL;
        listCount++;
        System.out.println(addedText + newDL.toString() + listLengthText);
    }

    public void addEvent(String description, String at) {
        Event newEV = new Event(description, at);
        dukeList[listCount] = newEV;
        listCount++;
        System.out.println(addedText + newEV.toString()+ listLengthText);
    }

    public void addTodo(String description) {
        Todo newTD = new Todo(description);
        dukeList[listCount] = newTD;
        listCount++;
        System.out.println(addedText + newTD.toString() + listLengthText);
    }

    public void start() {
        greet();
        label:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            String firstWord = input.split(" ")[0].toLowerCase();
            switch (firstWord) {
                case "bye":
                    exit();
                    break label;
                case "list":
                    showList();
                    break;
                case "deadline":
                    String[] deadDesc = input.split(" ", 2)[1].split("/by ", 2);
                    addDeadline(deadDesc[0], deadDesc[1]);
                    break;
                case "event":
                    String[] eventDesc = input.split(" ", 2)[1].split("/at ", 2);
                    addEvent(eventDesc[0], eventDesc[1]);
                    break;
                case "todo":
                    String description = input.split(" ", 2)[1];
                    addTodo(description);
                    break;
                case "done":
                    markDone(input.substring(input.length() - 1));
                    break;
                default:
                    System.out.println("Oops! Looks like you have entered something incorrectly. Try again :-)");
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
