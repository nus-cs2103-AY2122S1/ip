public class Message {
    public static void print(String[] msgLines) {
        if (msgLines.length == 0) {
            return;
        }
        System.out.println("\t_______________________________________________________");
        for (String msg : msgLines) {
            System.out.println("\t  " + msg);
        }
        System.out.println("\t_______________________________________________________");
    }

    public static void print(String msg) {
        if (msg.length() == 0) {
            return;
        }
        Message.print(new String[] {msg});
    }

    public static void printError(String msg) {
        if (msg.length() == 0) {
            return;
        }
        System.err.println("\t_______________________________________________________");
        System.err.println("\t" + msg);
        System.err.println("\t_______________________________________________________");
    }
}
