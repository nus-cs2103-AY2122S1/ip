package duke.util;

import duke.taskTypes.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Storage {

    private File savedOutput;

    public Storage(String filePath) {
        try {
            File dir = new File(filePath);
            dir.mkdirs();
            File savedOutput = new File(filePath + "/savedOutput.txt");
            if (!savedOutput.exists()) {
                savedOutput.createNewFile();
            }
            this.savedOutput = savedOutput;
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public List<String> loadSaved() {
        List<String> pastCommand = new ArrayList<>();
        try {
            Scanner scan = new Scanner(savedOutput);
            while (scan.hasNext()) {
                pastCommand.add(scan.nextLine());
            }
        } catch (IOException e) {
            System.out.println("file not found");
        }
        return pastCommand;
    }

    public void saveAdded(Task task) {
        String msg = task.saveTask();
        try {
            FileWriter fileWriter = new FileWriter(savedOutput, true);
            fileWriter.write(msg);
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public void saveUpdate(TaskList taskList) {
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
            System.out.println("file not found");
        }
    }

}
