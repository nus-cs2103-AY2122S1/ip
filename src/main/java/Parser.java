public class Parser {

    private static String addTasks(TaskList taskList, Task task) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                taskList.addToList(task), taskList.taskCount());
    }

    private static String deleteTask(TaskList taskList, int index) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                taskList.deleteFromList(index), taskList.taskCount());
    }

    public static Boolean parse(String command,TaskList taskList, Ui ui) throws DukeException{
        boolean descriptionEmpty = false;

            String userInput = command; //what user typed in
            String firstWord = userInput;   //first word of what's typed in
            String restOfInput = " ";

            if (userInput.contains(" ")) { //if there's a spacing after first word
                firstWord = userInput.split(" ",2)[0];
                restOfInput = userInput.split(" ",2)[1]; //can be spaces or words
            } else { // if only one word is typed in with no spaces following
                descriptionEmpty = true;
            }

            switch (firstWord) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return false;
            case "list":
                System.out.println("Here are the tasks in your list:\n" + taskList.getList());
                break;
            case "done":
                System.out.println("Nice! I've marked this task as done:\n" + taskList.taskDone(Integer.parseInt(restOfInput)));
                break;
            case "delete":
                System.out.println(deleteTask(taskList, Integer.parseInt(restOfInput)));
                break;
            case "todo":
                Task currToDo = new ToDo(restOfInput, descriptionEmpty);
                System.out.println(addTasks(taskList, currToDo));
                break;
            case "deadline":
                Task currDeadline = new Deadline(restOfInput, descriptionEmpty);
                System.out.println(addTasks(taskList, currDeadline));
                break;
            case "event":
                Task currEvent = new Event(restOfInput, descriptionEmpty);
                System.out.println(addTasks(taskList, currEvent));
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            return true;
        }
}

