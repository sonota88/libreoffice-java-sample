package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelTest {

    Model sut;

    @BeforeEach
    public void setup() {
        sut = new Model();
    }

    /**
     * 動作確認用。
     */
    @Test
    public void test() throws Exception {
        sut.main("sample.fods");
    }

}
