package duke.util;

import duke.exception.DukeException;
import duke.task.*;

public class Parser {
    private final TaskList TASKLIST;

    /**
     * Factory method of Parser class. Initializes tasklist.
     *
     * @param taskList TaskList the commands will be working on
     */
    public static Parser initialize(TaskList taskList) {
        return new Parser(taskList);
    }

    /**
     * Constructor for Parser class
     * @param taskList TaskList the commands will be working on
     */
    private Parser(TaskList taskList) {
        this.TASKLIST = taskList;
    }

    /**
     * Parses string command and returns system reply
     *
     * @return System's reply message to user input
     */
    public String parseCommand(String userInput) {
        if (userInput.equals("list")) {
            return String.format(
                    "Here are the tasks in your list:\n%s",
                    TASKLIST.toString()
            );
        } else if (userInput.matches(TaskList.FIND_COMMAND_REGEX)) {
            //eg. find <word>
            String keyword = userInput.split(" ", 2)[1];
            Integer[] indexesFrom0 = TASKLIST.filter((task) -> task.isInTaskSummary(keyword));
            String res = TASKLIST.selectedTasks(indexesFrom0);
            return String.format("Here are the matching tasks in your list:\n%s", res);


            //loop, check if isInTaskSummary
            //store int[] indexes, return TaskList.selectedIndexes


//            if (TaskList.isValidIndex(idxFrom0, TASKLIST.length())) { //valid argument indexes
//                TASKLIST.toggleDone(idxFrom0);
//                return String.format(
//                        "Nice! I've marked this task as done:\n    %s",
//                        TASKLIST.get(idxFrom0).toString()
//                );
        } else if (userInput.matches(TaskList.DONE_COMMAND_REGEX)) {
            //eg. done 12
            //limiting tasks from 0-99
            String inputBody = userInput.split(" ", 2)[1];
            int idxFrom0 = Integer.parseInt(inputBody) - 1;
            if (TaskList.isValidIndex(idxFrom0, TASKLIST.length())) { //valid argument indexes
                TASKLIST.toggleDone(idxFrom0);
                return String.format(
                        "Nice! I've marked this task as done:\n    %s",
                        TASKLIST.get(idxFrom0).toString()
                );
            }
        } else if (userInput.matches(TaskList.DELETE_COMMAND_REGEX)) {
            //eg. delete 3
            String inputBody = userInput.split(" ", 2)[1];
            int idxFrom0 = Integer.parseInt(inputBody) - 1;
            if (TaskList.isValidIndex(idxFrom0, TASKLIST.length())) { //valid argument indexes
                String reply = String.format(
                        "Noted. I've removed this task:\n    %s\nNow you have %d tasks in the list.",
                        TASKLIST.get(idxFrom0).toString(),
                        TASKLIST.length() - 1
                );
                TASKLIST.removeTask(idxFrom0);
                return reply;
            }
        } else if (userInput.matches(ToDo.COMMAND_REGEX)) {
            //eg. todo read book
            String inputBody = userInput.split(" ", 2)[1];
            Task newTask = ToDo.of(inputBody);
            String reply = String.format(
                    "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.toString(),
                    TASKLIST.length() + 1
            );
            TASKLIST.addTask(newTask);
            return reply;
        } else if (userInput.matches(Deadline.COMMAND_REGEX)) {
            //eg. deadline xxx /by dd-MM-uuuu HHmm
            String inputBody = userInput.split(" ", 2)[1];
            String[] deadlineDetails = inputBody.split("\s/by\s", 2);
            String deadlineTask = deadlineDetails[0];
            String deadlineByDate = deadlineDetails[1];

            Task newTask = Deadline.of(deadlineTask, deadlineByDate);
            String reply = String.format(
                    "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.toString(),
                    TASKLIST.length()  + 1
            );
            TASKLIST.addTask(newTask);
            return reply;
        } else if (userInput.matches(Event.COMMAND_REGEX)) {
            //eg. event xxx /by xxx
            String inputBody = userInput.split(" ", 2)[1];
            String[] eventDetails = inputBody.split("\s/at\s", 2);
            String eventTask = eventDetails[0];
            String eventTime = eventDetails[1];

            Task newTask = Event.of(eventTask, eventTime);
            String reply = String.format(
                    "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                    newTask.toString(),
                    TASKLIST.length()  + 1
            );
            TASKLIST.addTask(newTask);
            return reply;
        }
        throw DukeException.of(userInput);
    }
}
