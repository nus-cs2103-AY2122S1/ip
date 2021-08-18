import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> itemList = new ArrayList<Task>();

    public static void greet() {
        System.out.println("Hello! I'm Duke" + '\n' + "What can I do for you?");
    }

    public static String getFirstWord(String input) {
        String arr[] = input.split(" ", 2);
        String firstWord = arr[0];
        return firstWord;
    }

    public static String getSecondWord(String input) {
        String arr[] = input.split(" ", 2);
        String secondWord = arr[1].strip();
        return secondWord;
    }

    public static Integer getTaskNumber(String input) {
        String arr[] = input.split(" ", 2);
        Integer taskNumber = Integer.parseInt(arr[1]);
        return taskNumber;
    }

    public static void markTaskDone(String input) {
        try {
            int taskDoneNum = getTaskNumber(input);
            Task taskDone = itemList.get(taskDoneNum - 1);
            taskDone.markAsDone();
            System.out.println("Nice! I've marked this task as done:" + '\n' + taskDone.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! No such task can be marked as done!");
            start();
        }
    }

    public static void deleteTask(String input) {
        try {
            int taskDeleteNum = getTaskNumber(input);
            Task taskToDelete = itemList.get(taskDeleteNum - 1);
            taskToDelete.markUndone();
            System.out.println("Noted. I've removed this task:" + '\n' + taskToDelete.toString());
            itemList.remove(taskDeleteNum - 1);
            printTaskNumber();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! No such task can be deleted!");
            start();
        }
    }

    public static void printItemList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(i + 1 + "." + itemList.get(i).toString());
        }
    }

    public static void printTaskNumber() {
        System.out.println("Now you have " + itemList.size() + (itemList.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    public static void addComplete(Task t) {
        itemList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        printTaskNumber();
    }

    public static void addToDo(String description) {
        Task task = new Todo(description);
        addComplete(task);
    }

    public static void addDeadline(String description, String time) {
        Deadline deadline = new Deadline(description, time);
        addComplete(deadline);
    }

    public static void addEvent(String description, String time) {
        Event event = new Event(description, time);
        addComplete(event);
    }

    public static void start() {

        String input;
        String lineBreak = "========================================================================";

        Scanner in = new Scanner(System.in);
        System.out.println(lineBreak);

        try {
            while (in.hasNextLine()) {
                input = in.nextLine();

                if (input.equals("bye")) {
                    itemList.clear();
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (input.equals("list")) {
                    printItemList();
                    System.out.println(lineBreak);
                } else {
                    String check = getFirstWord(input);

                    switch (check) {
                        case "done":
                            markTaskDone(input);
                            break;
                        case "delete":
                            deleteTask(input);
                            break;
                        case "todo":
                            String description = input.substring(5);
                            if (description.length() < 1) {
                                throw new InvalidDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            addToDo(description);
                            break;
                        case "deadline":
                            String[] arr = input.split("\\(");
                            String deadlineDescription = getSecondWord(arr[0]);
                            String deadlineTime = getSecondWord(arr[1]).substring(0, getSecondWord(arr[1]).length() - 1);
                            if (getSecondWord(arr[1]).length() < 1) {
                                throw new InvalidTimeException("☹ OOPS!!! The timing of a deadline cannot be empty.");
                            } else if (!getFirstWord(arr[1]).equals("by:")) {
                                throw new InvalidTimeException("☹ OOPS!!! Please enter a suitable deadline!");
                            }
                            addDeadline(deadlineDescription, deadlineTime);
                            break;
                        case "event":
                            String[] arr_event = input.split("\\(");
                            String eventDescription = getSecondWord(arr_event[0]);
                            String eventTime = getSecondWord(arr_event[1]).substring(0, getSecondWord(arr_event[1]).length() - 1);
                            if (getSecondWord(arr_event[1]).length() < 1) {
                                throw new InvalidTimeException("☹ OOPS!!! The timing of an event cannot be empty.");
                            } else if (!getFirstWord(arr_event[1]).equals("at:")) {
                                throw new InvalidTimeException("☹ OOPS!!! Please enter a suitable event timing!");
                            }
                            addEvent(eventDescription, eventTime);
                            break;
                        default:
                            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println(lineBreak);
                }
            }
        } catch (InvalidInputException | InvalidTimeException | InvalidDescriptionException e) {
            System.out.println(e.getMessage());
            start();
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please include an appropriate description/time!");
            start();
        }
        return;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        start();
    }
}
