package duke;

import duke.command.Command;

import duke.task.TaskList;

import java.io.IOException;

public class Duke {

    // storage to handle save file (loading and saving)
    private Storage storage;
    // storage to store tasks
    private TaskList tasks;
    // handles interaction with the user
    private UI ui;

    public Duke(String filePath) {
        this.tasks = new TaskList();
        this.ui = new UI();
        // Initialise Storage with the tasks storage and the filepath to the save file
        this.storage = new Storage(this.tasks, filePath);
        // Loading save file from the filepath
        try {
            storage.load();
        } catch (IOException e) {
            System.out.println(
                UI.tabAndFormat(
                    "☹ OOPS!!! Please enter a proper file path! e.g.:\n'./data/duke.txt'"
                )
            );
        }
    }

    public void run() {
        // Starting with a welcome message
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            
            try {
                Command userCommand = new Parser(input, tasks).checkOperation();
                userCommand.execute();
                isExit = userCommand.isExit();
                storage.save();
            } catch (DukeException | IllegalArgumentException e) {
                System.out.println(UI.tabAndFormat(e.getMessage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }   
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
