import java.util.List;

public class Command {

    List<Task> storedInputs;
    String userInput;
    int index;

    public Command(List<Task> storedInputs, String userInput, int index) {
        this.storedInputs = storedInputs;
        this.userInput = userInput;
        this.index = index;
    }

    public void bye_execute() {
        String byeMsg = "    ----------------------------\n"
                + "    okay :<, bye!" + "\n"
                + "    ----------------------------";
        System.out.println(byeMsg);
    }

    public void list_execute() {
        String message = "    ----------------------------\n"
                + "    " + "Here are the tasks in your list:\n";
        int i = 0;
        while (i < storedInputs.size()) {
            message += "    " + (i+1) + ". " + storedInputs.get(i).toString() + "\n";
            i++;
        }
        message += "    ----------------------------";
        System.out.println(message);
    }

    public void done_execute() {
        String userIndex = userInput.substring(5);
        int i = Integer.valueOf(userIndex);
        if (storedInputs.get(i-1) == null) {
            System.out.println("no task found!");
        } else {
            storedInputs.get(i-1).markAsDone();
            String message = "    ----------------------------\n"
                    +"Nice! I have marked this task as done:\n"
                    +"[X] " + storedInputs.get(i-1).getDescription() + "\n" + "    ----------------------------";
            System.out.println(message);
        }
    }

    public void todo_execute() {
        try {
            Task A = new ToDo(userInput.substring(5));
            storedInputs.add(A);
            Duke.index++;
            String message = "    ----------------------------\n"+
                    "Got it, I've added this task: \n"
                    + A.toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! The description of a todo cannot be empty!");
        }
    }

    public void event_execute() {
        try {
            int i = userInput.indexOf("/");
            String description = userInput.substring(6,i-1);
            String time = userInput.substring(i+1);
            Task A = new Event(description, time);
            storedInputs.add(A);
            Duke.index++;
            String message = "    ----------------------------\n"
                    +"Got it, I've added this task: \n"
                    + A.toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! The description of an event cannot be empty and must contain a time!");
        }
    }

    public void deadline_execute() {
        try {
            int i = userInput.indexOf("/");
            String description = userInput.substring(9,i-1);
            String time = userInput.substring(i+1);
            Task A = new Deadlines(description, time);
            storedInputs.add(A);
            Duke.index++;
            String message = "    ----------------------------\n"
                    +"Got it, I've added this task: \n"
                    + A.toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! The description of a deadline cannot be empty and must contain a time!");
        }
    }

    public void delete_execute() {
        String userIndex = userInput.substring(7);
        int i = Integer.valueOf(userIndex);
        if (storedInputs.get(i-1) == null) {
            System.out.println("no task found!");
        } else {
            Duke.index--;
            String message = "    ----------------------------\n"
                    +"Got it, I've removed this task: \n"
                    + storedInputs.get(i-1).toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
            storedInputs.remove(i-1);
        }
    }

    public String generateTasks() {
        String tasks = "";
        int i = 0;
        while (i < storedInputs.size()) {
            tasks += storedInputs.get(i).toString() + "\n";
            i++;
        }
        return tasks;
    }
}
