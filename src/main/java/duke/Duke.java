package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.gui.Main;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskWithTime;
import duke.task.Todo;
import exception.DukeException;
import javafx.application.Application;

/**
 * Personal Assistant Chat bot to keep track of tasks
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;


    /**
     * Class Constructor
     *
     * @param filePath The file to save and load tasks
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
    }

    /**
     * Gets Duke's response based on user input
     *
     * @param input The user input
     * @return The response from Duke
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            Command inputCommand = parser.parseCommand(input);
            assert inputCommand != null;
            switch (inputCommand) {

            case LIST: {
                if (this.tasks.isEmpty()) {
                    return this.ui.showNoTaskMessage();
                } else {
                    return this.ui.showTaskList(this.tasks);
                }
            }

            case DONE: {
                int taskNo = parser.getTaskNo(input);
                Task doneTask = this.tasks.get(taskNo);
                String oldContent = doneTask.toString();
                doneTask.markAsDone();
                this.storage.editTask(oldContent, doneTask.toString());
                return this.ui.showDoneMessage(doneTask);
            }

            case DELETE: {
                int taskNo = parser.getTaskNo(input);
                Task deletedTask = this.tasks.get(taskNo);
                this.tasks.remove(taskNo);
                this.storage.deleteTask(deletedTask.toString());
                return this.ui.showDeleteMessage(deletedTask, this.tasks.size());
            }

            case TODO: {
                String description = parser.parseDescription(input);
                Task newTask = new Todo(description);
                this.tasks.add(newTask);
                this.storage.writeTask(newTask.toString());
                return this.ui.showAddTaskMessage(newTask, this.tasks.size());
            }

            case DEADLINE: {
                String[] parsed = parser.parseDescription(input, "by");
                LocalDateTime dateTime = LocalDateTime.parse(parsed[1], inputFormatter);
                Task newTask = new Deadline(parsed[0], dateTime);
                this.tasks.add(newTask);
                this.storage.writeTask(newTask.toString());
                return this.ui.showAddTaskMessage(newTask, this.tasks.size());
            }

            case EVENT: {
                String[] parsed = parser.parseDescription(input, "at");
                LocalDateTime dateTime = LocalDateTime.parse(parsed[1], inputFormatter);
                Task newTask = new Event(parsed[0], dateTime);
                this.tasks.add(newTask);
                this.storage.writeTask(newTask.toString());
                return this.ui.showAddTaskMessage(newTask, this.tasks.size());
            }

            case FIND: {
                String keyword = parser.parseDescription(input);
                return this.ui.showMatchMessage(keyword, this.tasks);
            }

            case BYE: {
                return this.ui.bye();
            }

            case EDIT: {
                int taskNo = parser.getTaskNo(input);
                String oldTask = this.tasks.get(taskNo).toString();
                String[] parsedContent = parser.parseDescription(input, "to");
                if (parsedContent[0].equals("description")) {
                    this.tasks.get(taskNo).setDescription(parsedContent[1]);
                } else {
                    TaskWithTime task = (TaskWithTime) this.tasks.get(taskNo);
                    task.setDateTime(LocalDateTime.parse(parsedContent[1], inputFormatter));
                }
                Task newTask = this.tasks.get(taskNo);
                this.storage.editTask(oldTask, newTask.toString());
                return this.ui.showEditMessage(newTask);
            }

            default: {
                assert false;
                return "An error has occurred!";
            }

            }
        } catch (DukeException e) {
            return this.ui.showErrorMessage(e);
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
