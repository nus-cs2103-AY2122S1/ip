import java.time.LocalDateTime;
import java.util.Scanner;
public class Duke {
    private final Storage storage;
    private final DateTimeHandler dth;
    private final TaskList taskList;

    public static String formatMessage(String s) {
        return "    ____________________________________________________________\n     " +
                s + "\n" +
                "    ____________________________________________________________";
    }
    public Duke() {
        taskList = new TaskList();
        storage = new Storage(taskList);
        dth = new DateTimeHandler();

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(formatMessage("Hello! I'm Duke\n" + "     What can I do for you?"));
        while (true) {
            String input = scanner.nextLine();
            String[] params = input.split(" ", 2);
            String[] parts;
            String arg;
            if(params[0].equals("bye")) {
                break;
            }
            switch (params[0]) {
            case "list":
                System.out.println(formatMessage(taskList.printList()));
                break;
            case "done":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter a number after done"));
                    break;
                }
                arg = params[1];
                try {
                    int index = Integer.parseInt(arg);
                    if (index > taskList.size()) {
                        System.out.println(formatMessage("There are only " + taskList.size() + " tasks"));
                        break;
                    } else if (index == 0) {
                        System.out.println(formatMessage("There is no task 0"));
                        break;
                    }
                    Task t = taskList.getTask(index - 1);
                    t.completeTask();
                    System.out.println(formatMessage(
                            "Nice! I've marked this task as done:\n       " + t + "\n     " +
                                    taskList.numOfTasks()
                    ));

                } catch (NumberFormatException e) {
                    System.out.println(formatMessage("Please enter a number after done"));
                }
                break;
            case "delete":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter a number after delete"));
                    break;
                }
                arg = params[1];
                try {
                    int index = Integer.parseInt(arg);
                    if (index > taskList.size()) {
                        System.out.println(formatMessage("There are only " + taskList.size() + " tasks"));
                        break;
                    } else if (index == 0) {
                        System.out.println(formatMessage("There is no task 0"));
                        break;
                    }
                    Task t = taskList.getTask(index - 1);
                    taskList.removeTask(index-1);
                    System.out.println(formatMessage(
                            "Noted. I've removed this task:\n       " + t + "\n     " +
                                    taskList.numOfTasks()
                    ));

                } catch (NumberFormatException e) {
                    System.out.println(formatMessage("Please enter a number after done"));
                }
                break;
            case "todo":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter the name of the task after todo"));
                    break;
                }
                Todo t = new Todo(params[1], false);
                taskList.addToList(t);
                System.out.println(taskList.taskAddedMessage(t));
                break;
            case "deadline":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter the name of the task after deadline"));
                    break;
                }
                if (!params[1].contains("/by")) {
                    System.out.println(formatMessage("Please enter the deadline of the task after /by"));
                    break;
                }
                parts = params[1].split(" /by ");
                LocalDateTime deadlineDate = dth.parseDate(parts[1]);
                if (deadlineDate == null) {
                    System.out.println(formatMessage("Please enter a valid date-time format. Type formats to see valid formats"));
                    break;
                }
                Deadline d = new Deadline(parts[0], false, deadlineDate);
                taskList.addToList(d);
                System.out.println(taskList.taskAddedMessage(d));
                break;
            case "event":
                if (params.length == 1) {
                    System.out.println(formatMessage("Please enter the name of the task after event"));
                    break;
                }
                if (!params[1].contains("/at")) {
                    System.out.println(formatMessage("Please enter the start date of the task after /at"));
                    break;
                }
                parts = params[1].split(" /at ");
                LocalDateTime startDate = dth.parseDate(parts[1]);
                if (startDate == null) {
                    System.out.println(formatMessage("Please enter a valid date-time format. Type formats to see valid formats"));
                    break;
                }
                Event e = new Event(parts[0], false, startDate);
                taskList.addToList(e);
                System.out.println(taskList.taskAddedMessage(e));
                break;
            case "formats":
                System.out.println(formatMessage(dth.getFormatList()));
                break;
            default:
                System.out.println(formatMessage("That is not a recognised command"));
            }
            storage.writeToFile(taskList);
        }
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));

    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
