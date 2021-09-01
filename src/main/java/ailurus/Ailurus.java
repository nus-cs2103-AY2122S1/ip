package ailurus;

import ailurus.task.Deadline;
import ailurus.task.Event;
import ailurus.task.TaskList;
import ailurus.task.Todo;

/**
 * The main task chatbot class for Ailurus Chatbot
 *
 * @author Leeroy Liu
 */
public class Ailurus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Ailurus Chatbot
     *
     * @param directory directory of the file to store data
     * @param filename filename of the file to store data (with .txt)
     */
    public Ailurus(String directory, String filename) {
        this.ui = new Ui();
        this.storage = new Storage(directory, filename);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (AilurusException e) {
            this.ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Run the chatbot program with the correct environment setups.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                String command = Parser.parse(fullCommand);
                switch (command) {
                case "bye":
                    this.storage.unload(this.tasks);
                    this.ui.sayBye();
                    isExit = true;
                    break;
                case "list":
                    this.ui.sayList(this.tasks);
                    break;
                case "done":
                    String str = Parser.parseMessage(fullCommand);
                    this.ui.sayDone(this.tasks.done(str));
                    break;
                case "todo":
                    String todoMessage = Parser.parseMessage(fullCommand);
                    this.ui.sayAdd(this.tasks.addTask(new Todo(todoMessage)), this.tasks.length());
                    break;
                case "deadline":
                    String deadlineMessage = Parser.parseMessage(fullCommand);
                    this.ui.sayAdd(this.tasks.addTask(new Deadline(deadlineMessage)), this.tasks.length());
                    break;
                case "event":
                    String eventMessage = Parser.parseMessage(fullCommand);
                    this.ui.sayAdd(this.tasks.addTask(new Event(eventMessage)), this.tasks.length());
                    break;
                case "delete":
                    String deleteMessage = Parser.parseMessage(fullCommand);
                    this.ui.sayDelete(this.tasks.deleteTask(deleteMessage), this.tasks.length());
                    break;
                case "find":
                    String match = Parser.parseMessage(fullCommand);
                    this.ui.sayFind(Parser.parseFind(match, this.tasks));
                    break;
                default:
                    this.ui.sayInvalidCommand();
                }
            } catch (AilurusException e) {
                this.ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Ailurus("./data/", "tasks.txt").run();
    }
}
