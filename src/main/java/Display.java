public class Display {

    public static String line = "*********************************************";

    public static void introduction() {
        System.out.println(line);
        System.out.println("Hello! I am Skeltal! \n"
                + "What can I do for you?");
        System.out.println(line);
    }

    public static void response(String userReply) {
        String[] arrReply = processReply(userReply);

        System.out.println(line);
        switch(arrReply[0]) {
            case "list":
                Task.listReply();
                break;
            case "done":
                Task.done(arrReply[1]);
                break;
            case "deadline":
                Deadline dead = new Deadline(arrReply[1]);
                Task.addReply(dead);
                break;
            case "event":
                Event event = new Event(arrReply[1]);
                Task.addReply(event);
                break;
            case "todo":
                ToDo todo = new ToDo(arrReply[1]);
                Task.addReply(todo);
                break;
        }
        System.out.println(line);

    }

    private static String[] processReply(String userReply) {
        String[] strArr = userReply.split(" ", 2);
        return strArr;
    }
}
