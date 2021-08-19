public class InputValidator {
    private static InputValidator singleInstance = null;

    private InputValidator() { }

    public static InputValidator getInstance() {
        if(singleInstance == null)
            singleInstance = new InputValidator();
        return singleInstance;
    }

    public boolean checkTodo(String[] input) {
        return input.length > 1;
    }
}
