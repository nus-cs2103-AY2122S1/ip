import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Memory {
    /** The array holding the tasks */
    private static final ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a task string to the Memory.
     *
     * @param task the task string
     */
    public static void add(Task task) {
        list.add(task);
        Printer.print(
                "Got it. I've added this task:",
                String.format("\t%s", task.toString()),
                String.format(
                        "Now you have %d %s in the list",
                        Memory.getSize(),
                        Memory.getSize() == 1 ? "task" : "tasks"));
    }

    /**
     * Appends an index number to the tasks in memory in increasing order, starting from 1.
     *
     * @return a new string deque with the numbered tasks
     */
    private static ArrayDeque<String> indexAppender() {
        return IntStream.range(0, getSize())
                .mapToObj((pos) -> String.format("%d.%s", pos + 1, list.get(pos).toString()))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    /** Prints the contents of the memory */
    public static void print() {
        ArrayDeque<String> output = indexAppender();
        output.addFirst("Here are the tasks in your list:");
        Printer.print(output.toArray(String[]::new));
    }

    /**
     * Checks if the string passed in is a valid numeral.
     *
     * @param string the string to be checked
     * @return true if the string is a numeral, false otherwise
     */
    private static boolean isInvalidIndex(String string) {
        Pattern pattern = Pattern.compile("^\\d+$");
        if (string == null) {
            return true;
        }
        return !pattern.matcher(string).matches();
    }

    /**
     * Marks the task in memory as done by string index.
     *
     * @param stringIndex the index of the task as a string
     * @throws TaskOutOfRangeException if the input index exceeds the number of tasks present
     * @throws BadInputFormatException if the input does not specify a valid index
     */
    public static void markTaskAsDoneByIndex(String stringIndex)
            throws BadInputFormatException, TaskOutOfRangeException {
        if (isInvalidIndex(stringIndex)) {
            throw new BadInputFormatException();
        }
        int index = Integer.parseInt(stringIndex);
        if (index > getSize()) {
            throw new TaskOutOfRangeException();
        }
        list.get(Integer.parseInt(stringIndex) - 1).markTaskAsDone();
    }

    /** Returns the number of tasks in the array */
    public static int getSize() {
        return list.size();
    }
}
