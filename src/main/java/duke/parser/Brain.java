package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.BotException;
import duke.datastore.DataStore;
import duke.membuffer.MemoryBuffer;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.ToDo;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * This class represents the main logic unit of the
 * JARVIS chatbot that performs the actual functions.
 *
 * @author Rishabh Anand
 * @version CS2103 AY21/22 Semester 1
 */
public class Brain {
    public final String INVALID_DATE_MESSAGE = "You've given me an invalid date. Learn to read a calendar ...";
    public Brain() {}

    /**
     *
     * Lists out the items in memory when requested by the user.
     *
     * @param dataStore the list containing the up-to-date task records.
     * @return the final result for the given query.
     */
    public String listItems(DataStore dataStore) {
        StringBuilder builder = new StringBuilder();
        builder.append("A list of tasks for you to totally ignore: \n");
        for (int i = 0; i < dataStore.length(); i++) {
            Task task = dataStore.get(i);

            String message = i+1 + ". " + task.toString() + "\n";
            builder.append(message);
        }
        return builder.toString();
    }

    /**
     * 
     * Returns the parsed LocalDate object using the given string date.
     * 
     * @param date The date to be parsed in String format.
     * @return the LocalDate representation of the string date.
     */
    public String processDate(String date) {
        date = date.strip();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

        try {
            LocalDateTime datetime = LocalDateTime.parse(date, formatter);
            String finalTime = datetime.format(newFormatter);
            return finalTime;
        } catch (Exception e) {
            return INVALID_DATE_MESSAGE;
        }
    }

    /**
     *
     * Creates a new ToDo task based on the description given.
     *
     * @param input the user's input with the description.
     * @param dataStore the list containing the up-to-date task records.
     * @return the final result for the given query.
     */
    public String createTodo(String input, DataStore dataStore) {
        String query = input.strip().split(" ", 2)[1].strip();
        ToDo todo = new ToDo(query);
        int prevNumTasks = dataStore.length();
        dataStore.add(todo);

        StringBuilder builder = new StringBuilder();
        String message = "Got it. I've added this task: \n"
                        + "\t" + todo + "\n"
                        + "Now you have " + dataStore.length() + " tasks in the list.\n";
        builder.append(message);

        int newNumTasks = dataStore.length();

        assert newNumTasks - prevNumTasks == 1 : "Data Store task count is not functioning as expected; Task increment not observed.";

        return builder.toString();
    }

    /**
     *
     * Creates a Deadline task given a description and deadline.
     *
     * @param input the user's input with the description and deadline.
     * @param dataStore the list containing the up-to-date task records.
     * @return the final result for the given query.
     */
    public String createDeadline(String input, DataStore dataStore) {
        input = input.strip().split(" ", 2)[1].strip();
        String query = input.split("/by")[0];
        String limit = input.split("/by")[1];
        String procLimit = processDate(limit);

        if (procLimit.equals(INVALID_DATE_MESSAGE)) {
            return "You've given me an invalid date.\nLearn to read a calendar ...";
        }

        int prevNumTasks = dataStore.length();

        Deadline deadlineTask = new Deadline(query, procLimit);
        dataStore.add(deadlineTask);

        StringBuilder builder = new StringBuilder();
        String message = "Awesome! I've added this task: \n"
                          + "\t" + deadlineTask + "\n"
                          + "Now you have " + dataStore.length() + " tasks in the list.\n";
        builder.append(message);

        int newNumTasks = dataStore.length();

        assert newNumTasks - prevNumTasks == 1 : "Data Store task count is not functioning as expected; Task increment not observed.";

        return builder.toString();
    }

    /**
     *
     * Creates an Event task given a description and timing.
     *
     * @param input the user's input with the description and timing.
     * @param dataStore the list containing the up-to-date task records.
     * @return the final result for the given query.
     */
    public String createEvent(String input, DataStore dataStore) {
        input = input.strip().split(" ", 2)[1].strip();
        String query = input.split("/at")[0];
        String datetime = input.split("/at")[1];
        String procDate = processDate(datetime);
        int prevNumTasks = dataStore.length();

        if (procDate.equals(INVALID_DATE_MESSAGE)) {
            return "You've given me an invalid date.\nLearn to read a calendar ...";
        }

        Event eventTask = new Event(query, procDate);
        dataStore.add(eventTask);

        StringBuilder builder = new StringBuilder();
        String message = "Got it. I've added this task: \n"
                + "\t" + eventTask + "\n"
                + "Now you have " + dataStore.length() + " tasks to complete.\n";
        builder.append(message);

        int newNumTasks = dataStore.length();

        assert newNumTasks - prevNumTasks == 1 :
                "Data Store task count is not functioning as expected; Task increment not observed.";

        return builder.toString();
    }

    /**
     *
     * Sets a given task to completed status.
     *
     * @param input the user's input with the ID of the task in the list.
     * @param dataStore the list containing the up-to-date task records.
     * @return the final result for the given query.
     * @throws BotException throws a bot-specific exception when an error is encountered.
     */
    public String completeTask(String input, DataStore dataStore) {
        StringBuilder builder = new StringBuilder();
        String[] breakdown = input.split(" ");

        if (breakdown.length <= 1) { // only "done" is provided
            builder.append("I need an index with that, my friend ...");
        } else {
            int idx = Integer.parseInt(breakdown[1]);

            if (1 <= idx && idx <= dataStore.length()) {
                Task task = dataStore.get(idx-1);
                task.setDone();
                assert task.getStatus().equals(true) : "Task has not actually been set to true. Task still false;";

                String message = "Nice! I've marked this task as done: \n" + "\t" + task + "\n";
                builder.append(message);
            } else {
                builder.append("You've given me an invalid index, I'm afraid.");
            }
        }

        return builder.toString();
    }

    /**
     *
     * Shuts down the bot and stops the program from running.
     *
     * @param dataStore the list containing the up-to-date task records.
     * @param memBuff the memory buffer for reading and writing files.
     * @return the final result for the given query.
     */
    public String shutdownBot(DataStore dataStore, MemoryBuffer memBuff) {
        // save current task log
        StringBuilder builder = new StringBuilder();
        Alert alert = new Alert(AlertType.CONFIRMATION, "Shutdown JarVIS?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            builder.append("Goodbye! As always, a pleasure working with you.\n");
            System.exit(0);
        } else if (alert.getResult() == ButtonType.NO) {
            builder.append("Oh, welcome back, by the way.");
        }

        return builder.toString();
    }

    /**
     *
     * Deletes the current task from working memory.
     *
     * @param input the user's input with the task ID to be deleted.
     * @param dataStore the list containing the up-to-date task records.
     * @return the final result for the given query.
     */
    public String deleteTask(String input, DataStore dataStore) {
        StringBuilder builder = new StringBuilder();
        String[] breakdown = input.split(" ");

        if (breakdown.length <= 1) {
            builder.append("I need an index with that, my friend ...");
        } else {
            int idx = Integer.parseInt(breakdown[1]);
            try {
                Task task = dataStore.get(idx - 1);
                int prevNumTasks = dataStore.length();

                // check if index is within appropriate range
                if (1 <= idx && idx <= dataStore.length()) {
                    String message = "Understood. I've removed, \n" + "\t" + task.toString() + "\n";
                    builder.append(message);

                    dataStore.remove(idx - 1); // actual deletion
                    int newNumTasks = dataStore.length();

                    assert prevNumTasks - newNumTasks == 1 :
                            "Data Store task count is not functioning as expected; Task increment not observed.";

                    builder.append(String.format("You now have %d tasks to totally ignore.\n", dataStore.length()));
                } else {
                    builder.append("You've given me an invalid index, I'm afraid.");
                }
            } catch (Exception e) {
                builder.append("You've given me an invalid index, I'm afraid.");
            }
        }

        return builder.toString();
    }

    /**
     * Returns the closest matching results from list of tasks.
     * @param input the user query to be found.
     * @param dataStore the list containing the up-to-date task records.
     * @return the final result for the given query.
     */
    public String find(String input, DataStore dataStore) {
        String query = input.split(" ")[1];

        StringBuilder builder = new StringBuilder();

        builder.append("My search of the Oracle Cloud reveals these records: \n");
        for (int i = 0; i < dataStore.length(); i++) {
            Task record = dataStore.get(i);
            String message = record.toString();

            if (record.getDescription().contains(query)) {
                String text = (i + 1) + ". " + message + "\n";
                builder.append(text);
            }
        }

        return builder.toString();
    }

    /**
     * Returns a guide/manual for the user that presents all available commands
     * that can be used in JarVIS.
     *
     * @return string that represents the user manual and available commands for JarVIS.
     */
    public String manual() {
        StringBuilder builder = new StringBuilder();
        String message = "Hello! Welcome to the JarVIS User Manual.\n" + "Available commands:\n\n";
        builder.append(message);

        String[] commands = new String[]{
                "list",
                "todo <task_name>",
                "deadline <task_name> /by yyyy-MM-dd HH:mm",
                "event  <task_name> /at yyyy-MM-dd HH:mm",
                "delete <task_idx>",
                "done <task_idx>",
                "find <keyword>"
        };

        String[] cmdInfo = new String[]{
                "lists all items in the memory",
                "creates a ToDo task with no deadline",
                "creates a Deadline task with a timestamp deadline",
                "creates a Event task taking place at a timestamp",
                "deletes the task found at the given index",
                "sets the specified task's status to done",
                "finds the tasks containing a given keyword"
        };

        for (int i = 0; i < commands.length; i++) {
            String cmd = commands[i];
            String info = cmdInfo[i];
            String desc = String.format("%d. %s\n\t%s\n\n", i + 1, cmd, info);
            builder.append(desc);
        }

        return builder.toString();
    }
}
