package duke;

/**
 * The Duke chat bot app
 */

public class Duke {

    // UI related stuff
    private final Ui ui;

    // Saved data related stuff
    private final Storage storage;

    // Parsing user input stuff
    private final Parser parser;

    // The list of items
    private Items list;

    /**
     * Duke Constructor
     * @param directory Relative path to directory of the saved data
     * @param file File name of the saved data
     */
    public Duke(String directory, String file) {
        ui = new Ui();
        storage = new Storage(directory, file);
        parser = new Parser();
        try {
            list = new Items(storage.load());
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
            list = new Items();
        }
    }

    /**
     * Entry point of the Duke program
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("./data", "duke.txt").run();
    }

    /**
     * The Duke application run function
     */
    public void run() {

        // Show Greetings
        ui.showGreetings();

        String rawInput = ui.getInput();
        String output = "";
        label:
        while (true) {

            try {
                String[] input = parser.parseInput(rawInput);
                Constants.Commands command = Constants.Commands.valueOf(input[0]);
                String task;

                switch (command) {
                    case help:
                        // Show help
                        output = ui.getCommandMenu();

                        break;
                    case bye:
                        // Quit program
                        break label;
                    case list:
                        // Displays the tasks in the list
                        output = list.getListMessage();

                        break;
                    case done:
                        int idx = Integer.parseInt(input[1]);
                        // Marks a task as done.
                        output = list.markDone(idx);
                        // Edit the file content.
                        task = storage.getFileLine(idx - 1);
                        task = task.substring(0, 4) + "1" + task.substring(5);
                        storage.updateLineFile(idx - 1, task);

                        break;
                    case todo:
                        // Add a todo task in the list.
                        output = list.addItem(new Todo(input[1]));
                        // Add to file content.
                        task = "T | 0 | " + input[1];
                        storage.addToFile(task);

                        break;
                    case deadline:
                        // Add a deadline task in the list.
                        output = list.addItem(new Deadline(input[1], input[2]));
                        // Add to file content.
                        task = "D | 0 | " + input[1] + " | " + input[2];
                        storage.addToFile(task);

                        break;
                    case event:
                        // Add an event task in the list.
                        output = list.addItem(new Event(input[1], input[2]));
                        // Add to file content.
                        task = "E | 0 | " + input[1] + " | " + input[2];
                        storage.addToFile(task);

                        break;
                    case delete:
                        int id = Integer.parseInt(input[1]);
                        // Delete an event from the list.
                        output = list.removeItem(id);
                        // Remove from file content.
                        storage.removeFromFile(id-1);

                        break;
                    case dates:
                        output = ui.getAllAcceptedDates();

                        break;
                    case find:
                        output = list.find(input[1]);
                }
                ui.showMessage(output);
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
                return;
            }

            rawInput = ui.getInput();
        }
        // Good bye message
        ui.showGoodBye();
    }
}
