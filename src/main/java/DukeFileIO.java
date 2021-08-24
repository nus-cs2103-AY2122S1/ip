import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFileIO {

    public static void readPastData(ArrayList<Task> taskList) throws FileNotFoundException {
        File data = new File("data/duke.txt");
        Scanner dataReader = new Scanner(data);
        char[] prefixes = {'T', 'E', 'D'};
        while (dataReader.hasNext()) {
            Duke.counter++;
            String entry = dataReader.nextLine();
            String[] components = entry.split("[\\|]");
//            for (String s : components) {
//                System.out.println("this" + s);
//            }
            char prefix = entry.charAt(0);
            switch (prefix) {
            case 'T':
                String description = components[2];
                ToDo t = new ToDo(description);
                if (checkIfDone(components[1])) {
                    t.markAsDone();
                }
                taskList.add(t);
                break;
            case 'D':
                description = components[2];
                String time = components[3];
                Deadline d = new Deadline(description, time);
                if (checkIfDone(components[1])) {
                    d.markAsDone();
                }
                taskList.add(d);
                break;
            case 'E':
                description = components[2];
                time = components[3];
                Event e = new Event(description, time);
                if (checkIfDone(components[1])) {
                    e.markAsDone();
                }
                taskList.add(e);
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
}
