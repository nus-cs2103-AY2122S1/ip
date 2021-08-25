package bubbles;

import bubbles.tasks.Task;
import bubbles.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    // deals with loading bubbles.tasks from the file and saving bubbles.tasks in the file
    private File bubbles;
    private Parser parser;
    private TaskList taskList;

    public Storage() {
        this.parser = new Parser();
    }

    public void loadFile(String filePath) {
        bubbles = new File(filePath);

        try {
            taskList = readFile();
        } catch (FileNotFoundException e) {
            createFile(filePath);
            taskList = new TaskList();
        }
    }

    private void createFile(String filePath) {
        String[] arr = filePath.split("/");

        File file = new File(filePath);
        File dir = new File(arr[0]);
        dir.mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("There is an error while creating the database!");
        }
    }

    private TaskList readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(bubbles);
        TaskList toDoList = new TaskList();

        while (sc.hasNext()) {
            String task = sc.nextLine();
            Object[] inputs = parser.formatTask(task);

            toDoList.addTask((String) inputs[0], (String) inputs[1], (Boolean) inputs[2]);
        }

        return toDoList;
    }

    public void writeTasks() {
        ArrayList<Task> tasks = taskList.getTasks();

        try {
            FileWriter fw = new FileWriter(bubbles, false);

            for (Task t : tasks) {
                String task = t.format();

                fw.write(task);
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
