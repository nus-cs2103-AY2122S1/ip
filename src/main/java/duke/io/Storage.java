package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Storage class encapsulates a I/O system that reads and writes to an
 * external storage. Currently, it writes to a text file.
 */
public class Storage {
    private static final String HOME = System.getProperty("user.home");
    private static String dukeText;

    /**
     * Constructor for a storage. It takes in a path to the directory of
     * a text file.
     *
     * @param filePath path to the directory of the text file
     */
    public Storage(String filePath) {
        assert filePath != null :"file path should not be null";

        dukeText = filePath;
    }

    /**
     * Reads the text from the text file, deciphers the message, adds them
     * to an ArrayList and returns it.
     *
     * @return an ArrayList of tasks read from the text file
     * @throws DukeException "unable to retrieve file, creating a new list" exception
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedList = new ArrayList<>();
        Scanner listScan;

        try {
            listScan = new Scanner(new File(HOME + dukeText));
        } catch (FileNotFoundException f) {
            throw new DukeException("Unable to retrieve file. Creating a new list!");
        }

        while (listScan.hasNext()) {
            String listInput = listScan.nextLine();
            String[] listInputArray = listInput.split("\\|", 6);

            for (int i = 0; i < listInputArray.length; i++) {
                listInputArray[i] = listInputArray[i].trim();
            }

            switch (listInputArray[0]) {
            case "T":
                ToDo todo = new ToDo(listInputArray[3]);

                if (listInputArray[1].equals("1")) {
                    todo.isDone();
                }

                if (listInputArray[2].equals("N")) {
                    todo.writeNotes(listInputArray[4]);
                    todo.hasNotes();
                }

                loadedList.add(todo);
                break;
            case "D":
                String deadlineInput = listInputArray[3].trim() + "|" + listInputArray[4].trim();
                Deadline deadline = new Deadline(deadlineInput);

                if (listInputArray[1].equals("1")) {
                    deadline.isDone();
                }

                if (listInputArray[2].equals("N")) {
                    deadline.writeNotes(listInputArray[5]);
                    deadline.hasNotes();
                }

                loadedList.add(deadline);
                break;
            case "E":
                String eventInput = listInputArray[3].trim() + " | " + listInputArray[4].trim();
                Event event = new Event(eventInput);

                if (listInputArray[1].equals("1")) {
                    event.isDone();
                }

                if (listInputArray[2].equals("N")) {
                    event.writeNotes(listInputArray[5]);
                    event.hasNotes();
                }

                loadedList.add(event);
                break;
            default:
                break;
            }
        }

        listScan.close();

        return loadedList;

    }

    /**
     * Appends tasks to the text file.
     *
     * @param textToAppend tasks to write to the file
     * @param appendStatus if true, text is appended to text file
     * @throws DukeException "file requested cannot be found" exception
     */
    public void save(String textToAppend, boolean appendStatus) throws DukeException {
        try {
            FileWriter writeToFile = new FileWriter(HOME + dukeText, appendStatus);
            writeToFile.write(textToAppend);
            writeToFile.close();
        } catch (IOException e) {
            throw new DukeException("The file that you requested cannot be found.");
        }
    }

    /**
     * Overrides and re-writes a new taskList to the text file.
     *
     * @param taskList new taskList to re-write to the text file
     * @throws DukeException "file requested cannot be found" exception
     */
    public void rewrite(TaskList taskList) throws DukeException {
        try {
            FileWriter rewriteFile = new FileWriter(HOME + dukeText);
            rewriteFile.write(taskList.refreshList());
            rewriteFile.close();
        } catch (IOException e) {
            throw new DukeException("The file that you requested cannot be found.");
        }
    }
}
