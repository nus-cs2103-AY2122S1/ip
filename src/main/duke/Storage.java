package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

public class Storage {

    private File file;
    
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    private Task parseTask(String task) throws DukeException{
        String[] descriptions = task.split("\\|");
        Task t;
        switch (descriptions[0]) {
        case "T":
            t = new TodoTask(descriptions[2]);
            break;
        case "D":
            t = new DeadlineTask(descriptions[2], descriptions[3]);
            break;
        case "E":
            t = new EventTask(descriptions[2], descriptions[3]);
            break; 
        default:
            throw new DukeException("Incorrect Task Format!");         
        }
        if (descriptions[1] == "true") {
            t.finishTask();
        }
        return t;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                Task t = parseTask(task);
                taskList.add(t);
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("Cannot Make New File!");
        } 
        return taskList;
    }

    public void save(TaskList tasks) throws DukeException {
        String listString = "";
        for (int i = 0; i < tasks.size(); i++) {
            listString += String.format("%s\n", tasks.get(i).saveString());
        }
        try (PrintWriter writer = new PrintWriter(file)){
            if (!listString.isEmpty()) {
                writer.println(listString.trim());
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File Not Found!");
        }
    }      
}
