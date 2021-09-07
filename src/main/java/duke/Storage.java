package duke;

import duke.exception.DukeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidTaskException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that deals with loading and saving data to/from a file.
 *
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor of a storage.
     *
     * @param filePath A path that access the location of the file to be open and used.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to load any existing data from file to the current list
     *
     * @return ArrayList of current tasks.
     * @throws IOException thrown when file cannot be found.
     * @throws InvalidTaskException thrown when users give invalid input.
     * @throws InvalidDeadlineException thrown when users give invalid deadlines.
     */

    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);

        assert filePath != null: "filePath must not be null!";
        if (file.exists()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] stuff = str.split(" \\| ");
                Task task;
                switch (stuff[0]) {
                case "T":
                    task = new ToDos(stuff[2]);
                    break;
                case "D":
                    task = new Deadline(stuff[2], stuff[3]);
                    break;
                case "E":
                    task = new Event(stuff[2], stuff[3]);
                    break;
                default:
                    throw new InvalidTaskException();
                }

                if (stuff[1].equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
            }
        }
        return list;
    }

    /**
     * Method to update file when TaskList is changed.
     *
     * @param list TaskList after it is changed.
     * @throws IOException thrown when file is not found.
     */
    public void updateFile(TaskList list) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            return;
        }
        file.delete();
        File newFile = new File(filePath);
        newFile.createNewFile();
        FileWriter writer = new FileWriter(file);

        for (Task task : list.getList()) {
            writer.write(task.writeTask() + "\n");
        }
        writer.close();

    }
}
