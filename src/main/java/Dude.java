import dao.TaskDao;
import dao.TaskDaoImpl;
import logic.CommandLogicUnitImpl;
import logic.ICommandLogicUnit;
import parser.IParser;
import parser.ParserImpl;
import ui.IUi;
import ui.UiImpl;

import java.util.Scanner;

/**
 * Main driver program.
 */
public class Dude {
    /** Scanner to take input from the console */
    private final Scanner scanner = new Scanner(System.in);
    
    /** Logic Unit to process all the commands */
    private final ICommandLogicUnit commandLogicUnit;
    
    /** UI class responsible for the interaction and display with user */
    private final IUi ui;
    
    private final String LOGO = " ____        ____      \n"
            + "|  _ \\ _   _|  _ \\____\n"
            + "| | | | | | | | |/ __ \\\n"
            + "| |_| | |_| | |_|   __/\n"
            + "|____/ \\__,_|____\\____|\n";
    
    /**
     * Constructor of the Dude chatter-bot with the corresponding logic.
     *
     * @param commandLogicUnit logic.
     * @param ui Ui to display the interaction.
     */
    public Dude(ICommandLogicUnit commandLogicUnit, IUi ui) {
        this.commandLogicUnit = commandLogicUnit;
        this.ui = ui;
    }
    
    /**
     * Main driver method, entry point
     */
    public static void main(String[] args) {
        TaskDao taskDao = new TaskDaoImpl();
        IUi ui = new UiImpl();
        ICommandLogicUnit commandLogicUnit = new CommandLogicUnitImpl(taskDao, ui);
        
        new Dude(commandLogicUnit, ui).run();
    }
    
    /**
     * Runs Dude chatterbot.
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        System.out.println("Hello from\n" + LOGO + "by Simon - CS2103T/2122S1");
        
        starting();
        
        try {
            IParser commandProcessor = new ParserImpl(commandLogicUnit, ui);
            while (true) {
                String input = scanner.nextLine();
                commandProcessor.processInput(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void starting() {
        ui.printSentence(" Hello! I'm Dude\n"
                + " What can I do for you?");
    }
}
