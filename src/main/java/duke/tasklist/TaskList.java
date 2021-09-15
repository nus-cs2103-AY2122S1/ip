package duke.tasklist;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class TaskList {
    private List<Task> list;
    private final Ui ui;
    private final Storage storage;

    TaskList() {
        this.list = new ArrayList<Task>();
        this.ui = new Ui();
        this.storage = new Storage("list.txt");
    }

    public TaskList(List<Task> list) {
        this();
        this.list = list;
    }

    List<Task> getList() {
        return this.list;
    }

    Task getTask(int index) {
        return list.get(index - 1);
    }

    /**
     * Marks a task as done using their index (1-based).
     *
     * @param index Index of item that wants to be marked as done.
     */
    public String markTaskAsDone(int index) {
        String response = "";

        Task doneTask = getTask(index).markAsDone();
        list.set(index - 1, doneTask);

        response += "Nice! I've marked this task as done:\n";
        response += String.format("    %s%n", doneTask.toString());

        saveData();

        return response;
    }

    /**
     * Adds a task to the list.
     *
     * @param item The task that is to be added.
     */
    public String add(Task item) {
        String response = "";
        addWithoutPrinting(item);

        response += ("Noted! I've added the following task:\n");
        response += String.format("    %s%n", item.toString());
        response += printSize();

        assert !response.equals("");

        return response;
    }

    void addWithoutPrinting(Task item) {
        list.add(item);
        saveData();
    }

    /**
     * Deletes a task using their index (1-based).
     *
     * @param index Index of item that wants to be deleted.
     */
    public String delete(int index) {
        String response = "";
        Task removedItem = list.remove(index - 1);

        response += ("Got it. I've removed the following task:\n");
        response += String.format("    %s%n", removedItem.toString());
        response += printSize();

        saveData();

        assert !response.equals("");

        return response;
    }

    /**
     * Finds a task whose name matches a given keyword.
     *
     * @param keyword Keyword of task that wants to be found.
     */
    public String find(String keyword) {
        String response = "";
        List<Task> filteredList = list.stream().filter(task -> task.getName().contains(keyword))
                .collect(Collectors.toList());
        if (filteredList.size() > 0) {
            response += ("Here are the matching tasks that I found:\n");
            response += printItems(filteredList);
        } else {
            response += ("No tasks found.\n");
        }
        assert !response.equals("");

        return response;
    }

    public String tag(int index, String tag) {
        String response = "";
        Task taggedItem = list.get(index - 1);
        taggedItem.addTag(tag);

        response += ("Got it. I've tagged the following task:\n");
        response += String.format("    %s%n", taggedItem);

        saveData();

        assert !response.equals("");

        return response;
    }

    String printSize() {
        return String.format("Total tasks: %d%n", list.size());
    }

    /**
     * Prints the current list of tasks.
     */
    public String printItems() {
        String response = "Here are the tasks in your list:\n";
        return response + printItems(this.list);
    }

    /**
     * Prints the current list of tasks.
     */
    public String printItems(List<Task> list) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            response.append(String.format("%d. %s%n", (i + 1), list.get(i)));
        }
        return response.toString();
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
            storage.createFileIfNotExists(fileName);
            Path path = Paths.get(Storage.DIRECTORY + "/" + fileName);
            Files.write(path, listItems(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
