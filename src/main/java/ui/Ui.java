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
            Dialog commandsListDialog = Dialog.generate(dialogId);
            commandsListDialog.add("This is the following commands, I can perform:\n");
            commandsListDialog.add("1. 'todo <models.task description>' - add a todo models.task to the list");
            commandsListDialog.add("2. 'deadline <models.task description> /by <by when>' "
                    + "- add a deadline models.task with specific deadline");
            commandsListDialog.add("3. 'event <models.task description> /at <at when>' "
                    + "- add an event models.task with specific time");
            commandsListDialog.add("4. 'date <yyyy-mm-dd>' "
                    + "- list all todos and all the deadlines and events before specified time");
            commandsListDialog.add("5. 'list' - show the current models.task list");
            commandsListDialog.add("6. 'find <keyword>' - list the models.task with specific keywords");
            commandsListDialog.add("7. 'done <models.task index>' - mark that models.task as done");
            commandsListDialog.add("8. 'delete <models.task index>' - delete that models.task from the list");
            commandsListDialog.add("9. 'learn <vocab to learn>' - prompt the user with process to learn a vocab");
            commandsListDialog.add("10.'unlearn <vocab to unlearn>' - unlearn specific vocabulary taught by the user");
            commandsListDialog.add("10.'commands' - show this current command window");
            commandsListDialog.add("11.'bye' - end session and save your models.task list");
            return commandsListDialog.toString();
        }
    }

    public static String getAskForFeedbackText(String phraseToLearn) throws DialogException {
        String dialogId = "learn " + phraseToLearn;
        if (Dialog.containsId(dialogId)) {
            return Dialog.get(dialogId).toString();
        } else {
            Dialog learnPhraseDialog = Dialog.generate(dialogId);
            learnPhraseDialog.add("What should be my feedback to ...");
            learnPhraseDialog.add(phraseToLearn);
            learnPhraseDialog.add("I will recite every words you put down");
            return learnPhraseDialog.toString();
        }
    }

    public static String getUnlearnText(String phraseToUnlearn) throws DialogException {
        String dialogId = "unlearn " + phraseToUnlearn;
        if (Dialog.containsId(dialogId)) {
            return Dialog.get(dialogId).toString();
        } else {
            Dialog learnPhraseDialog = Dialog.generate(dialogId);
            learnPhraseDialog.add("Alice will unlearn the following:");
            learnPhraseDialog.add(phraseToUnlearn);
            learnPhraseDialog.add("Alice won't be able to recognize the phrase anymore ...");

            return learnPhraseDialog.toString();
        }
    }

    /** Print to the user the taskList stored in the Ui in a taskDialog format. */
    public String getCurrentListText() {
        return taskDialog.toString();
    }
}
