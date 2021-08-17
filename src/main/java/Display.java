public class Display {

    public static String line = "*********************************************";

    public static void introduction() {
        System.out.println(line);
        System.out.println("Hello! I am Skeltal! \n"
                + "What can I do for you?");
        System.out.println(line);
    }

    public static void response(String userReply) {
        System.out.println(line);
        switch(userReply) {
            case "list":
                Storage.listReply();
                break;

            default:
                Storage.addReply(userReply);
                System.out.println("added: " + userReply);
        }
        System.out.println(line);

    }
}
