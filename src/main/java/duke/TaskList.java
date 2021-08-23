package duke;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;
    private static int counter;

    public TaskList() {
        taskList = new ArrayList<Task>(100);
        counter = 0;
    }

    public static ArrayList<Task> getTaskList() {
        return TaskList.taskList;
    }

    public static int getCounter() {
        return TaskList.counter;
    }

    public static void addTask(Task currTask) {
        TaskList.taskList.add(currTask);
        TaskList.counter++;
    }

    public static void addTaskAndUpdate(Task currTask) throws IOException {
        TaskList.taskList.add(currTask);
        TaskList.counter++;
        appendToFile("data/jarvis.txt", (counter) + "." +
                TaskList.getTaskList().get(counter - 1).toPrintToFile()
                + System.lineSeparator());
    }

    public static void deleteTaskAndUpdate(Task currTask) throws IOException{
        TaskList.taskList.remove(currTask);
        counter--;
        if (TaskList.getTaskList().size() == 0) {
            writeToFile("data/jarvis.txt", "");
        } else {
            for (int i = 0; i < TaskList.getTaskList().size(); i++) {
                if (i == 0) {
                    writeToFile("data/jarvis.txt", (i + 1) + "." +
                            TaskList.getTaskList().get(i).toPrintToFile()
                            + System.lineSeparator());
                } else {
                    appendToFile("data/jarvis.txt", (i + 1) + "." +
                            TaskList.getTaskList().get(i).toPrintToFile()
                            + System.lineSeparator());
                }
            }
        }
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
