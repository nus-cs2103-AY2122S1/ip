public class Display {

    public static String line = "---------------------------------------------";

    public static void introduction() {
        System.out.println(line);
        System.out.println("Hello! I am Skeltal! \n"
                + "What can I do for you?");
        System.out.println(line);
    }

    public static void response(String userReply) {
        try {
            String[] arrReply = processReply(userReply);
            System.out.println(line);
            switch(arrReply[0]) {
                case "list":
                    Manager.listReply();
                    break;
                case "done":
                    Manager.done(arrReply[1]);
                    Storage.store();
                    break;
                case "deadline":
                    Deadline dead = new Deadline(arrReply[1]);
                    Manager.addReply(dead);
                    Storage.store();
                    break;
                case "event":
                    Event event = new Event(arrReply[1]);
                    Manager.addReply(event);
                    Storage.store();
                    break;
                case "todo":
                    if (arrReply.length == 1) {
                        throw new SkeltalException("OOPS! The description of todo cannot be empty!");
                    }
                    ToDo todo = new ToDo(arrReply[1]);
                    Manager.addReply(todo);
                    Storage.store();
                    break;
                case "delete":
                    System.out.println("Noted. I have removed this task");
                    Manager.delete(arrReply[1]);
                    Storage.store();
                    break;
                case "store":
                    Storage.store();
                    break;
                default:
                    throw new SkeltalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception spook) {
            System.out.println(spook.getMessage());
        }

        System.out.println(line);



    }

    private static String[] processReply(String userReply) {
        String[] strArr = userReply.split(" ", 2);
        return strArr;
    }
}
