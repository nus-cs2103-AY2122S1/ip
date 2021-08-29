package duke;

import duke.commands.Command;

import java.util.Scanner;

public class Duke {

    private TaskList taskList;

    private Ui ui = new Ui();

    private DukeStorage storage;

    public Duke(String path) {
        this.storage = new DukeStorage(path);
        try {
            this.taskList = this.storage.readTasks();
        } catch (DukeException e) {
            ui.loadErrorMessage();
            taskList = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.startMessage();
        boolean check = false;

        while (!check) {
            try {
                String strCommand = scanner.nextLine();
                ui.showLine();
                Command command = Parser.parse(strCommand);
                command.execute(taskList, ui, storage);
                check = command.isExit();
            } catch (DukeException e) {
                ui.errorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("tasklist.txt");
        duke.run();
    }
}