package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private final File data;
    private final TaskList tasks;

    public Storage(String filePath) {
        this.data = new File(filePath);
        this.tasks = new TaskList();
    }

    /**
     * Loads the saved task list from file in hard disk into Duke.
     * @return the saved task list
     * @throws DukeException If the file is not found
     */
    public TaskList load() throws DukeException {
        try {
            Scanner s = new Scanner(this.data);
            while (s.hasNextLine()) {
                tasks.readData(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("ERROR: File not found! :(");
        }
        return this.tasks;
    }

    /**
     * Saves the user's final task list into a file in hard disk.
     */
    public void save() {
        try {
            FileWriter myFile = new FileWriter(this.data);
            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = this.tasks.get(i);
                myFile.write(task.print() + "\n");
            }
            myFile.close();
        } catch (IOException e) {
            System.out.println("A new file was created! RIBBIT!");
        }
    }
}
