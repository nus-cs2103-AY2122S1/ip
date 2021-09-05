package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.LoadingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the class to store the data.
 *
 * @author QIN GUORUI
 */
public class Storage {
    /** The recommended working directory path. */
    private static String dir = System.getProperty("user.dir");

    /** The content in the data file. */
    private String content = "";

    /** Relative file path. */
    private String filePath;

    /** The actual file name. */
    private String file;

    /**
     * Sets up the store for data.
     *
     * @param filePath Where to store data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = filePath.substring(4);
    }

    /**
     * Returns the task list stored in data file.
     *
     * @return The task list.
     * @throws LoadingException The exception related to loading.
     */
    public ArrayList<Task> load() throws LoadingException, IOException {
        String dir = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(dir, "data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        if (!directoryExists) {
            new File(dir + "/data").mkdir();
        }
        File f = new File(path + file);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            f.createNewFile();
            throw new LoadingException();
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> taskList = new ArrayList<>();
        while (s.hasNextLine()) {
            String task = s.nextLine();
            int lens = task.length();
            String oneLine = task + System.lineSeparator();
            sb.append(oneLine);
            addRelatedTasks(task, taskList, lens);// Judge data input to add tasks.
        }
        content = sb.toString();//Update the content.
        return taskList;
    }


    public void addRelatedTasks(String task, ArrayList<Task> taskList, int lens) {
        char type = task.charAt(0);
        char done = task.charAt(4);
        if (type == 'T') {
            taskList.add(addTodo(task, lens, done));
        } else {
            taskList.add(addEventOrDeadline(task, lens, type, done));
        }
    }

    public Task addEventOrDeadline(String task, int lens, int type, char done) {
        String[] parts = task.substring(8, lens).split(" ~ ");
        String content = parts[0];
        String time = parts[1];
        Task deadlineOrEvent;
        if (type == 'D') {
            deadlineOrEvent = new Deadline(content, time);
        } else {
            deadlineOrEvent = new Event(content, time);
        }
        if (done == '1') {
            deadlineOrEvent.markAsDone();
        }
        return deadlineOrEvent;
    }

    public Task addTodo(String task, int lens, char done) {
        Task todo = new Task("");
        if (done == '1') {
            todo = new Todo(task.substring(8, lens));
            todo.markAsDone();
        } else {
            todo = new Todo(task.substring(8, lens));
        }
        return todo;
    }

    /**
     * Writes the text to data file, which overwrites initial storage.
     *
     * @param filePath The path to data file.
     * @param textToAdd The overwritten text.
     * @throws IOException When write to file fails.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Stores the task to data file.
     *
     * @param task The string format of task representation.
     */
    public void store(String task) throws IOException{
        String dataFile = dir + "/data" + file;
        String data = transformToData(task);
        appendToFile(dataFile, data + System.lineSeparator());
        content = content + data + System.lineSeparator();
    }

    /**
     * Transforms the task representation to storage form in data file.
     *
     * @param task The task string representation.
     * @return The string format stored in the data file.
     */
    public static String transformToData(String task) {
        int lens = task.length();
        char type = task.charAt(1);
        char done = task.charAt(4);
        String split = "by: ";
        String prefix = "0";
        String dataForm;
        if (type == 'E') {
            split = "at: ";
        }
        if (done == 'X') {
            prefix = "1";
        }
        if (type == 'T') {
            dataForm = "T | " + prefix + " | " + task.substring(7, lens);
        } else {
            String[] parts = task.substring(7, lens).split(split);
            String content = parts[0];
            int lensContent = content.length();
            content = content.substring(0, lensContent - 2);
            String time = parts[1];
            int lensBy = time.length();
            time = time.substring(0, lensBy - 1);
            dataForm = type + " | " + prefix + " | " + content + " ~ " + time;
        }
        return dataForm;
    }

    /**
     * Appends the text to existing data file.
     *
     * @param filePath The path to data file.
     * @param textToAppend The text tend to add.
     * @throws IOException When the append operation fails.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Replaces the unmarked task as marked, or just removes the task.
     *
     * @param place The index of task in task list.
     * @param tasks The task list.
     */
    public void replace(int place, TaskList tasks) {
        String dataFile = dir + "/data" + file;
        String[] parts = content.split(System.lineSeparator());
        int lens = parts.length;
        String newData = "";
        if (tasks != null) {
            newData = transformToData(tasks.elementToString(place));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lens; i++) {
            String temp = parts[i];
            if (i == place && tasks == null) {
                continue;
            } else if (i == place) {
                temp = newData;
            }
            temp = temp + System.lineSeparator();
            sb.append(temp);
        }
        content = sb.toString();
        try{
            writeToFile(dataFile, content);
        } catch (IOException e) {
            System.out.println("Delete failed.");
        }
    }
}
