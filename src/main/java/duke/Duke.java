package duke;

import exceptions.NoSuchCommandException;
import exceptions.NoTaskNameException;

import tasks.Task;

import java.io.IOException;

public class Duke {
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Storage storage = new Storage("./storage/save.txt");
        Ui ui = new Ui();
        try {
            taskList = new TaskList(storage.retrieve());
        } catch (IOException e) {
            ui.showError("ERROR! There was an issue retrieving the file. Creating" +
                    "new save file instead.");
        } catch (NoSuchCommandException e) {
            ui.showError("ERROR! Some commands in save.txt could not be recognised," +
                    "creating new save file.");
        } catch (NoTaskNameException e) {
            ui.showError("ERROR! Some tasks in save.txt had no name. Creating new" +
                    "save file.");
        }
        String fullCommand;
        String[] command_and_input;
        String command;
        String input;
        ui.greet();

        while(true) {
            fullCommand = ui.readCommand();
            command_and_input = Parser.parse(fullCommand);
            command = command_and_input[0];
            input = command_and_input[1];
            switch (command) {
            case "bye":
                try {
                    storage.save(taskList);
                } catch (IOException e) {
                    ui.showError("ERROR: TaskList could not be saved!");
                }
                ui.goodbye();
                return;
            case "list":
                ui.showList(taskList);
                break;
            case "done":
                taskList.doTask(Integer.parseInt(input) - 1);
                ui.showList(taskList);
                break;
            case "delete":
                taskList.delete(Integer.parseInt(input) - 1);
                ui.showDeleteTask(taskList);
                ui.showList(taskList);
                break;
            default:
                try {
                    taskList.add(Task.createTask(command, input));
                } catch (NoSuchCommandException e) {
                    ui.showError("ERROR! Command not recognised.");
                } catch (NoTaskNameException e) {
                    ui.showError("ERROR! No task name.");
                }
            }
        }
    }
}
