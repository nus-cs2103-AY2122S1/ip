package duke;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deals with loading tasks from the file and saving tasks in the file (duke.txt).
 */
public class Storage {
    private String filepath;
    private File file;

    /**
     * Represents a constructor for the Storage class where the filepath is initialized.
     *
     * @param filepath Filepath of the file (duke.txt) where the task data is stored.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.file = file;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the task data from the duke.txt storage file into the task list.
     *
     * @param taskList TaskList where the tasks are stored.
     */
    public void loadTaskListData(TaskList taskList) {
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] parts = str.split("\\|", 4);
                String taskType = parts[0].substring(3).trim();
                
                addTaskToTaskList(parts, taskList, taskType);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void addTaskToTaskList(String[] parts, TaskList taskList, String taskType) {
        Task task = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
        
        if (taskType.equals("duke.task.Todo")) {
            task = new Todo(parts[2].trim());
        } else if (taskType.equals("duke.task.Deadline")) {
            LocalDateTime dateTime = LocalDateTime.parse(parts[3].substring(5).trim(), dtf);
            task = new Deadline(parts[2].trim(), dateTime);
        } else if (taskType.equals("duke.task.Event")) {
            LocalDateTime dateTime = LocalDateTime.parse(parts[3].substring(5).trim(), dtf);
            task = new Event(parts[2].trim(), dateTime);
        }
        
        if (task != null) {
            if (parts[1].trim().equals("X")) {
                task.markAsDone();
            }
            taskList.addTask(task);
        }
    }
    
    /**
     * Writes the task data to the duke.txt file whenever the task list is updated.
     *
     * @param filePath Filepath of the file (duke.txt) where the task data is stored.
     * @param taskList TaskList where the tasks are stored.
     */
    public void writeToFile(String filePath, TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.getSize(); i++) {
                int num = i + 1;
                fw.write(num + ". " + taskList.getTask(i).getTaskListOnDisk() + "\n");
            }
            fw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
