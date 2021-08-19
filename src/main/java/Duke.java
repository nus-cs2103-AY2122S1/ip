import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * Arraylist to store the task items.
     */
    static List<Task> items = new ArrayList<>();

    /**
     * A method to print horizontal line.
     */
    public static void printHorizontalLine() {
        System.out.println("----------------------------------------------------");
    }

    /**
     * A method to print a message to show users the number of tasks there are in the list.
     *
     * @param index index of items, i.e. the number of tasks
     */
    public static void printTaskNum(int index) {
        System.out.printf("\nNow you have %d tasks in the list.\n", index);
    }

    /**
     * A method to print a message to show the task that has been successfully added to the list.
     *
     * @param task task added to items list
     */
    public static void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
    }

    /**
     * A method to print the welcome message when the bot is first called.
     */
    public static void printWelcomeMessage() {
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
    }

    /**
     * A method to print the list of tasks the user has currently.
     */
    public static void printList() {
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
     * A method to mark a task as done.
     *
     * @param idx index of the task in the items array list.
     */
    public static void doneTask(int idx) {
        items.get(idx).markAsDone();

        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(items.get(idx));
        printHorizontalLine();
    }

    /**
     * A method to add a Todo task to the list.
     *
     * @param userCommand command string entered by the user.
     */
    public static void addTodo(String userCommand) {
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
     * A method to add a Deadline Task to the items arraylist.
     *
     * @param userCommand command string entered by the user.
     */
    public static void addDeadline(String userCommand) {
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
     * A method to add a Event Task to the items arraylist.
     *
     * @param userCommand command string entered by the user.
     */
    public static void addEvent(String userCommand) {
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
     * A method to delete a task in the items arraylist.
     *
     * @param deleteIdx index of the task in the items arraylist.
     */
    public static void deleteTask(int deleteIdx) {
        Task taskToDelete = items.get(deleteIdx);

        items.remove(deleteIdx);

        printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToDelete);
        printTaskNum(items.size());
        printHorizontalLine();
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userCommand = sc.nextLine();

            // Solution below adapted from https://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
            if (userCommand.replaceAll("\\s+","").equalsIgnoreCase("bye")) {
                System.out.println("====================================================\n" +
                        "Goodbai. Hope to see you again soon! （ ● ___ ●.）" +
                        "\n====================================================");
                break;
            } else if (userCommand.replaceAll("\\s+","").equalsIgnoreCase("list")) {
                printList();
            } else {
                String[] words = userCommand.split(" ");

                try {
                    switch (words[0].toLowerCase()) {
                        case "done":
                                int idx = Integer.parseInt(words[1]) - 1;
                                doneTask(idx);
                            break;

                        case "todo":
                            addTodo(userCommand);
                            break;

                        case "deadline":
                            addDeadline(userCommand);
                            break;

                        case "event":
                            addEvent(userCommand);
                            break;

                        case "delete":
                            int deleteIdx = Integer.parseInt(words[1]) - 1;
                            deleteTask(deleteIdx);

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
                }
            }
        }
    }
}
