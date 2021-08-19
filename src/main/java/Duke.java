import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Shivam Tiwari
 * Chat bot to echo everything we input.
 * Features: Greetings, Echo and Exit on command
 */

public class Duke {

    // instance variable to store input  task values
    static ArrayList<Task> list;

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        list = new ArrayList<>();
        String input = "";
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you");
        Scanner sc = new Scanner(System.in);

        //loop to check if next input is available
        while (sc.hasNext()) {
            input = sc.nextLine();
            String[] split = input.split(" ", 2);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");

                //exit command for when entered exit code
                System.exit(1);
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (split.length < 2) {
                throw new NoDescriptionException("☹ OOPS!!! The description of a " + split[0] + " cannot be empty.");
            } else if (split[0].equals("done")) {
                if (split[1].isEmpty()) {
                    throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
                }
                doneTask(Integer.parseInt(split[1]));
            } else if (split[0].equals("delete")) {
                if (split[1].isEmpty()) {
                    throw new NoNumberException("☹ OOPS!!! No task number was given in the input");
                }
                deleteTask(Integer.parseInt(split[1]));
            } else if (split[0].equals("todo")) {
                createTodo(split[1]);
            } else if (split[0].equals("event")) {
                String time = split[1].split(" /at ", 2)[1];
                String task = split[1].split(" /at ", 2)[0];
                createEvent(task, time);
            } else if (split[0].equals("deadline")) {
                String time = split[1].split(" /by ", 2)[1];
                String task = split[1].split(" /by ", 2)[0];
                createDeadline(task, time);
            } else {
                throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * method to echo the input back to the user
     *
     * @param input String input from the user
     */
    static void Echo(String input) {
        System.out.println(input);
    }

    /**
     * Method to add the input to the list
     *
     * @param input String input from the user
     */
    static void addToList(Task input) {
        //Task task = new Task(input, false);
        list.add(input);
        System.out.println("Got it. I've added this task:");
        System.out.println(input.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * method to create an Event as a Task and add to
     * the task list
     *
     * @param input String task name
     * @param time  String time of event
     */
    static void createEvent(String input, String time) {
        Event event = new Event(input, false, time);
        addToList(event);
    }

    /**
     * method to create a Task with no time and
     * add it to the task list
     *
     * @param input String task name
     */
    static void createTodo(String input) {
        Todo todo = new Todo(input, false);
        addToList(todo);
    }

    /**
     * method to create a task with a given deadline and
     * add to the task list
     *
     * @param input String task name
     * @param time  String time of deadline
     */
    static void createDeadline(String input, String time) {
        Deadline deadline = new Deadline(input, false, time);
        addToList(deadline);
    }

    /**
     * Method to mark the task as done
     *
     * @param n the task number entered by the user
     */
    static void doneTask(int n) throws DukeException {
        if (n > list.size()) {
            throw new TaskNotFoundException("list has only " + list.size() + "tasks. Enter a valid task");
        }
        list.get(n - 1).markAsDone();
        System.out.println("Nice! I have marked this task as done:");
        System.out.println(list.get(n - 1).toString());
    }

    /**
     * Method to mark the task as done
     *
     * @param n the task number entered by the user
     */
    static void deleteTask(int n) throws DukeException {
        if (n > list.size()) {
            throw new TaskNotFoundException("list has only " + list.size() + "tasks. Enter a valid task");
        }
        String deletedTask = list.get(n - 1).toString();
        list.remove(n - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }


    /**
     * method to print task list on command
     */
    static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

}
