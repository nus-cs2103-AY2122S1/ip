import commands.Command;
import exceptions.MorganException;
import javafx.scene.image.Image;
import parser.CommandParser;
import storage.Storage;
import tasks.TaskList;


public class Morgan {
    private static final String EXIT_KEYWORD = "bye";
    private final TaskList taskList;
    private final Storage storage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image morganImage = new Image(this.getClass().getResourceAsStream("/images/Morgan.png"));

    /**
     * Constructor for Morgan.
     *
     */
    public Morgan() {
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    public boolean isExit(String keyword) {
        return keyword.trim().equalsIgnoreCase(EXIT_KEYWORD);
    }

    public void loadData() throws MorganException {
        this.storage.load(this.taskList);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * @param input The input string entered by the user.
     * @return The output upon execution of the command specified by the input.
     */
    public String getResponse(String input) {
        String output;
        Command command;
        CommandParser cp = new CommandParser();
        try {
            command = cp.getCommand(input.trim());
            output = command.execute(taskList, this.storage);
        } catch (MorganException e) {
            output = e.getMessage();
        }
        return output;
    }

    public String getGreeting() {
        String greeting = "Hello, my name is Morgan, and I'm your personal task assistant."
                + " What can I do for you today?";
        return greeting;
    }
}
