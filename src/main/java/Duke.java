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
        //get which task number is completed
        int taskDoneNum = getTaskNumber(input);
        Task taskDone = itemList.get(taskDoneNum - 1);
        taskDone.markAsDone();
        System.out.println("Nice! I've marked this task as done:" + '\n' + taskDone.toString());
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

                    if (check.equals("done")) {
                        markTaskDone(input);
                    } else if (check.equals("todo")) {
                        String description = input.substring(5);
                        if (input.length() <= 1) {
                            throw new InvalidDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        addToDo(description);
                    } else if (check.equals("deadline")) {
                        String[] arr = input.split("/");
                        String description = getSecondWord(arr[0]);
                        String time = getSecondWord(arr[1]);
                        if (getSecondWord(arr[1]).length() < 1) {
                            throw new InvalidTimeException("☹ OOPS!!! The timing of a deadline cannot be empty.");
                        } else if (!getFirstWord(arr[1]).equals("by:")) {
                            throw new InvalidTimeException("☹ OOPS!!! Please enter a suitable deadline!");
                        }
                        addDeadline(description, time);
                    } else if (check.equals("event")) {
                        String[] arr = input.split("/");
                        String description = getSecondWord(arr[0]);
                        String time = getSecondWord(arr[1]);
                        if (getSecondWord(arr[1]).length() < 1) {
                            throw new InvalidTimeException("☹ OOPS!!! The timing of an event cannot be empty.");
                        } else if (!getFirstWord(arr[1]).equals("at")) {
                            throw new InvalidTimeException("☹ OOPS!!! Please enter a suitable event timing!");
                        }
                        addEvent(description, time);
                    } else {
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
