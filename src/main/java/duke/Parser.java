package duke;

public class Parser {
    /**
     * Static method that attempts to parse user input to form a new task.
     * If unsuccessful, it returns a null value, meaning the command does not
     * involve creating a new task, and is handled by the caller.
     *
     * @param input the input entered by the user
     * @return The task if applicable, or null if the command does not involve
     * creating a task.
     */
    public static Task parseInput(String input) {
        if (input.startsWith("todo")) {
            String substr = input.replaceFirst("todo", "").stripLeading();
            return new ToDo(substr);

        } else if (input.startsWith("deadline")) {
            String substr = input.replaceFirst("deadline", "").stripLeading();
            String[] substrArray = substr.split(" /by ", 2);

            return new Deadline(substrArray[0], substrArray[1]);
        } else if (input.startsWith("event")) {
            String substr = input.replaceFirst("event", "").stripLeading();
            String[] substrArray = substr.split(" /at ", 2);

            return new Event(substrArray[0], substrArray[1]);
        } else {
            return null;
        }
    }

    public static Task parseFileLine(String fileLine) {
        Task newTask;
        if (fileLine.startsWith("[T]", 3)) {
            newTask = new ToDo(fileLine.substring(10));
        } else if (fileLine.startsWith("[D]", 3)) {
            String[] subStringArray = fileLine.substring(10).split(" \\(by: ", 2);
            String deadLineStr = subStringArray[1];
            newTask = new Deadline(subStringArray[0], deadLineStr.substring(0, deadLineStr.length() - 1));
        } else {
            String[] subStringArray = fileLine.substring(10).split(" \\(at: ", 2);
            String deadLineStr = subStringArray[1];
            newTask = new Event(subStringArray[0], deadLineStr.substring(0, deadLineStr.length() - 1));
        }

        if (fileLine.startsWith("[x]", 6)) {
            newTask.markDone();
        }

        return newTask;
    }

}
