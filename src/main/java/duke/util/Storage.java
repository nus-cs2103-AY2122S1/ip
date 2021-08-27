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



public class Storage {

    private File savedOutput;

    public Storage(String filePath) throws DukeException {
        File dir = new File(filePath);
        dir.mkdirs();
        File savedOutput = new File(filePath + "/savedOutput.txt");
        if (!savedOutput.exists()) {
            throw new FileNotFoundException("Invalid FilePath");
        }
        this.savedOutput = savedOutput;
    }

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

    public void saveAdded(Task task) throws DukeException {
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

    public void saveUpdate(TaskList taskList) throws DukeException {
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
