package sample;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {

    Model sut;
    
    @Before
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
