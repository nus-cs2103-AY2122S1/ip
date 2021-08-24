import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskDatabase {
    public static List<Task> readTaskData(File file) {
        List<Task> tasks = new ArrayList<>();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner sc = new Scanner(new FileInputStream(file));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] split = line.split(" \\| ");
                boolean isDone = Integer.parseInt(split[1]) == 1;
                switch (split[0]) { 
                    case "T":
                        tasks.add(new ToDo(split[2], isDone));
                        break;
                    case "D":
                        tasks.add(new Deadline(split[2], LocalDate.parse(split[3]), isDone));
                        break;
                    case "E":
                        tasks.add(new Event(split[2], LocalDate.parse(split[3]), isDone));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
    
    public static void writeTaskData(File file, List<Task> tasks) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(file));
            for (Task t : tasks) {
                writer.println(t.toDataString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
