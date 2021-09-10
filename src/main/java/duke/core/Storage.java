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
        final String REGEX = "/";
        String[] splittedLine = line.split(REGEX);
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
        final String REGEX = "/";
        String[] splittedLine = line.split(REGEX);
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
        final String REGEX = "/";
        String[] splittedLine = line.split(REGEX);
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

    private boolean checkStoredTodoValidity(String[] splittedLine, int lineNumber) throws DukeException {
        String errorHeading = String.format("Error in Line %s of storage file: ", lineNumber);
        if (splittedLine.length != 3) {
            throw new DukeException(errorHeading
                    + String.format("There should be %s segments in storage data", N_SEGMENTS_IN_TODO));
        }
        if (!(splittedLine[1].equals("0") || splittedLine[1].equals("1"))) {
            throw new DukeException(errorHeading + "Completion status should be 0 or 1");
        }
        return true;
    }

    private boolean checkStoredEventValidity(String[] splittedLine, int lineNumber) throws DukeException {
        String errorHeading = String.format("Error in Line %s of storage file: ", lineNumber);
        if (splittedLine.length != N_SEGMENTS_IN_EVENT) {
            throw new DukeException(errorHeading
                    + String.format("There should be %s segments in storage data", N_SEGMENTS_IN_EVENT));
        }
        if (!(splittedLine[1].equals("0") || splittedLine[1].equals("1"))) {
            throw new DukeException(errorHeading + "Completion status should be 0 or 1");
        }
        return true;
    }

    private boolean checkStoredDeadlineValidity(String[] splittedLine, int lineNumber) throws DukeException {
        String errorHeading = String.format("Error in Line %s of storage file: ", lineNumber);
        if (splittedLine.length != N_SEGMENTS_IN_DEADLINE) {
            throw new DukeException(errorHeading
                    + String.format("There should be %s segments in storage data", N_SEGMENTS_IN_DEADLINE));
        }
        if (!(splittedLine[1].equals("0") || splittedLine[1].equals("1"))) {
            throw new DukeException(errorHeading + "Completion status should be 0 or 1");
        }
        return true;
    }

}
