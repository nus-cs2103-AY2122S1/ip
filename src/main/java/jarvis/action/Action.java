package jarvis.action;

import jarvis.exception.JarvisException;
import jarvis.task.TaskList;

public abstract class Action {
    public static Action createAction(String userInput) throws JarvisException {
        String trimmedUserInput = userInput.trim();
        ActionTypeEnum actionType = ActionTypeEnum.identifyActionType(trimmedUserInput);
        String userInputWithoutActionPrefix = trimmedUserInput.substring(actionType.getActionTypeStringLength());

        switch (actionType) {
            case DEADLINE:
                return new DeadlineAction(userInputWithoutActionPrefix);
            case DELETE:
                return new DeleteAction(userInputWithoutActionPrefix);
            case DONE:
                return new MarkAsDoneAction(userInputWithoutActionPrefix);
            case EVENT:
                return new EventAction(userInputWithoutActionPrefix);
            case TODO:
                return new TodoAction(userInputWithoutActionPrefix);
            default:
                // When action does not match the other actions above, then it must be list action
                return new ListAction();
        }
    }

    public abstract void execute(TaskList taskList) throws JarvisException;
}
