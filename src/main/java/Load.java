import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Load extends FileAccess{
    public Load() {
        super();
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
        return "loading file from " + this.filePath;
    }
}
