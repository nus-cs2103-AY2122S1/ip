abstract class CommandHandler {
    protected TaskList taskList;

    protected CommandHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * @param input - the input entered by the user
     * @return response
     */
    abstract String handle(String input) throws BlueException;
}
