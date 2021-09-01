package nyx;

/**
 * Entry point of the Nyx chatbot application.
 */
public class Nyx {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs a nyx.Nyx object to initialize the chatbot with the specified folder and file names.
     * @param folderName Name of the folder where the file is.
     * @param fileName Name of the file to store the tasks.
     */
    public Nyx(String folderName, String fileName) {
        this.storage = new Storage(folderName, fileName);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (NyxException e) {
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) throws NyxException {
        return Parser.parse(input, taskList, storage);
    }
}
