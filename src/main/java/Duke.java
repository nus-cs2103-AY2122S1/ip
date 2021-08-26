import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Magnolia\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while(true) {
            try {
                String input = sc.nextLine();
                String first_word = input.split(" ")[0];


                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("All tasks:");
                    int i = 1;
                    for (Task item : list) {
                        System.out.println(i + ". " + item.getTaskType() + item.getStatusIcon() + " " + item.getDescription());
                        i++;
                    }

                } else if (first_word.equals("done")) {
                    int index;
                    try {
                        index = Integer.parseInt(input.split(" ")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Sorry, please enter an integer after 'done'. (e.g. done 2)");
                    }

                    Task temp = list.get(index);
                    temp.setStatus(true);
                    System.out.println("Marked as done:\n" + temp.getDescription());

                } else if (first_word.equals("delete")) {
                    int index;
                    try {
                        index = Integer.parseInt(input.split(" ")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Sorry, please enter an integer after 'delete'. (e.g. delete 2)");
                    }

                    Task temp = list.get(index);
                    System.out.println("Deleted:\n" + temp.getDescription());
                    list.remove(index);
                    System.out.println("There are " + list.size() + " tasks remaining in the list");
                }
                else {
                    Task task = null;
                    String remaining;
                    try {
                        remaining = input.split(" ", 2)[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Sorry, tasks must include descriptions.");
                    }
                    if(first_word.equals("todo")) {
                        task = new Task.Todo(remaining);

                    } else if(first_word.equals("deadline")) {
                        String[] temp = remaining.split("by ", 2);
                        task = new Task.Deadline(temp[0], temp[1]);

                    } else if(first_word.equals("event")) {
                        String[] temp = remaining.split("at ", 2);
                        task = new Task.Event(temp[0], temp[1]);
                    } else {
                        throw new DukeException("Sorry, I don't understand what that means. :(");
                    }
                    list.add(task);
                    System.out.println("Added: " + task.getTaskType() + task.getStatusIcon() + " " + task.getDescription());
                    System.out.println("There are " + list.size() + " tasks in the list");
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());

            }

        }
    }
}
