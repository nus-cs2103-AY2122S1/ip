import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath = "data/duke.txt";
    protected static File f;

    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(this.filePath);
        f.mkdirs(); // handle the folder-does-not-exist-yet case
    }

    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            // create a blank new file to write to
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath);

            // write to file
            for (Task t : tasks) {
                fw.write(t.getCat() + "," + t.getStatusIcon() + "," + t.getDesc() + "," + t.getDueTime());
                fw.write(System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);

            // scan from file
            while (s.hasNext()) {
                Task t;
                String c = s.nextLine();
                String[] temp = c.split(",");

                // Check type of Task
                switch (temp[0]) {
                case "T":
                    t = new Todo(temp[2]);
                    break;
                case "D":
                    t = new Deadline(temp[2], temp[3]);
                    break;
                case "E":
                    t = new Event(temp[2], temp[3]);
                    break;
                default:
                    throw new DukeException("Invalid task type when loading: " + temp[0]);
                }

                // Check if task is completed
                if (temp[1].equals("X")) {
                    t.markDone();
                }
                tasks.add(t);
            }
        } catch (FileNotFoundException e) {
            try {
                // create new file if not found
                f.createNewFile();
            } catch (IOException g){
                // throws error if file cannot be created
                throw new DukeException(g.getMessage());
            }
        }

        return tasks;
    }

    @Override
    public String toString() {
        return "file path is " + filePath;
    }
}
