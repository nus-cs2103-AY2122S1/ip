package duke.io;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        dukeText = filePath;
    }

    /**
     * Reads the text from the text file, deciphers the message, adds them
     * to an ArrayList and returns it.
     *
     * @return an ArrayList of tasks read from the text file
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedList = new ArrayList<>();
        Scanner listScan;

        try{
            listScan = new Scanner(new File(HOME + dukeText));
        } catch (FileNotFoundException f) {
            throw new DukeException("Unable to retrieve file. Creating a new list!");
        }

        while (listScan.hasNext()) {
            String listInput = listScan.nextLine();
            String[] listInputArray = listInput.split("\\|", 3);
            for (int i = 0; i < listInputArray.length; i++) {
                listInputArray[i] = listInputArray[i].trim();
            }

            switch (listInputArray[0]) {
            case "T":
                ToDo todo = new ToDo(listInputArray[2]);
                if (listInputArray[1].equals("1")) {
                    todo.isDone();
                }
                loadedList.add(todo);
                break;
            case "D":
                String[] deadlineTask = listInputArray[2].split("\\|", 2);
                String deadlineInput = deadlineTask[0].trim() + "|" + deadlineTask[1].trim();
                Deadline deadline = new Deadline(deadlineInput);
                if (listInputArray[1].equals("1")) {
                    deadline.isDone();
                }
                loadedList.add(deadline);
                break;
            case "E":
                String[] eventTask = listInputArray[2].split("\\|", 2);
                String eventInput = eventTask[0] + " / " + eventTask[1];
                Event event = new Event(eventInput);
                if (listInputArray[1].equals("1")) {
                    event.isDone();
                }
                loadedList.add(event);
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
     * @throws DukeException
     */
    public void save(String textToAppend, boolean appendStatus) throws DukeException {
        try{
            FileWriter writeToFile = new FileWriter(HOME + dukeText, appendStatus);
            writeToFile.write(textToAppend);
            writeToFile.close();
        } catch (IOException i) {
            throw new DukeException("The file that you requested cannot be found.");
        }

    }

    /**
     * Overrides and re-writes a new taskList to the text file.
     *
     * @param taskList new taskList to re-write to the text file
     * @throws DukeException
     */
    public void rewrite(TaskList taskList) throws DukeException{
        try {
            FileWriter rewriteFile = new FileWriter(HOME + dukeText);
            rewriteFile.write(taskList.refreshList());
            rewriteFile.close();
        } catch (IOException i) {
            throw new DukeException("The file that you requested cannot be found.");
        }

    }
}
