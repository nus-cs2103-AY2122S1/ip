public class Duke {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        ChatBot chatBot = new ChatBot(inputHandler);
        chatBot.start();
    }
}
