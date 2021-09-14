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
     * A method that reads the command from the user and responds with the appropriate actions
     * @param input
     * @return
     */
    public String getResponse(String input) {
        if (botBrain.getIsTerminated()) {
            Platform.exit();
        }
        return botBrain.interact(input);
    }

    /**
     * A method that initiates the bot brain and prompt it to respond with greeting message
     * @return
     */
    public String initiate() {
        return botBrain.startBrain();
    }

    /**
     * A method that starts the bot brain to read data from the hard disk files
     * @return
     */
    public String checkMem() {
        return botBrain.wakeUpMemory();
    }


}
