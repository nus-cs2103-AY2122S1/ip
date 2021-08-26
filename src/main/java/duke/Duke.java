package duke;

import java.io.*;
import java.util.Scanner;

public class Duke {
    private final Ui ui;
    private final Scanner sc = new Scanner(System.in);
    private TaskList taskList = new TaskList();
    private final Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    public void run() {
        try {
            this.taskList = storage.readSave();
        } catch (EOFException e) {
            ui.showNewSave();
        } catch (IOException | ClassNotFoundException e) {
            ui.showReadSaveError();
        }
        ui.showIntro();
        outer:
        while (true) {
            String userEntry = sc.nextLine();
            Command command;
            try {
                command = Parser.parseUserInput(userEntry);
            } catch (DukeException e) {
                ui.print(e.toString());
                continue;
            }
            Task task;
            switch (command.getOperation()) {
            case "bye":
                ui.showOutro();
                try {
                    storage.writeSave(this.taskList);
                } catch (IOException e) {
                    ui.showWriteSaveError();
                }
                break outer;
            case "list":
                ui.showTasks(taskList);
                break;
            case "done":
                task = taskList.get(command.getIndex() - 1);
                task.setDone(true);
                ui.showDone(task);
                break;
            case "todo":
                task = new ToDo(command.getDescription());
                taskList.add(task);
                ui.showAdded(task, taskList.size());
                break;
            case "deadline":
                task = new Deadline(command.getDescription(), command.getTime());
                taskList.add(task);
                ui.showAdded(task, taskList.size());
                break;
            case "event":
                task = new Event(command.getDescription(), command.getTime());
                taskList.add(task);
                ui.showAdded(task, taskList.size());
                break;
            case "delete":
                task = taskList.get(command.getIndex() - 1);
                taskList.delete(command.getIndex() - 1);
                ui.showDeleted(task, taskList.size());
                break;
            case "find":
                TaskList filteredTasks = taskList.find(command.getDescription());
                ui.showMatches(filteredTasks);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
