import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class TaskList {
    private List<Task> list;
    private final Ui ui;
    private final Storage storage;

    TaskList() {
        this.list = new ArrayList<Task>();
        this.ui = new Ui();
        this.storage = new Storage("list.txt");
    }

    TaskList(List<Task> list) {
        this();
        this.list = list;
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
        ui.print("Nice! I've marked this task as done:");
        ui.print("    %s%n", doneTask.toString());
        saveData();
    }

    void add(Task item) {
        addWithoutPrinting(item);
        ui.print("Noted! I've added the following task:");
        ui.print("    %s%n", item.toString());
        printSize();
    }

    void addWithoutPrinting(Task item) {
        list.add(item);
        saveData();
    }

    void delete(int index) {
        Task removedItem = list.remove(index - 1);
        ui.print("Got it. I've removed the following task:");
        ui.print("    %s%n", removedItem.toString());
        printSize();
        saveData();
    }

    void printSize() {
        ui.print("Total tasks: %d%n", list.size());
    }

    void printItems() {
        ui.print("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            ui.print("%d. %s%n", (i + 1), list.get(i));
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
            boolean fileAlreadyExists = !storage.createFileIfNotExists(fileName);
            Path path = Paths.get(Storage.directory + "/" + fileName);
            Files.write(path, listItems(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
