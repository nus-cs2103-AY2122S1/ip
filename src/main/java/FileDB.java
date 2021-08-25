import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileDB {
    public static String DEFAULT_SAVE = "ip/src/main/resources/storage.txt";
    private File fileDB;
    private boolean alreadyExists;

    public FileDB() throws DukeIOException{
//        ClassLoader classLoader = getClass().getClassLoader();
        this.fileDB = new File(DEFAULT_SAVE);
        this.alreadyExists = false;
        try {
            this.alreadyExists = this.fileDB.createNewFile();
            if (!this.alreadyExists) {
                System.out.println("You have a record!");
            } else {
                System.out.println("I've created a record for you!");
            }
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }

    public FileDB(String location) throws DukeIOException{
        this.fileDB = new File(location);
        this.alreadyExists = false;
        try {
            this.alreadyExists = this.fileDB.createNewFile();
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }

    public void save(Task task) throws DukeIOException {
        try {
            String parseTask = this.parse(task);
            FileWriter fileWriter = new FileWriter(this.fileDB, true);
            fileWriter.write(parseTask + '\n');
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeIOException();
        } catch (DukeNoDateException e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO: make an interface called taskparseable?
    public String parse(Task task) throws DukeNoDateException {
        String taskType = task.getTaskType();
        String isCompleted = String.valueOf(task.isDone);
        String description = task.description;
        String date = "";
        if (task instanceof Deadline || task instanceof Event) {
            try {
                date = task.getDate();
            } catch (DukeNoDateException e) {
                throw e;
            }
        }
        String data = taskType + "|" + isCompleted + "|" + description + (date.equals("") ? "" : "|" + date);
        return data;
    }
}
