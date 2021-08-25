import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MESSAGE = "Hello! I'm Jacky\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "    Bye. Hope to see you again soon!";
    static boolean isTerminated = false;
    private TaskHandler taskHandler;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialiseDuke();
        duke.runDuke();
    }

    public void initialiseDuke() {
        taskHandler = new TaskHandler();
    }

    public void runDuke() {
        System.out.println(WELCOME_MESSAGE);
        Scanner sc = new Scanner(System.in);
        while(!isTerminated) {
            String cmd = sc.nextLine();
            String input = cmd.split(" ")[0].toUpperCase();
            try {
                switch (input) {
                    case "LIST":
                        taskHandler.printList();
                        break;

                    case "DONE":
                        taskHandler.markTaskAsDone(Integer.parseInt(cmd.substring(5)));
                        break;

                    case "DELETE":
                        taskHandler.deleteTask(Integer.parseInt(cmd.substring(7)));
                        taskHandler.printNoOfTasks();
                        break;

                    case "TODO":
                        if (cmd.length() < 6) {
                            throw new DukeException("    OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            ToDo toDo = new ToDo(cmd.substring(5));
                            taskHandler.addToDo(toDo);
                            taskHandler.printNoOfTasks();
                        }
                        break;

                    case "DEADLINE":
                        if (cmd.length() < 10) {
                            throw new DukeException("    OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            String[] split = cmd.split("/by ");
                            Deadline deadline = new Deadline(split[0].substring(9), split[1]);
                            taskHandler.addDeadline(deadline);
                            taskHandler.printNoOfTasks();
                            break;
                        }

                    case "EVENT":
                        if (cmd.length() < 7) {
                            throw new DukeException("    OOPS!!! The description of an event cannot be empty.");
                        } else {
                            String[] split = cmd.split("/at ");
                            Event event = new Event(split[0].substring(6), split[1]);
                            taskHandler.addEvent(event);
                            taskHandler.printNoOfTasks();
                            break;
                        }

                    case "BYE":
                        System.out.println(BYE_MESSAGE);
                        isTerminated = true;
                        System.exit(0);

                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

