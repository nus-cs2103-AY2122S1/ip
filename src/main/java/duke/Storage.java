package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private Path dirPath;
    private Path dataPath;
    private Utility utility;

    /**
     * Constructor for Storage class.
     *
     * @param filePath Path to data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        dataPath = Paths.get(filePath);
        utility = new Utility();
    }

    /**
     * Loads data stored on hard disk into local list for easy manipulation.
     *
     * @return ArrayList of Task objects from previous session.
     */
    public List<Task> loadData() {
        List<Task> tasks = new ArrayList<Task>(100);

        try {
            if (!Files.exists(this.dataPath)) {
                Files.createFile(this.dataPath);
            }

            Files.lines(this.dataPath).forEach(x -> {
                String[] data = x.split(",");
                String taskType = data[0];
                Task toAdd;
                if (taskType.equalsIgnoreCase("todo")) {
                    toAdd = new ToDo(data[2]);
                } else if (taskType.equalsIgnoreCase("deadline")) {
                    toAdd = new Deadline(data[2], utility.stringToDate(data[3]));
                } else if (taskType.equalsIgnoreCase("event")) {
                    toAdd = new Event(data[2], utility.stringToDate(data[3]));
                } else {
                    throw new IllegalArgumentException("Unrecognised task type on loading");
                }

                if (data[1].equals("1")) {
                    toAdd.setDone();
                }
                tasks.add(toAdd);
            });

        } catch (IOException e) {
            System.out.println(e);
        }

        return tasks;
    }

    /**
     * Rewrites content in dataset according to the ArrayList.
     *
     * @param list ArrayList of tasks.
     */
    public void updateDataSet(List<Task> list) {
        try {
            Path tempPath = Paths.get("temp.txt");
            Files.createFile(tempPath);

            for (int i = 0; i < list.size(); i++) {
                String toAdd = list.get(i).getDataRep();
                Files.writeString(tempPath, toAdd + System.lineSeparator(), StandardOpenOption.APPEND);
            }

            Files.move(tempPath, tempPath.resolveSibling(this.filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("cant update dataset");
        }
    }

    /**
     * Returns data path associated with this program.
     *
     * @return Path object.
     */
    public Path getDataPath() {
        return this.dataPath;
    }
}
