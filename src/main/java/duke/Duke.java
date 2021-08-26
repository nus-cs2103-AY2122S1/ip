package duke;

import java.io.IOException;
import java.time.LocalDate;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean end = false;
        while (!end) {
            try {
                String userInput = ui.readCommand();
                String[] command = Parser.parse(userInput);
                if (command[0].equals("end")) {
                    ui.showBye();
                    end = true;
                } else if (command[0].equals("list")) {
                    ui.showTaskList(tasks);
                } else if (command[0].equals("done")) {
                    int index = Integer.parseInt(command[1]);
                    Task doneTask = tasks.doneTask(index - 1);
                    storage.updateDone(index);
                    ui.showDoneTask(doneTask);
                } else if (command[0].equals("delete")) {
                    int index = Integer.parseInt(command[1]);
                    Task deleteTask = tasks.deleteTask(index - 1);
                    storage.deleteData(index);
                    ui.showDeleteTask(deleteTask, tasks.size());
                } else if (command[0].equals("todo")) {
                    Task task = new Todo(command[1], false);
                    tasks.addTask(task);
                    String dataDescription = "T , 0 , " + command[1];
                    storage.addData(dataDescription);
                    ui.showAddTask(task, tasks.size());
                } else if (command[0].equals("deadline")) {
                    LocalDate date = LocalDate.parse(command[2]);
                    Task task = new Deadline(command[1], date, false);
                    tasks.addTask(task);
                    String dataDescription = "D , 0 , " + command[1] + " , " + command[2];
                    storage.addData(dataDescription);
                    ui.showAddTask(task, tasks.size());
                } else if (command[0].equals("event")) {
                    LocalDate date = LocalDate.parse(command[2]);
                    Task task = new Event(command[1], date, false);
                    tasks.addTask(task);
                    String dataDescription = "E , 0 , " + command[1] + " , " + command[2];
                    storage.addData(dataDescription);
                    ui.showAddTask(task, tasks.size());
                }

            } catch (IOException | DukeException e) {
                ui.showError(e.getMessage());
                tasks = new TaskList();
            }
        }
    }

    public static void main (String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
