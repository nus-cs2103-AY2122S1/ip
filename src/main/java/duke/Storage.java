package duke;

import duke.exception.DukeUnableLoadTask;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the storage file that stores the user's tasklist.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Storage {
    private final String FILE_PATH;

    /**
     * Constructor for Storage.
     * @param filePath file path to the storage file.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Saves tasks from the given list of tasks to the storage.
     * @param listOfTasks the list of tasks keyed in by the user.
     * @throws IOException if there is an error in writing the file.
     */
    public void saveTasks(TaskList listOfTasks) throws IOException {
        StringBuffer tasksToWrite = new StringBuffer();
        consolidateTasks(listOfTasks, tasksToWrite);
        writeTasksToFile(tasksToWrite);
    }

    private void consolidateTasks(TaskList listOfTasks, StringBuffer tasksToWrite) {
        int len = listOfTasks.getTotalNumber();

        for (int i = 0; i < len; i++) {
            tasksToWrite.append(listOfTasks.getTask(i).saveToFile() + "\n");
        }
    }

    private void writeTasksToFile(StringBuffer tasksToWrite) throws IOException {
        FileWriter fileWriter = new FileWriter(this.FILE_PATH, false);
        fileWriter.write(tasksToWrite.toString());
        fileWriter.close();
    }

    /**
     * Loads tasks from the storage file into the given Duke chatbot.
     * @param duke Duke chatbot that the user is interacting with.
     * @throws IOException if there is an error in reading the file.
     * @throws DukeUnableLoadTask if there is a corruption in tasks of the file.
     */
    public void loadSavedTasks(Duke duke) throws IOException, DukeUnableLoadTask {
        File f = new File(this.FILE_PATH);
        Scanner sc = new Scanner(f); // Scanner with file as source
        processTasksToDuke(sc, duke);
    }

    private void processTasksToDuke(Scanner sc, Duke duke) {
        int i = 1; // index counter

        while (sc.hasNextLine()) {
            String entry = sc.nextLine();
            String[] pastEntry = entry.split(" \\| ");

            String taskType = pastEntry[0];
            String isDone = pastEntry[1];

            switch (taskType) {
            case "T":
                duke.addTaskToList("todo " + pastEntry[2]);
                if (isDone.equals("1")) {
                    duke.setTaskAsDone(i);
                }
                break;
            case "D":
                duke.addTaskToList("deadline " + pastEntry[2] + " /by " + pastEntry[3]);
                if (isDone.equals("1")) {
                    duke.setTaskAsDone(i);
                }
                break;
            case "E":
                duke.addTaskToList("event " + pastEntry[2] + " /at " + pastEntry[3]);
                if (isDone.equals("1")) {
                    duke.setTaskAsDone(i);
                }
                break;
            default:
                throw new DukeUnableLoadTask();
            }
            i++;
        }
    }
}
