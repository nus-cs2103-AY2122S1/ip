import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public List<Task> load() throws DukeException {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("IOException");
        }

        List<Task> strList = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String currentEntry = s.nextLine();
                String[] currentEntryArray = currentEntry.split(" \\| ");
                switch(currentEntryArray[0]) {
                    case "T":
                        Todo newTodo = new Todo(currentEntryArray[2]);
                        if (currentEntryArray[1].equals("1")) {
                            newTodo.setDone();
                        }
                        strList.add(newTodo);
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(currentEntryArray[2], LocalDate.parse(currentEntryArray[3]));
                        if (currentEntryArray[1].equals("1")) {
                            newDeadline.setDone();
                        }
                        strList.add(newDeadline);
                        break;
                    case "E":
                        Event newEvent = new Event(currentEntryArray[2], LocalDate.parse(currentEntryArray[3]));
                        if (currentEntryArray[1].equals("1")) {
                            newEvent.setDone();
                        }
                        strList.add(newEvent);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return strList;
    }

    public void save(TaskList tasks) {
        try {
            String saveStr = "";
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size() ; i++) {
                Task task = tasks.get(i);
                if (task instanceof Todo) {
                    String saveLine = "T | " + task.getStatusBinary() + " | "
                            + task.getDescription() + "\n";
                    saveStr = saveStr + saveLine;
                } else if (task instanceof Deadline) {
                    String saveLine = "D | " + task.getStatusBinary() + " | "
                            + task.getDescription() + " | " + ((Deadline) task).getBy()
                            + "\n";
                    saveStr = saveStr + saveLine;
                } else if (task instanceof Event) {
                    String saveLine = "E | " + task.getStatusBinary() + " | "
                            + task.getDescription() + " | " + ((Event) task).getAt()
                            + "\n";
                    saveStr = saveStr + saveLine;
                } else {
                    continue;
                }
            }
            fw.write(saveStr);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
