import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        try {
            File data = new File(filePath);
            Scanner dataReader = new Scanner(data);
            while (dataReader.hasNextLine()) {
                String task = dataReader.nextLine();
                System.out.println(task);
                String[] split = task.split(" , ");
                String type = split[0];
                boolean done;
                if (split[1].equals("1")) {
                    done = true;
                } else {
                    done = false;
                }
                String description = split[2];
                if (type.equals("D")) {
                    String time = split[3];
                    LocalDate date = LocalDate.parse(time);
                    Task deadline = new Deadline(description, date, done);
                    tasks.add(deadline);
                } else if (type.equals("E")) {
                    String time = split[3];
                    LocalDate date = LocalDate.parse(time);
                    Task event = new Event(description, date, done);
                    tasks.add(event);
                } else {
                    Task todo = new Todo(description, done);
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

    public void addData(String data) throws IOException {
        File file = new File(filePath);
        Writer output;
        output = new BufferedWriter(new FileWriter(file, true));
        output.append(data + "\n");
        output.close();
    }

    public void deleteData(int index) throws IOException {
        File data = new File(filePath);
        Scanner dataReader = new Scanner(data);
        StringBuffer buffer = new StringBuffer();
        int lineNumber = 1;
        while (dataReader.hasNextLine()) {
            String line = dataReader.nextLine();
            if (lineNumber != index) {
                buffer.append(line+System.lineSeparator());
            }
            lineNumber += 1;
        }
        String fileContents = buffer.toString();
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }

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
            buffer.append(dataReader.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        fileContents = fileContents.replaceAll(taskData, newData);
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }
}
