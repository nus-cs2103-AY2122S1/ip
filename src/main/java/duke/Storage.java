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

public class Storage {
//    public static final String DEFAULT_DIRECTORY_NAME = "data/";
//    public static final String DEFAULT_PATH_NAME = "data/duke.txt";
//
//    public final String dirName;
//    public final String pathName;
    public final File file;

//    public Storage() throws InvalidPathException {
//        this.pathName = DEFAULT_PATH_NAME;
//        this.dirName = DEFAULT_DIRECTORY_NAME;
//    }
    public Storage(File file) {
        this.file = file;
    }

//    public Storage(String dirName, String fileName) throws InvalidPathException{
//        this.fileName = fileName;
//        this.dirName = dirName;
//    }

    public PrintWriter load(File file) throws IOException {
        PrintWriter writer = new PrintWriter(file);

        if (file.createNewFile()) {
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

    public static void addData(PrintWriter writer, Task task) {
        writer.println(task.toString());
        writer.flush();
    }


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

