package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the Todo List for Duke.
 */
public class List extends ArrayList<Task> {
    public static ArrayList<Task> todos;

    /**
     * Constructor for List Class when there is no initial data.
     * Creates an empty list.
     */
    public List() {
        todos = new ArrayList<>();
    }

    /**
     * Overloaded Constructor for List Class.
     * Used when loading existing data from file.
     * Creates a list consistent with the data given.
     *
     * @param data
     */
    public List(ArrayList<Task> data) {
        todos = data;
    }

    /**
     * Adds Task to the List.
     *
     * @param input the command given.
     * @throws IOException If the file cannot be read/found.
     */
    public void addTask(String input) throws IOException {
        if (input.equals("list")) {
            showList();
        } else {
            try {
                Parser.process(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Display the current content of the list.
     * Ordered by creation time.
     */
    public void showList() {
        for (int i = 0; i < List.todos.size(); i++) {
            System.out.println(i + 1 + ". " + List.todos.get(i).toString());
        }
    }
}
