import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static int index = 0;
    static Task[] items = new Task[100];

    public static void printHorizontalLine() {
        System.out.println("----------------------------------------------------");
    }

    public static void printTaskNum(int index) {
        System.out.printf("\nNow you have %d tasks in the list.\n", index);
    }

    public static void printAddTask(Task task) {
        System.out.println("Got it. I've added this task: \n" + task);
    }

    public static void main(String[] args) {
        String logo = " ______       ____      __\n"
                     + "|   _   \\    /    \\    |  |\n"
                     + "|  |_|  /   /  /\\  \\   |  |\n"
                     + "|  |_|  \\  /  ____  \\  |  |\n"
                     + "|_______/ /__/    \\__\\ |__|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Bai.\n" +
                            "What can I do for you?");
        printHorizontalLine();

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
                printHorizontalLine();
                if (index > 0) {
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i <  index; i++) {
                        System.out.printf("%d. " + items[i] + "\n", i + 1);
                    }
                } else {
                    System.out.println("You have no tasks in your list.");
                }

                printHorizontalLine();
            } else {
                String[] words = userCommand.split(" ");

                try {
                    switch (words[0].toLowerCase()) {
                        case "done":
                                int idx = Integer.parseInt(words[1]) - 1;
                                items[idx].markAsDone();

                                printHorizontalLine();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(items[idx]);
                                printHorizontalLine();
                            break;

                        case "todo":
                            try {
                                Todo newTodo = new Todo(userCommand.substring(5));
                                items[index] = newTodo;
                                index++;

                                printHorizontalLine();
                                printAddTask(newTodo);
                                printTaskNum(index);
                                printHorizontalLine();
                            } catch (StringIndexOutOfBoundsException e) {
                                printHorizontalLine();
                                System.out.println("Please add a description for your todo!");
                                printHorizontalLine();
                            }
                            break;

                        case "deadline":
                            try {
                                int byIndex = userCommand.indexOf("/by");
                                String by = userCommand.substring(byIndex + 4);
                                Deadline newDeadline = new Deadline(userCommand.substring(9, byIndex - 1), by);
                                items[index] = newDeadline;
                                index++;


                                printHorizontalLine();
                                printAddTask(newDeadline);
                                printTaskNum(index);
                                printHorizontalLine();

                            } catch (StringIndexOutOfBoundsException e) {
                                printHorizontalLine();
                                System.out.println("Please add a description and/or deadline!");
                                printHorizontalLine();
                            }
                            break;

                        case "event":
                            try {
                                int atIndex = userCommand.indexOf("/at");
                                String at = userCommand.substring(atIndex + 4);
                                Event newEvent = new Event(userCommand.substring(6, atIndex - 1), at);
                                items[index] = newEvent;
                                index++;

                                printHorizontalLine();
                                printAddTask(newEvent);
                                printTaskNum(index);
                                printHorizontalLine();
                            } catch (StringIndexOutOfBoundsException e) {
                                printHorizontalLine();
                                System.out.println("Please add a description and/or date for your event!");
                                printHorizontalLine();
                            }
                            break;

                        case "delete":
                            int deleteIdx = Integer.parseInt(words[1]) - 1;
                            Task taskToDelete = items[deleteIdx];
                            Task[] tempArr = new Task[100];

                            System.arraycopy(items, 0, tempArr, 0, deleteIdx);
                            System.arraycopy(items, deleteIdx + 1, tempArr, deleteIdx, index - deleteIdx - 1);
                            index--;
                            items = tempArr;

                            printHorizontalLine();
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println(taskToDelete);
                            printTaskNum(index);
                            printHorizontalLine();
                            break;

                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means! D: ");
                    }
                } catch (DukeException e) {
                    // unrecognisable input command
                    printHorizontalLine();
                    System.out.println(e);
                    printHorizontalLine();

                } catch (ArrayIndexOutOfBoundsException e) {
                    // error when try to mark an invalid task as done / delete task

                    printHorizontalLine();
                    if (index > 0) {
                        System.out.printf("Invalid input! D: Please enter a number from 1 to %d.\n", index);
                    } else {
                        System.out.println("You have no tasks in your list to mark as done or delete.");
                    }

                    printHorizontalLine();

                } catch (NullPointerException e) {
                    // error encountered when trying to mark a null task as done
                    printHorizontalLine();
                    System.out.println("Uh oh! No such task exists.");
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
