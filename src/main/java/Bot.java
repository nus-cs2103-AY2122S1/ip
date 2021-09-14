import bot.assembly.BotBrain;
import javafx.application.Platform;

// A class that represents the Bot itself
public class Bot {

    private final BotBrain botBrain = new BotBrain();

    /**
     * Constructor
     */
    public Bot() {

    }

    /**
     * A method to receive the input from the user and refer it to botBrain to
     * continue processing the meaning for the input message.
     * @param input User Command
     * @return feedbacks corresponding to the specific commands
     */
    public String getResponse(String input) {
        if (botBrain.getIsTerminated()) {
            Platform.exit();
        }
        return botBrain.interact(input);
    }

    /**
     * A method that call for a greeting message to mark the start of the bot
     * @return A greeting message to the user in the dialogue box.
     */
    public String initiate() {
        return botBrain.startBrain();
    }

    /**
     * A method that calls the botBrain to start getting task list data from the
     * hard disk if any.
     * @return A task list summary / a prompt that there is no data yet
     */
    public String checkMem() {
        return botBrain.wakeUpMemory();
    }


}
