package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final File data = new File("./src/main/data/duke.txt");
    private static final TaskList tasks = new TaskList();

    public Storage() {
    }

    /**
     * Loads the saved task list from file in hard disk into Duke.
     * @return the saved task list
     * @throws DukeException If the file is not found
     */
    public static TaskList load() throws DukeException {
        try {
            Scanner scanner = new Scanner(data);
            while (scanner.hasNextLine()) {
                tasks.readData(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("ERROR: File not found! :(");
        }
        return tasks;
    }

    /**
     * Saves the user's final task list into a file in hard disk.
     */
    public static void save() {
        try {
            FileWriter myFile = new FileWriter(data);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                myFile.write(task.print() + "\n");
            }
            myFile.close();
        } catch (IOException e) {
            System.out.println("A new file was created! RIBBIT!");
        }
    }
}
