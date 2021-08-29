package commands;


import java.util.*;

/**
 * A command that edits the taskList based on an index given. Such a command
 * can accept multiple indexes in one go.
 */
public abstract class TaskListIndexCommand extends Command {

    private final String input;

    protected TaskListIndexCommand(String input) {
        this.input = input;
    }

    protected abstract String executeOnTaskList(int... listOfIndex);

    @Override
    public boolean execute() {
        try {
            // Checks if an argument is provided
            String arguments = this.input.split(" ", 2)[1].trim();
            int[] listOfIndexes = this.convertStringArrayToIntArray(this.getIndex(arguments));
            listOfIndexes = this.removeDuplicateIndex(listOfIndexes);
            this.setExecutionMessage(this.executeOnTaskList(listOfIndexes));
            return true;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            this.setExecutionMessage(this.getInvalidArgumentsMessage());
            return false;
        }
    }

    /**
     * Returns a list of indexes that the user has specified. The list of index can contain
     * either 1 or multiple indexes. The function then separates the list and adds each index
     * to an ArrayList.
     *
     * @param listOfIndex The list of index the user has inputted as a string.
     * @return The list of index stored in a String ArrayList.
     */
    protected String[] getIndex(String listOfIndex) {
        return listOfIndex.split(" ");
    }

    protected int[] convertStringArrayToIntArray(String[] listOfIndex) {
        int[] result = new int[listOfIndex.length];
        for (int i = 0; i < listOfIndex.length; i++) {
            result[i] = Integer.parseInt(listOfIndex[i]);
        }
        return result;
    }

    /**
     * Removes any duplicates index found in the list of indexes.
     * @param indexArr The list of indexes.
     * @return The list of indexes with any duplicates removed.
     */
    private int[] removeDuplicateIndex(int[] indexArr) {
        Set<Integer> indexSet = new HashSet<>();
        for (int i : indexArr) {
            indexSet.add(i);
        }
        int[] result = new int[indexSet.size()];
        int i = 0;
        for (int j : indexSet) {
            result[i++] = j;
            System.out.println(j);
        }
        return result;
    }

}
