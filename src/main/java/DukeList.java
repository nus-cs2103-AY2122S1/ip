/**
 * Encapsulates a list storing the users input messages.
 * Assumes there will be no more than 100 messages.
 */
public class DukeList {
    private static String[] list = new String[100];
    private static int indexOfNextNewItem = 0;

    /**
     * Adds items to the list that can take up to 100 items.
     *
     * @param item is the item be added to the list
     */
    public static void addItemToList(String item) {
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
            String listItem = String.format("%d. %s\n\t", i + 1, DukeList.list[i]);
            stringBuilderList.append(listItem);
        }

        return stringBuilderList.toString();
    }
}
