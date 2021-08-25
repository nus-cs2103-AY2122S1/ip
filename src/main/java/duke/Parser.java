package duke;

import java.time.LocalDate;

public class Parser {
    public static boolean parser(String input, TaskList taskList) throws DukeException{
        String[] pieces = input.split(" ", 2);
        String command = pieces[0];

        if (command.equals("list")) {
            Ui.list(taskList);
        } else if (command.equals("bye")) {
            Ui.bye();
        } else if (command.equals("done")) {
            try {
                int index = Integer.parseInt(pieces[1]);
                Task task = taskList.get(index - 1);
                task.doneTask();
                Ui.done(task);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Invalid task number");
            }

        } else if (command.equals("todo")) {
            try {
                Task newTask = new Todo(pieces[1]);
                taskList.add(newTask);
                Ui.addTask(taskList, newTask);
            } catch (ArrayIndexOutOfBoundsException e){
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (command.equals("event")) {
            try {
                String[] eventPieces = pieces[1].split("/", 2);
                String name = eventPieces[0];
                String[] timePieces = eventPieces[1].split("at ", 2);
                LocalDate time = LocalDate.parse(timePieces[1]);
                Task newTask = new Event(name, time);
                taskList.add(newTask);
                Ui.addTask(taskList, newTask);
            } catch (ArrayIndexOutOfBoundsException e){
                throw new DukeException("☹ Please enter the event command in 'event [task description]/at [start time]' format");
            }
        } else if (command.equals("deadline")) {
            try {
                String[] eventPieces = pieces[1].split("/", 2);
                String name = eventPieces[0];
                String[] timePieces = eventPieces[1].split("by ", 2);
                LocalDate time = LocalDate.parse(timePieces[1]);
                Task newTask = new Deadline(name, time);
                taskList.add(newTask);
                taskList.add(newTask);
            } catch (ArrayIndexOutOfBoundsException e){
                throw new DukeException("☹ Please enter the event command in 'deadline [task description]/by [end time]' format");
            }
        } else if (command.equals("delete")){
            try {
                int index = Integer.parseInt(pieces[1]);
                Task task = taskList.get(index - 1);
                taskList.remove(task);
                Ui.delete(taskList, task);
            } catch (IndexOutOfBoundsException e){
                throw new DukeException("☹ OOPS!!! Invalid task number");
            }

        }else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if(command == "bye") {
            return true;
        } else {
            return false;
        }
    }
}
