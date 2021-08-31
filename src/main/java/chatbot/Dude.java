package chatbot;

import logic.ICommandLogicUnit;
import parser.IParser;
import parser.ParserImpl;
import ui.IUi;

/**
 * Dude Chatbot.
 */
public class Dude implements IChatbot {
    private final IParser commandParser;
    
    private final IUi ui;
    
    /**
     * Constructor of the chatbot.Dude chatter-bot with the corresponding logic.
     *
     * @param commandLogicUnit logic.
     * @param ui Ui to display the interaction.
     */
    public Dude(ICommandLogicUnit commandLogicUnit, IUi ui) {
        this.ui = ui;
        commandParser = new ParserImpl(commandLogicUnit, ui);
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
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void processResponse(String s) {
        commandParser.processInput(s);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void shutdown() {
        ui.printSentence("Dude has been shutdown :( \n see you again my dude!");
    }
}
