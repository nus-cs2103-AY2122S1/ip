public class ChatBot {
    private boolean isRunning;

    public ChatBot() {
        isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String greet() {
        return "Hello! I'm Chatty Clifford! \nHow may I be of service to you?";
    }

    public String farewell() {
        this.isRunning = false;
        return "Bye! See you next time!";
    }

    public String listen(String input) {
        if(input.toLowerCase().trim().equals("bye")) {
            return farewell();
        }
        return echo(input);
    }

    public String echo(String input) {
        return input;
    }
}
