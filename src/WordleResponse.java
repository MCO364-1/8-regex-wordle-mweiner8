public class WordleResponse{
    char c;
    int index;
    LetterResponse resp;

    public WordleResponse(char c, int i, LetterResponse resp){
        this.c = c;
        index = i;
        this.resp = resp;
    }
}
