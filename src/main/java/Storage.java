import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private Path dirPath;
    private Path dataPath;
    private Utility utility;

    public Storage(String filePath) {
        this.filePath = filePath;
        //TO change
        dirPath = Paths.get("dataSets");
        dataPath = Paths.get(filePath);
        utility = new Utility();
    }

    public List<Task> loadData() {
        List<Task> tasks = new ArrayList<Task>(100);

        try {
            if(!Files.exists(this.dataPath)) {
                Files.createDirectories(this.dirPath);
                Files.createFile(this.dataPath);
            }

            Files.lines(this.dataPath).forEach(x -> {
                String[] data = x.split(",");
                String taskType = data[0];
                Task toAdd;
                if(taskType.equalsIgnoreCase("todo")) {
                    toAdd = new ToDo(data[2]);
                }else if(taskType.equalsIgnoreCase("deadline")) {
                    toAdd = new Deadline(data[2], utility.stringToDate(data[3]));
                } else if(taskType.equalsIgnoreCase("event")) {
                    toAdd = new Event(data[2], utility.stringToDate(data[3]));
                } else {
                    throw new IllegalArgumentException("Unrecognised task type on loading");
                }

                if(data[1].equals("1")) {
                    toAdd.setDone();
                }
                tasks.add(toAdd);
            });

        } catch(IOException e) {
            System.out.println(e);
        }

        return tasks;
    }

    /**
     * Rewrites content in dataset according to the ArrayList.
     * @param list ArrayList of tasks.
     * @param dataPath Path to data set.
     */
    public void updateDataSet(List<Task> list) {
        try {
            Path tempPath = Paths.get("datasets/temp.txt");
            Files.createFile(tempPath);

            for(int i = 0; i < list.size(); i++) {
                String toAdd = list.get(i).getDataRep();
                Files.writeString(tempPath, toAdd + System.lineSeparator(), StandardOpenOption.APPEND);
            }

            Files.move(tempPath, tempPath.resolveSibling("Data.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("cant update dataset");
        }
    }

    public Path getDataPath() {
        return this.dataPath;
    }
}
