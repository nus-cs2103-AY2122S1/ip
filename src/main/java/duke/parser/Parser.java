/**
 *
 * This class represents the Parser object that breaks queries
 * into tokens before deciding what to compute.
 *
 * @author Rishabh Anand
 * @version CS2103 AY21/22 Semester 1
 *
 */
package duke.parser;

import java.util.ArrayList;

import duke.BotException;
import duke.datastore.DataStore;
import duke.membuffer.MemoryBuffer;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class Parser {
    private static final String TODO_NO_DESC = "OOPS!!! The description of a todo cannot be empty.";
    private static final String DEADLINE_NO_INFO = "OOPS!!! A deadline must have a description and datetime limit.";
    private static final String UNRECOGNISED = "OOPS!!! I'm sorry, this is an unrecognised command.";
    private static final String EVENT_NO_INFO = "OOPS!!! An event must have a description and datetime duration.";
    private static final String IDX_OUT_OF_BOUNDS = "OOPS!!! You provided an invalid task index. Try again.";

    private final Brain brain;

    public Parser() {
        brain = new Brain();
    }

    /**
     *
     * Analyses the command and decides what function to perform.
     *
     * @param command the user input.
     * @param dataStore the list containing the up-to-date task records.
     * @param memBuff the memory buffer that reads and writes files
     */
    public String execute(String command, DataStore dataStore, MemoryBuffer memBuff) {
        String result = "";
        if (command.equals("JarVIS?")) {
            result = brain.manual();
        } else if (command.equals("list")) {
            result = brain.listItems(dataStore);
        } else if (command.contains("todo")) {
            result = brain.createTodo(command, dataStore);
        } else if (command.contains("deadline")) {
            result = brain.createDeadline(command, dataStore);
        } else if (command.contains("event")) {
            result = brain.createEvent(command, dataStore);
        } else if (command.contains("done")) {
            try {
                result = brain.completeTask(command, dataStore, IDX_OUT_OF_BOUNDS);
            } catch (BotException e) {
                result = e.getMessage();
            }
        } else if (command.equals("bye")) {
            result = brain.shutdownBot(dataStore, memBuff);
        } else if (command.contains("delete")) {
            result = brain.deleteTask(command, dataStore);
        } else if (command.contains("find")) {
            result = brain.find(command, dataStore);
        } else {
            try {
                errorHandle(command);
            } catch (BotException e) {
                result = UNRECOGNISED;
            }
        }

        return result;
    }

    /**
     *
     * Handles all invalid commands given by the user.
     *
     * @param input the user input.
     * @throws BotException throws a bot-specific exception when an error is encountered.
     */
    public void errorHandle(String input) throws BotException {
        input = input.strip(); // remove whitespace
        String[] brokenInput = input.split(" ");

        if (input.equals("todo")) {
            throw new BotException(TODO_NO_DESC);
        } else if (input.equals("deadline") || input.contains("deadline") && !input.contains("/by")) {
            throw new BotException(DEADLINE_NO_INFO);
        } else if (input.equals("event") || input.contains("event") && !input.contains("/at")) {
            throw new BotException(EVENT_NO_INFO);
        }

        ArrayList<String> prunedInput = new ArrayList<>();
        for (String str : brokenInput) {
            if (str.length() > 0) {
                prunedInput.add(str);
            }
        }

        // advanced syntax errors
        if (prunedInput.get(0).equals("deadline")) {
            // deadline with no description
            if (prunedInput.get(1).equals("/by")) { // eg: deadline /by Mon 2PM
                throw new BotException(DEADLINE_NO_INFO);
            }

            // deadline with no date limit
            int byIndex = prunedInput.indexOf("/by");
            if (byIndex == prunedInput.size() - 1) { // eg: deadline write program /by
                throw new BotException(DEADLINE_NO_INFO);
            }
        } else if (prunedInput.get(0).equals("event")) {
            // event with no description
            if (prunedInput.get(1).equals("/at")) { // eg: event /at Mon 2-4PM
                throw new BotException(EVENT_NO_INFO);
            }

            // event with no date
            int byIndex = prunedInput.indexOf("/at");
            if (byIndex == prunedInput.size() - 1) { // eg: event visit mom /at
                throw new BotException(EVENT_NO_INFO);
            }
        }
    }

    /**
     *
     * Parses the commands from the flat file and commits it to working memory.
     *
     * @param store a list of per-line strings from the flat file.
     * @return an array list of tasks that were stored originally.
     */
    public ArrayList<Task> parseFromFile(ArrayList<String> store) {
        ArrayList<Task> dataStore = new ArrayList<>();

        for (String record : store) {
            String[] splitRecord = record.split(";");

            for (int j = 0; j < splitRecord.length; j++) {
                splitRecord[j] = splitRecord[j].strip();
            }

            if (splitRecord[0].equals("T")) {
                ToDo todoTask = new ToDo(splitRecord[2]);
                if (splitRecord[1].equals("1")) {
                    todoTask.setDone();
                }

                // add to task data store
                dataStore.add(todoTask);
            } else if (splitRecord[0].equals("D")) {
                Deadline deadlineTask = new Deadline(splitRecord[2], splitRecord[3]);
                if (splitRecord[1].equals("1")) {
                    deadlineTask.setDone();
                }

                // add to task data store
                dataStore.add(deadlineTask);
            } else if (splitRecord[0].equals("E")) {
                Event eventTask = new Event(splitRecord[2], splitRecord[3]);
                if (splitRecord[1].equals("1")) {
                    eventTask.setDone();
                }

                // add to task data store
                dataStore.add(eventTask);
            }
        }

        return dataStore;
    }
}
