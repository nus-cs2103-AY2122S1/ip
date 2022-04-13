package bot.assembly;

import bot.assembly.function.BotCommandResponderUnit;
import bot.assembly.function.BotPrinter;
import bot.assembly.function.BotTaskStatusGeneratorUnit;
import bot.assembly.memory.BotDynamicMemoryUnit;
import bot.assembly.memory.BotStaticMemoryUnit;
import bot.assembly.memory.CommandInput;

/**
 * A class that assembles all features of the bot
 */
public class BotBrain {

    private BotPrinter botPrinter = new BotPrinter();
    private BotCommandResponderUnit botCommandResponderUnit = new BotCommandResponderUnit();
    private BotTaskStatusGeneratorUnit botTaskGeneratorUnit = new BotTaskStatusGeneratorUnit();
    private BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    private BotDynamicMemoryUnit botDynamicMemoryUnit = BotDynamicMemoryUnit.getInstance();

    private boolean isTerminated = false;

    /**
     * Constructor
     */
    public BotBrain() {}

    /**
     * A method that receives the command input and differentiate the type of the command
     * using the Enum class CommandInput. Then proceed to call the respective actions using
     * various units from different packages
     * @param input
     * @throws Exception
     */
    private String reactToCommand(String input) throws Exception {

        CommandInput commandInitial = botCommandResponderUnit.identifyCommand(input);

        switch (commandInitial) {
        case BYE:
            isTerminated = true;
            return botStaticMemoryUnit.MESSAGE_GOODBYE;
        case LIST:
            return botTaskGeneratorUnit.generateTaskTrackerReport();
        case DONE:
            return botCommandResponderUnit.markTaskAsDone(input);
        case DELETE:
            return botCommandResponderUnit.deleteTaskFromList(input);
        case FIND:
            return botCommandResponderUnit.findTaskFromList(input);
        case MASSOPS:
            botCommandResponderUnit.massOps(input);
            return botTaskGeneratorUnit.generateTaskTrackerReport();
        default:
            botCommandResponderUnit.addTask(input);
            return botTaskGeneratorUnit.generateAddTaskFeedback();
        }
    }

    /**
     * A method that attempts to initiate the loading process of data from HARD_DISK
     */
    public String wakeUpMemory() {

        try {
            botDynamicMemoryUnit.loadFromHardDisk();
            return botTaskGeneratorUnit.generateTaskTrackerReport();
        } catch (Exception error) {
            return botStaticMemoryUnit.ERROR_MESSAGE_PROMPT + error.getMessage();
        }

    }

    /**
     * A method that initiate reaction of Bot to user
     */
    public String interact(String input) {

        input.trim();

        while (!isTerminated) {
            try {
                String output = reactToCommand(input);
                botDynamicMemoryUnit.saveToHardDisk();
                return output;
            } catch (Exception error) {
                return botStaticMemoryUnit.ERROR_MESSAGE_PROMPT + error.getMessage();
            }
        }

        // If isTerminated, the application should already end.
        assert !isTerminated : "The application should not reach this step";
        return "BUG";
    }

    /**
     * A method that helps to print the greeting message
     * @return
     */
    public String startBrain() {
        String output = botStaticMemoryUnit.MESSAGE_GREETING;
        return output;
    }

    /**
     * A method that gets the status of termination
     * @return
     */
    public boolean getIsTerminated() {
        return this.isTerminated;
    }
}
