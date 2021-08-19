public class ExitMessage extends DukeMessage{
    public void display() {
        System.out.println("Ram Ram!");
        Duke.conversationState = false;
    }
}
