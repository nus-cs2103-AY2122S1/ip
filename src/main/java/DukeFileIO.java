import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeFileIO {

    public static void readPastData() throws FileNotFoundException {
        File data = new File("data/duke.txt");
        Scanner dataReader = new Scanner(data);
        char[] prefixes = {'T', 'E', 'D'};
        while (dataReader.hasNext()) {
            Duke.counter++;
            String entry = dataReader.nextLine();
            String[] components = entry.split("[\\|]");
            char prefix = entry.charAt(0);
            switch (prefix) {
            case 'T':
                String description = components[2].substring(1);
                ToDo t = new ToDo(description);
                if (checkIfDone(components[1])) {
                    t.markAsDone();
                }
                Duke.taskList.add(t);
                break;
            case 'D':
                description = components[2].substring(1);
                String time = components[3].substring(1);
                Deadline d = new Deadline(description, time);
                if (checkIfDone(components[1])) {
                    d.markAsDone();
                }
                Duke.taskList.add(d);
                break;
            case 'E':
                description = components[2].substring(1);
                time = components[3].substring(1);
                Event e = new Event(description, time);
                if (checkIfDone(components[1])) {
                    e.markAsDone();
                }
                Duke.taskList.add(e);
                break;
            }
        }
    }

    private static boolean checkIfDone(String component) {
        if (Integer.parseInt(component.substring(1,2)) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void writeCurrentData() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task t : Duke.taskList) {
            fw.write(t.toFile() + System.lineSeparator());
        }
        fw.close();
    }

    public static void createDataFile() {
        File data = new File("data/duke.txt");
        try {
            data.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
