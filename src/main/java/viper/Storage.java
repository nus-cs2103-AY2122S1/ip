package viper;

import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.Todos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */

public class Storage {
    
    private final String filePath;
    private File file;
    
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
    }
    
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(file);
        
        while (sc.hasNextLine()) {
            String readStr = sc.nextLine();
            String[] splitStr = readStr.split("]", 3);
            String taskInstruction = splitStr[0].substring(1);
            String taskIsDone = splitStr[1].substring(1);
            String taskDesc = splitStr[2].trim();
            
            switch (taskInstruction) {
            case "T":
                Todos addTodo = new Todos(splitStr[2].trim());
                if (!taskIsDone.isBlank()) {
                    addTodo.setDone();
                }
                taskList.add(addTodo);
                break;
            case "D":
                int bracketPos = taskDesc.indexOf("(");
                int closeBracket = taskDesc.indexOf(")");
                int colonPos = taskDesc.indexOf(":");
                String deadlineDesc = taskDesc.substring(0, bracketPos - 1).trim();
                String deadlineDate = taskDesc.substring(colonPos + 1, closeBracket).trim();
                Deadlines addDeadline = new Deadlines(deadlineDesc, deadlineDate);
                if (!taskIsDone.isBlank()) {
                    addDeadline.setDone();
                }
                taskList.add(addDeadline);
                break;
            case "E":
                bracketPos = taskDesc.indexOf("(");
                closeBracket = taskDesc.indexOf(")");
                colonPos = taskDesc.indexOf(":");
                String eventDesc = taskDesc.substring(0, bracketPos - 1).trim();
                String eventTime = taskDesc.substring(colonPos + 1, closeBracket).trim();
                Events addEvent = new Events(eventDesc, eventTime);
                if (!taskIsDone.isBlank()) {
                    addEvent.setDone();
                }
                taskList.add(addEvent);
                break;
            }
        }
        return taskList;
    }
    
    public void writeTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }
   
    public void deleteTask(Task task) throws IOException {
        File tempFile = File.createTempFile("temp", ".txt", file.getParentFile());

        FileWriter fw = new FileWriter(tempFile, true);
        Scanner sc = new Scanner(file);
        String lineDone = task.toString();
        
        while(sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (currLine.equals(lineDone)) {
                fw.write("");
            } else {
                fw.write(currLine + System.lineSeparator());
            }
        }
        
        fw.close();
        boolean success = tempFile.renameTo(file);
    }
    
    public void doneTask(Task task) throws IOException {
        File tempFile = File.createTempFile("temp", ".txt", file.getParentFile());
        
        FileWriter fw = new FileWriter(tempFile, true);
        Scanner sc = new Scanner(file);
        String lineDone = task.toString();
        
        while(sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (currLine.equals(lineDone)) {
                task.setDone();
                fw.write(task + System.lineSeparator());
            } else {
                fw.write(currLine + System.lineSeparator());
            }
        }
        
        fw.close();
        boolean success = tempFile.renameTo(file);
    }
       
}
