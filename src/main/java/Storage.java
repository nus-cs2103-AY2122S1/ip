import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    private File getStoreFile() throws IOException {
        File store = new File(this.filePath);
        String dirPath = store.getParent();
        File directory = new File(dirPath);
        // creates parent directories if they do not exist
        directory.mkdirs();
        // creates file if it does not exist
        store.createNewFile();
        return store;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File store = getStoreFile();
            BufferedReader reader = new BufferedReader(new FileReader(store));
            String fileLine = reader.readLine();
            int lineNo = 1;
            while (fileLine != null) {
                Task task = parseTask(fileLine, lineNo);
                tasks.add(task);
                lineNo++;
                fileLine = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(TaskList tasks) {
        try {
            File store = getStoreFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(store));
            ArrayList<String> taskStrings = tasks.getTaskStrings();
            for (String taskString : taskStrings) {
                writer.write(taskString);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Task parseTask(String fileLine, int lineNo) throws DukeException {
        try {
            String[] parts = fileLine.split(" \\| ");
            String taskType = parts[0];
            int isDoneInt = Integer.parseInt(parts[1]);
            boolean isDone = (isDoneInt == 1);
            String description = parts[2];
            switch(taskType) {
                case "T":
                    return new Todo(description, isDone);
                case "D":
                    String date = parts[3];
                    return new Deadline(description, date, isDone);
                case "E":
                    date = parts[3];
                    return new Event(description, date, isDone);
                default:
                    throw new DukeException("Could not parse task type on file line " + lineNo);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Task details are missing on file line " + lineNo);
        }
    }
}
