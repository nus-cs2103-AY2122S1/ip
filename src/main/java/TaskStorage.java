import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class TaskStorage {
    private ArrayList<Task> storage;
    private static TaskStorage instance = null;
    private File dataFile;
    static final String DATA_FILE_DIR = "./data";
    static final String DATA_FILE_PATH = "./data/tasks.txt";

    private TaskStorage() {
        try {
            this.dataFile = initializeDataFile();
            this.storage = readTasksFromMemory(this.dataFile);
        } catch (IOException e) {
            System.out.println("Cannot create database!");
        }
    }

    private File initializeDataFile() throws IOException {
        File dataDir = new File(DATA_FILE_DIR);
        File dataFile = new File(DATA_FILE_PATH);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        return dataFile;
    }

    private ArrayList<Task> readTasksFromMemory(File file) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                try {
                    Task task = parseTask(scanner.nextLine());
                    tasks.add(task);
                } catch (DukeException e) {
                    // ignore task with invalid format
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s\n", file.toString());
        }
        return tasks;
    }

    private Task parseTask(String taskString) throws DukeException {
        String[] tokens = taskString.split(" ");
        switch (tokens[0]) {
        case "todo":
            return new TodoTask(tokens[1], Boolean.parseBoolean(tokens[2]));
        case "event":
            return new EventTask(tokens[1], Boolean.parseBoolean(tokens[2]), LocalDateTime.parse(tokens[3]));
        case "deadline":
            return new DeadlineTask(tokens[1], Boolean.parseBoolean(tokens[2]), LocalDateTime.parse(tokens[3]));
        default:
            throw new DukeException("Task with invalid format!");
        }
    }

    private String taskToMemString(Task task) {
        if (task instanceof TodoTask) {
            @SuppressWarnings("unchecked")
            TodoTask todoTask = (TodoTask) task;
            return String.format("todo %s %b", todoTask.getContent(), todoTask.isDone());
        } else if (task instanceof EventTask) {
            @SuppressWarnings("unchecked")
            EventTask eventTask = (EventTask) task;
            return String.format("event %s %b %s", eventTask.getContent(), eventTask.isDone(), eventTask.getDate().toString());
        } else if (task instanceof DeadlineTask) {
            @SuppressWarnings("unchecked")
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return String.format("deadline %s %b %s", deadlineTask.getContent(), deadlineTask.isDone(), deadlineTask.getDeadline().toString());
        } else {
            // never reach this branch
            return null;
        }
    }

    private void writeToMem() throws IOException {
        FileWriter fileWriter = new FileWriter(DATA_FILE_PATH);
        String data = "";
        for (Task task : this.storage) {
            data += taskToMemString(task) + System.lineSeparator();
        }
        fileWriter.write(data);
        fileWriter.close();
    }

    public static TaskStorage getInstance() {
        if (instance == null) {
            instance = new TaskStorage();
            return instance;
        }
        return instance;
    }

    public ArrayList<Task> getAll() {
        return storage;
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return storage.get(index);
    }

    public int getSize() {
        return storage.size();
    }

    public boolean add(Task task) throws IOException {
        boolean isAdded = storage.add(task);
        writeToMem();
        return isAdded;
    }

    public Task delete(int index) throws IndexOutOfBoundsException, IOException {
        Task deletedTask = storage.remove(index);
        writeToMem();
        return deletedTask;
    }
}
