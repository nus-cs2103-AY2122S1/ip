package duke;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;

/**
 * The Duke class acts as a text bot that records down the tasks given to it. It can
 * then mark these tasks as completed and delete them based on the inputs given.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private String filePath;
    private boolean closed;

    /**
     * Checks the date input from the user to see if it is a valid date
     *
     * @param dateStr Date taken in to check for validity
     * @return If date is valid
     */
    public static boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter in the format: yyyy-mm-dd");
            return false;
        }
        return true;
    }

    public Duke(String filePath) {
        closed = false;
        this.filePath = "duke.txt" ;
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    public String process(String input) {
        String output = Parser.parse(input, this.tasks);
        if (input.equals("bye")) {
            storage.saveFile(tasks);
            closed = true;
        }
        return output;
    }

    public boolean isClosed() {
        return closed;
    }
}