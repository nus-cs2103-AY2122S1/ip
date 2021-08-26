package kermit;

import kermit.command.Command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Kermit {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Kermit(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (KermitException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showIntroMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (KermitException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
    public static void main(String[] args){
        new Kermit("data/tasks.txt").run();
    }
}