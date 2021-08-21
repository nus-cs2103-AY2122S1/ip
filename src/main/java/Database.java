import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Database {
    private final static DukeException ERROR_DB = new DukeException("Error loading database.");
    private List<Task> db = new ArrayList<>();
    private RandomAccessFile raf;
    private File txt;
    
    public Database() throws DukeException {
        try {
            txt = new File("duke.txt");
            raf = new RandomAccessFile(txt, "rwd");
            while (raf.getFilePointer() < raf.length()) {
                Task t = parse(raf.readLine());
                if (t != null) db.add(t);
            }
            update();
        } catch (IOException e) {
            throw ERROR_DB;
        }
    }
    
    private Task parse(String line) throws DukeException {
        try {
            String[] args = line.split("( \\| )");
            if (args.length < 3) return null;
            String type = args[0];
            boolean done = Integer.parseInt(args[1]) == 1;
            String desc = args[2];
            String details = (args.length > 3) ? args[3] : null;
            String opt = (args.length > 4) ? details + " / " + args[4] : details;
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
    
    private void update() throws DukeException {
        try {
            raf.seek(0);
            raf.setLength(0);
            for (Task a : db) {
                raf.writeBytes(a.toDB());
                raf.writeBytes(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DukeException("Error in writing to duke.txt.");
        }
    }
    
    /*
    private void edit(int index, Task task, CheckedBiConsumer<RandomAccessFile, Task> edit) {
        try {
            File temp = new File("temp.txt");
            RandomAccessFile tmpraf = new RandomAccessFile(temp, "rw");
            raf.seek(0);
            int i;
            for (i = 0; i < index && raf.getFilePointer() < raf.length(); i++) {
                String s = raf.readLine();
                if (s.equals("\n")) {
                    i--;
                    break;
                }
                tmpraf.writeBytes(s);
                tmpraf.writeBytes(System.lineSeparator());
            }
            edit.accept(tmpraf, task);
            if (raf.getFilePointer() < raf.length()) raf.readLine();
            for (i = index; i < db.size() && raf.getFilePointer() < raf.length(); i++) {
                String s = raf.readLine();
                if (s.equals("\n")) {
                    i--;
                    break;
                }
                tmpraf.writeBytes(s);
                tmpraf.writeBytes(System.lineSeparator());
            }
            raf.seek(0);
            tmpraf.seek(0);
            while (tmpraf.getFilePointer() < tmpraf.length()) {
                raf.writeBytes(tmpraf.readLine());
                raf.writeBytes(System.lineSeparator());
            }
            raf.setLength(tmpraf.length());
            tmpraf.close();
            temp.delete();
        } catch (IOException | DukeException e) { }
    }
     */

    // fix the adding problems
    public void add(Task task) throws DukeException {
        try {
            db.add(task);
            raf.writeBytes(task.toDB());
            raf.writeBytes(System.lineSeparator());
        } catch (IOException e) {
            throw ERROR_DB;
        }
    }
    
    public Task markAsDone(int index) throws DukeException {
        Task t = db.get(index);
        t.markComplete();
        update();
        return t;
    }

    public Task delete(int index) throws DukeException {
        Task t = db.get(index);
        db.remove(index);
        update();
        return t;
    }

    public int size() {
        return db.size();
    }
    
    public void close() throws DukeException {
        try {
            raf.writeBytes(System.lineSeparator());
            raf.close();
        } catch (IOException e) {
            throw ERROR_DB;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (db.size() == 0) return "You have no tasks!";
        for (int i = 1; i <= db.size(); i++) {
            sb.append("\n\t ");
            sb.append(i + "." + db.get(i-1));
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }
}
