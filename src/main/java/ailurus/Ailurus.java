package ailurus;

import ailurus.task.Deadline;
import ailurus.task.Event;
import ailurus.task.TaskList;
import ailurus.task.Todo;

/**
 * The main task chatbot class for Ailurus.Ailurus Chatbot
 *
 * @author Leeroy Liu
 */
public class Ailurus {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String command = Parser.parse(fullCommand);
                switch (command) {
                    case "bye":
                        this.storage.unload(this.tasks);
                        this.ui.sayBye();
                        isExit = true;
                        break;
                    case "list":
                        try {
                            this.ui.sayList(this.tasks);
                        } catch (AilurusException e) {
                            this.ui.showError(e.getMessage());
                        }
                        break;
                    case "done":
                        try {
                            String str = Parser.parseMessage(fullCommand);
                            this.ui.sayDone(this.tasks.done(str));
                        } catch (AilurusException e) {
                            this.ui.showError(e.getMessage());
                        }
                        break;
                    case "todo":
                        try {
                            String todoMessage = Parser.parseMessage(fullCommand);
                            this.ui.sayAdd(this.tasks.addTask(new Todo(todoMessage)), this.tasks.length());
                        } catch (AilurusException e) {
                            this.ui.showError(e.getMessage());
                        }
                        break;
                    case "deadline":
                        try {
                            String deadlineMessage = Parser.parseMessage(fullCommand);
                            this.ui.sayAdd(this.tasks.addTask(new Deadline(deadlineMessage)), this.tasks.length());
                        } catch (AilurusException e) {
                            this.ui.showError(e.getMessage());
                        }
                        break;
                    case "event":
                        try {
                            String eventMessage = Parser.parseMessage(fullCommand);
                            this.ui.sayAdd(this.tasks.addTask(new Event(eventMessage)), this.tasks.length());
                        } catch (AilurusException e) {
                            this.ui.showError(e.getMessage());
                        }
                        break;
                    case "delete":
                        try {
                            String str = Parser.parseMessage(fullCommand);
                            this.ui.sayDelete(this.tasks.deleteTask(str), this.tasks.length());
                        } catch (AilurusException e) {
                            this.ui.showError(e.getMessage());
                        }
                        break;
                    case "find":
                        try {
                            String match = Parser.parseMessage(fullCommand);
                            this.ui.sayFind(Parser.parseFind(match, this.tasks));
                        } catch (AilurusException e) {
                            this.ui.showError(e.getMessage());
                        }
                        break;
                    default:
                        this.ui.sayInvalidCommand();
                }
            } catch (AilurusException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Ailurus("./data/", "tasks.txt").run();
    }
}
