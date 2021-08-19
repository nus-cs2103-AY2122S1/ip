public class TaskFactory {
    public static Task createTask(String taskStr) {

        if(taskStr.stripLeading().regionMatches(0, "todo", 0, 4)) {
            //create a todo task
            String taskDescription = taskStr.substring(5);
            return new ToDo(taskDescription);
        } else if(taskStr.stripLeading().regionMatches(0, "deadline", 0, 8)) {
            //create a deadline task
            int slashIndex = taskStr.indexOf("/by");
            String taskDescription = taskStr.substring(9, slashIndex - 1);
            String deadline = taskStr.substring(slashIndex + 4);
            return new Deadline(taskDescription, deadline);
        } else if(taskStr.stripLeading().regionMatches(0, "event", 0, 5)) {
            //create an event
            int slashIndex = taskStr.indexOf("/at");
            String taskDescription = taskStr.substring(6, slashIndex - 1);
            String timeSlot = taskStr.substring(slashIndex + 4);
            return new Event(taskDescription, timeSlot);
        } else {
            //exit for now, later prompt with error messages
            System.out.println("invalid input!! terminating!!");
            System.exit(1);
            return new ToDo("never created");
        }
    }
}
