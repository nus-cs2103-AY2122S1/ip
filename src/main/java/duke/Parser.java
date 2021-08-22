package duke;

import duke.task.DukeList;

public class Parser {

    private DukeList list;

    public Parser(DukeList list) {
        this.list = list;
    }

    public void parse(String input) {
        String[] segment = input.split(" ", 2);

        try {
            if(input.equals("list")) {
                list.list();
            } else if (segment[0].equals("done") && segment.length == 2) {
                list.done(Integer.parseInt(segment[1]));
            } else if (segment[0].equals("delete") && segment.length == 2) {
                list.delete(Integer.parseInt(segment[1]));
            } else if (segment[0].equals("todo")) {
                list.addToDo(input.split("todo", 2)[1]);
            } else if (segment[0].equals("deadline")) {
                list.addDeadlines(input.split("deadline", 2)[1]);
            } else if (segment[0].equals("event")) {
                list.addEvents(input.split("event", 2)[1]);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry,"
                        + " but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
