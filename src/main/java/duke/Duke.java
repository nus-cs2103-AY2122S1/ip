package duke;

/**
 * Contains skeleton of duke.Duke chatbot for IP of CS2103 2021
 *
 * @author: Ren Weilin
 * @version: 19 August 2021
 */

public class Duke {
    private final UI userInterface;

    public Duke() {
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(taskList);
        userInterface = new UI(parser, storage, taskList);
    }

    public static void main(String[] args) {
        Duke dukeInstance = new Duke();
        dukeInstance.userInterface.start();
    }
}
