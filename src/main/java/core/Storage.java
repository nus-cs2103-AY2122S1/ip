package core;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Encapsulates a storage object that handles loading data from and updating data to a file.
 */
public class Storage {
    private File file;

    public Storage(String filePath) {
        file = new File("data/duke.txt");
        // Create folder for the file if it does not exist
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public void addTasksToFile(TaskList taskList) {
        taskList.saveContents(file);
    }

    public TaskList loadStorageToTaskList() {
        TaskList taskList = new TaskList();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                switch(nextLine.charAt(0)) {
                case 'T':
                    loadTodo(nextLine, taskList);
                    break;
                case 'D':
                    loadDeadline(nextLine, taskList);
                    break;
                case 'E':
                    loadEvent(nextLine, taskList);
                    break;
                }
//                System.out.println(sc.nextLine());
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("Storage file not found: " + e.getMessage());
            System.out.println("Task List will be initialized to empty state.");
            System.out.println("Duke.txt will be created for you once you add tasks to the list.");
            return taskList;
        }

    }

    public void loadTodo(String line, TaskList taskList) {
        String regex = "/";
        String[] splittedLine = line.split(regex);
        Todo todo = new Todo(splittedLine[2]);
        if (splittedLine[1].equals("1")) {
            todo.setCompleted();
        }
        taskList.addTaskQuietly(todo);
    }

    public void loadEvent(String line, TaskList taskList) {
        String regex = "/";
        String[] splittedLine = line.split(regex);
        Event event = new Event(splittedLine[2], splittedLine[3]);
        if (splittedLine[1].equals("1")) {
            event.setCompleted();
        }
        taskList.addTaskQuietly(event);
    }

    public void loadDeadline(String line, TaskList taskList) {
        String regex = "/";
        String[] splittedLine = line.split(regex);
        Deadline deadline = new Deadline(splittedLine[2], splittedLine[3]);
        if (splittedLine[1].equals("1")) {
            deadline.setCompleted();
        }
        taskList.addTaskQuietly(deadline);
    }

}
