import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    private File f;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");
    private List list;

    public Storage(String pathname) {
        this.list = new List();
        f = new File(pathname);
    }

    public List readFile() throws IOException {
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] parsed = task.split("\\|");

            switch (parsed[0]) {
                case "T":
                    Todos newTodo = new Todos(parsed[2], parsed[1]);
                    list.add(newTodo);
                    break;
                case "E":
                    LocalDateTime at = LocalDateTime.parse(parsed[3], formatter);
                    Events newEvent = new Events(parsed[2], parsed[1], at);
                    list.add(newEvent);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parsed[3], formatter);
                    Deadlines newDeadline = new Deadlines(parsed[2], parsed[1], by);
                    list.add(newDeadline);
                    break;
            }
        }
        return list;
    }


    public void writeToFile(List list) throws IOException {
        FileWriter fw = new FileWriter(f.getAbsoluteFile());

        for (int i = 0; i < list.getList().size(); i++) {
            fw.write(list.getList().get(i).toSaveString() + System.lineSeparator());
        }

        fw.close();
    }

}
