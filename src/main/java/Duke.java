import java.util.Scanner;
import java.util.ArrayList;

/**
 * This program is a chatbot that helps keep track of various tasks.
 *
 * @author Lethicia Renissa Santoso (G12)
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showErrorMessage(e);
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {
        Ui.showWelcomeMessage();
        Scanner scan = new Scanner(System.in);
        while(scan.hasNextLine()) {
            try {
                String input = scan.nextLine();
                String command = Parser.getCommand(input);
                if (command.equals("bye")) {
                    Ui.showExitMessage();
                    break;
                } else if (command.equals("list")) {
                    Ui.showTaskList(tasks);
                } else if (command.equals("done")) {
                    int taskNo = Parser.getTaskNo(input, command);
                    Task markedDone = tasks.doneTask(taskNo);
                    Ui.showDoneTask(markedDone);
                    storage.write(tasks);
                } else if (command.equals("delete")) {
                    int taskNo = Parser.getTaskNo(input, command);
                    Task deletedTask = tasks.deleteTask(taskNo);
                    Ui.showDeletedTask(deletedTask);
                    Ui.showTaskCount(tasks);
                    storage.write(tasks);
                } else if (command.equals("todo")) {
                    String[] fields = Parser.getToDoFields(input);
                    tasks.addTask(taskType.TODO, fields);
                    Ui.showAddedTask(tasks);
                    Ui.showTaskCount(tasks);
                    storage.writeNewTask(tasks.getTask(tasks.count()));
                } else if (command.equals("deadline")) {
                    String[] fields = Parser.getDeadlineFields(input);
                    tasks.addTask(taskType.DEADLINE, fields);
                    Ui.showAddedTask(tasks);
                    Ui.showTaskCount(tasks);
                    storage.writeNewTask(tasks.getTask(tasks.count()));
                } else if (command.equals("event")) {
                    String[] fields = Parser.getEventFields(input);
                    tasks.addTask(taskType.EVENT, fields);
                    Ui.showAddedTask(tasks);
                    Ui.showTaskCount(tasks);
                    storage.writeNewTask(tasks.getTask(tasks.count()));
                }
            } catch (DukeException e) {
                Ui.showErrorMessage(e);
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
