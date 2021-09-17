package Duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> records;

    public TaskList(ArrayList<Task> existingRecords) {
        if (existingRecords != null) {
            this.records = existingRecords;
        } else {
            this.records = new ArrayList<>();
        }
    }

    /**
     * Returns message for adding a to-do task.
     *
     * @param description description of to-do task.
     * @return added message for to-do task.
     */
    public String addTodo(String description) {
        Todo toAdd = new Todo(description);
        records.add(toAdd);
        return String.format("Got it. I've added this todo:\n\t %1$s \n\t" +
                "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), records.size());
    }

    /**
     * Returns message for adding an event task.
     *
     * @param description description of event task.
     * @param at string of event date/time.
     * @return added message for command event.
     */
    public String addEvent(String description, LocalDate at) {
        Event toAdd = new Event(description, at);
        records.add(toAdd);
        return String.format("Got it. I've added this event:\n\t %1$s \n\t" +
                "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), records.size());
    }

    /**
     * Returns message for adding deadline task.
     *
     * @param description description of deadline task.
     * @param by string of deadline date/time.
     * @return added message for adding deadline task.
     */
    public String addDeadline(String description, LocalDate by) {
        Deadline toAdd = new Deadline(description, by);
        records.add(toAdd);
        return String.format("Got it. I've added this deadline:\n\t %1$s \n\t" +
                "Now you have %2$d tasks in the list.\n\t", toAdd.toString(), records.size());
    }

    /**
     * Returns delete message for bot.
     *
     * @param index index of task to be deleted.
     * @return delete message for bot.
     */
    public String delete(int index) {
        Task removedTask = records.remove(index);
        return String.format("Noted! I've removed this task:\n\t %1$s\n\t" +
                "Now you have %2$d tasks in the list.\n\t", removedTask.toString(), records.size());
    }

    /**
     * Returns message representing list of all items user added.
     *
     * @return formatted string representing elements in records array.
     */
    public String list() {
        final StringBuilder list = new StringBuilder("Here are the tasks in your list:\n\t");
        records.forEach((el) -> list.append(
                String.format("%1$d. %2$s \n\t",
                        records.indexOf(el) + 1, el.toString())));
        return list.toString();
    }

    /**
     * Returns matches in task list for keyword.
     * @param keyword String
     * @return Returns matches in task list for keyword.
     */
    public String find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        final StringBuilder matchedTasks = new StringBuilder("Here are the matching tasks in your list:\n\t");

        for (int i = 0; i < records.size(); i++) {
            Task current = records.get(i);
            if (current.description.toLowerCase().contains(keyword.toLowerCase())) {
                result.add(current);
            }
        }
        result.forEach((el) -> matchedTasks.append(
                String.format("%1$d. %2$s \n\t",
                        result.indexOf(el) + 1, el.toString())));
        return matchedTasks.toString();
    }

    /**
     * Returns task at given index.
     * @param index int
     * @return task at given index.
     */
    public Task getTask(int index) {
        return records.get(index);
    }

    /**
     * Returns number of tasks in tasklist.
     *
     * @return number of tasks in tasklist.
     */
    public int length() {
        return records.size();
    }
}
