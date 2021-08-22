import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

import java.time.LocalDate;

public class TaskList {
    private static final String directory = "./data";
    private final List<Task> list;

    TaskList() {
        this.list = new ArrayList<Task>();
    }

    TaskList(List<Task> storage) {
        this.list = storage;
    }

    List<Task> getList() {
        return this.list;
    }

    Task getTask(int index) {
        return list.get(index - 1);
    }

    void markTaskAsDone(int index) {
        Task doneTask = getTask(index).markAsDone();
        list.set(index - 1, doneTask);
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("    %s%n", doneTask.toString());
        saveData();
    }

    void add(Task item) {
        addWithoutPrinting(item);
        System.out.println("Noted! I've added the following task:");
        System.out.printf("    %s%n", item.toString());
        printSize();
    }

    void addWithoutPrinting(Task item) {
        list.add(item);
        saveData();
    }

    void delete(int index) {
        Task removedItem = list.remove(index - 1);
        System.out.println("Got it. I've removed the following task:");
        System.out.printf("    %s%n", removedItem.toString());
        printSize();
        saveData();
    }

    void printSize() {
        System.out.printf("Total tasks: %d%n", list.size());
    }

    void printItems() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n", (i + 1), list.get(i));
        }
    }

    List<String> listItems() {
        List<String> items = new ArrayList<String>();
        for (Task task : list) {
            items.add(String.format("%s", task));
        }
        return items;
    }

    void saveData() {
        String fileName = "list.txt";
        try {
            boolean fileAlreadyExists = !createFileIfNotExists(fileName);
            Path path = Paths.get(directory + "/" + fileName);
            Files.write(path, listItems(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readData(String fileName) {
        String path = directory + "/" + fileName;
        File file = new File(path);
        if (!file.exists()) {
            return;
        }

        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String type = Character.toString(line.charAt(line.indexOf("[") + 1));
                boolean isDone = Character.toString(line.charAt(line.indexOf("[", 2) + 1)).equals("X");
                String name = line.substring(line.indexOf("]", line.indexOf("]") + 1) + 2);

                if (type.equals("T")) {
                    addWithoutPrinting(new ToDo(name, isDone));
                } else if (type.equals("D")) {
                    name = name.split("\\(")[0].stripTrailing();
                    String parsedInput = line.split("deadline:")[1];
                    String deadline = parsedInput.substring(1, parsedInput.length() - 1);
                    addWithoutPrinting(new Deadline(name, isDone, LocalDate.parse(deadline, Deadline.formatter)));
                } else {
                    name = name.split("\\(")[0].stripTrailing();
                    String parsedInput = line.split("at:")[1];
                    String at = parsedInput.substring(1, parsedInput.length() - 1);
                    addWithoutPrinting(new Event(name, isDone, at));
                }

                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean createFileIfNotExists(String fileName) throws IOException {
        File fileDirectory = new File(directory);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }

        File file = new File(directory + "/" + fileName);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }
}
