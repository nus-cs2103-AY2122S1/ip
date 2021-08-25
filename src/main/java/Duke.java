import java.io.IOException;

import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    Storage storage = new Storage("data/duke.txt");

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
    }

    public void addTodo(String description) {
        Task task = new Todo(description);
        this.taskList.addTask(task);
        ui.sayAddTask(task, taskList.size());
        this.saveData();
    }

    public void addDeadline(String description, String by) throws DateTimeParseException {
        Task task = new Deadline(description, by);
        this.taskList.addTask(task);
        ui.sayAddTask(task, taskList.size());
        this.saveData();
    }

    public void addEvent(String description, String at) throws DateTimeParseException {
        Task task = new Event(description, at);
        this.taskList.addTask(task);
        ui.sayAddTask(task, taskList.size());
        this.saveData();
    }

    public void markDone(int index) {
        // Converting 1-based to 0-based
        this.taskList.markDone(index - 1);
        ui.sayMarkDoneTask(index);
        this.saveData();
    }

    public void deleteTask(int index) {
        // Converting 1-based to 0-based
        Task deletedTask = this.taskList.getTask(index - 1);
        this.taskList.deleteTask(index - 1);
        ui.sayDeleteTask(deletedTask, this.taskList.size());
        this.saveData();
    }

    public void loadData() {
        ui.say("Retrieving data...");
        try {
            List<String> data = storage.load();
            taskList = TaskList.deserialize(data);

            ui.sayRetrieveData();
        } catch (IllegalArgumentException | DateTimeParseException e) {
            // Data stored in incorrect format
            ui.say("Unable to retrieve data. Data stored in invalid format");
        } catch (IOException e) {
            // Do nothing
            ui.say("No stored data found");
        }
    }

    public void saveData() {
        try {
            storage.store(taskList.serialize());
        } catch (IOException e) {
            // Do nothing
            ui.say("Unable to save data");
        }
    }

    public void run() {
        ui.sayGreet();
        ui.sayHelp();

        this.loadData();

        String response = "";

        while (true) {
            response = ui.getUserCommand();

            try {
                if (response.equals("list")) {
                    ui.sayList(this.taskList);
                } else if (response.equals("bye")) {
                    break;
                } else if (response.startsWith("done ")) {
                    int index = Integer.parseInt(response.substring(5));
                    this.markDone(index);
                } else if (response.startsWith("delete ")) {
                    int index = Integer.parseInt(response.substring(7));
                    this.deleteTask(index);
                } else if (response.startsWith("todo ")) {
                    String description = response.substring(5);
                    this.addTodo(description);
                } else if (response.startsWith("deadline ")) {
                    if (!response.contains(" /by ")) {
                        throw new DukeException("Invalid format for `deadline` command. '/by' keyword is needed");
                    }
                    String[] params = response.substring(9).split(" /by ");
                    String description = params[0];
                    String by = params[1];
                    this.addDeadline(description, by);
                } else if (response.startsWith("event ")) {
                    if (!response.contains(" /at ")) {
                        throw new DukeException("Invalid format for `event` command. '/at' keyword is needed");
                    }
                    String[] params = response.substring(6).split(" /at ");
                    String description = params[0];
                    String at = params[1];
                    this.addEvent(description, at);
                } else {
                    throw new DukeException("Invalid command: " + response);
                }
            } catch (DukeException e) {
                ui.say(e.getMessage() + "\n");
            } catch (DateTimeParseException e) {
                ui.say("Invalid date format (must be in yyyy-mm-dd). Unable to add task.");
            }
        }

        ui.sayGoodBye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.run();
    }
}
