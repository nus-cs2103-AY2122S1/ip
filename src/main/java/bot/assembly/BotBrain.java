package bot.assembly;

import bot.assembly.function.*;
import bot.assembly.memory.*;

import java.util.Scanner;

public class BotBrain {

    BotPrinter botPrinter = new BotPrinter();
    BotCommandResponderUnit botCommandReceiverUnit = new BotCommandResponderUnit();
    BotTaskStatusGeneratorUnit botTaskGeneratorUnit = new BotTaskStatusGeneratorUnit();
    BotStaticMemoryUnit botStaticMemoryUnit = new BotStaticMemoryUnit();
    BotDynamicMemoryUnit botDynamicMemoryUnit = BotDynamicMemoryUnit.getInstance();

    private boolean isTerminated = false;

    public BotBrain() {}

    private void reactToCommand(String input) throws Exception {
        CommandInput commandInitial = botCommandReceiverUnit.identifyCommand(input);
        switch (commandInitial) {
            case BYE:
                botPrinter.print(botStaticMemoryUnit.MESSAGE_GOODBYE);
                isTerminated = true;
                return;
            case LIST:
                botTaskGeneratorUnit.reportTaskTracker();
                break;
            case DONE:
                botCommandReceiverUnit.markTaskAsDone(input);
                break;
            case DELETE:
                botCommandReceiverUnit.deleteTaskFromList(input);
                break;
            default:
                botCommandReceiverUnit.addTask(input);
                botTaskGeneratorUnit.generateTaskFeedback();
        }
    }

    private void wakeUpMemory() {
        try {
            botDynamicMemoryUnit.loadFromHardDisk();
            botTaskGeneratorUnit.reportTaskTracker();
        } catch (Exception error) {
            botPrinter.print(botStaticMemoryUnit.ERROR_MESSAGE_PROMPT + error.getMessage());
        }
    }

    /**
     * A method to read the user's input and respond to it
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
     * A method to initiate the bot's brain to interact with the user
     */
    public void initiate() {
        System.out.println("\t" + botStaticMemoryUnit.LOGO.replaceAll("\n", "\n\t"));
        botPrinter.print(botStaticMemoryUnit.MESSAGE_GREETING);
        this.wakeUpMemory();
        this.interact();
    }
}
