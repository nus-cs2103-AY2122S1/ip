import java.util.ArrayList;

public class Printer {
    protected static final String logo = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n\n";

    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public static void prettyPrint(String content) {
        System.out.printf("\t%s\n\t%s\n\t%s\n%n", divider, content, divider);
    }

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
