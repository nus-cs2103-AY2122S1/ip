import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private List<Task> tasks;
    private File savedFileTasks = new File("./duke.txt");

    public TaskList() {
        tasks = new ArrayList<>();

        try {
            readFileAndSaveTasks();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void readFileAndSaveTasks () throws FileNotFoundException {
        Scanner tasksTextFile = new Scanner(savedFileTasks);

        while (tasksTextFile.hasNext()) {
            String eachLine = tasksTextFile.nextLine();
            char taskType = eachLine.charAt(3);
            char taskDone = eachLine.charAt(6);
            String taskDescription = eachLine.substring(9,eachLine.length());
            tasks.add(createTask(taskType, taskDone, taskDescription));
        }

    }

    private Task createTask(char taskType, char taskDone, String taskDescription) {
        Task createdTask = new Task("Created");
        Boolean isDone = (taskDone == 'X');

        switch (taskType) {
        case 'T':
            Task taskToDo = new ToDo(taskDescription);
            if (isDone) {
                taskToDo.markAsDone();
            }
            createdTask = taskToDo;
            break;
        case 'D':
            Task taskDeadline = new Deadline(taskDescription);
            if (isDone) {
                taskDeadline.markAsDone();
            }
            createdTask = taskDeadline;
            break;
        case 'E':
            Task taskEvent = new Event(taskDescription);
            if (isDone) {
                taskEvent.markAsDone();
            }
            createdTask = taskEvent;
            break;
        }

        return createdTask;
    }

    public String addToList(Task newTask) {
        tasks.add(newTask);

        if (!savedFileTasks.exists()) {
            try {
                savedFileTasks.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file " + e.getMessage());
            }
        }
        this.getList();

        return newTask.toString();
    }

    public String deleteFromList(int index) {
        Task curr = tasks.get(index - 1);
        tasks.remove(index - 1);
        this.getList();

        return curr.toString();
    }

    public String taskDone(int index) {
        Task curr = tasks.get(index - 1);
        curr.markAsDone();
        this.getList();
        return curr.toString();
    }

    public int taskCount() {
        return tasks.size();
    }

    public String getList() {
        int counter = 1;
        String result = "";

        for (int i = 0; i < tasks.size(); i++) {
                result = result + counter + "." + tasks.get(i) + "\n";
                counter++;
        }

        try {
            FileWriter fw = new FileWriter(".duke.txt");
            fw.write(result);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return result;
    }
}
