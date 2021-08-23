package view;

public class Response {
    private static void hLine() {
        System.out.println("\t----------------------------------------------");
    }
    private static void display(String arg) { System.out.println("\t" + arg);}

    public void respond(String[] reply) {
        hLine();
        for (int i = 0; i < reply.length - 1; i++) {
            display(reply[i]);
        }
        display(reply[reply.length - 1]);
        hLine();
    }

    public void respond(String reply) {
        respond(new String[]{reply});
    }
}
