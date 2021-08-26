import javax.xml.stream.FactoryConfigurationError;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

/**
 * Represents the duke chat bot, which has the ability to log and track and delete tasks.
 * 
 * @author Gordon Yit
 * @Since 23-08-21
 */
public class Duke {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int counter = 0;
    private int bufferLength = 5;
    
    public enum Command { done, list, delete, filter, others};
    
    /**
     * Constructor for the Duke class.
     * Prints out a statement to greet user.
     */
    public Duke() {
        String c = "~";
        String line1 = " Hello! I'm Duke ";
        String line2 = " What do you wanna do today? ";
        int limit = Integer.max(line1.length(), line2.length()) + bufferLength;
        String buffer1 = c.repeat((limit - line1.length()) / 2);
        String buffer2 = c.repeat((limit - line2.length()) / 2);
        String str = buffer1 + line1 + buffer1 + "\n" + buffer2 + line2 + buffer2 + "\n"; 
        System.out.println(str);
    }

    /**
     * Adds a task to the tasks list,
     * then prints a statement saying the text has been added.
     * 
     * @param input task text from user.
     * @return a log specifying that the task has been added and total number of tasks. 
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
            return new DukeException(se).getMessage();
        } catch (NullPointerException ne) {
            return new DukeException(ne).getMessage();
        } catch (DateTimeParseException de) {
            return new DukeException(de).getMessage();
        }
    }
    
    private String logger(Task task) {
        try {
            String string = String.format("Got it. I've added this task: \n~~" + task.toString() +
                    "~~\nNow you have " + tasks.size() +
                    (tasks.size() > 1 ? " tasks" : " task") + " in the list.\n");
            return string;
        } catch (NullPointerException ne) {
            return new DukeException(ne).getMessage();
        }
    }
    
    /**
     * Returns a string of tasks in order of which they are added.
     * 
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
            return new DukeException(ne).getMessage();
        }
    }
    
    /**
     * Marks a task as done, returns string confirming the said task is marked done.
     * 
     * @param input text from user.
     */
    public String markDone(String input) {
        try {
            String index = input.substring("done ".length());
            int taskNum = Integer.parseInt(index);
            if(taskNum > counter || taskNum < 1) {
                Exception ae = new ArrayIndexOutOfBoundsException();
                return new DukeException(ae).getMessage();
            }
            Task taskDone = tasks.get(taskNum - 1);
            taskDone.markAsDone();
            String string = "Nice! I've marked this task as done: \n";
            string += "~" + taskDone.toString() + "~ \n";
            return string;
        } catch (NullPointerException ne) {
            return new DukeException(ne).getMessage();
        } catch (StringIndexOutOfBoundsException se) {
            return new DukeException(se).getMessage();
        }
    }

    /**
     * Returns the corresponding task depending on the input.
     * 
     * @param input task text containing keywords "deadline", "event" or "todo".
     * @return a subtype of Task depending on the keyword in the input.
     */
    public Task setTask(String input) {
        Task t = null;
        if (input.contains("deadline")) {
            String[] arr = formatInput(input, "deadline ", " by ");
            t = new Deadline(arr[0], arr[1]);
        } else if (input.contains("event")) {
            String[] arr = formatInput(input, "event ", " at ");
            t = new Event(arr[0], arr[1]);
        } else if (input.contains("todo")) {
            t = new Todo(input.substring("todo ".length()));
        }
        return t;
    }

    private String[] formatInput(String input, String keyword, String tag) {
        int start = input.indexOf(keyword);
        int by = input.indexOf(tag);
        String taskDescription = input.substring(start + keyword.length(), by);
        String taskTime = input.substring(by + tag.length());
        return new String[]{taskDescription, taskTime};
    }

    /**
     * Takes in user input and generates a reply depending on the input.
     * 
     * @param input The user input command.
     * @return a reply corresponding to the user input command.
     */
    public String generateReply(String input) {
        String action = input.split(" ", 2)[0]; 
        Command cmd = Command.others;
        for (Command c: Command.values()) {
            if (action.equals(c.toString())) {
                cmd = c;
            }
        }
        switch (cmd) {
            case done:    
                return markDone(input);
            case list:
                return iterate();
            case delete:
                return delete(input);
            case filter:
                return filter(input);
            default:
                return add(input);
        }
    }
    
    /**
     * Deletes a task from tasks list.
     * 
     * @param input consisting of delete and index of the task to be deleted. 
     * @return string containing the deleted task and updated number of task left.
     */
    public String delete(String input) {
        try {
            String index = input.substring("delete ".length());
            int taskNum = Integer.parseInt(index);
            if(taskNum > counter || taskNum < 1) {
                Exception ae = new ArrayIndexOutOfBoundsException();
                return new DukeException(ae).getMessage();
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
            return new DukeException(ne).getMessage();
        } catch (ArrayIndexOutOfBoundsException ae) {
            return new DukeException(ae).getMessage();
        } catch (StringIndexOutOfBoundsException se) {
            Exception ne = new NullPointerException();
            return new DukeException(ne).getMessage();
        }
    }

    /**
     * filters tasks which dates matches the input date and prints out the tasks.
     * 
     * @param date the date of interest.
     * @return a list of events with dates matching the given date.
     */
    public String filter(String date) {
        try {
            String datetime = date.substring(date.indexOf(" ") + 1);
            DateDue dateDue = new DateDue(datetime);
            LocalDate localDate = dateDue.getLocalDate();
            System.out.println(String.format("On %s, you have: ", dateDue.toString()));
            for (Task t : tasks) {
                if (t.isSameDate(localDate)) {
                    System.out.println(t.toString());
                }
            }
            return "";
        } catch (DateTimeParseException e) {
            return new DukeException(e).getMessage();
        }
    }
    /**
     * Closes the duke bot, by bidding user farewell.
     * @return a closing statement to user.
     */
    public String close() {
        String str = "@@@@ See ya later, alligator @@@@";
        return str;
    }

    /**
     * Tests the functionality of Duke by scanning user input and printing relevant replies.
     * @param args Unused.
     */
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
            System.out.println(duke.generateReply(input));
            input = sc.nextLine();
        }
        sc.close();
        System.out.println(duke.close());
    }
}
