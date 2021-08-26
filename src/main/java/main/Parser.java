package main;


import task.deadline.Deadline;
import task.event.Event;
import task.Task;
import task.Todo.Todo;

public class Parser {

    public Parser() {}

<<<<<<< HEAD
    public void read_command(Ui ui, String next_line, TaskList task) {
        int first_time = 0;
=======
    /**
     * Read the user inputs and respond accordingly
     *
     * @param ui Ui to scan in lines
     * @param nextLine the next line being read
     * @param task TaskList the task used to access ArrayList of task n done_check
     */
    public void readCommand(Ui ui, String nextLine, TaskList task) {
        int firstTime = 0;

>>>>>>> 7f3ded1 (Follow Coding Standard)
        while (true) {
            if (firstTime == 1) {
                nextLine = ui.scanNextLine();
            }

            // exit if bye
            if (nextLine.equals("bye")) {
                this.bye();
                break;
            }

            // outputting the list
<<<<<<< HEAD
            else if (next_line.equals("list")) {
                task.output_list(next_line);
                first_time = 1;
=======
            else if (nextLine.equals("list")) {
                task.outputList();
                firstTime = 1;
>>>>>>> 7f3ded1 (Follow Coding Standard)
            }

            //marking task as done
            else if (nextLine.length() > 4 && nextLine.substring(0, 4).equals("done")) {
                task.markTaskAsDone(nextLine);
                firstTime = 1;
            }

            //deleting task from the list
            else if (nextLine.length() > 6 && nextLine.substring(0, 6).equals("delete")) {
                task.deleteTask(nextLine);
                firstTime = 1;
            }

            // adding todo to the list
            else if (nextLine.length() > 4 && nextLine.substring(0, 4).equals("todo")) {
                Task todo = new Todo(nextLine);
                task.addTodo(nextLine, todo);
                firstTime = 1;
            }

            //adding deadline to the list
            else if (nextLine.length() > 8 && nextLine.substring(0, 8).equals("deadline")) {
                Task deadline = new Deadline(nextLine);
                task.addDeadline(nextLine, deadline);
                firstTime = 1;
            }

            //adding event to the list
            else if (nextLine.length() > 5 && nextLine.substring(0, 5).equals("event")) {
                Task event = new Event(nextLine);
                task.addEvent(nextLine, event);
                firstTime = 1;
            }

            else {
                firstTime = 1;
                ui.randomDescriptionException(nextLine);
            }
        }
    }

    public void bye() {
        System.out.println("Bye bye! Hope to see you again soon!");
    }

}
