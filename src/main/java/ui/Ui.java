package ui;

import dialog.Dialog;
import dialog.TaskDialog;
import dialog.exceptions.DialogException;
import model.task.TaskList;

/**
 * Act as the bottleneck between TaskDialog and ChatPage.
 * Allow Alice to have access to her Dialog and where to output that Dialog.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.02
 */
public class Ui {

    /** responsible for outputting result */
    private TaskDialog taskDialog;
    /** layout for showing the result */
    private ChatPage chatPage;

    /**
     * Constructor of Ui.
     * Require to manually set taskDialog and chatPage through importing task list and
     * setting chat page.
     */
    public Ui() {
        taskDialog = null;
        chatPage = null;
    }

    /**
     * Constructor of Ui.
     * Does not require to manually set taskDialog or ChatPage.
     *
     * @param taskDialog taskDialog for Ui to deal with dialog which has TaskList.
     * @param chatPage   chat page for outputting the dialog.
     */
    public Ui(TaskDialog taskDialog, ChatPage chatPage) {
        this.taskDialog = taskDialog;
        this.chatPage = chatPage;
    }

    /**
     * Load the taskList to the Ui TaskDialog.
     *
     * @param taskList the taskList to be imported.
     */
    public void importTaskList(TaskList taskList) {
        this.taskDialog = (TaskDialog) TaskDialog.generate("taskList: " + taskList.hashCode(), taskList);
    }

    /**
     * Setter for chat page.
     *
     * @param chatPage chat page for outputting the dialog.
     */
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

    /**
     * Getter for chat page.
     *
     * @return current chat page in which the ui is outputting the result.
     */
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
            greeting.add("Hello! I'm Alice, your personal assistant, what can I do for you?");
            return greeting.toString();
        }
    }

    /**
     * Static method returning a String of user commands in which alice can perform.
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
            commandsListDialog.add("1. 'todo <task description>' - add a todo task to the list");
            commandsListDialog.add("2. 'deadline <task description> /by <by when>' "
                + "- add a deadline task with specific deadline");
            commandsListDialog.add("3. 'event <task description> /at <at when>' "
                + "- add an event task with specific time");
            commandsListDialog.add("4. 'date <yyyy-mm-dd>' "
                + "- list all todos and all the deadlines and events before specified time");
            commandsListDialog.add("5. 'list' - show the current task list");
            commandsListDialog.add("6. 'find <keyword>' - list the task with specific keywords");
            commandsListDialog.add("7. 'done <task index>' - mark that task as done");
            commandsListDialog.add("8. 'delete <task index>' - delete that task from the list");
            commandsListDialog.add("9. 'learn <vocab to learn>' - prompt the user with process to learn a vocab");
            commandsListDialog.add("10.'unlearn <vocab to unlearn>' - unlearn specific vocabulary taught by the user");
            commandsListDialog.add("11.'commands' - show this current command window");
            commandsListDialog.add("12.'bye' - end session and save your task list");
            return commandsListDialog.toString();
        }
    }

    /**
     * Get text for asking the user to feed in the feedback for Alice to learn new vocabulary.
     *
     * @param phraseToLearn phrase for alice to recognize.
     * @return the dialog for Alice to ask user for feedback with specific phrase.
     * @throws DialogException the dialog cannot have the same id while the app is running.
     */
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

    /**
     * Get text for informing the user that Alice would not get the input phrase anymore.
     *
     * @param phraseToUnlearn phrase for alice to recognize.
     * @return the dialog for Alice to inform the user of the phrase that she will forget.
     * @throws DialogException the dialog cannot have the same id while the app is running.
     */
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
