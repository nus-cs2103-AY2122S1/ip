import java.io.IOException;
import java.time.DateTimeException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String dirPath, String fileName) throws IOException {
        storage = new Storage(dirPath, fileName);
        tasks = new TaskList(storage.loadFile());
    }

    public void run() {
        String[] inputWords = null;
        Ui.greet();
        do {
            inputWords = Parser.parse(Ui.readCommand());
            try {
                switch (inputWords[0]) {
                    case "bye":
                        break;
                    case "list":
                        tasks.printTasks();
                        break;
                    case "done": {
                        if (inputWords.length == 1) {
                            throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                        }
                        int taskId = Integer.parseInt(inputWords[1]);
                        tasks.markTaskAsDone(taskId);
                        storage.saveFile(tasks.tasksAsString());
                        break;
                    }
                    case "todo":
                        if (inputWords.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks.addTodo(inputWords[1]);
                        storage.saveFile(tasks.tasksAsString());
                        break;
                    case "deadline":
                        if (inputWords.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        tasks.addDeadline(inputWords[1]);
                        storage.saveFile(tasks.tasksAsString());
                        break;
                    case "event":
                        if (inputWords.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        tasks.addEvent(inputWords[1]);
                        storage.saveFile(tasks.tasksAsString());
                        break;
                    case "delete":
                        if (inputWords.length == 1) {
                            throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                        }
                        int taskId = Integer.parseInt(inputWords[1]);
                        tasks.deleteTask(taskId);
                        storage.saveFile(tasks.tasksAsString());
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DateTimeException e) {
                Ui.printError("Please provide date/time in the correct format: yyyy-mm-dd HH:mm");
            } catch (Exception e) {
                Ui.printError(e.getMessage());
            }
        }
        while(!inputWords[0].equals("bye"));
        System.out.println("Bye, hope to see you again!");
    }

    public static void main(String[] args) throws IOException {
        new Duke("./src/main/data/", "duke.txt").run();
    }
}

