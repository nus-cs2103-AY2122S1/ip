package duke;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StorageHandler {
    private static String path = "src/main/data/TaskList.txt";
    private static String fileNewLine = System.lineSeparator();

    /**
     * Loads list of tasks from TaskList.txt file into TaskList.
     *
     * @return boolean True indicates that the data was successfully loaded, and vice-verso.
     */
    public static boolean loadAll() {
        try {
            File taskListCSV = new File("src/main/data/TaskList.txt");
            boolean fileExists = taskListCSV.createNewFile();
            if (fileExists) {
                //new file created
                //do nothing
            } else {
                //file was there alr
                //load data into TaskList
                Scanner myReader = new Scanner(taskListCSV);
                char taskType = 'x';
                int done = 0;
                String description = "";
                String dateString = "";
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    taskType = data.charAt(1);
                    done = data.charAt(4) - 49;
                    if(taskType == 'E' || taskType == 'D') {
                        int slashIndex = taskType == 'E'
                                ? data.indexOf("(at:")
                                : data.indexOf("(by:");
                        description = data.substring(7, slashIndex - 1);
                        dateString = data.substring(slashIndex + 5, data.length() - 1);
                    } else {
                        description = data.substring(7);
                    }
                    Task task;
                    if (taskType == 'E') {
                        task = new Event(description, dateString);
                        if (done == 1)
                            task.markAsDone();
                        TaskList.getTaskList().addTask(task);
                    } else if (taskType == 'D') {
                        LocalDate deadlineDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        task = new Deadline(description, deadlineDate);
                        if (done == 1)
                            task.markAsDone();
                        TaskList.getTaskList().addTask(task);
                    } else {
                        task = new ToDo(description);
                        if (done == 1)
                            task.markAsDone();
                        TaskList.getTaskList().addTask(task);
                    }
                }
                myReader.close();
            }
        } catch (IOException E) {
            return false;
        }

        return true;
    }

    /**
     * Inserts a list of tasks from TaskList to TaskList.txt.
     *
     * @return boolean True indicates that the data was successfully transferred, and vice-verso.
     */
    public static boolean insertTasks() {
        String linebreak = System.lineSeparator();
        boolean rewrite = true;
        for(Task task: TaskList.getTaskList().getTasks()) {
            try {
                if(rewrite) {
                    FileWriter myWriter = new FileWriter(path);
                    myWriter.write(task.getTaskString());
                    myWriter.write(linebreak);
                    myWriter.close();
                    rewrite = false;
                } else {
                    FileWriter myWriter = new FileWriter(path, true);
                    myWriter.write(task.getTaskString());
                    myWriter.write(linebreak);
                    myWriter.close();
                }

            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
