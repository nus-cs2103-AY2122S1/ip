import java.util.ArrayDeque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Memory {
    /** The array holding the tasks */
    private static final Task[] list = new Task[100];

    /** The index of the most recent task string added */
    private static int index = 0;

    /**
     * Adds a task string to the Memory.
     *
     * @param task the task string
     */
    public static void add(String task) {
        list[index] = new Task(task);
        index++;
        Printer.print("added: " + task);
    }

    /**
     * Appends an index number to the tasks in memory in increasing order, starting from 1.
     *
     * @return a new string deque with the numbered tasks
     */
    private static ArrayDeque<String> indexAppender() {
        return IntStream.range(0, index)
                .mapToObj((pos) -> String.format("%d.%s", pos + 1, list[pos].toString()))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    /** Prints the contents of the memory */
    public static void print() {
        ArrayDeque<String> output = indexAppender();
        output.addFirst("Here are the tasks in your list:");
        Printer.print(output.toArray(String[]::new));
    }
}
