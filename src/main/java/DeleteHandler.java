public class DeleteHandler extends CommandHandler {
    protected DeleteHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    String handle(String input) throws BlueException {
        String[] arguments = Parser.getArguments(input);
        if (arguments.length > 0) {
            try {
                int index = Integer.parseInt(arguments[0]);
                if (0 <= index && index < taskList.size()) {
                    Task task = taskList.remove(index);
                    String response = "Noted. I've removed this task:\n" + task + "\n";
                    response += "Now you have " + taskList.size() + " tasks in the list.";
                    return response;
                } else {
                    throw new BlueException("☹ OOPS!!! No task found at index " + index + ".");
                }
            } catch (NumberFormatException e) {
                throw new BlueException("☹ OOPS!!! Index must be a number.");
            }
        } else {
            throw new BlueException("☹ OOPS!!! The index of delete cannot be empty.");
        }
    }
}
