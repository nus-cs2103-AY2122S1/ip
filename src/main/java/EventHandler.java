public class EventHandler extends CommandHandler {
    public EventHandler(TaskList taskList) {
        super(taskList);
    }

    @Override
    String handle(String input) throws BlueException {
        if (input.contains(" /at ")) {
            int indexSpace = input.indexOf(" ");
            int indexAt = input.indexOf(" /at ");
            String title = input.substring(indexSpace + 1, indexAt).strip();
            String at = input.substring(indexAt + 5).strip();
            Event event = new Event(title, at);
            taskList.add(event);
            String response = "Got it. I've added this task:\n" + event + "\n";
            response += "Now you have " + taskList.size() + " tasks in the list.";
            return response;
        } else {
            throw new BlueException("☹ OOPS!!! The time of an event cannot be empty.");
        }
    }
}
