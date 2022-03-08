package storage;

import model.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JadenOutputWriter {
    private File outputFile;

    public JadenOutputWriter(String outputFileName) {
        this.outputFile = new File(outputFileName);
        try {
            this.outputFile.createNewFile();
        } catch (IOException e) {
            System.out.println("I See A Problem With Writing Outputs.");
            e.printStackTrace();
        }
    }

    public void writeOutput(TaskList taskList) {
        String outputString = taskList.saveTasks();
        try {
            FileWriter outputWriter = new FileWriter(outputFile);
            outputWriter.write(outputString);
            outputWriter.close();
        } catch (IOException e) {
            System.out.println(" There Is Something Wrong With Our Output Target.");
        }
    }
}
