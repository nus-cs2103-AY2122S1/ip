package me.yukun99.ip.commands;

import me.yukun99.ip.HelpBot;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;

public class ExitCommand extends Command {
    private final HelpBot helpBot;

    /**
     * Constructor for an ExitCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to add task to.
     * @param ui Ui to send feedback to.
     * @param helpBot HelpBot instance to exit from.
     */
    public ExitCommand(String[] args, TaskList taskList, Ui ui, HelpBot helpBot) {
        super(args, taskList, ui);
        this.helpBot = helpBot;
    }

    @Override
    public void run() {
        helpBot.exit();
    }
}
