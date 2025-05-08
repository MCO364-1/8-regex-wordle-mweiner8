import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMethods {

    private static Pattern pattern;
    private static Matcher matcher;

    public static boolean properName(String s){
        pattern = Pattern.compile("[A-Z][a-z]*");
        matcher = pattern.matcher(s);
        return matcher.find();
    }

    public static boolean integer(String s){
        pattern = Pattern.compile("^[+-]?[1-9]\\d*$|^[+-]?[1-9]\\d*\\.\\d+$|^[+-]?0\\.\\d+|^0$");
        matcher = pattern.matcher(s);
        return matcher.find();
    }

    public static boolean ancestor(String s){
        pattern = Pattern.compile("(?:(?:great-)*grand)?(?:father|mother)");
        matcher = pattern.matcher(s);
        return matcher.find();
    }

    public static boolean palindrome(String s){
        String lowercase = s.toLowerCase();
        pattern = Pattern.compile("(?<one>[a-zA-z])(?<two>[a-zA-z])(?<three>[a-zA-z])(?<four>[a-zA-z])(?<five>[a-zA-z])(\\k<five>)(\\k<four>)(\\k<three>)(\\k<two>)(\\k<one>)");
        matcher = pattern.matcher(lowercase);
        return matcher.find();
    }

    public static List<String> wordleMatches(List<List<WordleResponse>> responses) throws IOException {
        File wordList = new File("wordle-words");
        FileReader reader = new FileReader(wordList);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String word;
        StringBuilder allWords = new StringBuilder();
        while ((word = bufferedReader.readLine()) != null){
            allWords.append(word);
            allWords.append("\n");
        }
        bufferedReader.close();
        //System.out.println(allWords);

        List<String> list = new ArrayList<>();
        int index;
        char lowerC;
        WordleResponse wr;
        ArrayList<Integer> skipIndex = new ArrayList<>();
        skipIndex.add(6);
        skipIndex.add(6);
        skipIndex.add(6);
        WordleResponse wrj;
        WordleResponse wrk;
        int counter = 0;
        for (List<WordleResponse> l : responses){
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 4; j++) {
                    wrj = l.get(j);
                    for (int k = j + 1; k < 5; k++) {
                        wrk = l.get(k);
                        if (wrj.c == wrk.c){
                            if (wrj.resp == LetterResponse.WRONG_LETTER && (wrk.resp == LetterResponse.CORRECT_LOCATION || wrk.resp == LetterResponse.WRONG_LOCATION)){
                                skipIndex.set(counter++, wrj.index);
                            } else if (wrk.resp == LetterResponse.WRONG_LETTER && (wrj.resp == LetterResponse.CORRECT_LOCATION || wrj.resp == LetterResponse.WRONG_LOCATION)) {
                                skipIndex.set(counter++, wrk.index);
                            }
                        }
                    }
                }
                wr = l.get(i);
                index = wr.index;
                lowerC = Character.toLowerCase(wr.c);
                System.out.println(lowerC);
                if (!skipIndex.contains(i)){
                    if (wr.resp == LetterResponse.WRONG_LETTER) {
                        pattern = Pattern.compile("(?!.*[a-z]*[" + lowerC + "][a-z]*).*");
                        matcher = pattern.matcher(allWords);
                        list.clear();
                        while (matcher.find()){
                            word = matcher.group();
                            if (word.length() == 5){
                                list.add(word);
                            }
                        }
                        allWords.delete(0, allWords.length());
                        for (String s : list){
                            allWords.append(s);
                            allWords.append("\n");
                        }
                    } else if (wr.resp == LetterResponse.CORRECT_LOCATION) {
                        pattern = Pattern.compile("(?=.*[a-z]{" + index + "}[" + lowerC + "][a-z]{" + (4 - index) + "}).*");
                        matcher = pattern.matcher(allWords);
                        list.clear();
                        while (matcher.find()){
                            word = matcher.group();
                            if (word.length() == 5){
                                list.add(word);
                            }
                        }
                        allWords.delete(0, allWords.length());
                        for (String s : list){
                            allWords.append(s);
                            allWords.append("\n");
                        }
                    } else {
                        pattern = Pattern.compile("(?!.*[a-z]{" + index + "}[" + lowerC + "][a-z]{" + (4 - index) + "}).*(?=.*[a-z]*[" + lowerC + "][a-z]*).*");
                        matcher = pattern.matcher(allWords);
                        list.clear();
                        while (matcher.find()){
                            word = matcher.group();
                            if (word.length() == 5){
                                list.add(word);
                            }
                        }
                        allWords.delete(0, allWords.length());
                        for (String s : list){
                            allWords.append(s);
                            allWords.append("\n");
                        }
                    }
                    skipIndex.set(0, 6);
                    skipIndex.set(1, 6);
                    skipIndex.set(2, 6);
                    counter = 0;
                }
                System.out.println(list);
            }
        }

        return list;
    }
}