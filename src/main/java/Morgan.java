import javafx.scene.image.Image;
import commands.Command;
import exceptions.MorganException;
import parser.CommandParser;
import storage.Storage;
import tasks.TaskList;


public class Morgan {
    private static final String EXIT_KEYWORD = "bye";
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image morganImage = new Image(this.getClass().getResourceAsStream("/images/Morgan.png"));

    public Morgan() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    public static void main(String[] args) {
        Morgan morgan = new Morgan();
        morgan.run();
    }

    public boolean isExit(String keyword) {
        return keyword.trim().equalsIgnoreCase(EXIT_KEYWORD);
    }

    public void loadData() throws MorganException {
        this.storage.load(this.taskList);
    }

    public String getGreeting() {
        return ui.getGreeting();
    }

    private void run() {
        CommandParser p = new CommandParser();
        String userInput = this.ui.getInput();
        boolean isExitKeyword = userInput.trim().equalsIgnoreCase(EXIT_KEYWORD);
        while (!isExitKeyword) {
            try {
                Command command = p.getCommand(userInput);
                String output = command.execute(this.taskList, this.storage);
                this.ui.print(output);
            } catch (MorganException e) {
                this.ui.print(e.getMessage());
            }
            userInput = this.ui.getInput();
            isExitKeyword = userInput.trim().equalsIgnoreCase(EXIT_KEYWORD);
        }
        this.ui.printEndDisplay();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output;
        Command command;
        CommandParser cp = new CommandParser();
        try {
            command = cp.getCommand(input.trim());
            output = command.execute(taskList, this.storage);
        } catch(MorganException e) {
            output = e.getMessage();
        }
        return output;
    }


}
