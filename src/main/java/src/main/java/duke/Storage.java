package src.main.java.duke;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Represents the storage unit of the chat bot and contains all
 * operations regarding the data storage of the bot.
 */

public class Storage {

    /**
     * path of the file in the hard disk.
     */
    private String path;
    private TaskList list;

    Storage(String directoryName, String fileName, TaskList list) {
        this.list = list;
        File directory = new File(directoryName);
        if (!directory.exists()) {
            try {
                directory.mkdirs();
            } catch (Exception error) {
                System.out.println("Failed to create directory");
            }
        }
        String path = directoryName + "/" + fileName;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Failed to create a File");
            }
        }
        this.path = directoryName + "/" + fileName;
    }

    /**
     * method to read from the a text file on the system.
     */
    void readFile() throws DukeException {
        File file = new File(this.path);
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] data = task.split(" \\| ");
                boolean isDone = data[1].equals("1");

                addToList(data, isDone);
            }
        } catch (IOException e) {
            System.out.println("Failed to read from file.");
        }

    }

    /**
     * method to add the given data to the list of tasks.
     *
     * @param data   the task in string to be added.
     * @param isDone check if the task is done or not.
     * @throws DukeException
     */
    void addToList(String[] data, boolean isDone) throws DukeException {
        if (data[0].equals("T")) {
            System.out.println(data[2]);
            Todo todo = new Todo(data[2], isDone);
            list.add(todo);
        } else if (data[0].equals("E")) {
            Event event = new Event(data[2], isDone, data[3]);
            list.add(event);
        } else {
            Deadline deadline = new Deadline(data[2], isDone, data[3]);
            list.add(deadline);
        }
    }

    /**
     * method to write the data to the text file.
     */
    void writeToFile() {
        try {
            Files.write(Paths.get(this.path), list.convertListtoStringList(), StandardCharsets.UTF_8);
        } catch (IOException error) {
            System.out.println("Failed to write task to file");
        }
    }
}
