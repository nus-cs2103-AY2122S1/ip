import java.io.*;

public class Storage {
    private String path;
    private DukeList list;

    public Storage(String path, DukeList list) {
        this.path = path;
        this.list = list;
    }


    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            fileWriter.write(list.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }


    public void load() {
    }
}
