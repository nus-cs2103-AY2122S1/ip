package bot.assembly;

import bot.assembly.function.*;
import bot.assembly.memory.*;

import java.util.Scanner;

/**
 * A class that assembles all features of the bot
 */
public class BotBrain {

    BotPrinter botPrinter = new BotPrinter();
    BotCommandResponderUnit botCommandReceiverUnit = new BotCommandResponderUnit();
    BotTaskStatusGeneratorUnit botTaskGeneratorUnit = new BotTaskStatusGeneratorUnit();
    BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    BotDynamicMemoryUnit botDynamicMemoryUnit = BotDynamicMemoryUnit.getInstance();

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

        CommandInput commandInitial = botCommandReceiverUnit.identifyCommand(input);

        switch (commandInitial) {
            case BYE:
                botPrinter.print(botStaticMemoryUnit.MESSAGE_GOODBYE);
                isTerminated = true;
                return;
            case LIST:
                botTaskGeneratorUnit.generateTaskTrackerReport();
                break;
            case DONE:
                botCommandReceiverUnit.markTaskAsDone(input);
                break;
            case DELETE:
                botCommandReceiverUnit.deleteTaskFromList(input);
                break;
            default:
                botCommandReceiverUnit.addTask(input);
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
