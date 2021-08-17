public class HAL9000Exception extends Exception {
    public HAL9000Exception(String str) {
        super("Error: Whoops... " + str);
    }
}
