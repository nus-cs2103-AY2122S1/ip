import bot.assembly.BotBrain;

// A class that represents the Bot itself
public class Bot {

    /**
     * initiate bot's brain to respond to inputs
     * @param args
     */
    public static void main(String[] args) {
        new BotBrain().initiate();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
