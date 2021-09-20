package duke;

import java.util.Scanner;

/**
 * Represents a Duke chatbot. It helps to collate tasks for the user.
 */
public class Duke {

    //private static final String LOCAL_FILE = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor that constructs a Duke object.
     *
     * @param filePath File Path for Storage to obtain saved data. If data does not exist, a new file will be created
     *                 with that filePath.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);

        assert (this.tasks.noOfTask() >= 0) : "Number of tasks cannot be negative";
        try {
            if (parser.isList()) {
                // Run based on list command
                return ui.listAll(this.tasks);

            } else if (parser.isDone()) {
                try {
                    // Run based on done command
                    this.tasks.done(parser.getSecondPartInInt());
                    this.storage.save(parser.getCommand());

                    this.storage.assertFile();
                    return ui.doneTask(this.tasks.getMostRecent());

                } catch (DukeException e) {
                    return ui.showError(e);

                }
            } else if (parser.isToDo()) {
                try {
                    // Run based on todo command
                    ToDo task = new ToDo(parser.getSecondPart());
                    this.tasks.add(task);

                    this.storage.assertFile();
                    this.storage.save(parser.getCommand());
                    return ui.addTask(this.tasks.getMostRecent(), this.tasks);

                } catch (DukeException e) {
                    // Display error
                    return ui.showError(e);

                }
            } else if (parser.isDeadline()) {
                try {
                    // Run based on deadline command
                    Deadline task = new Deadline(parser.splitSecondPartForDeadline()[0],
                            parser.splitSecondPartForDeadline()[1]);
                    this.tasks.add(task);
                    this.storage.save(parser.getCommand());

                    this.storage.assertFile();
                    return ui.addTask(this.tasks.getMostRecent(), this.tasks);

                } catch (DukeException e) {
                    // Display error
                    return ui.showError(e);

                }
            } else if (parser.isEvent()) {
                try {
                    // Run based on event command
                    Event task = new Event(parser.splitSecondPartForEvent()[0],
                            parser.splitSecondPartForEvent()[1]);
                    this.tasks.add(task);
                    this.storage.save(parser.getCommand());

                    this.storage.assertFile();
                    return ui.addTask(this.tasks.getMostRecent(), this.tasks);

                } catch (DukeException e) {
                    // Display error
                    return ui.showError(e);

                }
            } else if (parser.isDelete()) {
                try {
                    // Run based on delete command
                    this.tasks.delete(parser.getSecondPartInInt());
                    return ui.deleteTask(this.tasks.getMostRecent(), this.tasks);

                } catch (DukeException e) {
                    // Display error
                    return ui.showError(e);

                }
            } else if (parser.isFind()) {
                try {
                    TaskList taskList = this.tasks.find(parser.getSecondPart());
                    return ui.findTask(taskList);

                } catch (DukeException e) {
                    return ui.showError(e);

                }
            } else {
                throw new DukeException("I do not know what you want to do!");
            }
        } catch (DukeException e) {
            // Display error
            return ui.showError(e);

        }

    }

    /**
     * Runs Duke object. Follows the commands scanned by the Scanner. Ends when "bye" command is detected.
     */
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
                    ui.listAll(this.tasks);

                    // Scan for next command
                    parser = new Parser(sc.nextLine());
                } else if (parser.isDone()) {
                    try {
                        // Run based on done command
                        this.tasks.done(parser.getSecondPartInInt());
                        this.storage.save(parser.getCommand());
                        ui.doneTask(this.tasks.getMostRecent());

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
                } else if (parser.isFind()) {
                    try {
                        TaskList taskList = this.tasks.find(parser.getSecondPart());
                        ui.findTask(taskList);

                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        ui.showError(e);

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
}
