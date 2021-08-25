public class Parser {

    final private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void parse(String input){
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("Bye. Hope to see you again soon!");

        }

        else if (input.equalsIgnoreCase("list")){
            taskList.showTasks();
        }

        else if (input.contains("done")){
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            taskList.markAsDone(index);
            System.out.println("Nice! I've marked this task as done:\n" + taskList.getTask(index).description);
        }

        else if(input.contains("delete")){
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removedTask = taskList.getTask(index);
            System.out.println("Noted. I've removed this task:\n" + removedTask.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
            taskList.removeTask(index);
        }

        else if (input.contains("todo") || input.contains("deadline") || input.contains("event")){
            Task newTask = new Task("null");
            try {
                if(input.contains("todo")){
                    newTask = new Todo(input.split(" ",2)[1]);
                }
                if(input.contains("deadline")) {
                    newTask = new Deadline(input.split("/by")[0].split(" ",2)[1],input.split("/by ")[1]);
                }
                if (input.contains("event")) {
                    newTask = new Event(input.split("/at")[0].split(" ",2)[1],input.split("/at ")[1]);
                }
                taskList.addTask(newTask);
                System.out.println("Got it. I've added this task:\n" + newTask + "\nNow you have " + taskList.size() + " tasks in the list.");

            } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                System.out.println("OOPS!!! your description is not complete or is in a wrong format");
            }
        }
        else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(" );
        }
    }



}
