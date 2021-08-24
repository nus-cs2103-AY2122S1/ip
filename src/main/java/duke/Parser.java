package duke;

public class Parser {
    public static Task parseDateTask(String descriptionAndDate, String command) throws DukeException {
        String[] splitDescriptionAndDate;
        Task task = null;

        try {
            if (command.equals("deadline")) {
                splitDescriptionAndDate = descriptionAndDate.split(" /by ");
                task = new Deadline(splitDescriptionAndDate[0].trim(), splitDescriptionAndDate[1].trim());
            } else if (command.equals("event")) {
                splitDescriptionAndDate = descriptionAndDate.split(" /at ");
                task = new Event(splitDescriptionAndDate[0].trim(), splitDescriptionAndDate[1].trim());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Oops!!! Deadlines or events should contain a description, followed by " +
                    "a /by or /at respectively, followed by a date.");
        }

        return task;
    }

}
