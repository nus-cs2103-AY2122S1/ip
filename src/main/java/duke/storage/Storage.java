package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private String filePath;
    
    public Storage(String filePath) {
       this.filePath = filePath; 
    }
    
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File data = new File(filePath);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String[] argArr = sc.nextLine().split("\\|");
                Task taskToBeAdded;
                switch (argArr[0]) {
                    case "E":
                        taskToBeAdded = new Event(argArr[2], LocalDate.parse(argArr[3]));
                        break;
                    case "D":
                        taskToBeAdded = new Deadline(argArr[2], LocalDate.parse(argArr[3]));
                        break;
                    default:
                        taskToBeAdded = new Todo(argArr[2]);
                        break;
                }
                if (argArr[1].equals("1")) {
                    taskToBeAdded.markTaskAsDone();
                }
                taskList.add(taskToBeAdded);
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Sorry, I can't find your list of tasks. I'll create a new one for you.");
        }
    }
    
    public void write(TaskList tasks) throws DukeException {
        try {
            // need to use the filePath and cut off file name
            File directoryName = new File("./data");
            if (!directoryName.exists()) {
                directoryName.mkdirs();
            }
            FileWriter fw = new FileWriter(filePath);
            for (Task currTask : tasks.getTasks()) {
                fw.write(currTask.getTaskType() + "|" + (currTask.getIsDone() ? "1" : "0") + "|"
                        + currTask.getDescription()
                        + (currTask.getTiming() == null ? "\n" : "|" + currTask.getTiming().toString() + "\n"));
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry, I was unable to store your list of tasks");
        }
    }
    
}
