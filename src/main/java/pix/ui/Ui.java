package pix.ui;

import java.util.Scanner;

import pix.command.Command;

/**
 * Class that manages all messages displayed on the UI.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor of the Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message for the user.
     *
     * @return Returns the welcome message of Pix.
     */
    public String displayWelcome() {
        return "This is Pix. Why did you summon me AGAIN...\nWhat do want now?";
    }

    /**
     * Shows a loading error if the file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("I can't load your file, it's as bad as you!");
    }

    /**
     * Displays a message that there is no command to undo.
     *
     * @return Returns the message that there is no last command to undo.
     */
    public String showNoLastCommandMessage() {
        return "You haven't done any command to change any data!";
    }

    /**
     * Displays undo message when undoing the previous command
     *
     * @return Returns the message that the last command was undone.
     */
    public String showUndoMessage() {
        return "I undid your last change. Maybe stop making mistakes?";
    }
}
