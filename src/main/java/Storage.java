import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void save(ArrayList<Task> tasks) {
        try {
            File file = new File("data/duke.txt");

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter("data/duke.txt");
            String toSave = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                toSave = toSave.concat(currentTask.taskType()
                        + " | "
                        + currentTask.isDoneToInt()
                        + " | "
                        + currentTask.getTaskDetails() + "\n");
            }

            writer.write(toSave);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static TaskList load() {
        ArrayList<Task> loadedTaskList = new ArrayList<>();
        try {
            File file = new File("data/duke.txt");
            if (file.createNewFile()) {
                return new TaskList();
            }

            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] lineSplit = s.nextLine().split(" \\| ",4);
                if (lineSplit[0].equals("T")) {
                    loadedTaskList.add(new ToDo(lineSplit[2]));
                } else if (lineSplit[0].equals("D")) {
                    loadedTaskList.add(new Deadline(lineSplit[2], lineSplit[3]));
                } else if (lineSplit[0].equals("E")) {
                    loadedTaskList.add(new Event(lineSplit[2], lineSplit[3]));
                }

                if (lineSplit[1].equals("0")) {
                    loadedTaskList.get(loadedTaskList.size() - 1).markDone();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new TaskList(loadedTaskList);
    }
}
