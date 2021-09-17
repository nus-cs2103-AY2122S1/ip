package sariel.util.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sariel.util.commands.AddCommand;
import sariel.util.commands.CommandList;
import sariel.util.commands.ExitCommand;
import sariel.util.commons.Messages;
import sariel.util.parser.Parser;
import sariel.util.storage.Storage;
import sariel.util.tasks.DateTaskTable;
import sariel.util.tasks.Deadline;
import sariel.util.tasks.DukeException;
import sariel.util.tasks.Event;
import sariel.util.tasks.Task;
import sariel.util.tasks.TaskList;
import sariel.util.tasks.ToDo;
import sariel.util.ui.Ui;
import sariel.util.windows.DialogBox;



/**
 * The class representing the Duke.
 *
 *
 */
public class Sariel {
    private static final String saveDirectory = "data/";
    private static final String saveFilePath = "save.txt";
    private static final String tempFilePath = "temp.txt";
    private static ListView<Task> out;

    private final Parser parser;
    private final Ui ui = new Ui();
    private final Storage storage;
    private final TaskList tasks;
    private final DateTaskTable dateTaskTable;
    private TaskList displayTasks;
    private Stage stage;

    /**
     * Constructor for duke.
     *
     * @param filename The file to save at.
     * @param tempFilePath The tempfile name to use.
     */
    public Sariel(String filename, String tempFilePath, Stage stage) {
        ExitCommand.setSariel(this);
        this.stage = stage;
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
        this.displayTasks = this.tasks;
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
            DialogBox.setLabelColor(DialogBox.Color.BLACK);
            CommandList cmds = parser.inputsParser(inpt);
            String result = cmds.executeAll();
            this.storage.write(this.tasks);
            return result;
        } catch (DukeException | IOException e) {
            DialogBox.setLabelColor(DialogBox.Color.RED);
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Messages.EXPECTED_DATE_FORMAT;
        }
    }



    /**
     * Adds a todo task
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
     * Adds a deadline task.
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
     * Filters the stored tasks and outputs into
     * the display section.
     *
     * @param filter The predicate to filter with, keeps the task in display if true.
     */
    public void filterDisplayList(Predicate<Task> filter) {
        this.displayTasks = this.tasks.filter(filter);
        this.printList();
    }


    /**
     * Quits Sariel.
     *
     *
     */
    public void quit() {
        this.stage.close();
    }




    /**
     * Prints the list to dukes list feature.
     * Renders the list command kind of obsolete. Is there a way to make
     * this method call not used?
     */
    public void printList() {
        Sariel.out.setItems(FXCollections.observableArrayList(this.displayTasks));
    }


    public static void setOut(ListView<Task> viewer) {
        Sariel.out = viewer;
    }






}
