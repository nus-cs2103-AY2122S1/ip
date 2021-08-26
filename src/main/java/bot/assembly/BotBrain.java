package bot.assembly;


import bot.assembly.function.BotPrinter;
import bot.assembly.function.BotCommandResponderUnit;
import bot.assembly.function.BotTaskStatusGeneratorUnit;
import bot.assembly.memory.BotDynamicMemoryUnit;
import bot.assembly.memory.BotStaticMemoryUnit;
import bot.assembly.memory.CommandInput;

import java.util.Scanner;

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
    private void reactToCommand(String input) throws Exception {

        CommandInput commandInitial = botCommandResponderUnit.identifyCommand(input);

        switch (commandInitial) {
            case BYE:
                botPrinter.print(botStaticMemoryUnit.MESSAGE_GOODBYE);
                isTerminated = true;
                return;
            case LIST:
                botTaskGeneratorUnit.generateTaskTrackerReport();
                break;
            case DONE:
                botCommandResponderUnit.markTaskAsDone(input);
                break;
            case DELETE:
                botCommandResponderUnit.deleteTaskFromList(input);
                break;
            case FIND:
                botCommandResponderUnit.findTaskFromList(input);
                break;
            default:
                botCommandResponderUnit.addTask(input);
                botTaskGeneratorUnit.generateAddTaskFeedback();
        }
    }

    /**
     * A method that attempts to initiate the loading process of data from HARD_DISK
     */
    private void wakeUpMemory() {

        try {
            botDynamicMemoryUnit.loadFromHardDisk();
            botTaskGeneratorUnit.generateTaskTrackerReport();
        } catch (Exception error) {
            botPrinter.print(botStaticMemoryUnit.ERROR_MESSAGE_PROMPT + error.getMessage());
        }

    }

    /**
     * A method that initiate reaction of Bot to user
     */
    private void interact() {

        Scanner sc = new Scanner(System.in);

        while (!isTerminated) {
            try {
                String input = sc.nextLine().trim();
                reactToCommand(input);
                botDynamicMemoryUnit.saveToHardDisk();
            }
            catch (Exception error){
                botPrinter.print(botStaticMemoryUnit.ERROR_MESSAGE_PROMPT + error.getMessage());
            }
        }
    }

    /**
     * A method to initiate the bot and print LOGO
     */
    public void initiate() {
        System.out.println("\t" + botStaticMemoryUnit.LOGO.replaceAll("\n", "\n\t"));
        botPrinter.print(botStaticMemoryUnit.MESSAGE_GREETING);
        this.wakeUpMemory();
        this.interact();
    }
}
