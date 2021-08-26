//Parser: deals with making sense of the user command

import java.io.IOException;
import java.sql.SQLOutput;

public class Parser {
    public static boolean parse(String command) throws DukeException, IOException {
        if (command.contains("bye")) {
            return true;
        }
        if (command.startsWith("list")) {
            Duke.getTaskList();
        } else if (command.startsWith("done")) {
            int i = Integer.valueOf(command.substring(5));
            Duke.markDone(i);
        } else if (command.contains("delete")) {
            int i = Integer.valueOf(command.substring(7));
            System.out.println(i);
            Duke.deleteTask(i);
        } else if (command.startsWith("todo")) {
            Duke.toDo(command);
        } else if (command.startsWith("deadline")) {
            Duke.deadline(command);
        } else if (command.startsWith("event")) {
            Duke.event(command);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n");
        }
        return false;
    }
}
