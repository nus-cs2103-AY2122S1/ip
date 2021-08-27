import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SavedHistory {

    private File dataFolder = new File("./src/main/data");
    private File txtFile = new File("./src/main/data/titi.txt");
    private ArrayList<Task> savedTasks = new ArrayList<>();

    public SavedHistory() {
        checkFile();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(txtFile));
            String line = reader.readLine();
            while (line != null) {
                savedTasks.add(read(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // check and create necessary folder and file if needed
    private void checkFile() {
        if (!txtFile.exists()) {
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
            try {
                txtFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private Task read(String string) {
        char type = string.charAt(1);
        char status = string.charAt(4);
        Task nextTask;
        String description;
        int lineBreaker;

        switch (type) {
            case 'T':
                description = string.substring(7);
                nextTask = new ToDo(description);
                if (status == 'X') {
                    nextTask.complete();
                }
                break;

            case 'D':
                lineBreaker = string.indexOf(" (by: ");
                description = string.substring(7, lineBreaker);
                String by = string.substring(lineBreaker + 6, string.length() - 2);
                nextTask = new Deadline(description, by);
                if (status == 'X') {
                    nextTask.complete();
                }
                break;

            case 'E':
                lineBreaker = string.indexOf(" (at: ");
                description = string.substring(7, lineBreaker);
                String at = string.substring(lineBreaker + 6, string.length() - 2);
                nextTask = new Event(description, at);
                if (status == 'X') {
                    nextTask.complete();
                }
                break;

            default:
                nextTask = null;
        }

        return nextTask;
    }


    public ArrayList<Task> readHistory() {
        return savedTasks;
    }


    public void saveHistory(ArrayList<Task> newTasks) {
        try {
            FileWriter fileWriter = new FileWriter(txtFile);
            String tasks = "";
            for (int i = 0; i < newTasks.size(); i++) {
                tasks += newTasks.get(i) + System.lineSeparator();
            }
            fileWriter.write(tasks);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
