public class TaskList {
    /**
     * This is the array that Virushade keeps the tasks in.
     */
    private static final Task[] taskList = new Task[100];

    /**
     * This keeps track of the size of the TaskList.
     */
    private static int listCount = 0;

    public static void add(String addedTaskDescription){
        if (listCount < 100) {
            Task addedTask = new Task(addedTaskDescription);
            taskList[listCount] = addedTask;
            listCount++;
            System.out.println("Added: " + addedTask.getTaskDescription());
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

    public static void completeTask(String str) {
        if (str.length() < 6) {

            // Checks for the corner case of the string being "done" or "done "
            System.out.println("Please enter the index of the task that you want to be marked done");
        } else {

            // Since the string comprises "done" as the first word,
            // we can safely take out the first 5 letters of the string, "done ".
            String maybeIndex = str.substring(5);

            try {

                int index = Integer.parseInt(maybeIndex);
                if (index <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                } else if (index <= listCount) {
                    Task doneTask = taskList[index - 1];
                    doneTask.completeTask();
                } else {
                    System.out.println("Please check that you have entered the correct number!");
                }
            } catch (NumberFormatException e) {

                // Tells the user that he did not enter a number.
                System.out.println("Please enter a number after 'done ' instead.\n" + e);
            }
        }
    }
}
