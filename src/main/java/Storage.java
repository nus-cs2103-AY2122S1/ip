import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Represents the class that deals with loading tasks from file and saving tasks to file.
 * 
 * @author Sherman Ng Wei Sheng
 */
public class Storage {
    private final String FILEDIR = "data/";
    private final String FILENAME = "duke.txt";
    private final String FILEPATH = FILEDIR + FILENAME;
    private String data = "";
    
    public Storage() {
        try {
            File storageDir = new File(FILEDIR);
            File storageFile = new File(FILEPATH);
            boolean isDirExistent = storageDir.exists();
            boolean isFileExistent = storageFile.exists();
            if (!isDirExistent) {
                storageDir.mkdirs();
            }
            if (!isFileExistent) {
                storageFile.createNewFile();
            }
            Scanner scanner = new Scanner(storageFile);
            while (scanner.hasNext()) {
                this.data = this.data + scanner.nextLine() + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadDataTo(TaskList taskList) {
        String[] lines = this.data.split("\n");
        if (lines[0].equals("")) {
            return;
        }
        for (String line : lines) {
            String[] split = line.split(" \\| ");
            String taskType = split[0];
            boolean isDone = split[1].equals("0") ? false : true;;
            String description = split[2];
            String dateTime;
            switch (taskType) {
            case "T":
                ToDo toDoTask = new ToDo(description, isDone);
                taskList.add(toDoTask);
                break;
            case "D":
                dateTime = split[3];
                Deadline deadlineTask = new Deadline(description, dateTime, isDone);
                taskList.add(deadlineTask);
                break;
            case "E":
                dateTime = split[3];
                Event eventTask = new Event(description, dateTime, isDone);
                taskList.add(eventTask);
                break;
            }
        }
    }
    
    public void saveDataFrom(TaskList list) {
        try {
            int size = list.size();
            String finalMessage = "";
            for (int i = 0; i < size; i++) {
                Task currentTask = list.get(i);
                String taskMessage = currentTask.encodeTaskForStorage();
                finalMessage += taskMessage;
                if (i != size - 1) {
                    finalMessage += "\n";
                }
            }
            FileWriter fw = new FileWriter(FILEPATH);
            fw.write(finalMessage);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
