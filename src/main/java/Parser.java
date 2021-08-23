import java.util.ArrayList;

public class Parser {
    private static final String TODO_NO_DESC = "OOPS!!! The description of a todo cannot be empty.";
    private static final String DEADLINE_NO_INFO = "OOPS!!! A deadline must have a description and datetime limit.";
    private static final String UNRECOG = "OOPS!!! I'm sorry, this is an unrecognised command.";
    private static final String EVENT_NO_INFO = "OOPS!!! An event must have a description and datetime duration.";
    private static final String IDX_OUT_OF_BOUNDS = "OOPS!!! You provided an invalid task index. Try again.";

    private Brain brain;

    public Parser() {
        brain = new Brain();
    }

    public void execute(String command, DataStore dataStore, Ui userInt, MemoryBuffer memBuff) {
        if (command.equals("list")) {
            brain.listItems(dataStore, userInt);
        } else if (command.contains("todo")) {
            brain.createTodo(command, dataStore, userInt);
        } else if (command.contains("deadline")) {
            brain.createDeadline(command, dataStore, userInt);
        } else if (command.contains("event")) {
            brain.createEvent(command, dataStore, userInt);
        } else if (command.contains("done")) {
            try {
                brain.completeTask(command, dataStore, userInt, IDX_OUT_OF_BOUNDS);
            } catch (BotException e) {
                userInt.say(e.getMessage());
            }
        } else if (command.equals("bye")) {
            brain.shutdownBot(userInt, dataStore, memBuff);
        } else if (command.contains("delete")) {
            brain.deleteTask(command, dataStore, userInt);
        } else {
            try {
                errorHandle(command);
            } catch (BotException e) {
                userInt.say(UNRECOG);
            }
        }
    }

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

        ArrayList<String> prunedInput = new ArrayList<String>();
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

    public ArrayList<Task> parseFromFile(ArrayList<String> store) {
        ArrayList<Task> dataStore = new ArrayList<Task>();

        for (int i = 0; i < store.size(); i++) {
            String record = store.get(i);
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
