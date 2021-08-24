package ponyo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.data.task.Todo;
import ponyo.data.task.Deadline;
import ponyo.data.task.Event;
import ponyo.data.exceptions.PonyoException;


public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static final String PATH = "src/main/data";
    private static final String FILENAME = "tasks.txt";

    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList();
            readTasksFromFile(tasks, filePath);
            return tasks;
        } catch (IOException e) {
            throw new PonyoException("Error loading from file :(");
        }

    }

    // Check if file and folder exists
    public void fileFolderCheck(String filePath) throws IOException {
        File file = new File(filePath);
        File directory = new File(PATH);

        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void readTasksFromFile(ArrayList tasks, String filePath) throws IOException {
        fileFolderCheck(filePath);
        Scanner read = new Scanner(new File(filePath));
        read.useDelimiter(Pattern.compile("(\\n)| - "));

        while (read.hasNext()) {
            String taskCode = read.next();

            switch (taskCode) {
                case "T":
                    int marked = Integer.parseInt(read.next());
                    String description = read.next();
                    Task t = new Todo(description);
                    if (marked == 1) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                    break;
                case "D":
                    marked = Integer.parseInt(read.next());
                    description = read.next();
                    String by = read.next();
                    t = new Deadline(description, by);
                    if (marked == 1) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                    break;
                case "E":
                    marked = Integer.parseInt(read.next());
                    description = read.next();
                    String at = read.next();
                    t = new Event(description, at);
                    if (marked == 1) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                    break;
            }
        }
        read.close();
    }

    public void fileLineToWrite(Task t) {
        try {
            appendToFile(PATH + "/" + FILENAME, t.toStringInFile() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void getFullContents(TaskList tasks) {
        try {
            String allContent = "";
            for (int i = 1; i <= tasks.size(); i++) {
                allContent += tasks.retrieveTask(i).toStringInFile() + "\n";
            }
            overwriteFile(PATH + "/" + FILENAME, allContent);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        fileFolderCheck(filePath);
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void overwriteFile(String filePath, String fileContent) throws IOException {
        fileFolderCheck(filePath);
        FileWriter fw = new FileWriter(filePath); // create a FileWriter
        fw.write(fileContent);
        fw.close();
    }
}
