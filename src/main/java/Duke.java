import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static boolean isTerminated = false;
    private TaskHandler taskHandler;
    private Storage storage;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialiseDuke();
        duke.runDuke();
    }

    public void initialiseDuke() {
        try {
            storage = new Storage("./data/taskList.txt");
            ArrayList<Task> list = storage.loadTasks();
            taskHandler = new TaskHandler(list);
        } catch (DukeException e) {
            System.err.println("Error: Unable to initialise duke");
            System.out.println(e.getMessage());
        }
    }

    public void runDuke() {
        Ui.welcome();
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
                        storage.updateFile(taskHandler.formatTaskToSave());
                        break;

                    case "DELETE":
                        taskHandler.deleteTask(Integer.parseInt(cmd.substring(7)));
                        taskHandler.printNoOfTasks();
                        storage.updateFile(taskHandler.formatTaskToSave());
                        break;

                    case "TODO":
                        if (cmd.length() < 6) {
                            throw new DukeException(Ui.emptyDescription("todo"));
                        } else {
                            ToDo toDo = new ToDo(cmd.substring(5));
                            taskHandler.addToDo(toDo);
                            taskHandler.printNoOfTasks();
                            storage.updateFile(taskHandler.formatTaskToSave());
                        }
                        break;

                    case "DEADLINE":
                        if (cmd.length() < 10) {
                            throw new DukeException(Ui.emptyDescription("deadline"));
                        } else {
                            String[] split = cmd.split("/by ");
                            if (split.length > 1) {
                                Deadline deadline = new Deadline(split[0].substring(9), split[1]);
                                taskHandler.addDeadline(deadline);
                                taskHandler.printNoOfTasks();
                                storage.updateFile(taskHandler.formatTaskToSave());
                            } else {
                                throw new DukeException(Ui.dateMissing());
                            }
                            break;
                        }

                    case "EVENT":
                        if (cmd.length() < 7) {
                            throw new DukeException(Ui.emptyDescription("event"));
                        } else {
                            String[] split = cmd.split("/at ");
                            if (split.length > 1) {
                                Event event = new Event(split[0].substring(6), split[1]);
                                taskHandler.addEvent(event);
                                taskHandler.printNoOfTasks();
                                storage.updateFile(taskHandler.formatTaskToSave());
                            } else {
                                throw new DukeException(Ui.dateMissing());
                            }
                            break;
                        }

                    case "BYE":
                        Ui.bye();
                        isTerminated = true;
                        System.exit(0);

                    default:
                        throw new DukeException(Ui.inputUnknown());
                }
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

