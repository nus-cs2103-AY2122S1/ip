import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;


public class TaskReader {
    public static ArrayList<Task> read(String fileName) throws IOException {

        ArrayList<Task> tasks = new ArrayList<>(100);
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName));

            for (String line : lines) {
                //System.out.println(line);
                String[] tokens = line.trim().split("\\s\\|\\s", 3); // max range 2
//                System.out.println(tokens[0]);
//                System.out.println(tokens[1]);
//                System.out.println(tokens[2]);
                String taskIdentifier = tokens[0];
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                String remainder = tokens[2];
                String desc;
                String time;

                switch (taskIdentifier) {
                case "T":
                    desc = remainder;
                    tasks.add(new Todo(isDone, desc));
                    break;
                case "D":
                    String[] deadlineTokens = remainder.split("\\s\\|\\s*");
                    desc = deadlineTokens[0];
                    time = deadlineTokens[1];
                    tasks.add(new Deadline(isDone, desc, time));
                    break;
                case "E":
                    String[] eventTokens = remainder.split("\\s\\|\\s*");
                    desc = eventTokens[0];
                    time = eventTokens[1];
                    tasks.add(new Event(isDone, desc, time));
                    break;
                default:
                    break;
                }
            }
        } catch (NoSuchFileException e) {
            Path storagePath = Paths.get(fileName);
            Files.createDirectories(storagePath.getParent());
            Files.createFile(storagePath);
            System.out.println("No file found, but we created one!");
            System.out.println(e.toString() + e.getMessage());
            return read(fileName);
        } catch (IOException e) {
            System.out.println("Something went wrong during reading!");
            System.out.println(e.toString() + e.getMessage());
        } finally {

            return tasks;
        }
    }

    public static void write (String filename, ArrayList<Task> tasks) throws IOException{
        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(filename, false));
            for (int i = 0; i < tasks.size(); i++) {
                // Maybe:
                outputWriter.write(tasks.get(i).getData());
                // Or:
                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong during writing!");
            System.out.println(e.toString() + e.getMessage());
        } finally {

        }
    }
}
