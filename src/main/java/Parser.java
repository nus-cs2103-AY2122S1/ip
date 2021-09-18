import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {
    private String userInput;

    Parser(String userInput) {
        this.userInput = userInput;
    }
    public static void handleInput(String userInput, ArrayList<Task> tasks) {
        if (userInput.equals("bye")) {
            System.out.println("Duke : Bye, Hope to see you again soon !");
            return;
        } else if (userInput.equals("list")) {
            int numberOfTasks = tasks.size();
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            System.out.println("\n ----------------------------------");

        } else if (userInput.startsWith("done")) {
            int id = Integer.parseInt(userInput.substring(5));
            Task currTask = null;
            try {
                currTask = tasks.get(id - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
            if (currTask != null) {
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + currTask);
                System.out.println("\n ----------------------------------");
            }

        } else if (userInput.startsWith("delete")) {
            int id = Integer.parseInt(userInput.substring(7));

            try {
                Task removedTask = tasks.get(id - 1);
                tasks.remove(id);
                System.out.println("Successfully removed task : " + removedTask);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            Task newTask = null;
            try {
                newTask = handleTaskInput(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            if (newTask != null) {
                tasks.add(newTask);
                System.out.println("Duke: Added Task " + userInput);

            }
            System.out.println("\n ----------------------------------");
        }
    }

    public static Task handleTaskInput(String userInput) throws DukeException{
        if(userInput.startsWith("todo")) {
            int id = userInput.indexOf("todo") + 4;
            String task = userInput.substring(id);
            if(task.replaceAll("\\s+","").equals("")){
                //if remaining string is whitespace or empty
                throw new DukeException("Todo needs to have description !");
            }
            return new Todo(task, false);
        } else if(userInput.startsWith("deadline")) {
            int start_id = userInput.indexOf("deadline");
            int task_id = userInput.indexOf("/by");
            String task = userInput.substring(start_id + 9, task_id);
            String date = userInput.substring(task_id + 3);
            if(task_id == -1) {
                throw new DukeException("You need to specify at using /by !");
            }
            if(task.replaceAll("\\s+","").equals("")){
                //if remaining string is whitespace or empty
                throw new DukeException("Task needs to have description !");
            }
            if(date.replaceAll("\\s+","").equals("")) {
                //if remaining string is whitespace or empty
                throw new DukeException("deadline needs to have dates !");
            }
            return new Deadline(task, false, LocalDate.parse(date.trim()));
        } else if(userInput.startsWith("event")) {
            int start_id = userInput.indexOf("event");
            int task_id = userInput.indexOf("/at");
            String task = userInput.substring(start_id + 6, task_id);
            String date = userInput.substring(task_id + 3);

            if(task_id == -1) {
                throw new DukeException("You need to specify at using /at !");
            }
            return new Event(task, false, LocalDate.parse(date.trim()));
        } else {
            throw new DukeException("I don't understand what you are talking about !");
        }
    }
}


