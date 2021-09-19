package duke;


/**
 * Contains main() method for Duke chatbot for IP of CS2103 2021.
 *
 * author: Ren Weilin A0216723J
 */
public class Duke{

    private final UI userInterface;
    private final TaskList taskList;

    /**
     * Constructor which initializes the necessary components for Duke to function.
     */
    public Duke() {
        Parser parser = new Parser();
        taskList = new TaskList();
        Storage storage = new Storage(taskList);
        userInterface = new UI(parser, storage, taskList);
    }

    /**
     * Start of Duke chatbot.
     *
     * @param args CMD arguments.
     */
    public static void main(String[] args) {
        Duke dukeInstance = new Duke();
        try {
            dukeInstance.userInterface.start();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            dukeInstance.userInterface.start();
        }
    }

}
