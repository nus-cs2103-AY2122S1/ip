/**
 * This class handles the storage of the current list (i.e. the updating and retrieval).
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


public class Storage {

    private final ArrayList<String> commandsSaved = new ArrayList<>(); // array of saved lines in the new txt file
    private final File FILE;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The filePath where the file is found or will be created.
     */
    public Storage(String filePath) {
        FILE = new File(filePath);
    }

    /**
     * Loads the existing file for the todo list if it exists, or creates a file if it does not.
     *
     * @return an ArrayList of tasks for the todo list
     * @throws IOException If there are errors processing the file.
     */
    public ArrayList<Task> load() throws IOException {

        final ArrayList<Task> tasks = new ArrayList<>();

        // If the file exists, print out the previous data if it exists
        if (FILE.exists()) {

            if (FILE.length() == 0) {
                System.out.println("Oops! Looks like you don't have anything saved :(");

            } else {
                System.out.println("Here's your progress:");
                Scanner fileSc = new Scanner(FILE);

                while (fileSc.hasNext()) {
                    String nextLine = fileSc.nextLine();

                    // Interprets each line of the file / each command
                    String[] txtFileCmd = nextLine.split("@");
                    String taskType = txtFileCmd[0];
                    boolean taskState = Integer.parseInt(txtFileCmd[1]) != 0;  // 0 for not done, 1 for done
                    String taskInfo = txtFileCmd[2];
                    ArrayList<String> taskTags = formTagList(txtFileCmd[4]);

                    // This is either the dateBy for Deadline or eventDetails for Events. Empty field for Todo.
                    String moreTaskInfo = txtFileCmd[3];


                    commandsSaved.add(nextLine);

                    // Checks the task type (i.e. deadline, todo or event) and add them to tasks respectively
                    switch (taskType) {
                    case "T": {
                        tasks.add(new Todo(taskInfo, taskState, taskTags));
                        break;

                    }
                    case "D": {
                        // String dateBy = txtFileCmd[3];
                        tasks.add(new Deadline(taskInfo, LocalDate.parse(moreTaskInfo), taskState, taskTags));
                        break;

                    }
                    case "E": {
                        // String eventDetails = txtFileCmd[3];
                        tasks.add(new Event(taskInfo, moreTaskInfo, taskState, taskTags));
                        break;
                    }
                    default:

                    }
                }
                fileSc.close();

                for (int count = 0; count < tasks.size(); count++) {
                    System.out.println((count + 1) + ". " + tasks.get(count).toString());
                }
            }

        // If the file does not exist, create a new file
        } else {
            System.out.println("Welcome new user!");
            System.out.println("Let me create a save file for you :)");
            if (!FILE.getParentFile().exists()) {
                FILE.getParentFile().mkdir();
            }
            FILE.createNewFile();
        }

        return tasks;
    }

    /**
     * Forms an ArrayList<String> tags from a String tags.
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
//    private static ArrayList<String> formTagList(String tags) {
//        String[] splitTags = tags.split(" ");
//        return (ArrayList<String>) Arrays.asList(splitTags);
//    }

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

        PrintWriter pw = new PrintWriter(FILE);
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
