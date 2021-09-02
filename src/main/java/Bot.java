import bot.assembly.BotBrain;

// A class that represents the Bot itself
public class Bot {

    private BotBrain botBrain = new BotBrain();

    /**
     * Constructor
     */
    public Bot() {

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return botBrain.interact(input);
    }
}
