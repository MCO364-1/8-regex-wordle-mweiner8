import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class RegexMethodsTest {

    @Test
    void properNameTrue() {
        String name = "Smith";
        assertTrue(RegexMethods.properName(name));
    }

    @Test
    void properNameFalse() {
        String name = "smith";
        assertFalse(RegexMethods.properName(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12","43.23","-34.5","+98.7","0","6","0.0230"})
    void integerTrue(String num){
        assertTrue(RegexMethods.integer(num));
    }

    @ParameterizedTest
    @ValueSource(strings = {"023","3.","abc"})
    void integerFalse(String num){
        assertFalse(RegexMethods.integer(num));
    }

    @ParameterizedTest
    @ValueSource(strings = {"father","mother","grandfather","grandmother","great-grandfather","great-grandmother","great-great-grandmother"})
    void ancestorTrue(String ancestor){
        assertTrue(RegexMethods.ancestor(ancestor));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aunt","great-aunt", "omerth"})
    void ancestorFalse(String ancestor){
        assertFalse(RegexMethods.ancestor(ancestor));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdfggfdsa","asdFggfDsa","AsdfggfdsA"})
    void palindromeTrue(String pal){
        assertTrue(RegexMethods.palindrome(pal));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdfggfdsb","asdFggfDa"})
    void palindromeFalse(String pal){
        assertFalse(RegexMethods.palindrome(pal));
    }

    @Test
    void wordleMatchesBasic() throws IOException {
        List<List<WordleResponse>> totalList = new ArrayList<>();

        List<WordleResponse> res1 = new ArrayList<>();
        res1.add(new WordleResponse('t', 0, LetterResponse.WRONG_LETTER));
        res1.add(new WordleResponse('r', 1, LetterResponse.WRONG_LETTER));
        res1.add(new WordleResponse('a', 2, LetterResponse.WRONG_LOCATION));
        res1.add(new WordleResponse('i', 3, LetterResponse.WRONG_LOCATION));
        res1.add(new WordleResponse('n', 4, LetterResponse.WRONG_LETTER));
        totalList.add(res1);

        List<WordleResponse> res2 = new ArrayList<>();
        res2.add(new WordleResponse('p', 0, LetterResponse.WRONG_LETTER));
        res2.add(new WordleResponse('a', 1, LetterResponse.WRONG_LOCATION));
        res2.add(new WordleResponse('i', 2, LetterResponse.CORRECT_LOCATION));
        res2.add(new WordleResponse('r', 3, LetterResponse.WRONG_LETTER));
        res2.add(new WordleResponse('s', 4, LetterResponse.WRONG_LETTER));
        totalList.add(res2);

        List<WordleResponse> res3 = new ArrayList<>();
        res3.add(new WordleResponse('a', 0, LetterResponse.CORRECT_LOCATION));
        res3.add(new WordleResponse('s', 1, LetterResponse.WRONG_LETTER));
        res3.add(new WordleResponse('i', 2, LetterResponse.CORRECT_LOCATION));
        res3.add(new WordleResponse('d', 3, LetterResponse.CORRECT_LOCATION));
        res3.add(new WordleResponse('e', 4, LetterResponse.CORRECT_LOCATION));
        totalList.add(res3);

        List<String> words = RegexMethods.wordleMatches(totalList);
        assertEquals("abide", words.get(0));
    }

    @Test
    void wordleMatchesWithDoubledLetters() throws IOException {
        List<List<WordleResponse>> totalList = new ArrayList<>();

        List<WordleResponse> res1 = new ArrayList<>();
        res1.add(new WordleResponse('t', 0, LetterResponse.WRONG_LETTER));
        res1.add(new WordleResponse('r', 1, LetterResponse.WRONG_LETTER));
        res1.add(new WordleResponse('a', 2, LetterResponse.WRONG_LETTER));
        res1.add(new WordleResponse('i', 3, LetterResponse.WRONG_LOCATION));
        res1.add(new WordleResponse('n', 4, LetterResponse.WRONG_LOCATION));
        totalList.add(res1);

        List<WordleResponse> res2 = new ArrayList<>();
        res2.add(new WordleResponse('p', 0, LetterResponse.WRONG_LETTER));
        res2.add(new WordleResponse('i', 1, LetterResponse.WRONG_LOCATION));
        res2.add(new WordleResponse('n', 2, LetterResponse.WRONG_LOCATION));
        res2.add(new WordleResponse('c', 3, LetterResponse.WRONG_LOCATION));
        res2.add(new WordleResponse('h', 4, LetterResponse.WRONG_LETTER));
        totalList.add(res2);

        List<WordleResponse> res3 = new ArrayList<>();
        res3.add(new WordleResponse('C', 0, LetterResponse.WRONG_LOCATION));
        res3.add(new WordleResponse('L', 1, LetterResponse.WRONG_LETTER));
        res3.add(new WordleResponse('I', 2, LetterResponse.CORRECT_LOCATION));
        res3.add(new WordleResponse('N', 3, LetterResponse.CORRECT_LOCATION));
        res3.add(new WordleResponse('G', 4, LetterResponse.CORRECT_LOCATION));
        totalList.add(res3);

        List<String> words = RegexMethods.wordleMatches(totalList);
        assertEquals("icing", words.get(0));
    }
}