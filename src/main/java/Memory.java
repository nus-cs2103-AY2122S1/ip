public class Memory {
    /**
     * This is the array that Duke keeps the list of tasks in.
     */
    private static final String[] taskList = new String[100];

    private static int listCount = 0;

    public static void add(String task){
        if (listCount < 100) {
            taskList[listCount] = task;
            listCount++;
            System.out.println("Added: " + task);
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
        }

        while (index < listCount) {
            System.out.println((index + 1) + ". " + taskList[index]);
            index++;
        }
    }
}
