package duke;

import duke.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class that encapsulates all the methods relevant to storing of the tasks in a hard drive.
 */
public class Storage {

    private final File file;

    /**
     * Pubic constructor for storage class that takes in a file to write data to.
     *
     * @param file File data is written to.
     */
    public Storage(File file) {
        this.file = file;
    }

    /**
     * Getter to get the file.
     *
     * @return The file.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Method that loads the file, by creating it if it does not exist and adds the format
     * of the file to the top and clears all previous data before starting.
     *
     * @return Printwriter instance to write data into file.
     * @throws IOException If file cant be created.
     */
    public PrintWriter load() throws IOException {
        PrintWriter writer = new PrintWriter(this.file);

        if (this.file.createNewFile()) {
            System.out.println("New file created");
        } else {
            System.out.println("Data file already exists. No new file created.");
            writer.flush(); // ensures that file is empty before starting
            writer.println("Format is as follows: " +
                    "[Task Type][X if completed, else empty] {task description}");
            writer.println("If the task has been deleted, it will be shown as: " +
                    "[Task Type][X if completed, else empty] {task description} [deleted]");
        }
        return writer;
    }

    /**
     * Method that writes data into the file.
     * @param writer Printwriter to write data into file.
     * @param task Task to be written to file.
     */
    public static void addData(PrintWriter writer, Task task) {
        writer.println(task.toString());
        writer.flush();
    }


    /**
     * Method that edits tasks in the file and marks them as deleted.
     *
     * @param file File data is written to.
     * @param task Task to be deleted from file.
     */
    public static void markAsDeleted(File file, Task task) {
        try {
            Scanner sc = new Scanner(file);
            StringBuffer buffer = new StringBuffer();

            while (sc.hasNext()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }

            String fileContents = buffer.toString();
            sc.close();

            String oldLine = task.toString();
            String newLine = oldLine + " [deleted]";
            fileContents = fileContents.replace(oldLine, newLine);

            FileWriter wr = new FileWriter(file);
            wr.append(fileContents);
            wr.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method that edits tasks in file and marks them as completed.
     *
     * @param file File data is written to.
     * @param task Task to be updated as completed in file.
     * @param oldLine Initial description of the task.
     */
    public static void saveAsCompleted(File file, Task task, String oldLine) {
        try {
            Scanner sc = new Scanner(file);
            StringBuffer buffer = new StringBuffer();

            while (sc.hasNext()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }

            String fileContents = buffer.toString();
            sc.close();

            String newLine = task.toString();
            fileContents = fileContents.replace(oldLine, newLine);

            FileWriter wr = new FileWriter(file);
            wr.append(fileContents);
            wr.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

