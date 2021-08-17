public class GreetMessage extends DukeMessage{
    public void display() {
        System.out.println("Hello!");
        Duke.conversationState = true;
    }
}
