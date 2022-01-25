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
    private final File taskList;

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
    public String write(TaskList list) {
        try {
            assert taskList.canWrite() : "Error! File cannot be written!";
            FileWriter writer = new FileWriter(taskList);

            for (int i = 0; i < list.size(); i++) {
                String temp = list.get(i).toString();
                writer.write(temp);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
        return Ui.saveList();
    }

    /**
     * Reads the provided file and interpreting it into a TaskList.
     *
     * @param list the provided task list to rebuild.
     */
    public void read(TaskList list) {
        try {
            assert taskList.canRead() : "Error! File cannot be read!";
            Scanner scanner = new Scanner(taskList);

            while (scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                list.add(Parser.read(temp));
            }
            scanner.close();
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}
