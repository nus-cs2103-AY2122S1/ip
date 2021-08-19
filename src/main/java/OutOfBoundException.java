public class OutOfBoundException extends Exception {
    @Override
    public String toString() {
        return "Task does not exist. Please send a correct number ><";
    }
}
