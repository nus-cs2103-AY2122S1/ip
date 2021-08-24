public class DeadlineHandler extends CommandHandler {
    public DeadlineHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    String handle(String input) throws BlueException {
        if (input.contains(" /by ")) {
            int indexSpace = input.indexOf(" ");
            int indexBy = input.indexOf(" /by ");
            String title = input.substring(indexSpace + 1, indexBy);
            String by = input.substring(indexBy + 5);
            Deadline deadline = new Deadline(title, by);
            taskList.add(deadline);
            String response = "Got it. I've added this task:\n" + deadline + "\n";
            response += "Now you have " + taskList.size() + " tasks in the list.";
            return response;
        } else {
            throw new BlueException("☹ OOPS!!! The time of a deadline cannot be empty.");
        }
    }
}
