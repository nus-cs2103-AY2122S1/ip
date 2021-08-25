package kermit;

import kermit.command.Command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Driver class for Kermit, a frog that keeps tracks of your tasks.
 */
public class Kermit {
    private Storage storage;
    private ToDo taskList;
    private Ui ui;

    /**
     * Converts convenient user string to LocalDate.
     *
     * @param s Date string in form DD-MM-YYYY.
     * @return LocalDate of string.
     * @throws KermitException when the date is not parseable.
     */
    private static LocalDate formatUserDateFormat(String s) throws KermitException {
        String[] components = s.split("-");
        String day = components[0];
        String month = components[1];
        String year = components[2];
        try {
            LocalDate parsedDate = LocalDate.parse(String.join("-", year, month, day));
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new KermitException("BAH That's not a time is it?? Try writing like this DD/MM/YYYY");
        }
    }

    /**
     * Constructor for Kermit.
     *
     * @param filePath File path for where data for task list should be stored.
     */
    public Kermit(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new ToDo(storage.load());
        } catch (KermitException e) {
            ui.showLoadingError();
            taskList = new ToDo();
        }
    }

    /**
     * Driver function to start Kermit.
     */
    public void run() {
        ui.showIntroMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (KermitException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Starts Kermit and saves data in data/tasks.txt.
     *
     * @param args  The commandline arguments.
     */
    public static void main(String[] args){
        new Kermit("data/tasks.txt").run();
    }
}