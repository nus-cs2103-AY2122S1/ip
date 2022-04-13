package chatbot;

import dao.TaskDao;
import logic.CommandArgument;
import logic.ICommandLogicUnit;
import logic.ReminderTask;
import model.Command;
import parser.IParser;
import parser.ParserImpl;
import ui.IUi;

/**
 * Dude Chatbot.
 */
public class Dude implements IChatbot {
    private final IParser commandParser;
    private final TaskDao taskDao;
    private final IUi ui;
    
    /**
     * Constructor of the chatbot.Dude chatter-bot with the corresponding logic.
     *
     * @param commandLogicUnit logic.
     * @param ui Ui to display the interaction.
     */
    public Dude(ICommandLogicUnit commandLogicUnit, TaskDao taskDao, IUi ui) {
        this.ui = ui;
        this.taskDao = taskDao;
        commandParser = new ParserImpl(commandLogicUnit, ui);
        
        initialize();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        String logo = " ____        ____      \n"
                + "|   _ \\ _   _|   _  \\___\n"
                + "|  | |  | | | |  | | / __ \\\n"
                + "|  |_| | |_| |  |_|    __/\n"
                + "|____/ \\__,|____\\____|\n";
        
        ui.printSentence("Hello from\n" + logo + " Sup Dude!");
        
        ReminderTask reminderTask = new ReminderTask(taskDao, ui);
        new Thread(reminderTask).start();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void processResponse(String s) {
        assert s != null;
        CommandArgument argument = commandParser.parseInput(s);
        commandParser.processCommand(argument);
    }
    
    @Override
    public Boolean canProcessResponse(String s) {
        return commandParser.parseInput(s).getCommand() != Command.INVALID;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void shutdown() {
        ui.printSentence("Dude has been shutdown :( \n see you again my dude!");
    }
}
