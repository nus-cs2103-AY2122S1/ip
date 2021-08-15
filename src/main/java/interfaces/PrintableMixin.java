package interfaces;

public interface PrintableMixin {
    default void print() {
        System.out.println(this);
    }

    default void print(String s) {
        System.out.println(s);
    }
}
