import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

/**
 * A class that encapsulates the duke chat bot, which has ability to greet, echo and exit.
 */
public class Duke {
    private Task[] tasks = new Task[100];
    int counter = 0;

    /**
     * Constructor for the Duke class.
     * Does not take in any parameters, prints out a statement to greet user.
     */

    public Duke() {
        System.out.println("~~ Hello! I'm Duke ~~~~~~~~~\n~~ What can I do for you? ~~");
    }

    /**
     * method to add a task to the list in the chat bot,
     * adds the task to the next available slot in tasks,
     * then prints a statement saying the text has been added.
     * @param task task to be added
     */
    public void add(Task task) {
        tasks[counter] = task;
        counter++;
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t~~" + task.toString() + "~~");
        System.out.println("\tNow you have " + counter + (counter > 1 ? " tasks" : " task") + " in the list.");
    }

    /**
     * method to iteratively print out each task stored in tasks
     * in order of which they are added.
     */

    public void iterate() {
        System.out.println("\tHere are the tasks in your list: ");
        for(int i = 0; i < counter; i++) {
            System.out.println("\t" + (i + 1) + "." + tasks[i].toString());
        }
    }

    /**
     * method to duke to mark a task as done.
     * Marks task indexed num - 1 in tasks done,
     * then, prints out the task.
     * @param num index of the task to be marked done
     */

    public void markDone(int num) {
        Task taskDone = tasks[num - 1];
        taskDone.markAsDone();
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t~" + taskDone.toString() + "~ ");
    }

    /**
     * method that returns the corresponding task depending on the input
     * @param input task text
     *              containing keywords "deadline", "event" or "todo"
     * @return a subtype of Task depending on the keyword in the input
     */

    public Task setTask(String input) {
        Task t;
        if (input.contains("deadline")) {
            int start = input.indexOf("deadline");
            int by = input.indexOf("/");
            String taskDescription = input.substring(start + "deadline ".length(), by - 1);
            String taskDeadline = input.substring(by + "/by ".length());
            t = new Deadline(taskDescription, taskDeadline);
        } else if (input.contains("event")){
            int start = input.indexOf("event");
            int at = input.indexOf("/");
            String eventDescription = input.substring(start + "event ".length(), at - 1);
            String eventTime = input.substring(at + "/at ".length());
            t = new Event(eventDescription, eventTime);
        } else {
            t = new Todo(input.substring("todo ".length()));
        }
        return t;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                duke.iterate();
            } else if (input.contains("done")) {
                String index = input.substring("done ".length());
                int taskNum = Integer.parseInt(index);
                duke.markDone(taskNum);
            } else {
                Task t = duke.setTask(input);
                duke.add(t);
            }

            input = sc.nextLine();
        }
        System.out.println("@@@ Bye. Hope to see you again soon! @@@");
        sc.close();
    }
}
