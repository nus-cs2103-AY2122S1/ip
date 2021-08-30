package util.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import util.commands.AddCommand;
import util.commands.CommandList;
import util.commands.ExitCommand;
import util.commons.Messages;
import util.parser.Parser;
import util.storage.Storage;
import util.tasks.*;
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
    private static ListView<Task> out;


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


    /**
     * Returns the response from the duke when it takes in a String input.
     *
     * @param inpt The input string.
     * @return
     */
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
     * Function to add a todo task
     * from just taking in a String.
     *
     * @param string
     */
    public void addTodo(String string) {

        AddCommand taskAdd = new AddCommand(this.tasks, ToDo.of(string));
        try {
            this.stg.write(this.tasks);
        } catch (IOException e) {
            ui.print_error_message(e);
        }
        taskAdd.execute();

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
            AddCommand res = new AddCommand(this.tasks, deadline);
            this.dateTaskTable.add(deadline);
            this.stg.write(this.tasks);
            res.execute();
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
            AddCommand addEvent = new AddCommand(this.tasks, event);
            this.dateTaskTable.add(event);
            this.stg.write(this.tasks);
            addEvent.execute();
        } catch (DukeException | IOException e) {
            ui.print_error_message(e);
        }
    }


    /**
     * Method to print the input string into the Text box text
     *
     * @param text The Text to show at
     * @param val The value to print in the text.
     */
    public void printToText(Text text, String val) {
        if (text == null) {
            return;
        }
        text.setText(val);

    }


    /**
     * Handles the moment when the remove button is pressed.
     *
     *
     */
    public void removeHandler(ObservableList<Task> itemsToRemove) {
        for (int i = 0; i < itemsToRemove.size(); i++) {
            this.tasks.remove(itemsToRemove.get(i));
        }
        try {
            this.stg.write(this.tasks);
        } catch (IOException e) {
            ui.print_error_message(e);
        }

    }






    /**
     * A method to print the list to dukes list feature.
     * Renders the list command kind of obsolete.
     *
     */
    public void printList() {
        Duke.out.setItems(FXCollections.observableArrayList(this.tasks));
    }


    public static void setOut(ListView<Task> viewer) {
        Duke.out = viewer;


    }






}
