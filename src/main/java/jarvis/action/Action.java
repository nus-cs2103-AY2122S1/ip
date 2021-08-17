package jarvis.action;

import jarvis.task.TaskList;

public abstract class Action {
    public static Action createAction(String userInput) {
        String trimmedUserInput = userInput.trim();
        ActionTypeEnum actionType = ActionTypeEnum.identifyActionType(trimmedUserInput);
        String userInputWithoutActionPrefix = trimmedUserInput.substring(actionType.getActionTypeStringLength());

        if (actionType == ActionTypeEnum.LIST) {
            return new ListAction();
        } else if (actionType == ActionTypeEnum.TODO) {
            return new TodoAction(userInputWithoutActionPrefix);
        } else if (actionType == ActionTypeEnum.DEADLINE) {
            return new DeadlineAction(userInputWithoutActionPrefix);
        } else if (actionType == ActionTypeEnum.DONE) {
            return new MarkAsDoneAction(userInputWithoutActionPrefix);
        } else if (actionType == ActionTypeEnum.EVENT) {
            return new EventAction(userInputWithoutActionPrefix);
        }

        return new ListAction();

    }

    public abstract void execute(TaskList taskList);
}
