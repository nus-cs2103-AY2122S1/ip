public class Parser {

    public static int decoder(String userInput) {
        int res;
        switch (userInput) {
            case "bye":
                res = 0;
                break;
            case "list":
                res = 1;
                break;
            case "done":
                res = 2;
                break;
            case "todo":
                res = 3;
                break;
            case "deadline":
                res = 4;
                break;
            case "event":
                res = 5;
                break;
            case "delete":
                res = 6;
                break;
            default:
                res = 7;
                break;
        }
        return res;
    }

}
