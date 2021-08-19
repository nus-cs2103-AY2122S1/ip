import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static int numOfTasks = 0;

    public static void markAsDone(String s) throws DukeException {
        try {
            int taskNum = Integer.parseInt(s.substring(5));
            Task curr = tasks[taskNum - 1];
            curr.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  "
                    + curr.toString());
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please only enter an integer after command 'done'!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Task number " + s.substring(5) + " does not exist!");
        } catch (NullPointerException npe) {
            throw new DukeException("Task number " + s.substring(5) + " does not exist!");
        }
    }

    public static void addToDo(String s) throws DukeException {
        try {
            Task curr = new ToDo(s.substring(5));
            tasks[numOfTasks] = curr;
            numOfTasks += 1;
            System.out.println("Got it. I've added this task:\n  "
                    + curr.toString() + "\nNow you have " + numOfTasks
                    + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    public static void addEvent(String s) throws DukeException {
        try {
            int at = s.lastIndexOf(" /at ");
            Task curr = new Event(s.substring(6, at), s.substring(at + 5));
            tasks[numOfTasks] = curr;
            numOfTasks += 1;
            System.out.println("Got it. I've added this task:\n  "
                    + curr.toString() + "\nNow you have " + numOfTasks
                    + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description and time of an event cannot be empty.");
        }
    }

    public static void addDeadline(String s) throws DukeException {
        try {
            int by = s.lastIndexOf(" /by ");
            Task curr = new Deadline(s.substring(9, by), s.substring(by + 5));
            tasks[numOfTasks] = curr;
            numOfTasks += 1;
            System.out.println("Got it. I've added this task:\n  "
                    + curr.toString() + "\nNow you have " + numOfTasks
                    + " task(s) in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description and time of a deadline cannot be empty.");
        }
    }

    public static void main(String[] args) {
        System.out.println("*******************************************");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("*******************************************");

        Scanner myScanner= new Scanner(System.in); // open scanner
        boolean hasQuit = false;

        while (!hasQuit && myScanner.hasNextLine()) {
            String userInput = myScanner.nextLine();

            try {
                if (userInput.equals("bye")) { // if user enters "bye"
                    hasQuit = true;
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (userInput.equals("list")) { // if user enters "list"
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < numOfTasks; i++) {
                        Task curr = tasks[i];
                        int taskNum = i + 1;
                        System.out.println(taskNum + "." + curr.toString());
                    }
                } else if (userInput.startsWith("done")) {
                    markAsDone(userInput);
                } else if (userInput.startsWith("todo")) {
                    addToDo(userInput);
                } else if (userInput.startsWith("event")) {
                    addEvent(userInput);
                } else if (userInput.startsWith("deadline")) {
                    addDeadline(userInput);
                } else { // any other input from user
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("*******************************************");
        }
        myScanner.close(); // close scanner
    }
}

