package duke;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.notes.Note;
import duke.item.Item;
import duke.exceptions.DukeException;

/**
 * Represents the hard disk/file that stores all the tasks in the application.
 */
public class Storage {
    private String path;
    private String directory;
    private ArrayList<Task> listOfTasks;
    private ArrayList<Note> listOfNotes;

    /**
     * Creates an object of the Storage class to store all tasks on the hard disk.
     *
     * @param filePath Path of the file that stores all the tasks in the application.
     */
    public Storage(String filePath) {
        this.path = filePath;
        this.directory = filePath.split("/")[0];
        this.listOfTasks = new ArrayList<>();
        this.listOfNotes = new ArrayList<>();
    }

    /**
     * Loads the tasks on the hard drive into an ArrayList when the application restarts.
     *
     * @return Arraylist containing all the tasks in the hard disk.
     */
    private void loadMemory() {
        try {
            if (!isDirectoryPresent()) {
                createDirectory();
            } else if (!isFilePresent()) {
                createFile();
            }
            getData(this.path, this.listOfTasks, this.listOfNotes);
        } catch (IOException e) {
            System.out.println("Could not get the contents of the file!");
        } catch(DukeException e) {
            System.out.println("Error occurred when storing the files from the hard disk to a list");
        }
    }

    /**
     * Gets all the tasks stored in the hard disk.
     *
     * @return ArrayList of tasks stored in the hard disk.
     */
    public ArrayList<Task> getTasksFromMemory() {
        //Only load the memory from hard disk once and since we get tasks from memory first we load it here.
        loadMemory();
        return this.listOfTasks;
    }

    /**
     * Gets all the notes stored in the hard disk.
     *
     * @return Arraylist of notes stored in the hard disk.
     */
    public ArrayList<Note> getNotesFromMemory() {
        return this.listOfNotes;
    }


    private boolean isDirectoryPresent() {
        boolean hasDirectory = Files.exists(Paths.get(this.directory));
        return hasDirectory;
    }

    private boolean isFilePresent() {
        boolean hasFile = Files.exists(Paths.get(this.path));
        return hasFile;
    }

    /**
     * Creates a file if it does not already exist.
     */
    private void createFile() {
        File file = new File(path);
    }

    /**
     * Creates a directory if it does not already exist.
     */
    private void createDirectory() {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch(IOException e) {
            System.out.println("Could not create a directory!");
        }
    }

    /**
     * Gets all the tasks in the hard disk.
     *
     * @param filePath Path of the file that contains all the tasks and notes in the application.
     * @param listOfTasks Arraylist that stores all the tasks.
     * @param listOfNotes Arraylist that stores all the notes.
     * @throws FileNotFoundException If file is not found.
     * @throws DukeException If there is an error in creating a todo, event, deadline task or note.
     */
     private void getData(String filePath, ArrayList<Task> listOfTasks, ArrayList<Note> listOfNotes) throws FileNotFoundException, DukeException {
        File file = new File(filePath);
        assert file.exists();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            try {
                String typeOfData = data.split("/")[0];
                String indicationOfTaskCompletion = data.split("/")[1];
                if(typeOfData.equals("T")) {
                    String todoTask = data.split("/")[2];
                    ToDo todo = new ToDo(todoTask);
                    listOfTasks.add(todo);
                    todo.setTaskCompletionStatus(indicationOfTaskCompletion);
                } else if(typeOfData.equals("D")) {
                    String deadlineTask = data.split("/", 4)[2];
                    String completeBy = data.split("/", 4)[3];
                    Deadline deadline = new Deadline(deadlineTask, completeBy);
                    listOfTasks.add(deadline);
                    deadline.setTaskCompletionStatus(indicationOfTaskCompletion);
                } else if(typeOfData.equals("E")) {
                    String eventTask = data.split("/", 4)[2];
                    String eventAt = data.split("/", 4)[3];
                    Event event = new Event(eventTask, eventAt);
                    listOfTasks.add(event);
                    event.setTaskCompletionStatus(indicationOfTaskCompletion);
                } else if(typeOfData.equals("N")) {
                    String noteDescription = data.split("/")[1];
                    Note note = new Note(noteDescription);
                    listOfNotes.add(note);
                } else {
                    assert false;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Appends a task or note to the end of the file.
     *
     * @param item item to be appended to the end of the file.
     */
    public void appendToFile(Item item) {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("data/duke.txt", true));
            write.append(item.storeItem());
            write.newLine();
            write.close();
        }  catch(IOException e) {
            System.out.println("error occurred when appending task to file!");
        }
    }

    /**
     * Rewrites the entire file from the start.
     *
     * @param tasks The Arraylist storing all the tasks.
     * @param notes The Arraylist storing all the notes.
     */
    public void rewriteFile(ArrayList<Task> tasks, ArrayList<Note> notes) {
        try {
            PrintWriter writer = new PrintWriter("data/duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.println(task.storeItem());
            }
            for (int i = 0; i < notes.size(); i++) {
                Note note = notes.get(i);
                writer.println(note.storeItem());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("error occurred in rewriting entire file!");
        }
    }
}
