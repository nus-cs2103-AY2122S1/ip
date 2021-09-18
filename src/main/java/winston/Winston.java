package winston;

/**
 * A class that creates your personal assistant Winston
 */
public class Winston {
    private final Parser parser;

    /**
     * Constructor for class Winston
     */
    public Winston() {
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage.readFromFile());
        taskList.setStorage(storage);
        this.parser = new Parser(taskList);
    }

    /**
     * A method that calls commands based on the input string
     * 
     * @param str Input string that is used to call commands. 
     * @param winston the class that manages the other classes.
     * @return The reply of the bot.
     */
    protected String getResponse(String str, Winston winston) {
        Command command = winston.parser.parse(str);
        return command.run();
    }
    

}
