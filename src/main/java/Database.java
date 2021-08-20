import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private final static DukeException ERROR_DB = new DukeException("Error loading database.");
    private List<Task> db = new ArrayList<>();
    private FileWriter txtw;
    private File txt;
    
    public Database() throws DukeException {
        try {
            txt = new File("duke.txt");
            txtw = new FileWriter("duke.txt");
            Scanner sc = new Scanner(txt);
            while (sc.hasNextLine()) {
                Task t = parse(sc.nextLine());
                if (t != null) db.add(t);
            }
        } catch (IOException e) {
            throw ERROR_DB;
        }
    }
    
    private Task parse(String line) throws DukeException {
        try {
            String[] args = line.split(" | ");
            String type = args[0];
            boolean done = Integer.parseInt(args[1]) == 1;
            String desc = args[2];
            String opt = (args.length > 3) ? args[3] : "";
            switch (type) {
                case "T" :
                    return new Todo(desc, done);
                case "D" :
                    return new Deadline(desc, opt, done);
                case "E" :
                    return new Event(desc, opt, done);
                default:
                    throw ERROR_DB;
            }
        } catch (IndexOutOfBoundsException | DukeException e) { 
            throw ERROR_DB;
        }
    }

    // fix the adding problems
    public void add(Task task) throws DukeException {
        try {
            db.add(task);
            txtw.write(task.toDB());
            txtw.close();
        } catch (IOException e) {
            throw ERROR_DB;
        }
    }

    public Task get(int index) {
        return db.get(index);
    }

    // TODO: DELETE FROM DB
    public Task delete(int index) {
        Task t = db.get(index);
        db.remove(index);
        return t;
    }

    public int size() {
        return db.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (db.size() == 0) return " You have no tasks!";
        for (int i = 1; i <= db.size(); i++) {
            sb.append("\n\t ");
            sb.append(i + "." + db.get(i-1));
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
