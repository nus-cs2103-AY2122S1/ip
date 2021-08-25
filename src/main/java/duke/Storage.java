package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {
    private Path filePath = null;
    private Path directoryPath = null;
    public Storage(String path) {
        String home = System.getProperty("user.home");
        //        java.nio.file.Path directoryPath = java.nio.file.Paths.get(home, "iP", "data");
        this.filePath = java.nio.file.Paths.get(home, "iP", path.split("/")[0], path.split("/")[1]);
        this.directoryPath = java.nio.file.Paths.get(home, "iP", path.split("/")[0]);
    }
    public String convertTaskToText(Task task) {
        String result = task.type + "|";
        if(task.isCompleted()) {
            result += "1|";
        }else {
            result += "0|";
        }
        result += task.getTaskContent();
        if(task.type.equals("D") || task.type.equals("E")) {
            result += "|" + task.getTiming();
        }
        return result;
    }
    public Task convertTextToTask(String text) {
        String[] str = text.split("\\|");
        Task newTask;
        if(str[0].equals("T")) {
            newTask = new ToDo(str[2]);
        }else if(str[0].equals("D")) {
            newTask = new Deadline(str[2], str[3]);
        }else {
            newTask = new Event(str[2], str[3]);
        }
        if(str[1].equals("1")) {
            newTask.markCompleted();
        }
        return newTask;
    }
    public void writeToFile(TaskList taskList) {
        StringBuilder combinedTask = new StringBuilder();
        for(int i = 0; i < taskList.length(); i++) {
            combinedTask.append(convertTaskToText(taskList.get(i))).append("\n");
        }
        try {
            FileWriter writer = new FileWriter(String.valueOf(filePath), false);
            writer.write(combinedTask.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("error occurred 2");
        }
    }
    public void createFile() {
        File newDirectory = new File(String.valueOf(directoryPath));
        File newFile = new File(String.valueOf(filePath));
        try {
            newDirectory.mkdir();
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error occurred 1");
        }
    }
    public void saveTask(TaskList taskList) {
        boolean directoryExists = java.nio.file.Files.exists(filePath);
        if(!directoryExists) {
            createFile();
        }
        writeToFile(taskList);
    }
    public TaskList loadTask() {
        TaskList taskList = new TaskList();
        try {
            File myObj = new File(String.valueOf(filePath));
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                taskList.addTask(convertTextToTask(data));
            }
            myReader.close();
//            taskList.forEach(task -> {
//                System.out.println(task.toString());
//            });
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }
}
