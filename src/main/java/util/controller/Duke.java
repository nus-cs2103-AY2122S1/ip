package util.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.scene.text.Text;
import util.commands.CommandList;
import util.commands.ExitCommand;
import util.commons.Messages;
import util.parser.Parser;
import util.storage.Storage;
import util.tasks.DateTaskTable;
import util.tasks.DukeException;
import util.tasks.TaskList;
import util.tasks.ToDo;
import util.tasks.Deadline;
import util.tasks.Event;
import util.ui.Ui;


/**
 * The class representing the Duke.
 *
 *
 */
public class Duke {
    private static final String saveDirectory = "data/";
    private static final String saveFilePath = "save.txt";
    private static final String tempFilePath = "temp.txt";

    private final Parser parser;
    private final Ui ui = new Ui();
    private final Storage stg;
    private final TaskList tasks;
    private final DateTaskTable dateTaskTable;
    private static Text out;


    /**
     * Constructor for duke.
     *
     * @param filename The file to save at.
     * @param tempFilePath The tempfile name to use.
     */
    public Duke(String filename, String tempFilePath) {
        this.tasks = new TaskList();
        this.dateTaskTable = new DateTaskTable();
        this.parser = new Parser(this.ui, this.tasks, this.dateTaskTable);
        this.stg = new Storage(saveDirectory + filename,
                saveDirectory + tempFilePath,
                this.dateTaskTable);
        try {
            this.tasks.addAll(this.stg.read());
        } catch (IOException | DukeException ex) {
            ui.print(ex.getMessage());
        }
        ui.print_logo();
    }




    public String getResponse(String inpt) {
        try {
            CommandList cmds = parser.inputsParser(inpt);
            String result = cmds.executeAll();
            this.stg.write(this.tasks);
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Expected date format YYYY MM DD";
        } catch (IOException e) {
            return e.getMessage();
        }

    }


    /**
     * Running Duke.
     */
    public void run() {

        //initialising Duke
        //via greetings
        ui.print(Messages.GREETINGS);

        while (!ExitCommand.isExit()) {
            String inpt = ui.getInput();
            try {
                CommandList cmds = parser.inputsParser(inpt);
                cmds.executeAll();
                stg.write(this.tasks);
            } catch (DukeException e) {
                ui.print_error_message(e);
            } catch (DateTimeParseException e) {
                ui.print("Expected date format YYYY MM DD");
            } catch (IOException e) {
                ui.print_error_message(e);
            }
        }
        ui.print(Messages.BYE);

    }

    /**
     * Function to add a todo task
     * from just taking in a String.
     *
     * @param string
     */
    public void addTodo(String string) {

        this.tasks.add(ToDo.of(string));

        try {
            this.stg.write(this.tasks);
            printToText(out);
        } catch (IOException e) {
            ui.print_error_message(e);
        }

    }

    /**
     * Function to add a deadline task
     *
     * @param string
     * @param date
     */
    public void addDeadline(String string, LocalDate date) {
        try {
            Deadline deadline = Deadline.of(string, date.toString());
            this.tasks.add(deadline);
            this.dateTaskTable.add(deadline);
            this.stg.write(this.tasks);
            printToText(out);
        } catch (DukeException | IOException e) {
            ui.print_error_message(e);
        }
    }

    /**
     * Function to add an event task
     *
     * @param string
     * @param date
     */
    public void addEvent(String string, LocalDate date) {
        try {
            Event event = Event.of(string, date.toString());
            this.tasks.add(event);
            this.dateTaskTable.add(event);
            this.stg.write(this.tasks);
            printToText(out);
        } catch (DukeException | IOException e) {
            ui.print_error_message(e);
        }
    }


    public void printToText(Text text) {
        if (text == null) {
            return;
        }
        try {
            text.setText(this.ui.list(this.tasks));
        } catch (DukeException e) {
            this.ui.print_error_message(e);
        }
    }

    public static void setOut(Text text) {
        Duke.out = text;
    }






    /**
     * The main function of Duke.
     *
     * @param args The arguments for duke.
     */
    public static void main(String[] args) {
        Duke d = new Duke(saveFilePath, tempFilePath);
        d.run();



    }
}
