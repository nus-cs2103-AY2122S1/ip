import java.util.Scanner;

/**
 * The Duke class encapsulates the action of the chatbot Duke.
 */
public class Duke {
    /** The tasklist for Duke to store all the tasks. */
    private TaskList taskList;

    /**
     * Constructor to initialise the Duke chatbot.
     */
    private Duke() {
        this.taskList = new TaskList();
    }

    /**
     * Method to carry out the command entered by the user to the toDoList.
     */
    public void commanding() throws DukeException {
        Scanner scan = new Scanner(System.in);
        String description = scan.nextLine();
        while (!description.equals("bye")) {
            System.out.println("-------------------------------------------------------------------");
            try {
                if (description.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    taskList.list();
                } else if (description.startsWith("done")) {
                    String taskNo = description.substring(5);
                    System.out.println("Nice! I've marked this task as done:");
                    taskList.done(Integer.parseInt(taskNo));
                } else if (description.startsWith("todo")) {
                    if (description.equals("todo")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    System.out.println("Got it boss! I've added this task:");
                    String des = description.substring(5);
                    Todo ttask = new Todo(des);
                    taskList.store(ttask);
                    System.out.println("  " + ttask.toString() + "\n" + taskList.toString());
                } else if (description.startsWith("deadline")) {
                    if (description.equals("deadline")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    System.out.println("Got it boss! I've added this task:");
                    int pos = description.indexOf("/");
                    String des = description.substring(9, pos - 1);
                    String by = description.substring(pos + 4);
                    Deadline dtask = new Deadline(des, by);
                    taskList.store(dtask);
                    System.out.println("  " + dtask.toString() + "\n" + taskList.toString());
                } else if (description.startsWith("event")) {
                    if (description.equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    System.out.println("Got it boss! I've added this task:");
                    int pos = description.indexOf("/");
                    String des = description.substring(6, pos - 1);
                    String at = description.substring(pos + 4);
                    Event etask = new Event(des, at);
                    taskList.store(etask);
                    System.out.println("  " + etask.toString() + "\n" + taskList.toString());
                } else {
                    throw new DukeException("☹ I'm sorry! I'm not quite sure what you need me to do!");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
            System.out.println("-------------------------------------------------------------------");
            description = scan.nextLine();
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Okay then! I hope to see you again soon master!");
        System.out.println("-------------------------------------------------------------------");
        scan.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Hello I'm\n" + logo + "How may I help you today master?\n");
        System.out.println("-------------------------------------------------------------------");

            Duke chatbot = new Duke();
            chatbot.commanding();
    }
}
