public class TaskList {
    /**
     * This is the array that Virushade keeps the tasks in.
     */
    private static final Task[] taskList = new Task[100];

    /**
     * listCount keeps track of the size of the TaskList.
     */
    private static int listCount = 0;

    /**
     * Partitions the string into 2, seperated by the first '/'.
     * @param str The input string
     * @return The pair of strings.
     */
    private static String[] slashPartition(String str) {
        int index = str.indexOf('/');

        String[] pair = new String[2];

        if (index > -1) {
            // Gets rid of a space in the end of the first partition (If there is one).
            if (str.charAt(index - 1) == ' ') {
                pair[0] = str.substring(0, index - 1);
            } else {
                pair[0] = str.substring(0, index);
            }

            pair[1] = str.substring(index + 1);
        } else {
            // If there exists no '/' in the string, set the tail of the pair as an empty string.
            pair[0] = str;
            pair[1] = "";
        }
        return pair;
    }

    /**
     * Adds a Task to taskList.
     * @param addedTaskDescription Name of the added Task.
     */
    public static void add(String addedTaskDescription, String taskType){
        if (listCount < 100) {
            Task addedTask;
            String[] pair = slashPartition(addedTaskDescription);

            switch (taskType) {
                case "TODO":
                    addedTask = new ToDo(addedTaskDescription);
                    break;

                case "DEADLINE":
                    if (pair[1].startsWith("by ")) {
                        addedTask = new Deadline(pair[0], pair[1].substring(3));
                    } else {
                        System.out.println("Please include a deadline after your task name. " +
                                "(e.g. /by Sunday)");
                        return;
                    }
                    break;

                case "EVENT":
                    pair = slashPartition(addedTaskDescription);
                    if (pair[1].startsWith("at ")) {
                        addedTask = new Event(pair[0], pair[1].substring(3));
                    } else {
                        System.out.println("Please include a time after your task name. " +
                                "(e.g. /at 12 noon)");
                        return;
                    }
                    break;

                default:
                    addedTask = new Task(addedTaskDescription);
            }

            taskList[listCount] = addedTask;
            listCount++;
            System.out.println("Added: " + addedTask.getTaskDescription());
            System.out.printf("Now you have %d tasks in the list.\n", listCount);
        } else {
            System.out.println("Sorry, Virushade cannot keep track of more than 100 tasks!!!");
        }
    }

    /**
     * Display the stored values in taskList for the user.
     */
    public static void list() {
        int index = 0;

        if (listCount == 0) {
            System.out.println("Nothing in the list as of now.");
            return;
        }

        System.out.println("Here are the tasks in your list:");
        while (index < listCount) {
            System.out.println((index + 1) + "." + taskList[index].toString());
            index++;
        }
    }

    /**
     * Marks a task as complete.
     * @param str Input string, determines which task to mark as complete.
     */
    public static void completeTask(String str) {

        try {
            // If what comes after "done " is not an integer, this will throw a NumberFormatException.
            int index = Integer.parseInt(str);

            if (index <= 0) {
                System.out.println("Please enter an integer greater than 0.");
            } else if (index <= listCount) {
                Task doneTask = taskList[index - 1];
                doneTask.completeTask();
            } else {
                System.out.println("Please check that you have entered the correct number!");
            }
        } catch (NumberFormatException e) {

            // Tells the user that he did not enter a number.
            System.out.println("Please enter an integer after 'done ' instead.\n" + e);
        }
    }
}
