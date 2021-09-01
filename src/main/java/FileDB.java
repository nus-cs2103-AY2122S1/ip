import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileDB {
    public static String DEFAULT_SAVE = "ip/src/main/resources/storage.txt";
    private File fileDB;
    private boolean alreadyExists;
    private Parser parser;

    public FileDB() throws DukeIOException{
        this.fileDB = new File(DEFAULT_SAVE);
        this.parser = new Parser();
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
        this.parser = new Parser();
        this.alreadyExists = false;
        try {
            this.alreadyExists = this.fileDB.createNewFile();
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }

    public void save(Task task) throws DukeIOException {
        try {
            String parseTask = this.parser.parseTask(task);
            FileWriter fileWriter = new FileWriter(this.fileDB, true);
            fileWriter.write(parseTask + '\n');
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeIOException();
        } catch (DukeNoDateException e) {
            System.out.println(e.getMessage());
        }
    }
}
