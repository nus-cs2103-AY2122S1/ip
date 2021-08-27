package Duke.Tool;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.time.format.DateTimeFormatter;

/**
 * Represents Storage class：deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final String filePath;
    private final DateTimeFormatter  timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    /**
     * The constructor for Storage
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * The method readdata for reading data from input file path
     * @return ArrayList
     * @throws IOException
     */
    public ArrayList<Task> readData() throws IOException {
        ArrayList<Task> task = new ArrayList<>();

        try {
            File myData = new File(filePath);
            Scanner sc = new Scanner(myData);
            while (sc.hasNext()) {
                String cur = sc.nextLine();
                String[] curTask = cur.split(" \\| ");
                boolean isDone = curTask[1].equals("1");
                if (curTask[0].equals("T")) {
                    task.add(new Todo(curTask[2], isDone));
                } else if(curTask[0].equals("D")) {
                    task.add(new Deadline(curTask[2], LocalDateTime.parse(curTask[3], timeFormat), isDone));
                } else if(curTask[0].equals("E")) {
                    task.add(new Event(curTask[2], LocalDateTime.parse(curTask[3], timeFormat), isDone));
                } else {}
            }

        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("data folder does not exist, create now");
            } else if (new File("data/duke.txt").createNewFile()){
                System.out.println("duke.txt file not exist, create now");
            }
        }
        return task;
    }

    /**
     * The method of write data to input file path
     * @param task
     */
    public void writeData(ArrayList<Task> task) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            for (Task t : task) {
                fw.write(t.formatChange() + "\n");
            }
            fw.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
