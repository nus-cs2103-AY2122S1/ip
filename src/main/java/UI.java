public class UI {
    public String formatMessage(String s) {
        return "    ____________________________________________________________\n     " +
                s + "\n" +
                "    ____________________________________________________________";
    }
    public String formatMessage(String[] messages) {
        StringBuilder res = new StringBuilder("    ____________________________________________________________" );
        for(String s : messages) {
            res.append("\n    ").append(s);
        }
        res.append("\n    ____________________________________________________________");
        return res.toString();
    }
    public void welcomeMessage() {
        System.out.println(formatMessage("Hello! I'm Duke\n" + "     What can I do for you?"));
    }
}
