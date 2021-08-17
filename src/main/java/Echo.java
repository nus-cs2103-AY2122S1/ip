public class Echo extends Responses{

    private static void appendList(String uResponse) {
        Task t = new Task(uResponse);
        Responses.list[Responses.currLength] = t;
        Responses.currLength += 1;
    }

    public static void chat(String dResponse) {
        appendList(dResponse);
        Responses.interact(String.format("\tadded: %s\n", dResponse));
    }

}
