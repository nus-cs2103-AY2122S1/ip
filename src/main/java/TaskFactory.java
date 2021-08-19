public class TaskFactory {
    public static Task createTask(String taskStr) throws IllegalFormatException, EmptyDescriptionException, InvalidCommandException {

        if(taskStr.substring(0, 4).equals("todo")) {
            //create a todo task
            try {
                String taskDescription = taskStr.substring(5);
                if(taskDescription.stripLeading().equals(""))
                    throw new EmptyDescriptionException("Ja ammi se todo ka format seekh ke aa!\n" +
                            "description bhi dena padta hai");
                return new ToDo(taskDescription);
            } catch (StringIndexOutOfBoundsException e) {
                throw new EmptyDescriptionException("Ja ammi se todo ka format seekh ke aa!\n" +
                        "description bhi dena padta hai");
            }
        } else if(taskStr.substring(0, 8).equals("deadline")) {
            //create a deadline task
            try {
                int slashIndex = taskStr.indexOf("/by");
                String taskDescription = taskStr.substring(9, slashIndex - 1);
                String deadline = taskStr.substring(slashIndex + 4);
                return new Deadline(taskDescription, deadline);
            } catch (Exception e) {
                throw new IllegalFormatException("Ja ammi se deadline banane ka format seekh ke aa!");
            }
        } else if(taskStr.substring(0, 5).equals("event")) {
            //create an event
            try {
                int slashIndex = taskStr.indexOf("/at");
                String taskDescription = taskStr.substring(6, slashIndex - 1);
                String timeSlot = taskStr.substring(slashIndex + 4);
                return new Event(taskDescription, timeSlot);
            } catch (Exception e) {
                throw new IllegalFormatException("Ja ammi se event banane ka format seekh ke aa!");
            }
        } else {
            throw new InvalidCommandException("Kya likhra h hero??");
        }
    }
}
