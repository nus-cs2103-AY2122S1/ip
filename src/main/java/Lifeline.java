import lifeline.command.Command;
import lifeline.exception.LifelineException;
import lifeline.parser.Parser;
import lifeline.storage.Storage;
import lifeline.task.TaskList;
import lifeline.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Lifeline {
    private Scanner sc;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    Lifeline(String filepath) {
        this.sc = new Scanner(System.in);
        this.storage = new Storage(filepath);
        this.ui = new Ui();
        try {
            this.taskList = storage.load();
        } catch (LifelineException e) {
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    private void getInput() {
        boolean exit = false;
        while (!exit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.equals("")) {
                    continue;
                }
                System.out.println();
                Command c = Parser.parse(fullCommand);
                c.execute.apply(fullCommand, storage, taskList, ui);
                if (c == Command.BYE) {
                    exit = true;
                }
            } catch (LifelineException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public void start() {
        ui.greet(taskList);
        this.getInput();
    }
}
