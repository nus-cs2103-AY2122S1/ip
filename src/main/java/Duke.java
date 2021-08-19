import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

/**
 * A class that encapsulates the duke chat bot, which has ability to greet, echo and exit.
 */
public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int counter = 0;
    /**
     * Constructor for the Duke class.
     * Does not take in any parameters, prints out a statement to greet user.
     */

    public Duke() {
        System.out.println("~~ Hello! I'm Duke ~~~~~~~~~\n~~ What can I do for you? ~~\n");
    }

    /**
     * method to add a task to the list in the chat bot,
     * adds the task to the next available slot in tasks,
     * then prints a statement saying the text has been added.
     * @param input task text
     * @return a log specifing that the task has been added and total tasks
     */
    public String add(String input) {
        try {
            Task task = setTask(input);
            if (task != null) {
                tasks.add(task);
                counter++;
            }
            return logger(task);
        } catch (StringIndexOutOfBoundsException se) {
            return new DukeException(se).print_message();
        } catch (NullPointerException ne) {
            return new DukeException(ne).print_message();
        }
    }
    
    private String logger(Task task) {
        try {
            String string = String.format("Got it. I've added this task: \n~~" + task.toString() +
                    "~~\nNow you have " + tasks.size() +
                    (tasks.size() > 1 ? " tasks" : " task") + " in the list.\n");
            return string;
        } catch (NullPointerException ne) {
            return new DukeException(ne).print_message();
        }
    }
    /**
     * method that returns a string of tasks
     * in order of which they are added.
     * @return string which when printed out displays every task in tasks
     */
    
    public String iterate() {
        String string = "";
        try {
            string += "Here are the tasks in your list: \n";
            for (int i = 0; i < counter; i++) {
                string += String.format("%d.%s\n", i + 1, tasks.get(i).toString());
            }
            string += "";
            return string;
        } catch (NullPointerException ne) {
            return new DukeException(ne).print_message();
        }
    }

    /**
     * method to duke to mark a task as done.
     * Marks task indexed num - 1 in tasks done,
     * then, prints out the task.
     * @param input the user input text
     */

    public String markDone(String input) {
        try {
            String index = input.substring("done ".length());
            int taskNum = Integer.parseInt(index);
            if(taskNum > counter || taskNum < 1) {
                Exception ae = new ArrayIndexOutOfBoundsException();
                return new DukeException(ae).print_message();
            }
            Task taskDone = tasks.get(taskNum - 1);
            taskDone.markAsDone();
            String string = "Nice! I've marked this task as done: \n";
            string += "~" + taskDone.toString() + "~ \n";
            return string;
        } catch (NullPointerException ne) {
            return new DukeException(ne).print_message();
        } catch (StringIndexOutOfBoundsException se) {
            Exception ne = new NullPointerException();
            return new DukeException(ne).print_message();
        }
    }

    /**
     * method that returns the corresponding task depending on the input
     * @param input task text
     * containing keywords "deadline", "event" or "todo"
     * @return a subtype of Task depending on the keyword in the input
     */

    public Task setTask(String input) {
        Task t = null;
        if (input.contains("deadline")) {
            String[] arr = formatInput(input, "deadline ", "/by ");
            t = new Deadline(arr[0], arr[1]);
        } else if (input.contains("event")) {
            String[] arr = formatInput(input, "event ", "/at ");
            t = new Event(arr[0], arr[1]);
        } else if (input.contains("todo")) {
            t = new Todo(input.substring("todo ".length()));
        }
        return t;
    }

    private String[] formatInput(String input, String keyword, String tag) {
        int start = input.indexOf(keyword);
        int by = input.indexOf(tag.charAt(0));
        String taskDescription = input.substring(start + keyword.length(), by - 1);
        String taskTime = input.substring(by + tag.length());
        return new String[]{taskDescription, taskTime};
    }

    /**
     * method to delete tasks from tasks
     * @param input user inputed string 
     * @return string containing the deleted task and updated number of task left.
     */
    
    public String delete(String input) {
        try {
            String index = input.substring("delete ".length());
            int taskNum = Integer.parseInt(index);
            if(taskNum > counter || taskNum < 1) {
                Exception ae = new ArrayIndexOutOfBoundsException();
                return new DukeException(ae).print_message();
            }
            Task task = tasks.get(taskNum - 1);
            tasks.remove(taskNum - 1);
            counter--;
            String string = "Alright. I've removed this task: \n";
            string += "~~" + task.toString() + "~~\n";
            string += String.format("Now you have %d %s in the list\n", tasks.size(), 
                                        (tasks.size() > 1 ? "tasks" : "task"));
            return string;
        } catch (NullPointerException ne) {
            return new DukeException(ne).print_message();
        } catch (ArrayIndexOutOfBoundsException ae) {
            return new DukeException(ae).print_message();
        } catch (StringIndexOutOfBoundsException se) {
            Exception ne = new NullPointerException();
            return new DukeException(ne).print_message();
        }
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
                System.out.println(duke.iterate());
            } else if (input.contains("done")) {
                System.out.println(duke.markDone(input));
            } else if (input.contains("delete")) {
                System.out.println(duke.delete(input));
            } else {
                System.out.println(duke.add(input));
            }
            input = sc.nextLine();
        }
        System.out.println("@@@ Bye. Hope to see you again soon! @@@");
        sc.close();
    }
}
