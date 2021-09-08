package duke;

import java.util.Scanner;

public class Duke {

    private static final String LOCAL_FILE = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public void run() {
        // Display welcome
        this.ui.welcome();

        // Initialise Scanner and Parser
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc.nextLine());

        // Run Duke while command is not bye
        while (!parser.isBye()) {
            try {
                if (parser.isList()) {
                    // Run based on list command
                    ui.list(this.tasks);

                    // Scan for next command
                    parser = new Parser(sc.nextLine());
                } else if (parser.isDone()) {
                    try {
                        // Run based on done command
                        this.tasks.done(parser.getSecondPartInInt());
                        this.storage.save(parser.getCommand());
                        ui.done(this.tasks.getMostRecent());

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isToDo()) {
                    try {
                        // Run based on todo command
                        ToDo task = new ToDo(parser.getSecondPart());
                        this.tasks.add(task);
                        this.storage.save(parser.getCommand());
                        ui.addTask(this.tasks.getMostRecent(), this.tasks);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isDeadline()) {
                    try {
                        // Run based on deadline command
                        Deadline task = new Deadline(parser.splitSecondPartForDeadline()[0],
                                parser.splitSecondPartForDeadline()[1]);
                        this.tasks.add(task);
                        this.storage.save(parser.getCommand());
                        ui.addTask(this.tasks.getMostRecent(), this.tasks);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isEvent()) {
                    try {
                        // Run based on event command
                        Event task = new Event(parser.splitSecondPartForEvent()[0],
                                parser.splitSecondPartForEvent()[1]);
                        this.tasks.add(task);
                        this.storage.save(parser.getCommand());
                        ui.addTask(this.tasks.getMostRecent(), this.tasks);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isDelete()) {
                    try {
                        // Run based on delete command
                        this.tasks.delete(parser.getSecondPartInInt());
                        ui.deleteTask(this.tasks.getMostRecent(), this.tasks);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        // Display error
                        ui.showError(e);

                        // Scan for next command
                        parser = new Parser(sc.nextLine());
                    }
                } else {
                    throw new DukeException("I do not know what you want to do!");
                }
            } catch (DukeException e) {
                // Display error
                ui.showError(e);

                // Scan for next command
                parser = new Parser(sc.nextLine());
            }
        }

        // Duke ends
        ui.bye();
        sc.close();
    }

    public static void main(String[] args) {
        new Duke(LOCAL_FILE).run();

    }
}
