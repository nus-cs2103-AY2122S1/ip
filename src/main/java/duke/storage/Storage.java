/**
 * This class handles the storage of the current list (i.e. the updating and retrieval).
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeDataLoadException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


public class Storage {

    private final ArrayList<String> commandsSaved = new ArrayList<>(); // array of saved lines in the new txt file
    private final File file;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The filePath where the file is found or will be created.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads the existing file for the todo list if it exists, or creates a file if it does not.
     *
     * @return an ArrayList of tasks for the todo list
     * @throws IOException If there are errors processing the file.
     * @throws DukeDataLoadException If the save file cannot be read properly
     */
    public ArrayList<Task> load() throws IOException, DukeDataLoadException {

        if (!file.exists()) {
            // Create the data folder if it does not exist.
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            file.createNewFile(); // Create the duke.txt file.
            return new ArrayList<Task>();
        }

        if (file.length() == 0) {
            return new ArrayList<Task>();
        }

        return readFileAddTasks();
    }

    /**
     * Reads the saved file and adds tasks to the tasklist.
     *
     * @return An ArrayList of tasks read from the save file.
     * @throws FileNotFoundException If saved file cannot be found.
     * @throws DukeDataLoadException If the data could not be loaded.
     */
    private ArrayList<Task> readFileAddTasks() throws FileNotFoundException, DukeDataLoadException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner fileSc = new Scanner(file);

        while (fileSc.hasNext()) {
            String nextLine = fileSc.nextLine();

            // Interprets each line of the file / each command
            String[] txtFileCmd = nextLine.split("@");
            String taskType = txtFileCmd[0];
            boolean taskState = Integer.parseInt(txtFileCmd[1]) != 0; // 0 for not done, 1 for done
            String taskInfo = txtFileCmd[2];
            String dateTime = txtFileCmd[3];
            ArrayList<String> taskTags = formTagList(txtFileCmd[4]);

            addExistingTask(tasks, taskType, taskState, taskInfo, dateTime, taskTags);
            commandsSaved.add(nextLine);
        }

        fileSc.close();
        return tasks;
    }


    /**
     * Adds a Task parsed from the save file (data/duke.txt) into an ArrayList of tasks.
     *
     * @param tasks The ArrayList of tasks to which the saved task will be added.
     * @param taskType The type of task to be added.
     * @param taskState Whether the task has been completed.
     * @param taskInfo Description of the task.
     * @param dateTime Additional task information. dateBy for Deadline; eventDetails for Events; Empty for Todo
     * @param taskTags Tags attached to the task.
     * @throws DukeDataLoadException The exception is thrown when the task is not recognised.
     */
    private void addExistingTask(ArrayList<Task> tasks, String taskType, boolean taskState, String taskInfo,
                                 String dateTime, ArrayList<String> taskTags) throws DukeDataLoadException {

        // Checks the task type (i.e. deadline, todo or event) and add them to tasks respectively
        switch (taskType) {
        case "T": {
            tasks.add(new Todo(taskInfo, taskState, taskTags));
            break;

        }
        case "D": {
            // String dateBy = txtFileCmd[3];
            tasks.add(new Deadline(taskInfo, LocalDateTime.parse(dateTime), taskState, taskTags));
            break;

        }
        case "E": {
            // String eventDetails = txtFileCmd[3];
            String startDateTime = dateTime.split("~")[0];
            String endTime = dateTime.split("~")[1];

            tasks.add(new Event(taskInfo, LocalDateTime.parse(startDateTime), LocalTime.parse(endTime),
                    taskState, taskTags));
            break;
        }
        default:
            throw new DukeDataLoadException("The task is not recognized!");
        }
    }

    /**
     * Forms an ArrayList of String called tags from a String of tags.
     *
     * @param tags the String of tags to be put into the ArrayList
     * @return the ArrayList formed from the tags
     */
    private ArrayList<String> formTagList(String tags) {
        String[] splitTags = tags.split(" ");
        if (splitTags.length == 0) {
            return new ArrayList<String>();
        } else {
            ArrayList<String> tagList = new ArrayList<>();
            for (String s : splitTags) {
                tagList.add(s);
            }
            return tagList;
        }
    }

    /**
     * Updates the file.
     *
     * @throws FileNotFoundException If the file is not found.
     */
    private void update() throws FileNotFoundException {
        StringBuilder txt = new StringBuilder();
        for (String s : commandsSaved) {
            txt.append(s).append("\n");
        }

        PrintWriter pw = new PrintWriter(file);
        pw.append(txt.toString());
        pw.flush();
    }

    /**
     * Updates the textArr element after it has been marked as "done".
     *
     * @param ref The index of the item in the textArr being referenced to
     * @param s The string to be added to the textArr
     * @throws IOException If there are errors processing the file.
     */
    public void updateDone(int ref, String s) throws IOException {
        commandsSaved.set(ref, s);
        update();
    }

    /**
     * Adds a task to the commandsSaved.
     *
     * @param s The string to be added to the commandsSaved.
     * @throws IOException If there are errors processing the file.
     */
    public void addTask(String s) throws IOException {
        commandsSaved.add(s);
        update();
    }

    /**
     * Adds a task to the commandsSaved.
     *
     * @param ref The index of the item in the commandsSaved being referenced to add the tag to.
     * @param task The string to be added to the commandsSaved.
     * @throws IOException If there are errors processing the file.
     */
    public void addTag(int ref, String task) throws IOException {
        commandsSaved.set(ref, task);
        update();
    }

    /**
     * Removes the task referenced to from commandsSaved.
     *
     * @param ref The index of the item in the commandsSaved being referenced to be removed.
     * @throws IOException If there are errors processing the file.
     */
    public void removeTask(int ref) throws IOException {
        commandsSaved.remove(ref);
        update();
    }
}
