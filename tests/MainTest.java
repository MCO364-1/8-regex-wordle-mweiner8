import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void properNameTrue() {
        String name = "Smith";
        assertTrue(Main.properName(name));
    }

    @Test
    void properNameFalse() {
        String name = "smith";
        assertFalse(Main.properName(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12","43.23","-34.5","+98.7","0","6","0.0230"})
    void integerTrue(String num){
        assertTrue(Main.integer(num));
    }

    @ParameterizedTest
    @ValueSource(strings = {"023","3.","abc"})
    void integerFalse(String num){
        assertFalse(Main.integer(num));
    }

    @ParameterizedTest
    @ValueSource(strings = {"father","mother","grandfather","grandmother","great-grandfather","great-grandmother","great-great-grandmother"})
    void ancestorTrue(String ancestor){
        assertTrue(Main.ancestor(ancestor));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aunt","great-aunt", "omerth"})
    void ancestorFalse(String ancestor){
        assertFalse(Main.ancestor(ancestor));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdfggfdsa","asdFggfDsa","AsdfggfdsA"})
    void palindromeTrue(String pal){
        assertTrue(Main.palindrome(pal));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdfggfdsb","asdFggfDa"})
    void palindromeFalse(String pal){
        assertFalse(Main.palindrome(pal));
    }
}