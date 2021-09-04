package duke.util.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.util.commands.AddCommand;
import duke.util.commands.CommandList;
import duke.util.commons.Messages;
import duke.util.parser.Parser;
import duke.util.storage.Storage;
import duke.util.tasks.DateTaskTable;
import duke.util.tasks.Deadline;
import duke.util.tasks.DukeException;
import duke.util.tasks.Event;
import duke.util.tasks.Task;
import duke.util.tasks.TaskList;
import duke.util.tasks.ToDo;
import duke.util.ui.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;



/**
 * The class representing the Duke.
 *
 *
 */
public class Duke {
    private static final String saveDirectory = "data/";
    private static final String saveFilePath = "save.txt";
    private static final String tempFilePath = "temp.txt";
    private static ListView<Task> out;

    private final Parser parser;
    private final Ui ui = new Ui();
    private final Storage storage;
    private final TaskList tasks;
    private final DateTaskTable dateTaskTable;



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
        this.storage = new Storage(saveDirectory + filename,
                saveDirectory + tempFilePath,
                this.dateTaskTable);
        try {
            this.tasks.addAll(this.storage.read());
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
            this.storage.write(this.tasks);
            return result;
        } catch (DukeException | IOException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Messages.EXPECTED_DATE_FORMAT;
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
        taskAdd.execute();
        try {
            this.storage.write(this.tasks);
        } catch (IOException e) {
            ui.printErrorMessage(e);
        }

    }

    /**
     * Function to add a deadline task
     *
     * @param description The description of the input deadline
     * @param date The date the deadline is by.
     */
    public void addDeadline(String description, LocalDate date) {
        try {
            Deadline deadline = Deadline.of(description, date.toString());
            AddCommand res = new AddCommand(this.tasks, deadline);
            this.dateTaskTable.add(deadline);
            res.execute();
            this.storage.write(this.tasks);
        } catch (DukeException | IOException e) {
            ui.printErrorMessage(e);
        }
    }

    /**
     * Function to add an event task
     *
     * @param description The description of the Event to add.
     * @param date The date the event is at.
     */
    public void addEvent(String description, LocalDate date) {
        try {
            Event event = Event.of(description, date.toString());
            AddCommand addEvent = new AddCommand(this.tasks, event);
            this.dateTaskTable.add(event);
            addEvent.execute();
            this.storage.write(this.tasks);
        } catch (DukeException | IOException e) {
            ui.printErrorMessage(e);
        }
    }


    /**
     * Method to print the input string into the Text box text
     *
     * @param out The Text to show at
     * @param input The value to print in the text.
     */
    public void printToText(Text out, String input) {
        assert out != null : "Text output is null";
        assert input != null : "String input is null";
        out.setText(input);

    }


    /**
     * Handles the moment when the remove button is pressed.
     */
    public void removeHandler(ObservableList<Task> itemsToRemove) {
        assert itemsToRemove != null : "The items to remove is null";
        for (int i = 0; i < itemsToRemove.size(); i++) {
            this.tasks.remove(itemsToRemove.get(i));
        }
        try {
            this.storage.write(this.tasks);
        } catch (IOException e) {
            ui.printErrorMessage(e);
        }

    }






    /**
     * A method to print the list to dukes list feature.
     * Renders the list command kind of obsolete. Is there a way to make
     * this method call not used?
     */
    public void printList() {
        Duke.out.setItems(FXCollections.observableArrayList(this.tasks));
    }


    public static void setOut(ListView<Task> viewer) {
        Duke.out = viewer;
    }






}
