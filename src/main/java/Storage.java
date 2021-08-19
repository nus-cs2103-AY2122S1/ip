import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String FILE_LOCATION = "C:\\Users\\kohjx\\projects\\ip\\data\\TaskList.txt";

    public static void saveList(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(Storage.FILE_LOCATION, false);
            for (Task task : tasks) {
                writer.write(task.saveString());
                writer.write("\r\n");
            }
            writer.close();
            // System.out.println(Ui.OUTPUT_DISPLAY + "Duke-san saved your list UwU");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TaskList loadList() {
        try {
            FileReader reader = new FileReader(Storage.FILE_LOCATION);
            File file = new File(Storage.FILE_LOCATION);
            Scanner text = new Scanner(file);

            ArrayList<Task> loaded = new ArrayList<>();
            while(text.hasNextLine()) {
                String task = text.nextLine();
                loaded.add(Task.StringToTask(task));
            }
            return new TaskList(loaded);

        } catch (FileNotFoundException e) {
            return new TaskList();

        } catch (ParseException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }
}
