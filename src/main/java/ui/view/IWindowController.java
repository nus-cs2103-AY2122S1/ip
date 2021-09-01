package ui.view;

/**
 * Interface for chatbot windows controller.
 */
public interface IWindowController {
    /**
     * Displays the dialog box for the bot which can be used when the bot wants to announce without user inputs.
     *
     * @param s String to be displayed.
     */
    void addBotDialog(String s);
    
    /**
     * Displays the dialog box for the user.
     *
     * @param s String to be displayed.
     */
    void addUserDialog(String s);
}
