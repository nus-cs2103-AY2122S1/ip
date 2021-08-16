import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final Scanner sc = new Scanner(System.in);
    private static boolean stop = false;
    private static ArrayList<Task> inputArr = new ArrayList<Task>();

    public static void listMethod(String input) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputArr.size(); i++) {
            Task currTask = inputArr.get(i);
            System.out.println((i+1) + "." + currTask.toString());
        }
    }

    public static void stopMethod() {
        stop = true;
        System.out.print("Bye. Hope to see you again soon!");
    }

    public static void doneMethod(String input) throws DukeException{
        int number = Integer.parseInt(String.valueOf(input.charAt(5)));
        if (number > inputArr.size()) {
            throw new DukeException("Item does not exist, we cannot mark it as done.");
        }
        inputArr.get(number - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(inputArr.get(number-1).toString());
    }

    public static void taskMethod(String input) throws DukeException{
        if (!input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Task currTask;
        if (input.startsWith("todo")) {
            if (input.length() == 4) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String taskDesc = input.substring(5);
            currTask = new Todo(taskDesc);
        }
        else if (input.startsWith("deadline")) {
            if (input.length() == 8) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            int byIndex = input.indexOf("/");
            String by = input.substring(byIndex+4);
            String taskDesc = input.substring(9, byIndex-1);
            currTask = new Deadline(taskDesc, by);
        }
        else {
            if (input.length() == 5) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
            int byIndex = input.indexOf("/");
            String by = input.substring(byIndex+4);
            String taskDesc = input.substring(6, byIndex-1);
            currTask = new Event(taskDesc,by);
        }
        inputArr.add(currTask);
        System.out.println("Got it. I have added this task:");
        System.out.println(currTask);
        System.out.println("Now you have " + inputArr.size() + " tasks in the list.");
    }

    public static void deleteMethod(String input) throws DukeException{
        int number = Integer.parseInt(String.valueOf(input.charAt(7)));
        if (number > inputArr.size()) {
            throw new DukeException("Item does not exist, we cannot delete it.");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(inputArr.get(number-1).toString());
        inputArr.remove(number - 1);
        System.out.println("Now you have " + inputArr.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n What can I do for you?");

        try {
            while (!stop) {
                String input = sc.nextLine();

                if (input.equals("list")) {
                    listMethod(input);
                } else if (input.equals("bye")) {
                    stopMethod();
                    break;
                } else if (input.startsWith("done")) {
                    doneMethod(input);
                } else if (input.startsWith("delete")) {
                    deleteMethod(input);
                } else {
                    taskMethod(input);
                }
            }
        }
        catch (DukeException e) {
            System.out.println("Caught the exception");
            System.out.print("Exception occurred: " + e);
        }
    }
}
