package tiger.exceptions.actions;

public class TigerIndexOutOfBoundsException extends TigerActionExecutionException {

    /**
     * Throws an exception if the index specified is out of bounds, usually when accessing elements of the {@code
     * TaskList}.
     *
     * @param index Index of the element accessed.
     * @param taskListSize Size of the {@code TaskList}.
     */
    public TigerIndexOutOfBoundsException(int index, int taskListSize) {
        super(String.format("Index %d is out of bounds of your task list size of %d.\nPlease make sure your index is "
                + "at least 1.\nUse the list command to see task indices.", index + 1, taskListSize));
    }
}
