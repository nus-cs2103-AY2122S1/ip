public class List extends Responses{

    private static String printList() {
        String response = "";
        for (int i = 0; i < Responses.list.size(); i++) {
            Task t = Responses.list.get(i);
            response += String.format("\t%d. %s\n", i + 1, t);
        }
        return response;
    }

    public static void chat() {
        Responses.interact(printList());
    }

}
