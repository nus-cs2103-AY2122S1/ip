/**
 * Encapsulates a list storing the users input messages.
 * Assumes there will be no more than 100 messages.
 */
public class DukeList {
    private static DukeTask[] list = new DukeTask[100];
    private static int indexOfNextNewItem = 0;

    /**
     * Adds items to the list that can take up to 100 items.
     *
     * @param item is the item be added to the list
     */
    public static void addItemToList(DukeTask item) {
        DukeList.list[DukeList.indexOfNextNewItem] = item;
        DukeList.indexOfNextNewItem += 1;
    }

    /**
     * Formats items in a numbered list form, starting from 1.
     *
     * @return the numbered list
     */
    public static String getList() {
        StringBuilder stringBuilderList = new StringBuilder();

        for (int i = 0; i < DukeList.indexOfNextNewItem; i++) {
            DukeTask task = DukeList.list[i];
            String listItem = String.format("%d. %s\n\t", i + 1, task.toString());
            stringBuilderList.append(listItem);
        }

        return stringBuilderList.toString();
    }

    /**
     * Takes in a task number and returns true if the task number exists in the list,
     * otherwise it returns false.
     *
     * @param taskNumber is the number that the task is listed by, starting from 1
     * @return true if the task number exists in the list, otherwise false
     */
    public static boolean contains(int taskNumber) {
        return taskNumber > 0 && taskNumber <= indexOfNextNewItem;
    }

    /**
     * Retrieves the task by the number it is listed by.
     *
     * @param taskNumber is the number that the task is listed by, starting from 1
     * @return a `DukeTask`
     */
    public static DukeTask getTaskByTaskNumber(int taskNumber) {
        return DukeList.list[taskNumber - 1];
    }

    /**
     * Gets the number of tasks in the list
     *
     * @return the number of tasks in the list
     */
    public static int getNumberOfTasks() {
        return indexOfNextNewItem;
    }
}
