import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Storage {

    private final String filepath;

    Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, this.filepath);
        boolean directoryExists = Files.exists(path);

        if (directoryExists) {
            try {
                List<String> text = Files.readAllLines(path);
                ArrayList<Task> taskList = new ArrayList<>();
                for (String x: text) {
                    String[] data = x.split(Pattern.quote(" | "));
                    Task t;
                    if (data.length < 2) {
                        continue;
                    }
                    switch (data[1]) {
                        case "T":
                            t = new ToDo(data[3]);
                            break;
                        case "E":
                            LocalDate date = LocalDate.parse(data[4]);
                            if (data.length == 5) {
                                t = new Event(data[3], date);
                            } else {
                                String hour = data[5].replace(":", "").substring(0, 2);
                                String minute = data[5].replace(":", "").substring(2, 4);
                                LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
                                t = new Event(data[3], date, time);
                            }
                            break;
                        case "D":
                            LocalDate date2 = LocalDate.parse(data[4]);
                            if (data.length == 5) {
                                t = new Deadline(data[3], date2);
                            } else {
                                String hour = data[5].replace(":", "").substring(0, 2);
                                String minute = data[5].replace(":", "").substring(2, 4);
                                LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
                                t = new Deadline(data[3], date2, time);
                            }
                            break;
                        default:
//                            Invalid task found when loading, skipped!
                            continue;
                    }

                    if (data[2].equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                }
                return taskList;
            } catch (IOException e) {
                throw new LoadingError("Couldn't load file :(");
            }
        } else {
            throw new LoadingError("File not found :(");
        }
    }

    public void save(TaskList taskList) {
        String home = System.getProperty("user.dir");
        String text = taskList.stringifyTasksForSave();
        Path path = Paths.get(home, "data");
        boolean directoryExists = Files.exists(path);

        if (!directoryExists) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.write(Paths.get(home,"data", "taskList.txt"), text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
