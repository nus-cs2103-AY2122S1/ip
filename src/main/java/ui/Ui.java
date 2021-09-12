package ui;

import dialog.Dialog;
import dialog.exceptions.DialogException;
import dialog.TaskDialog;

import model.task.TaskList;

/**
 * The Ui class dealing with user interacting with the application and its system.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.02
 * @since 0.01
 */
public class Ui {

    private TaskDialog taskDialog;
    private ChatPage chatPage;

    /**
     * Constructor of Ui.
     * The Ui object should be specified of which TaskList it is supposed to import
     * with the method importTaskList(TaskList taskList) not through the constructor.
     */
    public Ui() {
        taskDialog = null;
        chatPage = null;
    }

    public Ui(TaskDialog taskDialog, ChatPage chatPage) {
        this.taskDialog = taskDialog;
        this.chatPage = chatPage;
    }

    /**
     * Load the taskList to the Ui TaskDialog.
     *
     * @param taskList the taskList to be imported.
     */
    public void setTaskList(TaskList taskList) {
        this.taskDialog = (TaskDialog) TaskDialog.generate("taskList: " + taskList.hashCode(), taskList);
    }

    public void setChatPage(ChatPage chatPage) {
        this.chatPage = chatPage;
    }

    /**
     * Return the current taskDialog of the Ui object.
     * The TaskDialog equal to null if it has not loaded a TaskList yet.
     *
     * @return taskDialog of the current Ui to be pass around to other method or fields.
     */
    public TaskDialog getTaskDialog() {
        return this.taskDialog;
    }

    public ChatPage getChatPage() {
        return this.chatPage;
    }

    /**
     * Static method for printing an error to the Ui.
     *
     * @param exception the exception with its message to be printed to the user.
     */
    public static String getErrorText(Exception exception) throws DialogException {
        if (Dialog.containsId(exception.toString())) {
            return Dialog.get(exception.toString()).toString();
        } else {
            Dialog errorMessage = Dialog.generate(exception.toString());

            errorMessage.add("☹ OOPS!!! " + exception.getMessage());
            return errorMessage.toString();
        }
    }

    /**
     * Static method for printing to the user a welcome message and alice self-introduction.
     *
     * @throws DialogException the dialog cannot have the same id while the app is running.
     */
    public static String getWelcomeText() throws DialogException {
        String dialogId = "greeting";
        if (Dialog.containsId(dialogId)) {
            return Dialog.get(dialogId).toString();
        } else {
            Dialog greeting = Dialog.generate("greeting");
            greeting.add("Hello! I'm alice.Alice, your personal assistant, what can I do for you?");
            return greeting.toString();
        }
    }

    /**
     * Static method for showing to the user the command in which alice can perform.
     *
     * @throws DialogException the dialog cannot have the same id while the app is running.
     */
    public static String getCommandListText() throws DialogException {
        String dialogId = "commands";
        if (Dialog.containsId(dialogId)) {
            return Dialog.get(dialogId).toString();
        } else {
            Dialog commandsList = Dialog.generate(dialogId);
            commandsList.add("This is the following commands, I can perform:\n");
            commandsList.add("1. 'todo <models.task description>' - add a todo models.task to the list");
            commandsList.add("2. 'deadline <models.task description> /by <by when>' "
                    + "- add a deadline models.task with specific deadline");
            commandsList.add("3. 'event <models.task description> /at <at when>' "
                    + "- add an event models.task with specific time");
            commandsList.add("4. 'date <yyyy-mm-dd>' "
                    + "- list all todos and all the deadlines and events before specified time");
            commandsList.add("5. 'list' - show the current models.task list");
            commandsList.add("6. 'find <keyword>' - list the models.task with specific keywords");
            commandsList.add("7. 'done <models.task index>' - mark that models.task as done");
            commandsList.add("8. 'delete <models.task index>' - delete that models.task from the list");
            commandsList.add("9. 'learn <vocab to learn> - prompt the user with process to learn a vocab");
            commandsList.add("10. 'commands' - show this current command window");
            commandsList.add("11.'bye' - end session and save your models.task list");
            return commandsList.toString();
        }
    }

    /** Print to the user the taskList stored in the Ui in a taskDialog format. */
    public String getCurrentList() {
        return taskDialog.toString();
    }
}
