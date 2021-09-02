package bot.assembly;

//import java.util.Scanner;

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
            //botPrinter.print(botStaticMemoryUnit.MESSAGE_GOODBYE);
            return botStaticMemoryUnit.MESSAGE_GOODBYE;
        case LIST:
            //botTaskGeneratorUnit.generateTaskTrackerReport();
            return botTaskGeneratorUnit.generateTaskTrackerReport();
        case DONE:
            //botCommandResponderUnit.markTaskAsDone(input);
            return botCommandResponderUnit.markTaskAsDone(input);
        case DELETE:
            //botCommandResponderUnit.deleteTaskFromList(input);
            return botCommandResponderUnit.deleteTaskFromList(input);
        case FIND:
            //botCommandResponderUnit.findTaskFromList(input);
            return botCommandResponderUnit.findTaskFromList(input);
        default:
            botCommandResponderUnit.addTask(input);
            // botTaskGeneratorUnit.generateAddTaskFeedback();
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
        //Scanner sc = new Scanner(System.in);

        while (!isTerminated) {
            try {
                //String input = sc.nextLine().trim();
                String output = reactToCommand(input);
                botDynamicMemoryUnit.saveToHardDisk();
                return output;
            } catch (Exception error) {
                //botPrinter.print(botStaticMemoryUnit.ERROR_MESSAGE_PROMPT + error.getMessage());
                return botStaticMemoryUnit.ERROR_MESSAGE_PROMPT + error.getMessage();
            }
        }

        return "BUG";
    }
    /*
    public void initiate() {
        System.out.println("\t" + botStaticMemoryUnit.LOGO.replaceAll("\n", "\n\t"));
        botPrinter.print(botStaticMemoryUnit.MESSAGE_GREETING);
        this.wakeUpMemory();
        this.interact();
    }
    */

    /**
     *
     * @return
     */
    public String startMem() {
        String output = botStaticMemoryUnit.MESSAGE_GREETING;
        return output;
    }
}
