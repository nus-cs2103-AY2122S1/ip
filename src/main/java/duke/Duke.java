package duke;

import duke.exceptions.DukeException;

import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Ui;
import duke.util.Storage;

public class Duke {
    private boolean isExit;

    /**
     * Initializes Duke.
     */
    public Duke() {
        TaskList taskList = new TaskList();
        loadFile(taskList);
        this.isExit = false;
        Parser.setTaskList(taskList);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Returns an appropriate text reply for duke in the dialog box according
     * to user's input message.
     * 
     * @param input The user's input.
     * @return A text that duke should reply with.
     */
    public String getBotOutput(String input) {
        assert input != null : "User input should not be null";
        try { 
            String response = Parser.parse(input);
            if (Parser.isExit()) {
                this.isExit = true;
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }


    /**
     * Returns whether the program should exit.
     *
     * @return A boolean.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Retrieves file from the saved tasks.
     *
     * @param taskList Task list.
     */
    public void loadFile(TaskList taskList) {
        try {
            Storage.loadFromFile(taskList);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Runs the program with the correct components and storage file set-ups.
     */
    public void run() {
        TaskList taskList = new TaskList();
        loadFile(taskList);
        Ui ui = new Ui();
        System.out.print(Ui.greet());
        Parser.setTaskList(taskList);
        while (!this.isExit) {
            try {
                String fullCommand = ui.readNextLine();
                if (Parser.shouldExit(fullCommand)) {
                    this.isExit = true;
                    Storage.writeToFile(taskList);
                    System.out.print(ui.exit());
                } else {
                    String msg = Parser.parse(fullCommand);
                    System.out.print(msg);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}