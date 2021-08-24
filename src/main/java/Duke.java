import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Duke class
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-6
 */
public class Duke {
    public static final String divider = "-------------------------------------";
    private Scanner sc;
    private List list;
    private static final String filePath = "data/list.txt";

    //Constructor
    public Duke(Scanner sc) {
        this.sc = sc;
        this.list = new List();
    }

    /** Prints greeting statement */
    private static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println(Duke.divider);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(Duke.divider);
    }

    private void startUp() throws NoDescriptionException, InvalidDescriptionException,
            MissingTimeCommandException {
        try {
            ArrayList<String> unformattedTasks = FileHelper.startUpFile(Duke.filePath);

            int index = 1;
            for (String s : unformattedTasks) {
                String[] currTask = s.split(" \\| ", 3);

                String command = currTask[0];
                String isDone = currTask[1];
                String body = currTask[2];

                if (command.equals("T")) {
                    this.addToDo("todo " + body);
                } else if (command.equals("D") || command.equals("E")) {
                    String[] descAndTime = body.split(" \\| ", 3);
                    if (command.equals("D")) {
                        this.addDeadline("deadline " + descAndTime[0] + " /by " + descAndTime[1]);
                    } else {
                        this.addEvent("event " + descAndTime[0] + " /at " + descAndTime[1]);
                    }
                }

                if (isDone.equals("1")) {
                    this.done("done " + index);
                }
                index += 1;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    /** Prints response after successfully adding task */
    private void respondOnAddTask(Task task) {
        System.out.println(Duke.divider);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        this.list.getNumOfTasks();
        System.out.println(Duke.divider);
    }

    /** Prints goodbye statement */
    private static void goodbye() {
        System.out.println(Duke.divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Duke.divider);
    }

    /** Adds a To Do to the list */
    private ToDo addToDo(String req) throws NoDescriptionException, IOException {
        if (req.equals("todo")) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String[] splitReq = req.split(" ", 2);
        String body = splitReq[1];
        ToDo todo = new ToDo(body); //Could also throw and error in the constructor
        this.list.add(todo);
        return todo;
    }

    /** Adds a Deadline to the list */
    private Deadline addDeadline(String req) throws NoDescriptionException, MissingTimeCommandException{
        if (req.equals("deadline")) {
            throw new NoDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (!req.contains("/by")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/by' in the command.");
        }

        String[] splitReq = req.split("/by", 2);
        String desc = splitReq[0];
        String date = splitReq[1];
        Deadline deadline = new Deadline(desc, date);
        this.list.add(deadline);
        return deadline;
    }

    /** Adds an Event to the list */
    private Event addEvent(String req) throws NoDescriptionException, MissingTimeCommandException {
        if (req.equals("event")) {
            throw new NoDescriptionException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        if (!req.contains("/at")) {
            throw new MissingTimeCommandException("Missing Time Command: add '/at' in the command.");
        }

        String[] splitReq = req.split("/at", 2);
        String desc = splitReq[0];
        String date = splitReq[1];
        Event event = new Event(desc, date);
        this.list.add(event);
        return event;
    }

    /** Receives an invalid command and throws and exception*/
    private void invalidInput(String req) throws InvalidCommandException {
      throw new InvalidCommandException(
        "Sorry! I do not understand you? Try another command!");
    }

    /** Receives an index to specify a task is done */
    private Task done(String req) throws NoDescriptionException, InvalidDescriptionException {
        if (req.equals("done")) {
            throw new NoDescriptionException("Please specify the task number.");
        }

        String[] splitReq = req.split(" ", 2);
        String desc = splitReq[1];
        int index;

        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'done'.");
        }

        Task task = this.list.getIndex(index);
        task.markAsDone();
        return task;
    }

    /** Receives an index to specify a task to delete*/
    private void deleteTask(String req) throws NoDescriptionException, InvalidDescriptionException {
        if (req.equals("delete")) {
            throw new NoDescriptionException("Please indicate a task to delete.");
        }

        String[] splitReq = req.split(" ", 2);
        String desc = splitReq[1];
        int index;

        try {
            index = Integer.parseInt(desc);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("Please append a task number after 'delete'.");
        }

        this.list.removeTask(index);
    }

    /** Runs the instance of duke*/
    private void run() {
        Duke.greet();

        try {
            this.startUp();
        } catch (Exception e) {
            this.exceptionHandler(e);
        }

        String req = "";
        boolean end = false;

        do {
            try {
                //Get first command word
                req = this.sc.nextLine();
                String[] splitReq = req.split(" ", 2);
                String cmd = splitReq[0];

                //Switch statement based on initial command
                switch (cmd) {
                    case "list":
                        this.list.getAll();
                        break;

                    case "bye":
                        Duke.goodbye();
                        end = true;
                        break;

                    case "done":
                        Task completedTask = this.done(req);
                        System.out.println(Duke.divider);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(completedTask.toString());
                        System.out.println(Duke.divider);
                        this.list.rewriteFile();
                        break;

                    case "todo":
                        ToDo t = this.addToDo(req);
                        respondOnAddTask(t);
                        this.list.rewriteFile();
                        break;

                    case "deadline":
                        Deadline d = this.addDeadline(req);
                        respondOnAddTask(d);
                        this.list.rewriteFile();
                        break;

                    case "event":
                        Event e = this.addEvent(req);
                        respondOnAddTask(e);
                        this.list.rewriteFile();
                        break;

                    case "delete":
                        this.deleteTask(req);
                        this.list.rewriteFile();
                        break;

                    default:
                        this.invalidInput(req);
                }
            } catch (Exception e) {
                this.exceptionHandler(e);
            }
        } while (!end);
        sc.close();
    }

    /** Catches all exceptions from run. Should the param be  of type DukeException? */
    private void exceptionHandler(Exception e) {
        System.err.println(e.getMessage());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke(sc);
        duke.run();
    }
}
