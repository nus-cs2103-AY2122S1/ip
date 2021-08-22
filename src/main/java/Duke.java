import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DDL = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String FILE_DIR = "data";
    private static final String FILE_NAME = "duke.txt";


    private static List<Task> items = new ArrayList<>();

    /**
     * Prints a horizontal line.
     */
    private static void printHorizontalLine() {
        System.out.println("----------------------------------------------------");
    }

    /**
     * Prints a message to show users the number of tasks there are in the list.
     *
     * @param index index of items, i.e. the number of tasks
     */
    private static void printTaskNum(int index) {
        System.out.printf("\nNow you have %d tasks in the list.\n", index);
    }

    /**
     * Prints a message to show the task that has been successfully added to the list.
     *
     * @param task task added to items list
     */
    private static void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
    }

    /**
     * Prints the welcome message when the bot is first called.
     */
    private static void printWelcomeMessage() {
        String logo = " ______       ____      __\n"
                + "|   _   \\    /    \\    |  |\n"
                + "|  |_|  /   /  /\\  \\   |  |\n"
                + "|  |_|  \\  /  ____  \\  |  |\n"
                + "|_______/ /__/    \\__\\ |__|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Bai.\n" +
                "What can I do for you?\n\n" +
                "Available commands:\n" +
                "   todo <description> - add todo item\n" +
                "   deadline <description> /by <date> - add a task to be completed by <date>\n" +
                "   event <description> /at <date> - add an event scheduled at <date>\n" +
                "   done <number> - mark task <number> as done\n" +
                "   delete <number> - delete the specified task <number>\n" +
                "   list - display the list of tasks");
        printHorizontalLine();

//        String home = System.getProperty("user.home"); // /Users/xiaoyunwu
//
//        java.nio.file.Path path = java.nio.file.Paths.get(home, "my", "app", "dir"); // /Users/xiaoyunwu/my/app/dir
//
//        boolean directoryExists = java.nio.file.Files.exists(path); // false

    }

    /**
     * Prints the list of tasks the user has currently.
     */
    private static void printList() {
        printHorizontalLine();
        if (items.size() > 0) {
            System.out.println("Here are the tasks in your list:");

            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%d. " + items.get(i) + "\n", i + 1);
            }
        } else {
            System.out.println("You have no tasks in your list.");
        }

        printHorizontalLine();
    }

    /**
     * Marks a task as done.
     *
     * @param idx index of the task in the items array list.
     */
    private static void doneTask(int idx) {
        items.get(idx).markAsDone();

        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(items.get(idx));
        printHorizontalLine();
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param userCommand command string entered by the user.
     */
    private static void addTodo(String userCommand) {
        try {
            Todo newTodo = new Todo(userCommand.substring(5));
            items.add(newTodo);

            printHorizontalLine();
            printAddTask(newTodo);
            printTaskNum(items.size());
            printHorizontalLine();
        } catch (StringIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please add a description for your todo!");
            printHorizontalLine();
        }
    }

    /**
     * Adds a Deadline Task to the items arraylist.
     *
     * @param userCommand command string entered by the user.
     */
    private static void addDeadline(String userCommand) {
        try {
            int byIndex = userCommand.indexOf("/by");
            String by = userCommand.substring(byIndex + 4);
            Deadline newDeadline = new Deadline(userCommand.substring(9, byIndex - 1), by);
            items.add(newDeadline);

            printHorizontalLine();
            printAddTask(newDeadline);
            printTaskNum(items.size());
            printHorizontalLine();

        } catch (StringIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please add a description and/or deadline!");
            printHorizontalLine();
        }
    }

    /**
     * Adds a Event Task to the items arraylist.
     *
     * @param userCommand command string entered by the user.
     */
    private static void addEvent(String userCommand) {
        try {
            int atIndex = userCommand.indexOf("/at");
            String at = userCommand.substring(atIndex + 4);
            Event newEvent = new Event(userCommand.substring(6, atIndex - 1), at);
            items.add(newEvent);


            printHorizontalLine();
            printAddTask(newEvent);
            printTaskNum(items.size());
            printHorizontalLine();
        } catch (StringIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please add a description and/or date for your event!");
            printHorizontalLine();
        }
    }

    /**
     * Deletes a task in the items arraylist.
     *
     * @param deleteIdx index of the task in the items arraylist.
     */
    private static void deleteTask(int deleteIdx) {
        Task taskToDelete = items.get(deleteIdx);

        items.remove(deleteIdx);

        printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToDelete);
        printTaskNum(items.size());
        printHorizontalLine();
    }

    private static void writeToFile(List<Task> tasks) throws IOException {
        // Solution adapted from:
        // https://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path
        File dukeFile = new File(FILE_DIR, FILE_NAME);

        if (!dukeFile.exists()) {
            dukeFile.getParentFile().mkdir();
            dukeFile.createNewFile();
        }

        FileWriter fw = new FileWriter(dukeFile.getAbsoluteFile());

        for (Task t : tasks) {
            fw.write(t.toSaveString() + System.lineSeparator());
        }

        fw.close();
    }

    private static void readFile(String filePath) {
        try {
            File f = new File(filePath);
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String t = sc.nextLine();
                    String[] task = t.split("\\|");
                    switch (task[0]) {
                    case "T":
                        items.add(new Todo(task[2], task[1].equals("1")));
                        break;
                    case "E":
                        items.add(new Event(task[2], task[1].equals("1"), task[3]));
                        break;
                    case "D":
                        items.add(new Deadline(task[2], task[1].equals("1"), task[3]));
                        break;
                    default:
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        printWelcomeMessage();

        readFile("data/duke.txt");

        Scanner sc = new Scanner(System.in);

        while (true) {

            String userCommand = sc.nextLine();

            if (userCommand.replaceAll("\\s+","").equalsIgnoreCase(COMMAND_BYE)) {
                System.out.println("====================================================\n" +
                        "Goodbai. Hope to see you again soon! （ ● ___ ●.）" +
                        "\n====================================================");
                break;
            } else {
                String[] words = userCommand.split(" ");

                try {
                    switch (words[0].toLowerCase()) {
                    case COMMAND_LIST:
                        printList();
                        break;

                    case COMMAND_DONE:
                        int idx = Integer.parseInt(words[1]) - 1;
                        doneTask(idx);
                        writeToFile(items);
                        break;

                    case COMMAND_TODO:
                        addTodo(userCommand);
                        writeToFile(items);
                        break;

                    case COMMAND_DDL:
                        addDeadline(userCommand);
                        writeToFile(items);
                        break;

                    case COMMAND_EVENT:
                        addEvent(userCommand);
                        writeToFile(items);
                        break;

                    case COMMAND_DELETE:
                        int deleteIdx = Integer.parseInt(words[1]) - 1;
                        deleteTask(deleteIdx);
                        writeToFile(items);
                        break;

                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means! D:");
                    }
                } catch (DukeException e) {
                    // unrecognisable input command
                    printHorizontalLine();
                    System.out.println(e);
                    printHorizontalLine();

                } catch (ArrayIndexOutOfBoundsException e) {
                    printHorizontalLine();
                    System.out.println("Invalid input! D:");
                    printHorizontalLine();

                } catch (IndexOutOfBoundsException e) {
                    // error when try to mark an invalid task as done / delete task

                    printHorizontalLine();
                    if (items.size() > 0) {
                        System.out.printf("That task does not exist!\nPlease enter a number from 1 to %d.\n", items.size());
                    } else {
                        System.out.println("You have no tasks in your list to mark as done or delete.");
                    }
                    printHorizontalLine();

                } catch (NumberFormatException e) {
                    // error encountered when command followed by done is not Number e.g. done one
                    printHorizontalLine();
                    System.out.println("Please enter a numeric character to mark your task as done!");
                    printHorizontalLine();
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        }
    }
}
