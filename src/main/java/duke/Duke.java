package duke;

import duke.gui.Main;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
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
     * Method to run Duke
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        try {
            Command inputCommand = parser.parseCommand(input);
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
                String[] separatedContent = parser.parseDescription(input, "by");
                Task newTask = new Deadline(separatedContent[0], separatedContent[1]);
                this.tasks.add(newTask);
                this.storage.writeTask(newTask.toString());
                return this.ui.showAddTaskMessage(newTask, this.tasks.size());
            }

            case EVENT: {
                String[] separatedContent = parser.parseDescription(input, "at");
                Task newTask = new Event(separatedContent[0], separatedContent[1]);
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

            default: {
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
