package duke;

/**
 * 
 * Deals with making sense of the user's command.
 * It takes in user inputs and filters it to its respective command. 
 */
public class Parser {
    public Parser() {

    }

    /**
     * 
     * 
     * @param command Command is the user input.
     * @param taskArr List of tasks in a TaskList. 
     * @throws DukeException If input is unexpected or invalid.
     */
    public void parse(String command, TaskList taskArr) throws DukeException{
        String[] commandArr = command.split(" ");
        if (command.equals("list")) {
            listCommand(taskArr);
        } else if (commandArr[0].equals("done")) {
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            doneCommand(taskArrRef, taskArr);
        } else if (commandArr[0].equals("delete")) {
            int taskArrRef = Integer.parseInt(commandArr[1]) - 1;
            deleteCommand(taskArrRef, taskArr);
        } else if (commandArr[0].equals("find")) {
            try {
                String keyword = commandArr[1];
                findCommand(keyword, taskArr);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please insert a word after \"done\"");
            }
            
        } else {
            boolean wrongArrayLength = commandArr.length <= 1;
            String taskAdded = "Got it. I've added this task: ";
            if (commandArr[0].equals("todo")) {
                if (wrongArrayLength) {
                    throw new DukeException("The description of a todo cannot be empty!");
                } else {
                    System.out.println(taskAdded);
                    int spaceIndex = command.indexOf(" ");
                    Task t = new Todo(command.substring(spaceIndex + 1));
                    taskArr.add(t);
                    System.out.println(t);
                }

            } else if (commandArr[0].equals("deadline")) {
                if (wrongArrayLength) {
                    throw new DukeException("The description of a deadline cannot be empty!");
                } else if (!command.contains("/by ")) {
                    throw new DukeException("Remember to enter deadline in this format:\"[deadline] [task] /by [date]\"");
                } else {
                    System.out.println(taskAdded);
                    int spaceIndex = command.indexOf(" ");
                    int slashIndex = command.indexOf("/by ");
                    Task t = new Deadline(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));
                    taskArr.add(t);
                    System.out.println(t);
                }

            } else if (commandArr[0].equals("event")) {
                if (wrongArrayLength) {
                    throw new DukeException("The description of an event cannot be empty!");

                } else if (!command.contains("/at ")) {
                    throw new DukeException("Remember to enter event in this format:\"[event] [task] /at [date]\"");
                } else {
                    System.out.println(taskAdded);
                    int spaceIndex = command.indexOf(" ");
                    int slashIndex = command.indexOf("/at ");
                    Task t = new Event(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));
                    taskArr.add(t);
                    System.out.println(t);
                }

            } else {
                throw new DukeException("I'm sorry, but I don't know what that means!");
            }
            System.out.println("Now you have " + taskArr.size() + " tasks in the list.");

        }
    }

    /**
     * 
     * To list existing tasks
     * @param taskArr List of tasks in a TaskList. 
     */
    public static void listCommand(TaskList taskArr) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskArr.size(); i++) {
            int j = i + 1;
            System.out.println(j + ". " + taskArr.get(i));
        }
    }


    /**
     *
     * To set a task as completed.
     * @param index Index of the task in the TaskList.
     * @param taskArr List of tasks in a TaskList. 
     * @throws DukeException If input is unexpected or invalid.
     */
    public static void doneCommand(int index, TaskList taskArr) throws DukeException{
        if (index >= taskArr.size() || index < 0) {
            throw new DukeException("Invalid value!");
        } else {
            Task taskRef = taskArr.get(index);
            taskRef.setDone();
            System.out.println("Nice! I've marked this task as done:\n" + taskRef);
        }
    }

    /**
     *
     * To delete a task from existing list of tasks.
     * @param index Index of the task in the TaskList.
     * @param taskArr List of tasks in a TaskList. 
     * @throws DukeException If input is unexpected or invalid.
     */
    public static void deleteCommand(int index, TaskList taskArr) throws DukeException{
        if (index >= taskArr.size() || index < 0) {
            throw new DukeException("Invalid value!");
        } else {
            Task taskRef = taskArr.get(index);
            taskArr.remove(index);
            System.out.println("Noted. I've removed this task:\n" + taskRef);
            System.out.println("Now you have " + taskArr.size() + " tasks in the list.");
        }
    }

    public static void findCommand(String word, TaskList taskArr) {
        String returnMessage = "";
        for (int i = 0; i < taskArr.size(); i++) {
            Task taskInstance = taskArr.get(i);
            String taskDescription = taskInstance.getDescription();
            if (taskDescription.contains(word)) {
                returnMessage = returnMessage + taskInstance.toString() + "\n";
            }
        }
        if (returnMessage.equals("")) {
            System.out.println("There were no tasks found with keyword: " + word);
        } else {
            System.out.println("Here are the tasks in your list that match the keyword:\n" + returnMessage);
        }
    }

}
