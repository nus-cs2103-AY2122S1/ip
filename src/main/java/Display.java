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
        System.out.println(arrReply[0]);
        switch(arrReply[0]) {
            case "list":
                Task.listReply();
                break;
            case "done":
                Task.done(arrReply[1]);
                break;

            default:
                Task.addReply(userReply);
                System.out.println("added: " + userReply);
        }
        System.out.println(line);

    }

    private static String[] processReply(String userReply) {
        String[] strArr = userReply.split(" ", 2);
        return strArr;
    }
}
