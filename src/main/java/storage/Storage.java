package main.java.storage;

import main.java.parser.Parser;
import main.java.task.Deadline;
import main.java.task.Event;
import main.java.task.Task;
import main.java.task.Todo;
import main.java.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                    /*
                    String encodedString = Base64.getEncoder().encodeToString(deadlineTask.getDescription().getBytes());
                    line += "deadline " + deadlineTask.getIsDone() + " " + deadlineTask.getBy() + " " + encodedString;
                     */
                    line += Parser.encodeDeadline(deadlineTask);

                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    /*
                    String encodedDescription = Base64.getEncoder().encodeToString(eventTask.getDescription().getBytes());
                    String encodedAt = Base64.getEncoder().encodeToString(eventTask.getAt().getBytes());
                    line += "event " + eventTask.getIsDone() + " " + encodedAt + " " + encodedDescription;
                    */

                    line += Parser.encodeEvent(eventTask);

                } else {
                    //task instance of Todo
                    Todo todoTask = (Todo) task;
                    /*
                    String encodedString = Base64.getEncoder().encodeToString(task.getDescription().getBytes());
                    line += "todo " + task.getIsDone() + " " + encodedString;
                     */
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
                    Boolean isDone = Boolean.valueOf(dataArr[1]);
                    LocalDate by = LocalDate.parse(dataArr[2]);
                    String description = new String(Base64.getDecoder().decode(dataArr[3]));

                    Deadline deadlineTask = new Deadline(description, by);
                    if (isDone) {
                        deadlineTask.markDone();
                    }
                    taskList.add(deadlineTask);
                }

                else if (dataArr[0].equals("event")) {
                    Boolean isDone = Boolean.valueOf(dataArr[1]);
                    String at = new String(Base64.getDecoder().decode(dataArr[2]));
                    String description = new String(Base64.getDecoder().decode(dataArr[3]));

                    Event eventTask = new Event(description, at);
                    if (isDone) {
                        eventTask.markDone();
                    }
                    taskList.add(eventTask);

                }

                else {
                    Boolean isDone = Boolean.valueOf(dataArr[1]);
                    String description = new String(Base64.getDecoder().decode(dataArr[2]));

                    Todo todoTask = new Todo(description);
                    if (isDone) {
                        todoTask.markDone();
                    }
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
            if(!Files.exists(this.dir)) {
                messages.displayText("You currently do not have a directory to save the database. " +
                        "Creating one now at: " + this.dir);
                Files.createDirectory(this.dir);
            }
        } catch (IOException e) {
            messages.displayText("Error: Unable to create the directory.");
        }
    }

}
