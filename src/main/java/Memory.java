import java.util.stream.IntStream;

public class Memory {
    /** The array holding the string inputs */
    private static final String[] list = new String[100];

    /** The index of the most recent task string added */
    private static int index = 0;

    /**
     * Adds a task string to the Memory.
     *
     * @param task the task string
     */
    public static void add(String task) {
        list[index] = task;
        index++;
        Printer.print("added: " + task);
    }

    /**
     * Appends an index number to the tasks in memory in increasing order, starting from 1.
     *
     * @return a new string array
     */
    private static String[] indexAppender() {
        return IntStream.range(0, index)
                .mapToObj((pos) -> String.format("%d.%s", pos + 1, list[pos]))
                .toArray(String[]::new);
    }

    /** Prints the contents of the memory */
    public static void print() {
        Printer.print(indexAppender());
    }
}
