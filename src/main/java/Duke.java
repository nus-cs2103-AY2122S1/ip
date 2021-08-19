import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // handle greetings
        String greetings = "Hello! I'm Grace" + "\n" + "How can I help you?";
        System.out.println(greetings);

        //creating the list for task
        ArrayList<Task> list = new ArrayList<Task>();
        ArrayList<String> done_check = new ArrayList<String>();
        int total = 0;
        int task_number;


        //exception handling
        String next_line = scan.nextLine();
        int caught = 0;

        while (true) {
            try {
                Duke.catch_error(next_line);
            } catch (DukeException e) {
                System.out.println(e.output_error());
                caught = 1;
            } finally {
                if (caught == 1) {
                    next_line = scan.nextLine();
                    caught = 0;
                } else {
                    break;
                }
            }
        }


        int first_time = 0;
        while (true) {
            if (first_time == 1) {
                next_line = scan.nextLine();
            }

            // exit if bye
            if (next_line.equals("bye")) {
                System.out.println("Bye bye! Hope to see you again soon!");
                break;
            }

            // outputting the list
            else if (next_line.equals("list")) {
                int count = 1;
                System.out.println("Do these soon:" + "\n");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(count + ". [" + list.get(i).get_type() + "][" + done_check.get(i) + "] " + list.get(i).get_task());
                    count = count + 1;
                }
                first_time = 1;
            }

            //marking task as done
            else if (next_line.length() > 4 && next_line.substring(0, 4).equals("done")) {
                System.out.println("Yay! you have finished this task!");
                task_number = Integer.valueOf(next_line.substring(5)) - 1;
                done_check.set(task_number, "X");
                System.out.println("[" + list.get(task_number).get_type() + "][" + done_check.get(task_number) + "] " + list.get(task_number).get_task());
                first_time = 1;
            } else if (next_line.length() > 4 && next_line.substring(0, 4).equals("todo")) {
                total = total + 1;
                Task todo = new Todo(next_line);
                list.add(todo);
                done_check.add("");
                System.out.println("Added the task! :)");
                System.out.println("[" + todo.get_type() + "][] " + todo.get_task());
                System.out.println("Jiayou! you have " + total + " tasks in the list.");
                first_time = 1;
            } else if (next_line.length() > 8 && next_line.substring(0, 8).equals("deadline")) {
                total = total + 1;
                Task deadline = new Deadline(next_line);
                list.add(deadline);
                done_check.add("");
                System.out.println("Added the task! :)");
                System.out.println("[" + deadline.get_type() + "][] " + deadline.get_task());
                System.out.println("Jiayou! you have " + total + " tasks in the list.");
                first_time = 1;
            } else if (next_line.length() > 5 && next_line.substring(0, 5).equals("event")) {
                total = total + 1;
                Task event = new Event(next_line);
                list.add(event);
                done_check.add("");
                System.out.println("Added the task! :)");
                System.out.println("[" + event.get_type() + "][] " + event.get_task());
                System.out.println("Jiayou! you have " + total + " tasks in the list.");
                first_time = 1;
            } else {
                int caught2 = 0;

                while (true) {
                    try {
                        Duke.random_error(next_line);
                    } catch (DukeException e) {
                        System.out.println(e.output_error());
                        caught2 = 1;
                    } finally {
                        if (caught2 == 1) {
                            next_line = scan.nextLine();
                            caught2 = 0;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void random_error(String next_line) throws DukeException {
        DukeException e = new RandomDescription(next_line);
        throw e;
    }

    public static void catch_error(String next_line) throws DukeException {
        if (next_line.equals("todo")) {
            DukeException e = new EmptyDescription(next_line);
            throw e;
        } else if (next_line.equals("deadline")) {
            DukeException e = new EmptyDescription(next_line);
            throw e;
        } else if (next_line.equals("event")) {
            DukeException e = new EmptyDescription(next_line);
            throw e;
        }
    }
}
