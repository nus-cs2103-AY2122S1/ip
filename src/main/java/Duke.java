import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    // Colors used to Display Text
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_RED = "\u001B[31m";

    // The General Strings Used by the ChatBot
    private static final String logo = "\n" +
            "\t\t\t\t\t /$$      /$$ /$$                 /$$$$$$$              /$$\n" +
            "\t\t\t\t\t| $$  /$ | $$| $$                | $$__  $$            | $$\n" +
            "\t\t\t\t\t| $$ /$$$| $$| $$$$$$$   /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$\n" +
            "\t\t\t\t\t| $$/$$ $$ $$| $$__  $$ /$$__  $$| $$$$$$$  /$$__  $$|_  $$_/\n" +
            "\t\t\t\t\t| $$$$_  $$$$| $$  \\ $$| $$  \\ $$| $$__  $$| $$  \\ $$  | $$\n" +
            "\t\t\t\t\t| $$$/ \\  $$$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$  | $$ /$$\n" +
            "\t\t\t\t\t| $$/   \\  $$| $$  | $$|  $$$$$$/| $$$$$$$/|  $$$$$$/  |  $$$$/\n" +
            "\t\t\t\t\t|__/     \\__/|__/  |__/ \\______/ |_______/  \\______/    \\___/\n";

    private static final String line = "________________________________________________________________________________________________________________";

    private static final String bye = "\n" +
            "\t\t\t  /$$$$$$                            /$$ /$$$$$$$                            /$$\n" +
            "\t\t\t /$$__  $$                          | $$| $$__  $$                          | $$\n" +
            "\t\t\t| $$  \\__/  /$$$$$$   /$$$$$$   /$$$$$$$| $$  \\ $$ /$$   /$$  /$$$$$$       | $$\n" +
            "\t\t\t| $$ /$$$$ /$$__  $$ /$$__  $$ /$$__  $$| $$$$$$$ | $$  | $$ /$$__  $$      | $$\n" +
            "\t\t\t| $$|_  $$| $$  \\ $$| $$  \\ $$| $$  | $$| $$__  $$| $$  | $$| $$$$$$$$      |__/\n" +
            "\t\t\t| $$  \\ $$| $$  | $$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$| $$_____/\n" +
            "\t\t\t|  $$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$$| $$$$$$$/|  $$$$$$$|  $$$$$$$       /$$\n" +
            "\t\t\t\\______/  \\______/  \\______/  \\_______/|_______/  \\____  $$ \\_______/      |__/\n" +
            "\t\t\t                                                   /$$  | $$\n" +
            "\t\t\t                                                  |  $$$$$$/\n" +
            "\t\t\t                                                   \\______/\n";

    // The Global Variables used by the ChatBot
    private static final Scanner cmdReader = new Scanner(System.in);
    private static final ArrayList<Task> LIST = new ArrayList<>();
    private enum TYPE {START, MIDDLE, END, COMPLETE, ERROR}

    // Method to Print Greeting
    public static void greeting() {
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_BLUE + logo + COLOR_RESET);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        echo("Hello! I'm the WhoBot.", TYPE.START);
        echo("What can I do for you?", TYPE.END);
    }

    // Method to Print GoodBye Message
    public static void goodbye() {
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_BLUE + bye + COLOR_RESET);
        echo("I hope to see you again soon :)", TYPE.MIDDLE);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
    }

    public static void showHelp() {
        String helpString = "These are the list of commands you can give me:\n";
        helpString += "\t\t\t 1. list -> Prints out the List of Tasks.\n";
        helpString += "\t\t\t 2. todo [#description] -> Adds a ToDo Task to the List\n";
        helpString += "\t\t\t 3. event #description /at #timing -> Adds an Event Task to the List\n";
        helpString += "\t\t\t 4. deadline #description /by #deadline -> Adds a Deadline Task to the List\n";
        helpString += "\t\t\t 5. done #index -> Marks Task at #index in the List as completed\n";
        helpString += "\t\t\t 6. undo #index -> Marks Task at #index in the List as incomplete\n";
        helpString += "\t\t\t 7. delete #index -> Delete Task at #index in the List\n";
        helpString += "\t\t\t 8. bye/goodbye -> Quits the ChatBot";
        echo(helpString, TYPE.COMPLETE);
    }

    //Method to Echo the Given Word
    public static void echo(String answer, TYPE type) {
        if (type == TYPE.COMPLETE) {
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
        } else if (type == TYPE.START) {
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
        } else if (type == TYPE.END) {
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
        } else if (type == TYPE.MIDDLE) {
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
        } else if (type == TYPE.ERROR) {
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RED + answer + COLOR_RESET);
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
        }
    }

    //Method to Print List
    public static void printList() {
        if (LIST.isEmpty()) {
            echo("There are currently no tasks in your list.", TYPE.COMPLETE);
            return;
        }
        String listString = "The tasks in your list are:\n";
        int i;
        for (i = 0; i < LIST.size() - 1; i++) {
            listString = listString.concat("\t\t\t" + (i + 1) + ". " + LIST.get(i) + "\n");
        }
        listString = listString.concat("\t\t\t" + (i + 1) + ". " + LIST.get(i));
        echo(listString, TYPE.COMPLETE);
    }

    // Method to Mark Task as Done
    public static void markAsDone(String ind) throws DukeException {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                LIST.get(index).markAsDone();
                echo("Congrats! I have marked this task complete: \"" + LIST.get(index).getDescription() + "\"", TYPE.COMPLETE);
            } else {
                throw new DukeException("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks");
            }
        } catch (NumberFormatException  ex) {
            throw new DukeException("I didn't get what you meant. Ensure that the command is of the form \"done #index\"");
        }
    }

    // Method to Mark Task as Not Done
    public static void markAsUndone(String ind) throws DukeException {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                LIST.get(index).markAsUndone();
                echo("I have marked this task incomplete: \"" + LIST.get(index).getDescription() + "\"", TYPE.COMPLETE);
            } else {
                throw new DukeException("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks");
            }
        } catch (NumberFormatException  ex) {
            throw new DukeException("I didn't get what you meant. Ensure that the command is of the form \"undo #index\"");
        }
    }

    // Method to Delete Task from List
    public static void deleteFromList(String ind) throws DukeException {
        try {
            int index = Integer.parseInt(ind) - 1;
            if (index < LIST.size()) {
                Task temp = LIST.get(index);

                //Check for confirmation before deleting
                echo("Are you sure you want to delete this task: \"" + temp.getDescription() + "\" ? (Yes/No)", TYPE.COMPLETE);
                System.out.print(COLOR_PURPLE + "> " + COLOR_RESET);
                String confirm = cmdReader.nextLine().trim();
                if (confirm.toLowerCase(Locale.ROOT).equals("yes")) {
                    LIST.remove(index);
                    echo("I have deleted this task from the list: \"" + temp.getDescription() + "\"", TYPE.START);
                    echo("You now have " + LIST.size() + " task(s) in the list.", TYPE.END);
                } else {
                    echo("The deletion has been cancelled.", TYPE.COMPLETE);
                }

            } else {
                throw new DukeException("Oops, The index you gave is out of bound. There are only " + LIST.size() + " tasks");
            }
        } catch (NumberFormatException  ex) {
            throw new DukeException("I didn't get what you meant. Ensure that the command is of the form \"delete #index\"");
        }
    }

    // Method to Add a ToDos type Task into List
    public static void addTODO(String command) throws DukeException {
        try {
            Todo task = new Todo(command.substring(5));
            if (LIST.add(task)) {
                echo("I have added this ToDo Task to the list: \"" + task.getDescription() + "\"", TYPE.START);
                echo("You now have " + LIST.size() + " task(s) in the list.", TYPE.END);
            } else {
                throw new DukeException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Ensure that the command is of the form \"todo #description\". The description can not be empty.");
        }
    }

    // Method to Add a Events type Task into List
    public static void addEvent(String command) throws DukeException {
        try {
            if (!command.contains("/at ")) {
                throw new DukeException("Ensure that the command is of the form \"event #description /at #timing\". The timing must be given.");
            }
            Event task = new Event(command.substring(6));
            if (LIST.add(task)) {
                echo("I have added this Event Task to the list: \"" + task.getDescription() + "\"", TYPE.START);
                echo("You now have " + LIST.size() + " task(s) in the list.", TYPE.END);
            } else {
                throw new DukeException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Ensure that the command is of the form \"event #description /at #timing\". The description can not be empty.");
        }
    }

    // Method to Add a Deadline type Task into List
    public static void addDeadline(String command) throws DukeException {
        try {
            if (!command.contains("/by ")) {
                throw new DukeException("Ensure that the command is of the form \"deadline #description /by #deadline\". The deadline must be given.");
            }
            Deadline task = new Deadline(command.substring(9));
            if (LIST.add(task)) {
                echo("I have added this Deadline Task to the list: \"" + task.getDescription() + "\"", TYPE.START);
                echo("You now have " + LIST.size() + " task(s) in the list.", TYPE.END);
            } else {
                throw new DukeException("I am sorry. The task couldn't be added, please try again.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Ensure that the command is of the form \"deadline #description /by #deadline\". The description can not be empty.");
        }
    }

    //Main Method
    public static void main(String[] args) {

        greeting();

        while (true) {
            // Take in the input
            try {
            String command;
            System.out.print(COLOR_PURPLE + "> " + COLOR_RESET);
            command = cmdReader.nextLine().trim();

                if (command.isBlank()) {
                    throw new DukeException("The input is blank. Please enter something.");
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
                } else if (command.toLowerCase(Locale.ROOT).equals("help")) {
                    // If input is list, prints list
                    showHelp();
                } else if (commandList.length == 2 && commandList[0].equals("done")) {
                    //If input starts with done, mark the specific item in list as done
                    markAsDone(commandList[1]);
                } else if (commandList.length == 2 && commandList[0].equals("undo")) {
                    //If input starts with undo, mark the specific item in list as not done
                    markAsUndone(commandList[1]);
                } else if (commandList.length == 2 && commandList[0].equals("delete")) {
                    //If input starts with delete, delete the specific item in list
                    deleteFromList(commandList[1]);
                } else if (commandList[0].equals("todo")) {
                    //If input starts with todos, add that to list
                    addTODO(command);
                } else if (commandList[0].equals("event")) {
                    //If input starts with todos, add that to list
                    addEvent(command);
                } else if (commandList[0].equals("deadline")) {
                    //If input starts with todos, add that to list
                    addDeadline(command);
                } else {
                    // Else Invalid
                    throw new DukeException("Oops, That's an invalid command. Type in help to get list of possible commands.");
                }
            } catch (DukeException ex) {
                echo(ex.getMessage(), TYPE.ERROR);
            }
        }
    }
}
