package duke;

import java.io.IOException;
import java.time.LocalDate;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class
     *
     * @param filePath
     */
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


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * @return
     */
    public String getResponse(String input) {
        try {
            String[] command = Parser.parse(input);
            if (command[0].equals("end")) {
                return ui.showBye();
            } else if (command[0].equals("list")) {
                return ui.showTaskList(tasks);
            } else if (command[0].equals("done")) {
                int index = Integer.parseInt(command[1]);
                assert(index <= tasks.size());
                Task doneTask = tasks.doneTask(index - 1);
                storage.updateDone(index);
                return ui.showDoneTask(doneTask);
            } else if (command[0].equals("delete")) {
                int index = Integer.parseInt(command[1]);
                assert(index <= tasks.size());
                Task deleteTask = tasks.deleteTask(index - 1);
                storage.deleteData(index);
                return ui.showDeleteTask(deleteTask, tasks.size());
            } else if (command[0].equals("todo")) {
                Task task = new Todo(command[1], false);
                tasks.addTask(task);
                String dataDescription = "T , 0 , " + command[1];
                storage.addData(dataDescription);
                return ui.showAddTask(task, tasks.size());
            } else if (command[0].equals("deadline")) {
                LocalDate date = LocalDate.parse(command[2]);
                Task task = new Deadline(command[1], date, false);
                tasks.addTask(task);
                String dataDescription = "D , 0 , " + command[1] + " , " + command[2];
                storage.addData(dataDescription);
                return ui.showAddTask(task, tasks.size());
            } else if (command[0].equals("event")) {
                LocalDate date = LocalDate.parse(command[2]);
                Task task = new Event(command[1], date, false);
                tasks.addTask(task);
                String dataDescription = "E , 0 , " + command[1] + " , " + command[2];
                storage.addData(dataDescription);
                return ui.showAddTask(task, tasks.size());
            } else if (command[0].equals("find")) {
                return ui.showFindTask(tasks.find(command[1]));
            } else {
                return "Not a valid command";
            }
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
