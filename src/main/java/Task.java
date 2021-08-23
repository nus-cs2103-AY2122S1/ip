import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Task  {
    private static ArrayList<Task> taskList = new ArrayList<Task>(100);
    private static int counter = 0;

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void addTask() {
        Task.taskList.add(this);
        counter++;
    }

    public void addTaskAndUpdate() throws IOException{
        Task.taskList.add(this);
        counter++;
        appendToFile("data/jarvis.txt", (counter) + "." +
                Task.getTaskList().get(counter).toString()
                + System.lineSeparator());
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsDoneAndUpdate() throws IOException{
        this.isDone = true;
        for (int i = 0; i < Task.getTaskList().size(); i++) {
            if (i == 0) {
                //writeToFile("data/jarvis.txt", i + ".");
                writeToFile("data/jarvis.txt", (i + 1) + "." + Task.getTaskList().get(i).toString()
                        + System.lineSeparator());
            } else {
                //appendToFile("data/jarvis.txt", i + "." );
                appendToFile("data/jarvis.txt", (i + 1) + "." + Task.getTaskList().get(i).toString()
                        + System.lineSeparator());
            }
        }
    }

    public static ArrayList<Task> getTaskList() {
        return Task.taskList;
    }

    public static int getCounter() {
        return Task.counter;
    }

    public void deleteTask() {
        Task.taskList.remove(this);
        counter--;
    }

    public void deleteTaskAndUpdate() throws IOException{
        Task.taskList.remove(this);
        counter--;
        for (int i = 0; i < Task.getTaskList().size(); i++) {
            if (i == 0) {
                writeToFile("data/jarvis.txt", (i + 1) + "." + Task.getTaskList().get(i).toString()
                        + System.lineSeparator());
            } else {
                appendToFile("data/jarvis.txt", (i + 1) + "." + Task.getTaskList().get(i).toString()
                        + System.lineSeparator());
            }
        }
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}