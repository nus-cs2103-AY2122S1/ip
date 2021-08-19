public class GreetMessage extends DukeMessage{
    public void display() {
        System.out.println("Namaste chacha!\nKaise yaad kiye humko?");
        Duke.conversationState = true;
    }
}
