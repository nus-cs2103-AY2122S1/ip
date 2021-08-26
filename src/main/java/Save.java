import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Save extends FileAccess{
    public Save() {
        super();
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
        } catch (IOException e){
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "saving file to " + this.filePath;
    }
}
