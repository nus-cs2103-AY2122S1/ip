package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles the parsing of user commands.
 */
public class Parser {

    /**
     * Returns the command in user input.
     *
     * @param input String from user.
     * @return String command.
     */
    public static String getCommandFromInput(String input) {
        String[] commandAndDesc = splitInput(input);
        return commandAndDesc[0];
    }

    /**
     * Returns the description in user input.
     *
     * @param input String from user.
     * @return String description.
     */
    public static String getDescriptionFromInput(String input) {
        String[] commandAndDesc = splitInput(input);
        boolean containsDescription = commandAndDesc.length == 2;
        return containsDescription ? commandAndDesc[1] : "";
    }

    /**
     * Splits the input into its command and description.
     *
     * @param input String from user.
     * @return String[] containing parts of the input.
     */
    public static String[] splitInput(String input) {
        return input.split(" ", 2);
    }


    /**
     * Parses the description for the task number.
     *
     * @param description String containing the task number.
     * @return int task number.
     */
    public static int getTaskNum(String description) {
        try {
            return Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException("I'm Sorry, WHAT?!?!\n");
        }
    }

    /**
     * Parses the task string and returns the corresponding task.
     *
     * @param line String for a task retrieved from storage.
     * @return Task that is parsed.
     */
    public static Task generateTaskFromLine(String line) {
        //task storage string formatted as taskType | isComplete | description | time (optional)
        String[] fragments = line.split(" \\| ");

        String taskType = fragments[0];
        boolean isComplete = Boolean.parseBoolean(fragments[1]);
        String description = fragments[2];
        String tagsString = fragments[3];
        List<String> tagList = generateTagList(tagsString);
        String time = fragments[4];
        return generateTaskFromDetails(taskType, isComplete, description, time, tagList);
    }

    /**
     * Generates a tag list from a given String.
     *
     * @param tagsString String representation of tags.
     * @return List of tags.
     */
    private static List<String> generateTagList(String tagsString) {
        List<String> tagList;
        if (tagsString.contains(" #")) {
            tagList = new ArrayList<>(Arrays.asList(tagsString.split(" #")));
        } else {
            tagList = new ArrayList<>();
        }

        //remove empty tags
        tagList = tagList.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());

        return tagList;
    }


    /**
     * Generate a task from the given parameters.
     *
     * @param taskType        String type of task.
     * @param isCompletedTask boolean true if task is completed, else false.
     * @param taskDescription String description of the task.
     * @param time            String time associated with the task.
     * @param tags            list of Tags for the task.
     * @return Task generated.
     */
    private static Task generateTaskFromDetails(String taskType, boolean isCompletedTask, String taskDescription,
                                                String time,
                                                List<String> tags) {
        Task foundTask;

        //@formatter:off
        switch (taskType) {
        case "T":
            foundTask = new ToDo(taskDescription, isCompletedTask, tags);
            break;
        case "D":
            foundTask = new Deadline(taskDescription, time, isCompletedTask, tags);
            break;
        case "E":
            foundTask = new Event(taskDescription, time, isCompletedTask, tags);
            break;
        default:
            throw new DukeException("OOPS!!! I can't find your tasks.\n");
        }
        //@formatter:on

        return foundTask;
    }
}
