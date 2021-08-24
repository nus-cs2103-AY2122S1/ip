import java.util.*;

public class Duke {

    private String line = "__________________________________\n";
    private ArrayList<Task> taskList = new ArrayList<>();
    private class Task {
        private String done = "[] ";
        private String action;

        public Task(String action) {
            this.action = action;
        }

        public void done() {
            System.out.println("Nice! I've marked this task as done: ");
            done = "[X] ";
            System.out.println("    " + this);
        }

        public String toString() {
            return done + action;
        }
    }
    private void greet() {
        System.out.println(line);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    private void bye() {
        String line = "__________________________________";
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    private boolean echo() {
        Scanner user = new Scanner(System.in);
        String input = user.nextLine();
        if (input.equals("bye")) {
            bye();
            return false;
        } else {
            return choiceOfAction(input);
        }
    }

    private boolean choiceOfAction(String input) {
        if (input.equals("list")) {
            showList();
            return true;
        } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
            int index = Character.getNumericValue(input.charAt(5));
            if (index <= taskList.size() && index > 0) {
                taskList.get(index - 1).done();
            } else {
                System.out.println("invalid index");
            }
        }else {
            Task newTask = new Task(input);
            taskList.add(newTask);
            System.out.println(line);
            System.out.println("added:" + newTask);
        }
        System.out.println(line);
        return true;
    }

    private void showList() {
        int count = 0;

        for (Task action : taskList ) {
            count++;
            System.out.format("%d. " + action + "\n", count);
        }
        System.out.println(line);
    }


    public static void main(String[] args) {
        Duke bot = new Duke();

        bot.greet();
        boolean end = true;
        while (end) {
            end = bot.echo();
        }
    }
}
