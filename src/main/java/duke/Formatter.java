package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.exception.DukeException;

abstract class Formatter {

    private static final String ERROR_INITALIZE_STATIC = "Don't try to initialize this utility class! :(";
    private static final String ERROR_MISSING_DESCRIPTION = "OOPS!!! The description cannot be left empty.";
    private static final String ERROR_MISSING_DATE = "OOPS!!! The date cannot be left empty.";

    private Formatter() throws DukeException {
        throw new DukeException(ERROR_INITALIZE_STATIC);
    }

    /**
     * Returns the proper format for adding task string.
     *
     * @param task The formatted string of a task.
     * @param totalTasks The total number of tasks.
     * @return
     */
    public static List<String> addTaskString(String task, String totalTasks) {
        return List.of(
            "Got it. I've added this task: ",
            task,
            String.format("Now you have %s tasks in the list.", totalTasks)
        );
    }

    /**
     * Returns the proper format for deleting task string.
     *
     * @param task The formatted string of a task.
     * @param totalTasks The total number of tasks.
     * @return
     */
    public static List<String> deleteTaskString(String task, String totalTasks) {
        return List.of(
            "Noted. I've removed this task: ",
            task,
            String.format("Now you have %s tasks in the list.", totalTasks)
        );
    }

    /**
     * Parses the commands for task name from user-input.
     *
     * @return
     */
    public static String getTaskName(String[] commands) throws DukeException {
        int indexOfDateSeparator = IntStream
            .range(0, commands.length)
            .filter(i -> commands[i].equals("/at") || commands[i].equals("/by"))
            .findFirst()
            .orElse(commands.length);
        String[] tasksNames = Arrays.copyOfRange(commands, 1, indexOfDateSeparator);
        if (tasksNames.length == 0) {
            throw new DukeException(ERROR_MISSING_DESCRIPTION);
        }
        return Arrays.stream(tasksNames).collect(Collectors.joining(" "));
    }

    /**
     * Parses the commands for task date from user-input.
     *
     * @return
     */
    public static String getTaskDate(String[] commands) throws DukeException {
        int indexOfDateSeparator = IntStream
            .range(0, commands.length)
            .filter(i -> commands[i].equals("/at") || commands[i].equals("/by"))
            .findFirst()
            .orElseThrow(() -> new DukeException(ERROR_MISSING_DATE));
        String[] taskDate = Arrays.copyOfRange(commands, indexOfDateSeparator + 1, commands.length);
        if (taskDate.length == 0) {
            throw new DukeException(ERROR_MISSING_DATE);
        }
        return Arrays.stream(taskDate).collect(Collectors.joining(" "));
    }

    public static String formatOutput(String inputText) {
        return (
             inputText + "\n"
            );
    }

    /**
     * Formats by surrounding each text of the list of texts with 2 breaklines
     *
     * @param inputText
     * @return
     */
    public static String formatOutput(List<String> inputText) {
        String output = "";
        for (int i = 0; i < inputText.size(); i++) {
            output += inputText.get(i) + "\n";
        }
        return output;
    }

    public static String getResponseString(String inputText) {
        return formatOutput(inputText);
    }

    public static String getResponseString(List<String> inputText) {
        return formatOutput(inputText);
    }

    private static List<String> formatNumberedListOutput(String header, List<? extends Object> taskArray) {
        List<String> outputList = new ArrayList<>();
        outputList.add(header);
        for (int i = 0; i < taskArray.size(); i++) {
            outputList.add(i + 1 + ". " + taskArray.get(i));
        }
        return outputList;
    }

    public static String getNumberedListResponse(String header, List<? extends Object> taskArray) {
        List<String> outputList = formatNumberedListOutput(header, taskArray);
        return getResponseString(outputList);
    }
}
