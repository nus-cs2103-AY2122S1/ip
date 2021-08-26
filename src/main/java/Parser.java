public class Parser {

    Parser () {

    }

    public void read_command(Ui ui, String next_line, TaskList task) {
        int first_time = 0;
        while (true) {
            if (first_time == 1) {
                next_line = ui.scan_nextLine();
            }

            // exit if bye
            if (next_line.equals("bye")) {
                this.bye();
                break;
            }

            // outputting the list
            else if (next_line.equals("list")) {
                task.output_list(next_line);
                first_time = 1;
            }

            //marking task as done
            else if (next_line.length() > 4 && next_line.substring(0, 4).equals("done")) {
                task.mark_task_as_done(next_line);
                first_time = 1;
            }

            //deleting task from the list
            else if (next_line.length() > 6 && next_line.substring(0, 6).equals("delete")) {
                task.delete_task(next_line);
                first_time = 1;
            }

            // adding todo to the list
            else if (next_line.length() > 4 && next_line.substring(0, 4).equals("todo")) {
                Task todo = new Todo(next_line);
                task.Add_todo(next_line, todo);
                first_time = 1;
            }

            //adding deadline to the list
            else if (next_line.length() > 8 && next_line.substring(0, 8).equals("deadline")) {
                Task deadline = new Deadline(next_line);
                task.Add_deadline(next_line, deadline);
                first_time = 1;
            }

            //adding event to the list
            else if (next_line.length() > 5 && next_line.substring(0, 5).equals("event")) {
                Task event = new Event(next_line);
                task.Add_event(next_line, event);
                first_time = 1;
            }

            else {
                first_time = 1;
                ui.Random_Description_exception(next_line);
            }
        }
    }

    public void bye() {
        System.out.println("Bye bye! Hope to see you again soon!");
    }

}
