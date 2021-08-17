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

    public static String listTask(Task[] tasks) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.length - 1; i++) {
            if (tasks[i] != null)
                result.append("\t ")
                        .append(i + 1)
                        .append(".")
                        .append(tasks[i].toString())
                        .append("\n");
        }
        return result.toString();
    }
}
