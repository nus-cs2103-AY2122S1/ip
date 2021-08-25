package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage is a class that deals with loading and saving tasks from the
 * provided file.
 *
 * @author meerian
 */
public class Storage {
    /**
     * Represents the file to read/write the TaskList to.
     */
    File taskList;

    /**
     * Creates a Storage with the provided file.
     *
     * @param taskList The deadline's description.
     */
    public Storage(File taskList) {
        this.taskList = taskList;
    }

    /**
     * Writes the provided TaskList into the file.
     *
     * @param list the provided task list to write.
     */
    public void write(TaskList list) {
        try {
            FileWriter writer = new FileWriter(taskList);

            for (int i = 0; i < list.size(); i++) {
                String temp = list.get(i).toString();
                writer.write(temp);
                writer.write("\n");
            }

            System.out.println("List saved!");
            writer.close();

        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the provided file and interpreting it into a TaskList.
     *
     * @param list the provided task list to rebuild.
     */
    public void read(TaskList list) {
        try {
            Scanner scanner = new Scanner(taskList);

            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                list.read(Parser.read(temp));
            }

            System.out.println("List loaded successfully!");
            scanner.close();

        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}
