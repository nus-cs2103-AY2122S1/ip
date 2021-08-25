import java.util.ArrayList;

public class Storage {
    DukeDB dukeDb;
    public Storage(String filePath) {
        dukeDb = new DukeDB(filePath);
    }

    public ArrayList<Task> load(){
        ArrayList<Task> tasks = dukeDb.readData();
        return tasks;
    }

    public void add(Task task) {
        dukeDb.addData(task);
    }

    public void done(int index) {
        dukeDb.doneData(index);
    }

    public void delete(int index) {
        dukeDb.deleteData(index);
    }
}
