package duke;
import tasks.TaskList;

public class Parser {
    
    final private TaskList taskList;
    
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method that is called and runs until the bye command in inputted. 
     * Listens for all the commands input and responds accordingly.
     * 
     * @param str Input string by user.
     * @return Response message by system.
     */
    public String run(String str) {
        String HELPCOMMANDS = "These are the services I can provide you:\n" +
                "todo             " +
                "\tMake a todo task\n" +
                "deadline           " +
                "\tMake a new deadline event\n" +
                "event              " +
                "\tMake a event\n" +
                "list                  " +
                "\tList out your events\n" +
                "done {index}    " +
                "\tComplete the task at mentioned index\n" +
                "delete {index} " +
                "\tComplete the task at mentioned index\n" +
                "help                " +
                "\tShow all available commands\n" +
                "bye                 " +
                "\tQuit Duke.\n";
        String output;
        str = str.trim();
        try {
            //command done
            if (str.contains("done")) {
                output = taskList.markDone(str);
                return output;
            }
            //command list
            else if (str.equals("list")) {
                output = taskList.showList("");
                return output;
            }
            //command find 
            else if (str.contains("find")) {
                output = taskList.findTask(str.substring(5));
                return output;
            }
            //command to do
            else if (str.contains("todo")) {
                output = taskList.todoTask(str);
                return output;
            }
            //command deadline
            else if (str.contains("deadline")) {
                output = taskList.deadlineTask(str);
                return output;
            }
            //command event
            else if (str.contains("event")) {
                output = taskList.eventsTask(str);
                return output;
            } 
            // command delete
            else if (str.contains("delete")) {
                output = taskList.deleteTask(str);
                return output;
            } 
            else if (str.contains("help")) {
                return HELPCOMMANDS;
            }
            else {
                throw new DukeException("Command is not valid!");
            }
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return "I hear you say '" + str + "' aladin \nI am the Genie & I can do lots of things. \nTo find out what I can do, say help.";
    }
}
