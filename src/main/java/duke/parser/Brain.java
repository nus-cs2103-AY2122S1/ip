/**
 * This class represents the main logic unit of the
 * JARVIS chatbot that performs the actual functions.
 *
 * @author Rishabh Anand
 * @versionCS2103 AY21/22 Semester 1
 */

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
import duke.ui.Ui;

import java.io.IOException;

public class Brain {
    public Brain() {}

    /**
     *
     * Lists out the items in memory when requested by the user.
     *
     * @param dataStore the list containing the up-to-date task records.
     * @param userInt the user interface object for logging.
     * @return the final result for the given query.
     */
    public String listItems(DataStore dataStore, Ui userInt) {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are your tasks: \n");
        for (int i = 0; i < dataStore.length(); i++) {
            Task task = dataStore.get(i);

            builder.append(i+1 + ". " + task.toString() + "\n");
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
        LocalDateTime datetime = LocalDateTime.parse(date, formatter);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        String finalTime = datetime.format(newFormatter);
        System.out.println(finalTime);
        
        return finalTime;
    }

    /**
     *
     * Creates a new ToDo task based on the description given.
     *
     * @param input the user's input with the description.
     * @param dataStore the list containing the up-to-date task records.
     * @param userInt the user interface object for logging.
     * @return the final result for the given query.
     */
    public String createTodo(String input, DataStore dataStore, Ui userInt) {
        String query = input.split(" ", 2)[1].strip();
        ToDo todo = new ToDo(query);
        dataStore.add(todo);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task: \n"
                        + "\t" + todo.toString() + "\n"
                        + "Now you have " + dataStore.length() + " tasks in the list.\n"
        );

        return builder.toString();
    }

    /**
     *
     * Creates a Deadline task given a description and deadline.
     *
     * @param input the user's input with the description and deadline.
     * @param dataStore the list containing the up-to-date task records.
     * @param userInt the user interface object for logging.
     * @return the final result for the given query.
     */
    public String createDeadline(String input, DataStore dataStore, Ui userInt) {
        input = input.split(" ", 2)[1].strip();
        String query = input.split("/by")[0];
        String limit = input.split("/by")[1];
        String procLimit = processDate(limit);

        Deadline deadlineTask = new Deadline(query, procLimit);
        dataStore.add(deadlineTask);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task: \n"
                + "\t" + deadlineTask.toString() + "\n"
                + "Now you have " + dataStore.length() + " tasks in the list.\n"
        );

        return builder.toString();
    }

    /**
     *
     * Creates an Event task given a description and timing.
     *
     * @param input the user's input with the description and timing.
     * @param dataStore the list containing the up-to-date task records.
     * @param userInt the user interface object for logging.
     * @return the final result for the given query.
     */
    public String createEvent(String input, DataStore dataStore, Ui userInt) {
        input = input.split(" ", 2)[1].strip();
        String query = input.split("/at")[0];
        String datetime = input.split("/at")[1];
        String procDate = processDate(datetime);

        Event eventTask = new Event(query, procDate);
        dataStore.add(eventTask);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task: \n"
                + "\t" + eventTask.toString() + "\n"
                + "Now you have " + dataStore.length() + " tasks in the list.\n"
        );

        return builder.toString();
    }

    /**
     *
     * Sets a given task to completed status.
     *
     * @param input the user's input with the ID of the task in the list.
     * @param dataStore the list containing the up-to-date task records.
     * @param userInt the user interface object for logging.
     * @param warning the exact warning to be thrown when valid index range is exceeded.
     * @return the final result for the given query.
     * @throws BotException
     */
    public String completeTask(String input, DataStore dataStore, Ui userInt, String warning) throws BotException {
        int idx = Integer.parseInt(input.split(" ")[1]);

        StringBuilder builder = new StringBuilder();

        // check if index is within appropriate range
        if (idx < 0 || idx > dataStore.length()) {
            throw new BotException(warning);
        } else {
            Task task = dataStore.get(idx-1);
            task.setDone();
            userInt.say("Nice! I've marked this task as done: ");
            userInt.say("\t" + task.toString());

            builder.append("Nice! I've marked this task as done: \n"
                    + "\t" + task.toString() + "\n"
            );
        }

        return builder.toString();
    }

    /**
     *
     * Shuts down the bot and stops the program from running.
     *
     * @param userInt the user interface object for logging.
     * @param dataStore the list containing the up-to-date task records.
     * @param memBuff the memory buffer for reading and writing files.
     * @return the final result for the given query.
     */
    public String shutdownBot(Ui userInt, DataStore dataStore, MemoryBuffer memBuff) {
        // save current task log
        StringBuilder builder = new StringBuilder();

        try {
            memBuff.writeFile(dataStore);
        } catch (IOException e) {
            builder.append("OOPS!!! There was a problem saving your tasks. \n");
        }

        builder.append("Bye. Hope to see you again soon!\n");
        System.exit(0);

        return builder.toString();
    }

    /**
     *
     * Deletes the current task from working memory.
     *
     * @param input the user's input with the task ID to be deleted.
     * @param dataStore the list containing the up-to-date task records.
     * @param userInt the user interface object for logging.
     * @return the final result for the given query.
     */
    public String deleteTask(String input, DataStore dataStore, Ui userInt) {
        int idx = Integer.parseInt(input.split(" ")[1]);
        Task task = dataStore.get(idx-1);

        StringBuilder builder = new StringBuilder();

        builder.append("Noted. I've removed this task: \n"
                        + "\t" + task.toString() + "\n"
        );
        dataStore.remove(idx-1); // actual deletion
        builder.append("Now you have " + (dataStore.length()) + " tasks in the list.\n");

        return builder.toString();
    }

    /**
     * Returns the closest matching results from list of tasks.
     * @param input the user query to be found.
     * @param dataStore the list containing the up-to-date task records.
     * @param userInt the user interface object for logging.
     * @return the final result for the given query.
     */
    public String find(String input, DataStore dataStore, Ui userInt) {
        String query = input.split(" ")[1];

        StringBuilder builder = new StringBuilder();

        builder.append("Here are the matching tasks in your list: \n");
        for (int i = 0; i < dataStore.length(); i++) {
            Task record = dataStore.get(i);
            String message = record.toString();

            if (record.getDescription().contains(query)) {
                builder.append((i+1) + ". " + message + "\n");
            }
        }

        return builder.toString();
    }
}
