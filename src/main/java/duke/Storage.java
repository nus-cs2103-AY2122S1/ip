package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The type Storage to save and load data.
 */
public class Storage {

    /** filepath to save and load data from */
    private final String filePath;
    /** list of tasks to save data to or to load data from */
    private final TaskList tasks;

    /**
     * Instantiates a new Storage.
     *
     * @param tasks the tasks.
     */
    public Storage(TaskList tasks) {
        this.tasks = tasks;
        // default save file filepath
        filePath = "./duke.txt";
    }

    /**
     * Instantiates a new Storage.
     *
     * @param tasks    the list of tasks.
     * @param filePath the file path.
     */
    public Storage(TaskList tasks, String filePath) {
        this.tasks = tasks;
        this.filePath = filePath;
    }

    /**
     * Loads the data from the specified file path.
     *
     * @throws IOException the io exception when the file path is not valid.
     */
    public void load() throws IOException {
        File saveFile = new File(filePath);
        saveFile.createNewFile();
        Scanner saveReader = new Scanner(saveFile);
        while (saveReader.hasNextLine()) {
            String inputRead = saveReader.nextLine();
            String[] inputs = inputRead.split("[|]");

            switch (inputs[0]) {
            case "T":
                tasks.add(new Todo(inputs[2]));
                break;
            case "D":
                if (inputs.length == 4) {
                    tasks.add(new Deadline(inputs[2], inputs[3]));
                    break;
                }
                tasks.add(new Deadline(inputs[2], inputs[3], inputs[4]));
                break;
            case "E":
                if (inputs.length == 4) {
                    tasks.add(new Event(inputs[2], inputs[3]));
                    break;
                }
                tasks.add(new Event(inputs[2], inputs[3], inputs[4]));
                break;
            }

            if (inputs[1].equals("1")) {
                tasks.markDone(tasks.size() - 1);
            }
        }
        saveReader.close();
    }

    /**
     * Saves the date to the specified file path.
     *
     * @throws IOException the io exception when the file path is not valid.
     */
    public void save() throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        for (int i = 0; i < tasks.size(); i++) {
            writer.write(tasks.get(i).databaseString() + "\n");
        }
        writer.close();
    }
}
