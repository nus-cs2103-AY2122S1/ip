import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class SaveHelper{
    private static String fileDirectory = "./data";
    private static String filePath = "./data/duke.txt";

    public static void createFile() {
        try {
            Path path = Paths.get(fileDirectory);
            if (!Files.isDirectory(path)) {
                Files.createDirectory(path);
            }
            Path file = Paths.get(filePath);
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public static List<Task> readData() {
        SaveHelper.createFile();
        List<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] values = task.split(" \\| ");
                switch(values[0]) {
                case "T":
                    list.add(new ToDo(values[2], values[1].equals("1")));
                    break;
                case "E":
                    list.add(new Event(values[2], values[1].equals("1"), values[3]));
                    break;
                case "D":
                    list.add(new Deadline(values[2], values[1].equals("1"), values[3]));
                    break;
                }
            }
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        }
        return list;
    }

    public static void writeData(List<Task> list) {
        SaveHelper.createFile();
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String parsedTask = task.parseForStorage();
                if (i < list.size() - 1) {
                    parsedTask += "\n";
                }
                fw.write(parsedTask);
            }
            fw.close();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
