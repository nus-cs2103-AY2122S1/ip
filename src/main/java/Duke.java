import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    private ArrayList<Task> tasks = new ArrayList<>();

    private void serve() {
        System.out.println("Good Day Sir/Mdm, I am Duke.\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);

        while (true) {

            if (!sc.hasNextLine()) {
                sc.close();
                break;
            }
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are your tasks Sir/Mdm:" + this.list());
                System.out.println();
                continue;
            } else if (input.split(" ")[0].equals("done")) {
                try {
                    markAsDone(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                }
            } else if (input.split(" ")[0].equals("delete")) {
                try {
                    deleteTask(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                }
            } else {
                try {
                    addTask(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                }
            }
        }
    }

    private void markAsDone(String input) throws DukeException {
        String[] parsedInput = input.split(" ");

        if (parsedInput.length != 2) {
            throw (new DukeException("Please specify a task you would like marked as done Sir/Mdm:" + this.list()));
        }

        int taskToMark;

        try {
            taskToMark = Integer.parseInt(parsedInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a proper number within this range Sir/Mdm:" + this.list());
        }

        if (this.tasks.isEmpty()) {
            throw (new DukeException("You have no tasks to mark as done Sir/Mdm!"));
        }

        if (taskToMark < 0 || taskToMark > this.tasks.size() - 1) {
            throw (new DukeException("Please specify a task within this range Sir/Mdm:" + this.list()));
        }

        this.tasks.get(taskToMark).markAsDone();
        System.out.println("Good job Sir/Mdm! I shall mark this task as complete:\n   " +
                this.tasks.get(taskToMark) + "\n");
    }


    private void addTask(String input) throws DukeException {
        Pattern todoPattern = Pattern.compile("(^(todo ))");
        Pattern deadlinePattern = Pattern.compile("(^(deadline ))");
        Pattern eventPattern = Pattern.compile("(^(event ))");

        if (todoPattern.matcher(input).find() || input.equals("todo")) {
            String description = input.substring(4).trim();
            if (description.isBlank()) {
                throw new DukeException("Please provide a description of the todo Sir/Mdm.");
            }
            Task newTask = new Todo(description);
            this.tasks.add(newTask);
            System.out.println("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + newTask);
            System.out.println("Now you have " + this.tasks.size() + (this.tasks.size() == 1 ? " task." : " tasks.")
                    + "\n");

        } else if (deadlinePattern.matcher(input).find() || input.equals("deadline")) {
            int index = input.indexOf('/');
            if (index == -1) {
                if (input.equals("deadline")) {
                    throw new DukeException("Please provide a description and date of the deadline Sir/Mdm.");
                } else {
                    throw new DukeException("Please provide a date of the deadline Sir/Mdm.");
                }
            }
            String description = input.substring(9, index).trim();
            if (description.isBlank()) {
                throw new DukeException("Please provide a description of the deadline Sir/Mdm.");
            }
            String date = input.substring(index + 1);
            Task newTask = new Deadline(description, date);
            this.tasks.add(newTask);
            System.out.println("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + newTask);
            System.out.println("Now you have " + this.tasks.size() + (this.tasks.size() == 1 ? " task." : " tasks.")
                    + "\n");

        } else if (eventPattern.matcher(input).find() || input.equals("event")) {
            int index = input.indexOf('/');
            if (index == -1) {
                if (input.equals("event")) {
                    throw new DukeException("Please provide a description and timeline of the event Sir/Mdm.");
                } else {
                    throw new DukeException("Please provide a timeline of the event Sir/Mdm.");
                }
            }

            String description = input.substring(5, index).trim();
            if (description.isBlank()) {
                throw new DukeException("Please provide a description of the event Sir/Mdm.");
            }
            String date = input.substring(index + 1);
            Task newTask = new Event(description, date);
            this.tasks.add(newTask);
            System.out.println("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + newTask);
            System.out.println("Now you have " + this.tasks.size() + (this.tasks.size() == 1 ? " task." : " tasks.")
                    + "\n");
        } else {
            throw new DukeException("Pardon me Sir/Mdm, but I do not understand.");
        }

    }

    private String list() {
        String list = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            list = list + "\n" + (i + 1) + ". " + this.tasks.get(i);
        }

        return list;

    }

    private void deleteTask(String input) throws DukeException {
        String[] parsedInput = input.split(" ");

        if (parsedInput.length != 2) {
            throw (new DukeException("Please specify a task you would like to delete Sir/Mdm:" + this.list()));
        }

        int taskToDelete;

        try {
            taskToDelete = Integer.parseInt(parsedInput[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a proper number within this range Sir/Mdm:" + this.list());
        }

        if (this.tasks.isEmpty()) {
            throw (new DukeException("You have no tasks to delete Sir/Mdm!"));
        }

        if (taskToDelete < 0 || taskToDelete > this.tasks.size() - 1) {
            throw (new DukeException("Please specify a task within this range Sir/Mdm:" + this.list()));
        }


        Task deletedTask = this.tasks.get(taskToDelete);
        this.tasks.remove(taskToDelete);
        System.out.println("Much obliged Sir/Mdm! I shall delete this task:\n   " +
                deletedTask + "\n" + "Now you have " + this.tasks.size() +
                (this.tasks.size() == 1 ? " task." : " tasks.") + "\n");
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.serve();
    }


}



