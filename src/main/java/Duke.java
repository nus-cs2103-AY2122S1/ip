import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        start();
    }

    public static void start() {
        System.out.println("\nInput:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        replyTo(userInput);
    }

    public static void reply(String dukeReply) {
        String lines = "--------------------------------------------------------------------------------------------";
        String newString = lines + "\nOutput: \n" + dukeReply + "\n" + lines + "\n";
        System.out.println(newString);
        start();
    }

    public static void replyTo(String userInput) {
        if (userInput.equals("bye")) {
            reply("Good bye, see you soon!");
            System.exit(0); //Should have no error message, hence 0
        } else if (userInput.equals("list")) {
            String toDoListToPrint = "";
            for (int pos = 0; pos < toDoList.size(); pos++) {
                if (pos == toDoList.size() - 1) {
                    toDoListToPrint = addToStringToPrint(pos, toDoListToPrint);
                } else {
                    toDoListToPrint = addToStringToPrint(pos, toDoListToPrint) + "\n";
                }
            }
            reply(toDoListToPrint);
        } else {
            addToToDoList(userInput);
        }
    }

    public static String addToStringToPrint(int pos, String toDoListToPrint) {
        return toDoListToPrint + (pos + 1) + ". " + toDoList.get(pos);
    }

    public static void addToToDoList(String userInput) {
        toDoList.add(userInput);
        reply("Added: " + userInput);
    }
}
