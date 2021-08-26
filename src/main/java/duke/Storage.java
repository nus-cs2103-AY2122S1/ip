package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private File taskDataFile;
    
    public Storage() {
        try {
            File taskDataDir = new File(Paths.get("data").toString());
            if (!taskDataDir.exists()) {
                taskDataDir.mkdir();
            }
            File taskDataFile = new File(Paths.get("data", "taskData.txt").toString());
            if (!taskDataFile.exists()) {
                taskDataFile.createNewFile();
            }
            this.taskDataFile = taskDataFile;
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage() + "\n" 
                    + "Couldn't access/create necessary file to store tasks."
                    + "Please exit the bot if you don't want to lose new tasks.");
        }
    }
    
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner loadScan = new Scanner(taskDataFile);
            while (loadScan.hasNextLine()) {
                String[] nextLineArr = loadScan.nextLine().split("%");
                switch (nextLineArr[0]) {
                case "T":
                    tasks.add(new Todo(nextLineArr[2], Boolean.parseBoolean(nextLineArr[1])));
                    break;
                case "E":
                    tasks.add(new Event(nextLineArr[2], Boolean.parseBoolean(nextLineArr[1]),
                            LocalDateTime.parse(nextLineArr[3]), LocalDateTime.parse(nextLineArr[4])));
                    break;
                case "D":
                    tasks.add(new Deadline(nextLineArr[2], Boolean.parseBoolean(nextLineArr[1]),
                            LocalDateTime.parse(nextLineArr[3])));
                    break;
                default:
                    break;
                }
            }
            loadScan.close();
            return tasks;
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage() + "\n" 
                    + "Couldn't load data from stored file."
                    + "Please exit the bot if you don't want to lose new tasks.");
        }
    }
    
    
    public void storeAdd(String taskStorage) throws IOException {
        FileWriter taskDataWriter = new FileWriter(taskDataFile, true);
        taskDataWriter.write(taskStorage);
        taskDataWriter.close();
    }
    
    public void storeDone(int index, String taskStorage) throws IOException {
        BufferedReader taskDataReader = new BufferedReader(new FileReader(taskDataFile));
        StringBuilder newTaskData = new StringBuilder();
        
        for (int i = 0; i < index - 1; i++) {
            newTaskData.append(taskDataReader.readLine() + "\n");
        }
        
        taskDataReader.readLine();
        newTaskData.append(taskStorage);
        
        while (true) {
            String nextLine = taskDataReader.readLine();
            if (nextLine == null) {
                break;
            } else {
                newTaskData.append(nextLine + "\n");
            }
        }
        
        FileWriter taskDataWriter = new FileWriter(taskDataFile);
        taskDataWriter.write(newTaskData.toString());
        taskDataWriter.close();
        taskDataReader.close();
    }
    
    public void storeDelete(int index) throws IOException {
        BufferedReader taskDataReader = new BufferedReader(new FileReader(taskDataFile));
        StringBuilder newTaskData = new StringBuilder();
        
        for (int i = 0; i < index - 1; i++) {
            newTaskData.append(taskDataReader.readLine() + "\n");
        }
        
        taskDataReader.readLine();
        
        while (true) {
            String nextLine = taskDataReader.readLine();
            if (nextLine == null) {
                break;
            } else {
                newTaskData.append(nextLine + "\n");
            }
        }
        
        FileWriter taskDataWriter = new FileWriter(taskDataFile);
        taskDataWriter.write(newTaskData.toString());
        taskDataWriter.close();
        taskDataReader.close();
    }
}
