package bobbybot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load files from .txt file
     * @return List of tasks
     * @throws FileNotFoundException
     */
    public List<Task> load() throws FileNotFoundException {
        System.out.println("loading from db....");
        File f = new File(filePath);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<Task> tasks = new ArrayList<Task>();
        int totalTasks = 0;

        while (s.hasNext()) {
            String[] row = s.nextLine().split(",");
            // load row into task
            switch (row[0]) {
            // data format: [type],[isDone],[desc],[period]
            case "T":
                // load task
                tasks.add(new ToDo(row[2], row[1].equals("1")));
                break;
            case "D":
                // load deadline
                tasks.add(new Deadline(row[2], row[3], row[1].equals("1"), DT_FORMATTER));
                break;
            case "E":
                // load event
                tasks.add(new Event(row[2], row[3], row[1].equals("1")));
                break;
            }
            totalTasks += 1;
        }
        System.out.println("load completed");

        return tasks;
    }

    /**
     * Saves all tasks in bobbybot.BobbyBot to hardcoded text file
     */
    public void save(TaskList tasks) throws IOException {
        // save task to .txt file
        FileWriter fw = new FileWriter(filePath);
        for (Task task: tasks.getTasks()) {
            String saveRow = task.getSaveFormatString() + "\n";
            fw.write(saveRow);
        }
        fw.close();
    }
}

