package petal;

import org.junit.jupiter.api.Test;

public class PetalTest {

    private Petal petal;

    public PetalTest(Petal petal) {
        this.petal = petal;
    }

    @Test
    public void byeBoolean_noInput_false() {
        petal = new Petal();
    }

}
