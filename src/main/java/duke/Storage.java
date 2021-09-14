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
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.notes.Note;
import duke.exceptions.DukeException;

/**
 * Represents the hard disk/file that stores all the tasks in the application.
 */
public class Storage {
    private String path;

    /**
     * Creates an object of the Storage class to store all tasks on the hard disk.
     *
     * @param filePath Path of the file that stores all the tasks in the application.
     */
    public Storage(String filePath) {
        this.path = filePath;
    }

    /**
     * Loads the tasks on the hard drive into an ArrayList when the application restarts.
     *
     * @return Arraylist containing all the tasks in the hard disk.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<Note> listOfNotes = new ArrayList<>();
        try {
            boolean hasDirectory = Files.exists(Paths.get("data"));
            boolean hasFile = Files.exists(Paths.get("data/duke.txt"));
            if (!hasDirectory) {
                createDirectory();
            } else if (!hasFile) {
                createFile();
            }
            getData("data/duke.txt", listOfTasks, listOfNotes);
            checkEmptyTasksList(listOfTasks);
        } catch (IOException e) {
            System.out.println("Could not get the contents of the file!");
        } finally {
            return listOfTasks;
        }
    }

    public ArrayList<Note> loadNotes() {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<Note> listOfNotes = new ArrayList<>();
        try {
            boolean hasDirectory = Files.exists(Paths.get("data"));
            boolean hasFile = Files.exists(Paths.get("data/duke.txt"));
            if (!hasDirectory) {
                createDirectory();
            } else if (!hasFile) {
                createFile();
            }
            getData("data/duke.txt", listOfTasks, listOfNotes);
            checkEmptyTasksList(listOfTasks);
        } catch (IOException e) {
            System.out.println("Could not get the contents of the file!");
        } finally {
            return listOfNotes;
        }
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
     * Checks if the task list is empty.
     */
    private void checkEmptyTasksList(ArrayList<Task> listOfTasks) {
        try {
            if(listOfTasks.size() != 0) {
                System.out.println("There are no tasks");
            }
            printFile("data/duke.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    /**
     * Gets all the tasks in the hard disk.
     *
     * @param filePath Path of the file that contains all the tasks in the application.
     * @param list Arraylist that stores all the tasks.
     * @throws FileNotFoundException If file is not found.
     * @throws DukeException If there is an error in creating a todo, event, deadline task.
     */
     private void getData(String filePath, ArrayList<Task> list, ArrayList<Note> notesList) throws FileNotFoundException, DukeException {
        File file = new File(filePath);
        assert file.exists();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            try {
                String typeOfData = s.split("/")[0];
                String indicationOfTaskCompletion = s.split("/")[1];
                if(typeOfData.equals("T")) {
                    String todoTask = s.split("/")[2];
                    ToDo todo = new ToDo(todoTask);
                    list.add(todo);
                    todo.getTaskCompletionStatus(indicationOfTaskCompletion);
                } else if(typeOfData.equals("D")) {
                    String deadlineTask = s.split("/", 4)[2];
                    String completeBy = s.split("/", 4)[3];
                    Deadline deadline = new Deadline(deadlineTask, completeBy);
                    list.add(deadline);
                    deadline.getTaskCompletionStatus(indicationOfTaskCompletion);
                } else if(typeOfData.equals("E")) {
                    String eventTask = s.split("/", 4)[2];
                    String eventAt = s.split("/", 4)[3];
                    Event event = new Event(eventTask, eventAt);
                    list.add(event);
                    event.getTaskCompletionStatus(indicationOfTaskCompletion);
                } else {
                    String noteDescription = s.split("/")[1];
                    Note note = new Note(noteDescription);
                    notesList.add(note);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prints all tasks in the file when the application restarts.
     *
     * @param path Path of the file containing all the tasks.
     * @throws FileNotFoundException If file is not found.
     */
    private void printFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }

    /**
     * Appends a task to the end of the file storing all the tasks.
     *
     * @param task Task to be appended to the end of the file.
     */
    public void appendToFile(Task task) {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("data/duke.txt", true));
            write.append(task.storeTask());
            write.newLine();
            write.close();
        }  catch(IOException e) {
            System.out.println("error occurred when appending task to file!");
        }
    }

    public void appendToFile(Note note) {
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter("data/duke.txt", true));
            write.append(note.storeNote());
            write.newLine();
            write.close();
        }  catch(IOException e) {
            System.out.println("error occurred when appending note to file!");
        }
    }

    /**
     * Rewrites the entire file from the start.
     *
     * @param tasks The Arraylist storing all the tasks.
     */
    public void rewriteFile(ArrayList<Task> tasks) {
        try {
            PrintWriter writer = new PrintWriter("data/duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.println(task.storeTask());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("error occurred in rewriting entire file!");
        }
    }
}
