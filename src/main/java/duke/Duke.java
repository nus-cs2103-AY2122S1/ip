package duke;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import static java.lang.Integer.parseInt;

/**
 * The Duke class acts as a text bot that records down the tasks given to it. It can
 * then mark these tasks as completed and delete them based on the inputs given.
 */

public class Duke {
    /**
     * Checks the date input from the user to see if it is a valid date
     *
     * @param dateStr Date taken in to check for validity
     * @return If date is valid
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        ui.input = input;
        try {
            while (input.hasNextLine()) {
                String userInput = ui.readInput();
                Parser.parse(userInput, this.ui, this.tasks);
            }
        } catch (IllegalStateException e) {
            storage.saveFile(tasks);
        }
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\ronal\\OneDrive\\Desktop\\CS2103\\DUKE\\data\\duke.txt").run();
    }
}