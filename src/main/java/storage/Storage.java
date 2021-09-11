package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;


/**
 * Storage provides dependency injection to access and edit the device's local storage.
 * Data is stored locally in the form of a text (.txt) file.
 */
public class Storage {

    private Path dir;
    private Path fileDir;
    private Ui messages = new Ui();

    /**
     * Constructor of DatabaseEngine.
     */
    public Storage() {
        this.dir = Paths.get(System.getProperty("user.dir"), "database");
        this.fileDir = Paths.get(System.getProperty("user.dir"), "database", "database.txt");
    }

    /**
     * Writes current list to local storage's text file.
     *
     * @param taskList list to be saved.
     */
    public void writeToDatabase(List<Task> taskList) {
        this.deleteDatabase();
        this.createDatabase();

        try {
            FileWriter fw = new FileWriter(String.valueOf(this.fileDir), true);

            List<String> mappedList = taskList.stream().map(task -> {
                String line = "";
                if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    line += Parser.encodeDeadline(deadlineTask);

                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    line += Parser.encodeEvent(eventTask);

                } else {
                    //task instance of Todo
                    Todo todoTask = (Todo) task;
                    line += Parser.encodeTodo(todoTask);
                }
                line += "\n";
                return line;
            }).collect(Collectors.toList());

            for (String task: mappedList) {
                fw.write(task);
            }
            fw.close();

        } catch (IOException e) {
            messages.displayText("Error writing to database.");
        }
    }

    /**
     * Reads from local storage's text file.
     *
     * @return list containing Tasks stored in local storage.
     */
    public List<Task> readFromDatabase() {

        try {
            List<Task> taskList = new ArrayList<Task>();

            File file = new File(String.valueOf(this.fileDir));
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {

                String task = scanner.nextLine();
                String[] dataArr = task.split(" ");

                if (dataArr[0].equals("deadline")) {
                    Deadline deadlineTask = Parser.decodeDeadline(dataArr);
                    taskList.add(deadlineTask);
                } else if (dataArr[0].equals("event")) {
                    Event eventTask = Parser.decodeEvent(dataArr);
                    taskList.add(eventTask);
                } else {
                    Task todoTask = Parser.decodeTodo(dataArr);
                    taskList.add(todoTask);
                }
            }
            scanner.close();
            return taskList;

        } catch (NullPointerException e) {
            System.out.println(e);
            System.out.println("Error: File path was provided as null.");
            return new ArrayList<Task>();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.out.println("Error: File was not found in database.");
            return new ArrayList<Task>();
        }
    }

    /**
     * Creates a text file if not present in local storage.
     */
    public void createDatabase() {
        try {
            File file = new File(String.valueOf(this.fileDir));
            file.createNewFile();
        } catch (IOException e) {
            //should not occur since we are writing to local memory as opposed to network file
            messages.displayText("Error: Unable to create txt file.");
        }
    }

    /**
     * Deletes the text file from storage's directory.
     */
    public void deleteDatabase() {
        File fileDelete = new File(String.valueOf(this.fileDir));
        fileDelete.delete();
    }

    /**
     * Creates a directory if not present in local storage.
     */
    public void createDirectory() {
        try {
            if (!Files.exists(this.dir)) {
                messages.displayText("You currently do not have a directory to save the database. "
                        + "Creating one now at: " + this.dir);
                Files.createDirectory(this.dir);
            }
        } catch (IOException e) {
            messages.displayText("Error: Unable to create the directory.");
        }
    }

}
