package duke;

import java.util.Scanner;

import javafx.application.Platform;

/**
 * Parses user input.
 */
class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;
    private Scanner myScanner;
    private boolean breakLoop = false;
    private String filepath = "Data/DukeData.txt";

    Parser(Scanner myScanner) {
        this.storage = new Storage(filepath);
        this.myScanner = myScanner;
        this.tasklist = Storage.tasklist;
        try {
            Storage.readFile(filepath);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.ui = new Ui(tasklist);
    }

    public boolean getBreak() {
        return breakLoop;
    }

    public void breakOutLoop() {
        breakLoop = true;
        Platform.exit();
    }

    /**
     * Calls appropriate UI functions based on input.
     *
     * @param input String passed by user
     * @return String with output for user
     */
    public String parse(String input) {
        String output = "";
        if (input.equals("bye")) {
            output += ui.byeResponse();
            storage.saveTasks(tasklist);
            breakOutLoop();
        } else if (input.contains("list")) {
            output += ui.listResponse();
        } else if (input.contains("done")) {
            output += ui.doneResponse(input);
        } else if (input.contains("delete")) {
            output += ui.deleteResponse(input);
        } else if (input.contains("find")) {
            output += ui.findResponse(input);
        } else if (input.contains("todo")) {
            output += ui.todoResponse(input);
        } else if (input.contains("deadline")) {
            output += ui.deadlineResponse(input);
        } else if (input.contains("event")) {
            output += ui.eventResponse(input);
        } else if (input.contains("stats")) {
            output += ui.statsReponse(input);
        } else {
            output += ui.invalidInput();
        }
        return output;
    }
}
