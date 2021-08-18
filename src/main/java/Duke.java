import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        startBot();

        //level 4
        List<Task> toDoList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String message = sc.nextLine();
            try {

                if (message.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                } else if (message.equals("list")) {
                    list(toDoList);

                } else if (taskToCheck(message) != 0) {
                    canTaskBeChecked(taskToCheck(message), toDoList.size());
                    displayCheckedTask(toDoList.get(taskToCheck(message) - 1));

                } else {
                    if (isToDo(message)) {
                        isValidEntry(message, "todo");
                        String taskName = message.substring(message.indexOf(" "));
                        Task newTask = new Todo(taskName);
                        toDoList.add(newTask);
                        System.out.println(add(newTask.displayTask(), toDoList.size()));

                    } else {
                        if (isDeadline(message)) {
                            isValidEntry(message, "deadline");
                            isFormatCorrect(message, "deadline");
                            String taskName = message.substring(message.indexOf(" "), message.indexOf("/"));
                            String temp = message.substring(message.indexOf("/") + 1);
                            String due = temp.substring(temp.indexOf(" ") + 1);
                            Task newTask = new Deadline(taskName, due);
                            toDoList.add(newTask);
                            System.out.println(add(newTask.displayTask(), toDoList.size()));

                        } else if (isEvent(message)) {
                            isValidEntry(message, "event");
                            isFormatCorrect(message, "event");
                            String taskName = message.substring(message.indexOf(" "), message.indexOf("/"));
                            String temp = message.substring(message.indexOf("/") + 1);
                            String due = temp.substring(temp.indexOf(" ") + 1);
                            Task newTask = new Event(taskName, due);
                            toDoList.add(newTask);
                            System.out.println(add(newTask.displayTask(), toDoList.size()));

                        } else {
                            notValid();
                        }
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void startBot() {
        String intro = "Hello! I'm Duke\n" +
                "    What can I do for you?";
        System.out.println(intro);
    }

    public static String add(String add, int size) {
        return "Got it. I've added this task:\n "+ add + "\n Now you have " + size +  " tasks in the list.";
    }

    public static void list(List<Task> list) {
        int order = 1;

        System.out.println("Here are the tasks in your list:");
        for (Task s : list) {
            System.out.println(order++ +"." + s.displayTask());
        }
    }

    public static int taskToCheck(String message) {
        StringBuilder number;
        if (message.length() > 5) {
            String check = message.substring(0, 4);
            if (check.equals("done")) {
                char firstNumber = message.charAt(5);
                number = new StringBuilder(Character.toString(firstNumber));
                int counter = 6;
                while (counter < message.length()) {
                    char next = message.charAt(counter);
                    number.append(next);
                    counter++;
                }
                return Integer.parseInt(number.toString());
            }
        }
        return 0;
    }

    public static void displayCheckedTask(Task item) {
        item.checkOffTask();
        String display = "Nice! I've marked this task as done: \n  " + item.displayTask();
        System.out.println(display);
    }

    public static boolean isDeadline(String message) {
        return message.startsWith("deadline");
    }

    public static boolean isToDo(String message) {
        return message.startsWith("todo");
    }

    public static boolean isEvent(String message) {
        return message.startsWith("event");
    }

    //level 5
    public static void notValid() throws InvalidTaskTypeException {
        throw new InvalidTaskTypeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void isValidEntry(String message, String type) throws DescriptionEmptyException {
        if (message.length() <= type.length() || message.substring(message.indexOf(" ")).isBlank()) {
            throw new DescriptionEmptyException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }
    }

    public static void canTaskBeChecked(int taskIndex, int maxTaskIndex) throws TaskNotFoundException {
        if (taskIndex < 0 || taskIndex > maxTaskIndex) {
            throw new TaskNotFoundException("☹ OH NO!!! The task cannot be found. \n   Please try again.");
        }
    }

    public static void isFormatCorrect(String message, String type) throws IncorrectFormatException {
        if (type.equals("deadline") && !message.contains("/by")) {
            throw new IncorrectFormatException("Input format is incorrect. Please input again in this format : \n" +
                    " <task name> /by <deadline>");
        } else {
            if (type.equals("event") && !message.contains("/at")) {
                throw new IncorrectFormatException("Input format is incorrect. Please input again in this format : \n" +
                        " <event name> /at <event duration>");
            }
        }
    }
}
