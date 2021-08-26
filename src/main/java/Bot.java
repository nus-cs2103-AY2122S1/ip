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

}