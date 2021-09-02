package duke;

import java.util.Scanner;

class Parser {
    Ui ui;
    Storage storage;
    TaskList tasklist;
    Scanner myScanner;
    boolean breakLoop = false;


    Parser(Scanner myScanner) {
        this.storage = new Storage("Data/DukeData.txt");
        this.myScanner = myScanner;
        this.tasklist = Storage.tasklist;
        try {
            Storage.readFile("Data/DukeData.txt");
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
    }

    /**
     * Calls appropriate UI functions based on input
     *
     * @param input String passed by user
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
        } else {
            output += ui.invalidInput();
        }
        return output;
    }
}
