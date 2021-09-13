package duke;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Deals with making sense of the user's commands
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
     * Checks if the task done command keyed in by the user is correct before calling the markAsDoneAndUpdate() method.
     *
     * @param instruction User's input that followed the "done" command word
     * @throws DukeException if there is no task that has the index keyed in by the user
     * @throws IOException if there is an error in updated the list of tasks saved in the user's
     * hard disk after marking a task as completed
     */
    public static String parseDone(String instruction) throws DukeException, IOException {
        int taskNum = Integer.parseInt(instruction.substring(5)) - 1;

        // If the task number is invalid
        if (taskNum >= TaskList.getCounter()) {
            throw new DukeException(Ui.invalidTaskNum(taskNum));
        // If the task number is valid, update the task to done
        } else {
            return TaskList.markAsDoneAndUpdate(taskNum);
        }
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
        int taskNum = Integer.parseInt(instruction.substring(7)) - 1;

        //If the task number keyed in by the user is invalid
        if (taskNum >= TaskList.getCounter()) {
            throw new DukeException(Ui.invalidTaskNum(taskNum));
        //If the task number keyed in by the user is valid
        } else {
            return TaskList.deleteTaskAndUpdate(taskNum);
        }
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
        //If the description of the todo task is empty
        if (instruction.length() < 5) {
            throw new DukeException(Ui.EMPTY_TODO_DESCRIPTION);

        //If the description of the todo task is not empty
        } else {
            String taskDescription = instruction.substring(4);
            Todo newTodo = new Todo(taskDescription);

            //Add the task to the taskList array and update the document save in the user's local computer
            return TaskList.addTaskAndUpdate(newTodo);
        }
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
        //If the description of the deadline task is empty
        if (instruction.length() < 10) {
            throw new DukeException(Ui.EMPTY_DEADLINE_DESCRIPTION);

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
                throw new DukeException(Ui.INCOMPLETE_DEADLINE);

            //If the date keyed in by the user is formatted wrongly
            } else if (instruction.charAt(currIndex + 7) != '/' &&
                    instruction.charAt(currIndex + 10) != '/') {
                throw new DukeException(Ui.WRONGLY_FORMATTED_DATE);

            //If the time keyed in by the suer is formatted wrongly
            } else if (instruction.substring(currIndex).length() < 20){
                throw new DukeException(Ui.WRONGLY_FORMATTED_DEADLINE_TIME);

            //If the deadline is formatted correctly overall
            } else {
                String by = instruction.substring(currIndex + 5);
                Task newDeadline = new Deadline(taskDescription, by);

                //Add the task to the taskList array and update the document save in the user's local computer
                return TaskList.addTaskAndUpdate(newDeadline);
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
        //If the description of the deadline task is empty
        if (instruction.length() < 7) {
            throw new DukeException(Ui.EMPTY_EVENT_DESCRIPTION);
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
                throw new DukeException(Ui.INCOMPLETE_EVENT_TIMINGS);

            //If the date keyed in by the user is formatted wrongly
            } else if (instruction.charAt(currIndex + 7) != '/' &&
                    instruction.charAt(currIndex + 10) != '/') {
                throw new DukeException(Ui.WRONGLY_FORMATTED_DATE);

            //If the timings keyed in by the suer is formatted wrongly
            } else if (instruction.substring(currIndex).length() < 25){
                throw new DukeException(Ui.WRONGLY_FORMATTED_EVENT_TIMINGS);

            //If the timestamp is formatted correctly overall
            } else {
                String by = instruction.substring(currIndex + 5);
                Task newEvent = new Event(taskDescription, by);

                //Add the task to the taskList array and update the document save in the user's local computer
                return TaskList.addTaskAndUpdate(newEvent);
            }
        }
    }

    /**
     * Identifies the title and body of the note that is to be added and passes it to
     * addNoteAndUpdate() method
     *
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
     *
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
