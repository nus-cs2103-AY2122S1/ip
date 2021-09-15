package duke.util;

import duke.exception.DukeException;
import duke.exception.FileNotFoundException;
import duke.taskTypes.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class StorageTxt implements Storage {

    private File savedOutput;

    /**
     * Constructor for StorageTxt and sets the file that contains previous state
     *
     * @param filePath File location that contains text file containing previous state
     * @throws DukeException Thrown when file does not exist
     */
    public StorageTxt(String filePath) {
        File dir = new File(filePath);
        dir.mkdirs();
        File savedOutput = new File(filePath + "/savedOutput.txt");
        if (!savedOutput.exists()) {
            try{
                savedOutput.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.savedOutput = savedOutput;
    }

    /**
     * Reads and returns a list of previous task
     *
     * @return List of String read from the file
     * @throws DukeException
     */
    public List<String> loadSaved() throws DukeException {
        List<String> pastCommand = new ArrayList<>();
        try {
            Scanner scan = new Scanner(savedOutput);
            while (scan.hasNext()) {
                pastCommand.add(scan.nextLine());
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Invalid FilePath");
        }

        return pastCommand;
    }

    /**
     * Saves newly added task into storageTxt
     * @param task Newly added task
     * @throws DukeException Thrown when file does not exist
     */
    public void saveAddedTask(Task task) throws DukeException {
        String msg = task.saveTask();
        try {
            FileWriter fileWriter = new FileWriter(savedOutput, true);
            fileWriter.write(msg);
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            throw new FileNotFoundException("Invalid FilePath");
        }
    }

    /**
     * Updates and saves the state of the changed task
     * @param taskList current state
     * @throws DukeException Thrown when file does not exist
     */
    public void saveUpdateTask(TaskList taskList) throws DukeException {
        String[] currentState = taskList.saveState();
        try {
            // Reset the file
            FileWriter fileWriter = new FileWriter(savedOutput);
            fileWriter.write("");
            fileWriter.close();

            // Update with the new State
            fileWriter = new FileWriter(savedOutput, true);
            for( String msg : currentState){
                fileWriter.write(msg);
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FileNotFoundException("Invalid FilePath");
        }
    }

}
