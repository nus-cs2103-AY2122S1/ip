import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Exception.UnknownException;
import Exception.UnclearInstructionException;

public class Duke {
    private static List<Task> list = new ArrayList<>();

    private static void greet() {
        System.out.println("Hello! I'm Duke created by Tianyue.\n" +
                "What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        if (list.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
        }
    }

    private static void list() {
        if (list.isEmpty()) {
            System.out.println("You have no task for now.");
            return;
        }

        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d. %s",
                    i + 1, list.get(i)));
        }
    }

    private static void setAsDone(int index) {
        if (index > list.size()) {
            throw new IndexOutOfBoundsException("The input task number is too big.");
        }
        if (index < 1) {
            throw new IndexOutOfBoundsException("The input task number is non-positive.");
        }
        list.get(index - 1).maskAsDone();

        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + list.get(index - 1));
    }

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        for (int i = 0; i < items.length; i++) {
            if (inputStr.contains(items[i])) {
                return true;
            }
        }
        return false;
    }

    public static String extractTaskDescription(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 2);
        String task_type = contents[0];
        String description = "";

        if (contents.length != 2) {
            throw new UnclearInstructionException("☹ OOPS!!! The description of a " + task_type + " cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");

        if (task_type.equals("deadline") || task_type.equals("event")) {
            description = text.substring(istart, iend);
        }

        if (task_type.equals("todo")) {
            description = text.substring(istart);
        }

        if (description.equals("")) {
            throw new UnclearInstructionException("☹ OOPS!!! The description of a " + task_type + " cannot be empty.");
        }
        return description;
    }

    public static String extractTaskTime(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 2);
        String task_type = contents[0];
        if (contents.length != 2) {
            throw new UnclearInstructionException("☹ OOPS!!! The description of a " + task_type + " cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");
        String time = text.substring(iend + 4);

        if (time.equals("")) {
            throw new UnclearInstructionException("☹ OOPS!!! The time of a " + task_type + " cannot be empty.");
        }
        return time;
    }

    private static void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index > list.size()) {
            throw new IndexOutOfBoundsException("The input task number is too big.");
        }
        Task removedTask = list.remove(index - 1);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
    }

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        String[] keywords = {"done", "bye", "list", "bye", "deadline", "event", "todo", "delete"};

        try {
        while (!text.isEmpty()) {


                if (!stringContainsItemFromList(text, keywords)) {
                    throw new UnknownException();
                } else {
                    //mark as done
                    if (text.startsWith("done")) {
                        char last_digit = text.charAt(text.length() - 1);
                        int index = Character.getNumericValue(last_digit);
                        setAsDone(index);
                        text = scanner.nextLine();
                    }

                    //list
                    else if (text.equals("list")) {
                        list();
                        text = scanner.nextLine();
                    }

                    //exit program
                    else if (text.equals("bye")) {
                        bye();
                        break;
                    }

                    //delete task
                    else if (text.startsWith("delete")) {
                        char last_digit = text.charAt(text.length() - 1);
                        int index = Character.getNumericValue(last_digit);
                        deleteTask(index);
                        text = scanner.nextLine();
                    }

                    //add new task
                    else {
                        Task newTask = null;
                        if (text.contains("deadline")) {
                            String description = extractTaskDescription(text);
                            String time = extractTaskTime(text);
                            newTask = new Deadline(description, time);
                        } else if (text.contains("event")) {
                            String description = extractTaskDescription(text);
                            String time = extractTaskTime(text);
                            newTask = new Event(description, time);
                        } else if (text.contains("todo")) {
                            String description = extractTaskDescription(text);
                            newTask = new Todo(description);
                        }

                        list.add(newTask);
                        addTask(newTask);

                        text = scanner.nextLine();
                    }

                }
            }
        } catch (UnknownException e) {
            System.out.println(e.getMessage());
        } catch (UnclearInstructionException e) {
            System.out.println(e.getMessage());
        }

    }
}
