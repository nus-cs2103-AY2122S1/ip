package duke;

import duke.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private File file;
    
    public Storage(String filepath) {
        this.filepath = filepath;
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.file = file;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    public void loadTaskListData(TaskList taskList) {
        try {
            Scanner s = new Scanner(this.file); // create a Scanner using the File as the source
            if (!s.hasNext()) {
                Ui.printMessage("There are no items in your task list!");
            } else {
                Ui.printMessage("Here is your current task list: ");
                while (s.hasNext()) {
                    String str = s.nextLine();
                    System.out.println(str);
                    String[] parts = str.split("\\|", 4);
                    String subStr = parts[0].substring(3).trim();
                    if (subStr.equals("duke.task.Todo") || subStr.equals("duke.task.Todo")) {
                        Task task = new Todo(parts[2].trim());
                        if (parts[1].trim().equals("X")) {
                            task.markAsDone();
                        }
                        taskList.addTask(task);
                    } else if (subStr.equals("duke.task.Deadline") || subStr.equals("duke.task.Deadline")) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
                        LocalDateTime dateTime = LocalDateTime.parse(parts[3].substring(5).trim(), dtf);
                        Task task = new Deadline(parts[2].trim(), dateTime);
                        if (parts[1].trim().equals("X")) {
                            task.markAsDone();
                        }
                        taskList.addTask(task);
                    } else {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
                        LocalDateTime dateTime = LocalDateTime.parse(parts[3].substring(5).trim(), dtf);
                        Task task = new Event(parts[2].trim(), dateTime);
                        if (parts[1].trim().equals("X")) {
                            task.markAsDone();
                        }
                        taskList.addTask(task);
                    } 
                }
                Ui.printMessage("End of task list");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void writeToFile(String filePath, TaskList tl) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tl.size(); i++) {
                int num = i + 1;
                fw.write(num + ". " + tl.getTask(i).taskListOnDisk() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}