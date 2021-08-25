/**
 * This class handles the storage of the current list (i.e. the updating and retrieval).
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.storage;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final ArrayList<String> commandsSaved = new ArrayList<>(); // array of saved lines in the new txt file
    private final File FILE;

    public Storage(String filePath) {
        FILE = new File(filePath);
    }

    /**
     * Loads the existing file for the todo list if it exists, or creates a file if it does not.
     *
     * @return an ArrayList of tasks for the todo list
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {

        final ArrayList<Task> tasks = new ArrayList<>();

        // If the file exists, print out the previous data if it exists
        if (FILE.exists()) {

            if (FILE.length() == 0) {
                System.out.println("Oops! Looks like you don't have anything saved :(");

            } else {
                System.out.println("Here's your progress:");
                Scanner file_sc = new Scanner(FILE);


                while (file_sc.hasNext()) {
                    String nextLine = file_sc.nextLine();

                    // Interprets each line of the file / each command
                    String[] txtFileCmd = nextLine.split("@");
                    String taskType = txtFileCmd[0];
                    boolean taskState = Integer.parseInt(txtFileCmd[1]) != 0;  // 0 for not done, 1 for done
                    String taskInfo = txtFileCmd[2];

                    commandsSaved.add(nextLine);

                    // Checks the task type (i.e. deadline, todo or event) and add them to tasks respectively
                    switch (taskType) {
                    case "T": {
                        tasks.add(new Todo(taskInfo, taskState));
                        break;

                    }
                    case "D": {
                        String dateBy = txtFileCmd[3];
                        tasks.add(new Deadline(taskInfo, LocalDate.parse(dateBy), taskState));
                        break;

                    }
                    case "E": {
                        String eventDetails = txtFileCmd[3];
                        tasks.add(new Event(taskInfo, eventDetails, taskState));
                        break;
                    }

                    }
                }
                file_sc.close();

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
     * Updates the file.
     *
     * @throws FileNotFoundException
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
     * @throws IOException
     */
    public void updateDone(int ref, String s) throws IOException {
        commandsSaved.set(ref, s);
        update();
    }

    /**
     * Adds a task to the commandsSaved.
     *
     * @param s The string to be added to the commandsSaved.
     * @throws IOException
     */
    public void addTask(String s) throws IOException {
        commandsSaved.add(s);
        update();
    }

    /**
     * Removes the task referenced to from commandsSaved.
     *
     * @param ref The index of the item in the commandsSaved being referenced to be removed.
     * @throws IOException
     */
    public void removeTask(int ref) throws IOException{
        commandsSaved.remove(ref);
        update();
    }
}
