package ailurus;

import ailurus.task.Task;
import ailurus.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String directoryName;
    private String filename;
    private File directory = null;
    private File ailurusObj = null;
    private FileWriter writer = null;

    public Storage(String directoryName, String filename) {
        this.directoryName = directoryName;
        this.filename = filename;
        this.directory = new File(directoryName);
        this.ailurusObj = new File(directoryName.concat(filename));

        if (!directory.exists()) {
            // create new directory
            directory.mkdir();
        }

        if (!ailurusObj.exists()) {
            // create new file
            try {
                ailurusObj.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Task> load() throws AilurusException {
        ArrayList<Task> list = new ArrayList<>();
        // load tasks from file
        try {
            Scanner reader = new Scanner(ailurusObj);
            while (reader.hasNextLine()) {
                Task task = Parser.parseData(reader.nextLine());
                if (task != null) {
                    list.add(task);
                }
            }
            this.writer = new FileWriter(this.directoryName.concat(this.filename));
        } catch (FileNotFoundException e) {
            throw new AilurusException(AilurusException.Error.LOAD);
        } catch (IOException e) {
            throw new AilurusException(AilurusException.Error.LOAD);
        }
        return list;
    }

    public void unload(TaskList list) throws AilurusException {
        // write all tasks to file
        if (this.writer != null) {
            try {
                for (int i = 0; i < list.length(); i++) {
                    Task task = list.getTask(i);
                    task.log(writer);
                }
                writer.close();
            } catch (IOException e) {
                throw new AilurusException(AilurusException.Error.LOAD);
            }
        }
    }
}
