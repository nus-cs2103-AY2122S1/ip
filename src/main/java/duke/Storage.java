package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list from file
     *
     * @return List of task in file
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        try {
            File data = new File(filePath);
            Scanner dataReader = new Scanner(data);
            while (dataReader.hasNextLine()) {
                String task = dataReader.nextLine();
                String[] split = task.split(" , ");
                String type = split[0];
                boolean isDone;
                if (split[1].equals("1")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String description = split[2];
                if (type.equals("D")) {
                    String time = split[3];
                    LocalDate date = LocalDate.parse(time);
                    Task deadline = new Deadline(description, date, isDone);
                    tasks.add(deadline);
                } else if (type.equals("E")) {
                    String time = split[3];
                    LocalDate date = LocalDate.parse(time);
                    Task event = new Event(description, date, isDone);
                    tasks.add(event);
                } else {
                    Task todo = new Todo(description, isDone);
                    tasks.add(todo);
                }
            }
            dataReader.close();
            return tasks;
        } catch (FileNotFoundException e) {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File data = new File(filePath);
            if (!data.exists()) {
                data.createNewFile();
            }
            return tasks;
        }
    }

    /**
     * Adds task into file
     *
     * @param data
     * @throws IOException
     */
    public void addData(String data) throws IOException {
        File file = new File(filePath);
        Writer output;
        output = new BufferedWriter(new FileWriter(file, true));
        output.append(data + "\n");
        output.close();
    }

    /**
     * Delete task from file
     *
     * @param index
     * @throws IOException
     */
    public void deleteData(int index) throws IOException {
        File data = new File(filePath);
        Scanner dataReader = new Scanner(data);
        StringBuffer buffer = new StringBuffer();
        int lineNumber = 1;
        while (dataReader.hasNextLine()) {
            String line = dataReader.nextLine();
            if (lineNumber != index) {
                buffer.append(line + System.lineSeparator());
            }
            lineNumber += 1;
        }
        String fileContents = buffer.toString();
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }

    /**
     * Label task as done in file
     *
     * @param index
     * @throws IOException
     */
    public void updateDone(int index) throws IOException {
        File data = new File(filePath);
        Scanner dataReader = new Scanner(data);
        String taskData = null;
        StringBuffer buffer = new StringBuffer();
        while (index > 0) {
            taskData = dataReader.nextLine();
            index -= 1;
        }
        dataReader.close();
        String newData = taskData.replaceFirst("0", "1");
        dataReader = new Scanner(data);
        while (dataReader.hasNextLine()) {
            buffer.append(dataReader.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();
        fileContents = fileContents.replaceAll(taskData, newData);
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }
}
