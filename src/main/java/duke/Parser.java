package duke;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Deals with making sense of the user commands
 */
public class Parser {
    /**
     * Identifies and returns the command word from the user's input so that the corresponding methods
     * can be called
     *
     * @param command user's input
     * @return the extracted command word from the user's input
     */
    public static String parseCommand(String command) {
        String keyWord = "";
        if (command.equals("list")) {
            keyWord = "list";
        } else if (command.equals("notes")) {
            keyWord = "notes";
        } else if (command.startsWith("done")) {
            keyWord = "done";
        } else if (command.startsWith("delete note")) {
            keyWord = "delete note";
        } else if (command.startsWith("delete")) {
            keyWord = "delete";
        } else if (command.startsWith("todo")) {
            keyWord = "todo";
        } else if (command.startsWith("deadline")) {
            keyWord = "deadline";
        } else if (command.startsWith("event")) {
            keyWord = "event";
        } else if (command.startsWith("today")) {
            keyWord = "today";
        } else if (command.startsWith("find")) {
            keyWord = "find";
        } else if (command.startsWith("bye")) {
            keyWord = "bye";
        } else if (command.startsWith("note")) {
            keyWord = "note";
        }
        return keyWord;
    }

    /**
     * Identifies the task that is completed and passes it to markASDoneAndUpdate() method
     *
     * @param instruction User's input that followed the "done" command word
     * @throws DukeException if there is no task that has the index keyed in by the user
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseDone(String instruction) throws DukeException, IOException {
        String result = "";
        int taskNum = Integer.parseInt(instruction.substring(5)) - 1;
        if (taskNum >= TaskList.getCounter()) {
            throw new DukeException("Hmm, I don't have task " + (taskNum + 1) +
                    " in my list. Please key in 'list' if you'd like to " +
                    "view your list of tasks again!");
        } else {
            TaskList.getTaskList().get(taskNum).markAsDoneAndUpdate();
            result += "Good job! I've marked this task as done:\n";
            result += "\t" + TaskList.getTaskList().get(taskNum).toString() + "\n";
        }
        return result;
    }

    /**
     * Identifies the task that is to be deleted and passes it to deleteTaskAndUpdate() method
     *
     * @param instruction User's input that followed the "delete" command word
     * @throws DukeException if there is no task that has the index keyed in by the user
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseDelete(String instruction) throws DukeException, IOException {
        String result = "";

        int taskNum = Integer.parseInt(instruction.substring(7)) - 1;

        //If the task number keyed in by the user is invalid
        if (taskNum >= TaskList.getCounter()) {
            throw new DukeException("Hmm, I don't have task " + (taskNum + 1) +
                    " in my list. Please key in 'list' if you'd like to " +
                    "view your list of tasks again!");

        //If the task number keyed in by the user is valid
        } else {
            //Output to be printed to the user by Jarvis
            result += "Noted. I've removed this task from your main list:\n";
            result += "\t" + TaskList.getTaskList().get(taskNum).toString() + "\n";

            //The task is to be deleted from the taskList array and added to the document saved in the user's computer
            TaskList.deleteTaskAndUpdate(TaskList.getTaskList().get(taskNum));

            //Output to be printed to the user by Jarvis
            result += "Now you have " + TaskList.getCounter() + " task(s) in the list.\n";
        }
        return result;
    }

    /**
     * Identifies the description of the todo task that is to be added and passes it to
     * addTaskAndUpdate() method
     *
     * @param instruction User's input that followed the "todo" command word
     * @throws DukeException if there is no task description keyed in
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseTodo(String instruction) throws DukeException, IOException {
        String result = "";

        //If the description of the todo task is empty
        if (instruction.length() < 5) {
            throw new DukeException("Oops! The description of a todo cannot be empty.");

        //If the description of the todo task is not empty
        } else {
            String taskDescription = instruction.substring(4);
            Todo newTodo = new Todo(taskDescription);
            //Add the task to the taskList array and update the document save in the user's local computer
            TaskList.addTaskAndUpdate(newTodo);

            //Output to be printed to the user by Jarvis
            result += "Got it! I've added this task:\n";
            result += "\t" + newTodo.toString() + "\n";
            result += "Now you have " + TaskList.getCounter() + " task(s) in the list.\n";
        }
        return result;
    }

    /**
     * Identifies the description of the deadline task that is to be added and passes it to
     * addTaskAndUpdate() method
     *
     * @param instruction User's input that followed the "deadline" command word
     * @throws DukeException if there is no task description/no deadline/wrongly formatted deadline
     * keyed in
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseDeadline(String instruction) throws DukeException, IOException {
        String result = "";

        //If the description of the deadline task is empty
        if (instruction.length() < 10) {
            throw new DukeException("Oops! The description of a deadline cannot be empty.");

        //If the description of the deadline task is not empty
        } else {
            String taskDescription = "";
            int currIndex = 8;

            //Extracting the deadline (dd/mm/yyyy hh:mm)
            while (currIndex < instruction.length() &&
                    !instruction.substring(currIndex).startsWith(" /")) {
                taskDescription += instruction.substring(currIndex, currIndex + 1);
                currIndex++;
            }

            //If the extracted deadline is too short to contain all of the relevant details
            if (currIndex == instruction.length() ||
                    currIndex + 5 >= instruction.length()) {
                throw new DukeException("I think you forgot to key in your deadline! Please key it" +
                        " in as dd/mm/yyyy hh:mm (in 24 hours format)");

            //If the date keyed in by the user is formatted wrongly
            } else if (instruction.charAt(currIndex + 7) != '/' &&
                    instruction.charAt(currIndex + 10) != '/') {
                throw new DukeException("Please format the date as dd/mm/yyy");

            //If the time keyed in by the suer is formatted wrongly
            } else if (instruction.substring(currIndex).length() < 20){
                throw new DukeException("Please include the time in the 24 hour " +
                        "format (e.g. 15:00)");

            //If the deadline is formatted correctly overall
            } else {
                String by = instruction.substring(currIndex + 5);
                Task newDeadline = new Deadline(taskDescription, by);
                //Add the task to the taskList array and update the document save in the user's local computer
                TaskList.addTaskAndUpdate(newDeadline);

                //Output to be printed to the user by Jarvis
                result += "Got it! I've added this task:\n";
                result += "\t" + newDeadline.toString() + "\n";
                result += "Now you have " + TaskList.getCounter() +
                        " task(s) in the list.\n";
                return result;
            }
        }
    }

    /**
     * Identifies the description of the event task that is to be added and passes it to
     * addTaskAndUpdate() method
     *
     * @param instruction User's input that followed the "event" command word
     * @throws DukeException if there is no task description/no deadline/wrongly formatted deadline
     * keyed in
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseEvent(String instruction) throws DukeException, IOException {
        String result = "";

        //If the description of the deadline task is empty
        if (instruction.length() < 7) {
            throw new DukeException("Oops!!! The description of a event cannot be empty.");
        } else {
            String taskDescription = "";
            int currIndex = 5;

            //Extracting the timestamp (format: dd/mm/yyyy hh:mm-hh:mm)
            while (currIndex < instruction.length() &&
                    !instruction.substring(currIndex).startsWith(" /")) {
                taskDescription += instruction.substring(currIndex, currIndex + 1);
                currIndex++;
            }

            //If the extracted timestamp is too short to contain all of the relevant details
            if (currIndex == instruction.length() ||
                    currIndex + 5 >= instruction.length()) {
                throw new DukeException("I think you forgot to key in your event timing!");

            //If the date keyed in by the user is formatted wrongly
            } else if (instruction.charAt(currIndex + 7) != '/' &&
                    instruction.charAt(currIndex + 10) != '/') {
                throw new DukeException("Please format the date as dd/mm/yyy");

            //If the timings keyed in by the suer is formatted wrongly
            } else if (instruction.substring(currIndex).length() < 25){
                throw new DukeException("Please include the start and end times in the 24 hour " +
                        "format (e.g. 15:00-16:00)");

            //If the timestamp is formatted correctly overall
            } else {
                String by = instruction.substring(currIndex + 5);
                Task newEvent = new Event(taskDescription, by);
                //Add the task to the taskList array and update the document save in the user's local computer
                TaskList.addTaskAndUpdate(newEvent);

                //Output to be printed to the user by Jarvis
                result += "Got it! I've added this task:\n";
                result += "\t" + newEvent.toString() + "\n";
                result += "Now you have " + TaskList.getCounter() +
                        " task(s) in the list.\n";
                return result;
            }
        }
    }

    /**
     * Returns a list of tasks saved by Jarvis
     *
     * @return a list of tasks save by Jarvis
     */
    public static String parseList() {
        int num = 1;
        String result = "";

        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            result += num + "." + TaskList.getTaskList().get(i).toString() + "\n";//Print the task
            num++;
        }

        return result;
    }

    /**
     * Returns the list of tasks set for/due today and all todo tasks
     *
     * @return the list of tasks set for/due today and all todo tasks
     */
    public static String parseToday() {
        String result = "";
        result += "Tasks scheduled for today are: \n";
        int num = 1;

        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            //If the task is a todo, include it in today's list of tasks
            if (TaskList.getTaskList().get(i) instanceof Todo) {
                result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                num++;
            } else {
                //Retrieve the current year, month and date
                int currYear = LocalDateTime.now().getYear();
                int currMonth = LocalDateTime.now().getMonthValue();
                int currDate = LocalDateTime.now().getDayOfMonth();

                //Create a LocalDateTime object to represent start and end times of the day
                LocalDateTime start = LocalDateTime.of(currYear, currMonth, currDate, 0, 0);
                LocalDateTime end = LocalDateTime.of(currYear, currMonth, currDate, 23, 59);

                //If it is a deadline task
                if (TaskList.getTaskList().get(i) instanceof Deadline) {
                    //Check if the deadline is after the day starts
                    //TODO: Does this work if the time is 12am?
                    boolean deadlineIsAfterDayStarts =
                            (((Deadline) TaskList.getTaskList().get(i)).getDeadline()).isAfter(start);
                    //Check if the deadline is before the day end
                    boolean deadlineIsBeforeDayEnds =
                            (((Deadline) TaskList.getTaskList().get(i)).getDeadline()).isBefore(end);

                    if (deadlineIsAfterDayStarts && deadlineIsBeforeDayEnds) {
                        result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                        num++;
                    }

                //If the event start and end times fall in between the start and end times of the day, add it to the
                //list of tasks for the day
                } else if (TaskList.getTaskList().get(i) instanceof Event) {
                    //Check if the event starts after the day starts
                    boolean eventIsAfterDayStarts =
                            (((Event) TaskList.getTaskList().get(i)).getEventStart()).isAfter(start);
                    //Check if the event ends before the day end
                    boolean eventIsBeforeDayEnds =
                            (((Event) TaskList.getTaskList().get(i)).getEventEnd()).isBefore(end);

                    if (eventIsAfterDayStarts && eventIsBeforeDayEnds) {
                        result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                        num++;
                    }
                }
            }
        }

        //If there are no tasks for today
        if (num == 1) {
            result += "\tLooks like there is nothing due today!\n";
        }
        return result;
    }

    public static String parseFind(String search) {
        String result = "";
        result += "Here are the matching tasks in your list:\n";
        int num = 1;

        //Extract the search word/phrase
        search = search.substring(5);

        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            //Check if the search word/phrase is contained in the task description
            if (TaskList.getTaskList().get(i).getDescription().contains(search)) {
                result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                num++;
            }
        }
        //If there are no matching results
        if (num == 1) {
            result += "\tNo matching results found!\n";
        }
        return result;
    }

    /**
     * Identifies the title and body of the note that is to be added and passes it to
     * addNoteAndUpdate() method
     * @param instruction User's input that followed the "note" command word
     * @throws DukeException if there is no note title/no body/wrongly formatted note
     * keyed in
     * @throws IOException if there is an error in updated the list of notes saved in the user's
     * hard disk
     */
    public static String parseNote(String instruction) throws IOException, DukeException {
        StringBuilder noteTitle = new StringBuilder();
        String noteBody;

        String result = "";

        int currIndex = 5;

        //Checking for the '/' which separates the note title and body
        while (currIndex < instruction.length() &&
                !instruction.substring(currIndex).startsWith(" /")) {
            noteTitle.append(instruction.charAt(currIndex));
            currIndex++;
        }

        //If the '/' cannot be found, note is formatted wrongly
        if (currIndex == instruction.length()) {
            throw new DukeException("Oops, the note is formatted incorrectly! Please " +
                    "write it as title /body");
        } else {
            noteBody = instruction.substring(currIndex + 2); //Extracting the note body

            Note newNote = new Note(noteTitle.toString(), noteBody);
            NoteList.addNoteAndUpdate(newNote);

            result += "Got it! I've added this note:\n";
            result += "\t" + newNote.toString() + "\n";
            result += "Now you have " + NoteList.getCounter() + " note(s) in the list.\n";
            return result;
        }
    }

    /**
     * Prints out the list of notes saved by Jarvis
     */
    public static String parseNoteList() {
        int num = 1;
        String result = "";

        for (int i = 0; i < NoteList.getNoteList().size(); i++) {
            result += num + ". " + NoteList.getNoteList().get(i).toString() + "\n";
            num++;
        }

        return result;
    }

    /**
     * Identifies the note that is to be deleted and passes it to deleteNoteAndUpdate() method
     * @param instruction User's input that followed the "delete" command word
     * @throws DukeException if there is no note that has the index keyed in by the user
     * @throws IOException if there is an error in updated the list of note saved in the user's
     * hard disk
     */
    public static String parseDeleteNote(String instruction) throws DukeException, IOException {
        String result = "";
        int noteNum = Integer.parseInt(instruction.substring(13)) - 1;

        //If there is no corresponding note to the number keyed in by the user
        if (noteNum >= TaskList.getCounter()) {
            throw new DukeException("Hmm, I don't have task " + (noteNum + 1) +
                    " in my list. Please key in 'list' if you'd like to " +
                    "view your list of tasks again!");

        //If there is a corresponding note to the number keyed in by the user
        } else {
            result += "Noted. I've removed this task from your main list:\n";
            result += "\t" + NoteList.getNoteList().get(noteNum).toString() + "\n";

            //Delete the note and update the list on notes on user's hard disk
            NoteList.deleteNoteAndUpdate(NoteList.getNoteList().get(noteNum));

            result += "Now you have " + NoteList.getCounter() + " note(s) in the list.\n";
        }
        return result;
    }
}
