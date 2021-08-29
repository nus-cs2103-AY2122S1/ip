import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import org.junit.jupiter.api.Test;

import duke.task.ToDo;
import duke.util.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void taskList_addToDo_toDoAdded() {
        String inputString = "todo make breakfast every morning";
        TaskList taskList = new TaskList();

        String[] inputArr = inputString.split(" ");
        String firstWord = inputArr[0];
        String remainingText = Parser.getRemainingText(firstWord, inputString);
        try {
            ToDo myTodo = ToDo.newTodo(remainingText);
            Ui.display_message(taskList.addTask(myTodo));
        } catch (DukeException err) {
            Ui.display_message(err.getMessage());
        }
        assertEquals("1. [T][ ] make breakfast every morning", taskList.toString());
    }

    @Test
    public void taskList_addDeadline_deadlineAdded() {
        String inputString = "deadline make breakfast /by 0800";
        TaskList taskList = new TaskList();

        String[] inputArr = inputString.split(" ");
        String firstWord = inputArr[0];
        String remainingText = Parser.getRemainingText(firstWord, inputString);
        try {
            Deadline myDeadline = Deadline.newDeadline(remainingText, false);
            Ui.display_message(taskList.addTask(myDeadline));
        } catch (DukeException err) {
            Ui.display_message(err.getMessage());
        }
        assertEquals("1. [D][ ] make breakfast (by: 08.00 AM)", taskList.toString());
    }

    @Test
    public void taskList_addEvent_eventAdded() {
        String inputString = "event go to concert /at 0800";
        TaskList taskList = new TaskList();

        String[] inputArr = inputString.split(" ");
        String firstWord = inputArr[0];
        String remainingText = Parser.getRemainingText(firstWord, inputString);
        try {
            Event myEvent = Event.newEvent(remainingText, false);
            Ui.display_message(taskList.addTask(myEvent));
        } catch (DukeException err) {
            Ui.display_message(err.getMessage());
        }
        assertEquals("1. [E][ ] go to concert (at: 08.00 AM) ", taskList.toString());
    }

    private static final String INCOHERENT_INPUT_MESSAGE = "I'm sorry, but I don't know what that means :-(";

    @Test
    public void command_initialiseCommand_illegalCommandExceptionThrown() {
        String inputString = "I don't know how to use Duke";
        String actual = "";
        String[] inputArr = inputString.split(" ");
        String firstWord = inputArr[0];
        try {
            Command.initialiseCommand(firstWord);
        } catch (DukeException err) {
            actual = err.getMessage();
            Ui.display_message(err.getMessage());
        }
        assertEquals(INCOHERENT_INPUT_MESSAGE, actual);
    }

    private static final String OUTER_DIR = "data";
    private static final String FILE = "taskList.txt";
    private static final String[] FILE_PATH_ARR =  {".", OUTER_DIR , FILE};

    @Test
    public void storage_updateTaskListToFile_fileUpdated() {
        TaskList taskList = new TaskList();
        Storage myStorage = new Storage();
        try {
            taskList.addTask(ToDo.newTodo("eat lunch"));
            myStorage.updateTaskListToFile(taskList);

            Scanner taskScanner = new Scanner(new File(String.join(File.separator, FILE_PATH_ARR)));
            String taskLine = taskScanner.nextLine();
            taskScanner.close();
            assertEquals("T | 0 | eat lunch", taskLine);
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        } catch (FileNotFoundException err) {
            Ui.display_message("An error occurred. Unable to find file.");
        }
    }

    @Test
    public void storage_readTaskFile_fileRead() {
        TaskList taskList = new TaskList();
        Storage myStorage = new Storage();
        try {
            FileWriter fileWriter = new FileWriter(String.join(File.separator, FILE_PATH_ARR), false);
            fileWriter.write("T | 0 | eat lunch");
            fileWriter.close();

            myStorage.readTaskFile(taskList);
            assertEquals("T | 0 | eat lunch", taskList.getSaveFormat());
            // Clear the file after use
            FileWriter fileWriterClearFile = new FileWriter(String.join(File.separator, FILE_PATH_ARR), false);
            fileWriterClearFile.close();
        } catch (FileNotFoundException err) {
            Ui.display_message("An error occurred. Unable to find file.");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
