package duke.core;

import duke.gui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates a storage object that handles saving data into a file and loading data from the file.
 */
public class Storage {
    private File file;
    private static final int N_SEGMENTS_IN_DEADLINE = 4;
    private static final int N_SEGMENTS_IN_EVENT = 4;
    private static final int N_SEGMENTS_IN_TODO = 3;
    protected static final String REGEX_FOR_STORAGE = "_/_,_/_";
    /**
     * Constructs a Storage object.
     *
     * @param filePath The relative filepath of the storage file.
     */
    public Storage(String filePath) {
        file = new File(filePath);
        // Create folder for the file if it does not exist
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * Saves the tasks within the task list into the storage file.
     *
     * @param taskList The TaskList object storing all the tasks.
     * @throws IOException If the storage filepath exists but is a directory rather than a regular file,
     *  does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void saveTasksToFile(TaskList taskList) throws IOException {
        taskList.saveContents(file);
    }

    /**
     * Loads the tasks from the storage file into an ArrayList.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            // Could throw a FileNotFoundException if the file is missing
            Scanner sc = new Scanner(file);

            int lineNumber = 0;
            while (sc.hasNext()) {
                lineNumber ++;
                String nextLine = sc.nextLine();
                switch(nextLine.charAt(0)) {
                case 'T':
                    addTodoToList(nextLine, listOfTasks, lineNumber);
                    break;
                case 'D':
                    addDeadlineToList(nextLine, listOfTasks, lineNumber);
                    break;
                case 'E':
                    addEventToList(nextLine, listOfTasks, lineNumber);
                    break;
                default:
                    System.out.println(
                            String.format("Error in Line %s of storage: Line should begin with T, D or E", lineNumber));
                }
            }
            return listOfTasks;
        } catch (FileNotFoundException e) {
            Ui.showStorageFileNotFoundError(e.getMessage());
            return listOfTasks;
        }
    }

    private void addTodoToList(String line, List<Task> listOfTasks, int lineNumber) {
        String[] splittedLine = line.split(REGEX_FOR_STORAGE);
        try {
            checkStoredTodoValidity(splittedLine, lineNumber);
            Todo todo = new Todo(splittedLine[2]);
            if (splittedLine[1].equals("1")) {
                todo.setCompleted();
            }
            listOfTasks.add(todo);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addEventToList(String line, List<Task> listOfTasks, int lineNumber) {
        String[] splittedLine = line.split(REGEX_FOR_STORAGE);
        try {
            checkStoredEventValidity(splittedLine, lineNumber);
            Event event = new Event(splittedLine[2], splittedLine[3]);
            if (splittedLine[1].equals("1")) {
                event.setCompleted();
            }
            listOfTasks.add(event);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addDeadlineToList(String line, List<Task> listOfTasks, int lineNumber) {
        String[] splittedLine = line.split(REGEX_FOR_STORAGE);
        try {
            checkStoredDeadlineValidity(splittedLine, lineNumber);
            Deadline deadline = new Deadline(splittedLine[2], splittedLine[3]);
            if (splittedLine[1].equals("1")) {
                deadline.setCompleted();
            }
            listOfTasks.add(deadline);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Throws an exception if number of elements of splittedLine is less than expected. It will not throw an
     * exception if more components than expected was present, as the regex might be part of the task name as well.
     * @param splittedLine The result of the storage line splitted by regex.
     * @param lineNumber Line number of the storage line.
     * @param numSegments Number of elements of splittedLine one would normally expect.
     * @throws DukeException DukeException is thrown if there are less elements in splittedLine than expected.
     */
    private static void checkSplittedLineLen(String[] splittedLine, int lineNumber, int numSegments) throws DukeException {
        String errorHeading = String.format("Error in Line %s of storage file: ", lineNumber);
        String errorTail = "\nLine will subsequently be removed.";
        if (splittedLine.length < numSegments) {
            throw new DukeException(errorHeading
                    + String.format("There should be %s segments in storage data", N_SEGMENTS_IN_TODO)
                    + errorTail);
        }
    }

    private static void checkCompletionStatusFormat(String[] splittedLine, int lineNumber) throws DukeException {
        String errorHeading = String.format("Error in Line %s of storage file: ", lineNumber);
        String errorTail = "\nLine will subsequently be removed.";
        if (!(splittedLine[1].equals("0") || splittedLine[1].equals("1"))) {
            throw new DukeException(errorHeading + "Completion status should be 0 or 1" + errorTail);
        }
    }
    private boolean checkStoredTodoValidity(String[] splittedLine, int lineNumber) throws DukeException {
        checkSplittedLineLen(splittedLine, lineNumber, N_SEGMENTS_IN_TODO);
        checkCompletionStatusFormat(splittedLine, lineNumber);
        return true;
    }

    private boolean checkStoredEventValidity(String[] splittedLine, int lineNumber) throws DukeException {
        checkSplittedLineLen(splittedLine, lineNumber, N_SEGMENTS_IN_EVENT);
        checkCompletionStatusFormat(splittedLine, lineNumber);
        return true;
    }

    private boolean checkStoredDeadlineValidity(String[] splittedLine, int lineNumber) throws DukeException {
        checkSplittedLineLen(splittedLine, lineNumber, N_SEGMENTS_IN_DEADLINE);
        checkCompletionStatusFormat(splittedLine, lineNumber);
        return true;
    }
}
