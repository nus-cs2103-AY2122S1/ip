package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    File savedTasksFile;

    public Storage(String filePath) {
        this.savedTasksFile = new File(filePath);
        File dir = new File(savedTasksFile.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public ArrayList<String> load() {

        try {
            if (savedTasksFile.createNewFile()) {
                System.out.println("pee pee");
                return new ArrayList<>();
            } else {
                System.out.println("poo poo");
                Scanner reader = new Scanner(savedTasksFile);
                ArrayList<String> loadingStrings = new ArrayList<>();
                while (reader.hasNextLine()) {
                    String next = reader.nextLine();
                    loadingStrings.add(next);
                }
                return loadingStrings;
            }
        } catch (IOException e) {
            System.out.println("AN ERROR HAS OCCURRED WHILE LOADING");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void save(ArrayList<Task> tasks) {
        try {
            savedTasksFile.createNewFile();
            FileWriter writer = new FileWriter(savedTasksFile, false);
            for (int i = 0; i < tasks.size(); i++) {
                StringBuilder builder = new StringBuilder();
                Task task = tasks.get(i);
                builder.append(task.getType());
                builder.append("|");
                if (task.getStatusIcon() == "X") {
                    builder.append("1|");
                } else {
                    builder.append("0|");
                }
                builder.append(task.getDescription());
                builder.append("|");
                builder.append(task.getDateTimeString());
                builder.append("\n");
                writer.write(builder.toString());

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("AN ERROR HAS OCCURRED WHILE SAVING");
            e.printStackTrace();
        }

    }
}
