import java.io.*;
import java.util.ArrayList;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split("\\|");
                switch(split[0]) {
                    case("T"):
                        Todo t = new Todo(split[2]);
                        if (!split[1].equals("0")) {
                            t.markAsDone();
                        }
                        list.add(t);
                        break;
                    case("E"):
                        Event e = new Event(split[2], split[3]);
                        if (!split[1].equals("0")) {
                            e.markAsDone();
                        }
                        list.add(e);
                        break;
                    case("D"):
                        Deadline d = new Deadline(split[2], split[3]);
                        if (!split[1].equals("0")) {
                            d.markAsDone();
                        }
                        list.add(d);
                        break;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException(DukeException.Type.LOADING);
        }
        return list;
    }

    public void writeAll(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.list) {
                writer.write(task.toWrite());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
