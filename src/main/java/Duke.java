import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    // Colors used to Display Text
    public static final String COLOR_REST = "\u001B[0m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_PURPLE = "\u001B[35m";

    // The General Strings Used by the ChatBot
    private static final String logo = "\n" +
            "\t\t /$$      /$$ /$$                 /$$$$$$$              /$$    \n" +
            "\t\t| $$  /$ | $$| $$                | $$__  $$            | $$    \n" +
            "\t\t| $$ /$$$| $$| $$$$$$$   /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$  \n" +
            "\t\t| $$/$$ $$ $$| $$__  $$ /$$__  $$| $$$$$$$  /$$__  $$|_  $$_/  \n" +
            "\t\t| $$$$_  $$$$| $$  \\ $$| $$  \\ $$| $$__  $$| $$  \\ $$  | $$    \n" +
            "\t\t| $$$/ \\  $$$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$  | $$ /$$\n" +
            "\t\t| $$/   \\  $$| $$  | $$|  $$$$$$/| $$$$$$$/|  $$$$$$/  |  $$$$/\n" +
            "\t\t|__/     \\__/|__/  |__/ \\______/ |_______/  \\______/    \\___/  \n" +
            "                                                               \n";

    private static final String line = "__________________________________________________________________________________________";

    private static final String bye = "\n" +
            "\t  /$$$$$$                            /$$ /$$$$$$$                            /$$\n" +
            "\t /$$__  $$                          | $$| $$__  $$                          | $$\n" +
            "\t| $$  \\__/  /$$$$$$   /$$$$$$   /$$$$$$$| $$  \\ $$ /$$   /$$  /$$$$$$       | $$\n" +
            "\t| $$ /$$$$ /$$__  $$ /$$__  $$ /$$__  $$| $$$$$$$ | $$  | $$ /$$__  $$      | $$\n" +
            "\t| $$|_  $$| $$  \\ $$| $$  \\ $$| $$  | $$| $$__  $$| $$  | $$| $$$$$$$$      |__/\n" +
            "\t| $$  \\ $$| $$  | $$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$| $$_____/          \n" +
            "\t|  $$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$$| $$$$$$$/|  $$$$$$$|  $$$$$$$       /$$\n" +
            "\t\\______/  \\______/  \\______/  \\_______/|_______/  \\____  $$ \\_______/      |__/\n" +
            "\t                                                   /$$  | $$                    \n" +
            "\t                                                  |  $$$$$$/                    \n" +
            "\t                                                   \\______/                     \n";

    // The Global Variables used by the ChatBot
    private static final Scanner cmdReader = new Scanner(System.in);
    private static final ArrayList<Task> LIST = new ArrayList<>();
    private enum LEVEL {START, MIDDLE, END, COMPLETE}

    // Method to Print Greeting
    public static void greeting() {
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        System.out.println(COLOR_BLUE + logo + COLOR_REST);
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        echo("Hello! I'm the WhoBot.", LEVEL.START);
        echo("What can I do for you?", LEVEL.END);
    }

    // Method to Print GoodBye Message
    public static void goodbye() {
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        System.out.println(COLOR_BLUE + bye + COLOR_REST);
        echo("I hope to see you again soon :)", LEVEL.MIDDLE);
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        System.out.println(COLOR_CYAN + line + COLOR_REST);
    }

    //Method to Echo the Given Word
    public static void echo(String answer, LEVEL level) {
        if (level == LEVEL.COMPLETE) {
            System.out.println(COLOR_CYAN + line + COLOR_REST);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_REST + answer);
            System.out.println(COLOR_CYAN + line + COLOR_REST);
        } else if (level == LEVEL.START) {
            System.out.println(COLOR_CYAN + line + COLOR_REST);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_REST + answer);
        } else if (level == LEVEL.END) {
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_REST + answer);
            System.out.println(COLOR_CYAN + line + COLOR_REST);
        } else if (level == LEVEL.MIDDLE) {
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_REST + answer);
        }
    }

    //Method to Add to List
    public static void addToList(String item) {
        if (LIST.add(new Task(item))) {
            echo("I have added this task to the list: \"" + item + "\"", LEVEL.START);
            echo("You now have " + LIST.size() + " task(s) in the list.", LEVEL.END);
        } else {
            echo("I am sorry. The task couldn't be added, please try again.", LEVEL.COMPLETE);
        }
    }

    //Method to Print List
    public static void printList() {
        if (LIST.isEmpty()) {
            echo("There are currently no tasks in your list.", LEVEL.COMPLETE);
            return;
        }
        String listString = "The tasks in your list are: \n";
        int i;
        for (i = 0; i < LIST.size() - 1; i++) {
            listString = listString.concat("\t\t\t" + (i + 1) + ". " + LIST.get(i) + "\n");
        }
        listString = listString.concat("\t\t\t" + (i + 1) + ". " + LIST.get(i));
        echo(listString, LEVEL.COMPLETE);
    }

    // Method to Mark Task as Done
    public static void markAsDone(String ind) {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                LIST.get(index).markAsDone();
                echo("Congrats! I have marked this task complete: \"" + LIST.get(index).getDescription() + "\"", LEVEL.COMPLETE);
            } else {
                echo("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks", LEVEL.COMPLETE);
            }
        } catch (Exception  ex) {
            echo("I didn't get what you meant. Ensure that the command is of the form \"done #index\"", LEVEL.COMPLETE);
        }
    }

    // Method to Mark Task as Not Done
    public static void markAsUndone(String ind) {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                LIST.get(index).markAsUndone();
                echo("I have marked this task incomplete: \"" + LIST.get(index).getDescription() + "\"", LEVEL.COMPLETE);
            } else {
                echo("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks", LEVEL.COMPLETE);
            }
        } catch (Exception  ex) {
            echo("I didn't get what you meant. Ensure that the command is of the form \"undo #index\"", LEVEL.COMPLETE);
        }
    }

    // Method to Remove Task from List
    public static void removeFromList(String ind) {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                Task temp = LIST.remove(index);

                //Check for confirmation before deleting
                echo("Are you sure you want to remove this task: \"" + temp.getDescription() + "\" ? (Yes/No)", LEVEL.COMPLETE);
                String confirm = cmdReader.nextLine().trim();
                if (confirm.toLowerCase(Locale.ROOT).equals("yes")) {
                    echo("I have removed this task from the list: \"" + temp.getDescription() + "\"", LEVEL.START);
                    echo("You now have " + LIST.size() + " task(s) in the list.", LEVEL.END);
                } else {
                    echo("The deletion has been cancelled.", LEVEL.COMPLETE);
                }

            } else {
                echo("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks", LEVEL.COMPLETE);
            }
        } catch (Exception  ex) {
            echo("I didn't get what you meant. Ensure that the command is of the form \"remove #index\"", LEVEL.COMPLETE);
        }
    }

    //Main Method
    public static void main(String[] args) {

        greeting();

        while (true) {
            // Take in the input
            String command;
            System.out.print(COLOR_PURPLE + "> " + COLOR_REST);
            command = cmdReader.nextLine().trim();

            if (command.isBlank()) {
                echo("The input is blank. Please enter something.", LEVEL.COMPLETE);
                continue;
            }

            String[] commandList = command.toLowerCase(Locale.ROOT).split(" ");

            //All commands are taken as case-insensitive
            if (command.toLowerCase(Locale.ROOT).equals("bye") || command.toLowerCase(Locale.ROOT).equals("goodbye")) {
                // If input is bye or goodbye, quits program
                goodbye();
                break;
            } else if (command.toLowerCase(Locale.ROOT).equals("list")) {
                // If input is list, prints list
                printList();
            } else if (commandList.length == 2 && commandList[0].equals("done")) {
                //If input starts with done, mark the specific item in list as done
                markAsDone(commandList[1]);
            } else if (commandList.length == 2 && commandList[0].equals("undo")) {
                //If input starts with undo, mark the specific item in list as not done
                markAsUndone(commandList[1]);
            } else if (commandList.length == 2 && commandList[0].equals("remove")) {
                //If input starts with remove, delete the specific item in list
                removeFromList(commandList[1]);
            } else {
                // Else adds the input line to list
                addToList(command);
            }
        }
    }
}
