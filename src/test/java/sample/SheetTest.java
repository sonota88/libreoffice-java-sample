package sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SheetTest {

    Sheet sut;

    /**
     * https://wiki.documentfoundation.org/EscapingCharacters#Entering_a_single_quotation_mark_as_the_first_character_of_a_formula
     */
    @Test
    public void test_unescapeFormula() throws Exception {
        assertEquals("fdsa", Sheet.unescapeFormula("fdsa"));
        assertEquals("12.34", Sheet.unescapeFormula("'12.34"));
        assertEquals("'12.34", Sheet.unescapeFormula("''12.34"));
    }

}
