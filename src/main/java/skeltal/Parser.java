package skeltal;
public class Parser {
    public static void response(String userReply) {
        try {
            String[] arrReply = processReply(userReply);
            System.out.println(Ui.line);
            switch(arrReply[0]) {
                case "list":
                    TaskList.listReply();
                    break;
                case "done":
                    TaskList.done(arrReply[1]);
                    Storage.store();
                    break;
                case "deadline":
                    Deadline dead = new Deadline(arrReply[1]);
                    TaskList.addReply(dead);
                    Storage.store();
                    break;
                case "event":
                    Event event = new Event(arrReply[1]);
                    TaskList.addReply(event);
                    Storage.store();
                    break;
                case "todo":
                    if (arrReply.length == 1) {
                        throw new SkeltalException("OOPS! The description of todo cannot be empty!");
                    }
                    ToDo todo = new ToDo(arrReply[1]);
                    TaskList.addReply(todo);
                    Storage.store();
                    break;
                case "delete":
                    System.out.println("Noted. I have removed this task");
                    TaskList.delete(arrReply[1]);
                    Storage.store();
                    break;
                case "store":
                    Storage.store();
                    break;
                case "find":
                    TaskList.findMatchingTasks(arrReply[1]);
                    break;
                default:
                    throw new SkeltalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception spook) {
            System.out.println(spook.getMessage());
        }

        System.out.println(Ui.line);



    }

    private static String[] processReply(String userReply) {
        String[] strArr = userReply.split(" ", 2);
        return strArr;
    }
}
