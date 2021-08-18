import java.util.ArrayList;

public class Printer {
    protected static final String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n\n";

    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    /**
     * Print the given content in a nicely written format.
     *
     * @param content The string that is intended to be printed out.
     */
    public static void prettyPrint(String content) {
        System.out.printf("\t%s\n\t%s\n\t%s\n%n", divider, content, divider);
    }

    /**
     * Return a string of task list arranged nicely in bullet points form.
     *
     * @param tasks The list consists of all the added tasks.
     * @param numOfTask The latest number of task.
     * @return A string of task list arranged nicely in bullet points form.
     */
    public static String listTask(ArrayList<Task> tasks, int numOfTask) {
        StringBuilder result = new StringBuilder();
        int currentIndex = 1;
        for (Task task : tasks) {
            if (task != null)
                result.append("\t ")
                        .append(currentIndex)
                        .append(".")
                        .append(task.toString())
                        .append("\n");
            currentIndex ++;
        }
        return result.toString();
    }
}
