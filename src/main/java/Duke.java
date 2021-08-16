import java.util.Scanner;

public class Duke {
    public Duke() {}
    private static String greetText = "Hello I'm Duke\nWhat can I do for you?\n";
    private static String exitText = "Bye. Hope to see you again soon!";
    private static String dukeLogo = " ____        _        \n"
                                   + "|  _ \\ _   _| | _____ \n"
                                   + "| | | | | | | |/ / _ \\\n"
                                   + "| |_| | |_| |   <  __/\n"
                                   + "|____/ \\__,_|_|\\_\\___|\n";
    private static Task[] dukeList = new Task[100];
    private static int listCount = 0;

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
                showListText.append("\n").append(item.getNumber()).append(".").append(item.getStatusIcon()).append(item.getDescription());
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

    public void start() {
        greet();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            String firstWord = input.split(" ")[0];

            if (firstWord.equals("bye")) {
                exit();
                break;
            } else if (firstWord.equals("list")) {
                showList();
            } else if (firstWord.equals("done")) {
                markDone(input.substring(input.length() -1));
            } else {
                addToList(input);
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
