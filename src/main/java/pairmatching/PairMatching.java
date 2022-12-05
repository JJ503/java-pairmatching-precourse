package pairmatching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PairMatching {
    private HashMap<List<String>, List<List<String>>> pairMatch;

    PairMatching() {
        pairMatch = new HashMap<>();
    }

    public List<List<String>> generatePairMatch(List<String> categories, CrewsByCourse crews) {
        int tryCount = 0;
        boolean isDuplicate = false;
        while (!isDuplicate && tryCount < 3) {
            tryCount += 1;
            isDuplicate = pairMatch(categories, crews);
        }

        if (isDuplicate) {
            ExceptionMessage.PAIR_MATCHING_IS_NOT_POSSIBLE.throwException();
        }

        return getPairMatchResult(categories);
    }

    private boolean pairMatch(List<String> categories, CrewsByCourse crews) {
        List<List<String>> newPairMatch = generatePairs(crews);
        boolean isDuplicate = isDuplicatePair(newPairMatch, categories);
        if (!isDuplicate) {
            pairMatch.put(categories, newPairMatch);
        }

        return isDuplicate;
    }

    public List<List<String>> getPairMatchResult(List<String> categories) {
        if (isAlreadyMatch(categories)) {
            return pairMatch.get(categories);
        }

        // 예외 발생
        return null;
    }

    public boolean isAlreadyMatch(List<String> categories) {
        return pairMatch.containsKey(categories);
    }

    private List<List<String>> generatePairs(CrewsByCourse crews) {
        return pairMatchToStringList(crews.shuffledCrews());
    }

    private List<List<String>> pairMatchToStringList(List<String> shuffledCrew) {
        List<List<String>> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledCrew.size() - 1; i += 2) {
            List<String> pair = new ArrayList<>();
            pair.add(shuffledCrew.get(i));
            pair.add(shuffledCrew.get(i+1));
            pairs.add(pair);
        }
        if (isOdd(shuffledCrew.size())) {
            pairs.get(pairs.size() - 1).add(shuffledCrew.get(shuffledCrew.size() - 1));
        }
        return pairs;
    }

    private boolean isOdd(int size) {
        return size % 2 != 0;
    }

    private boolean isDuplicatePair(List<List<String>> pairs, List<String> categories) {
        for (List<String> key : pairMatch.keySet()) {
            if (isSameLevel(key, categories) && checkDuplicatePair(pairs, pairMatch.get(key))) {
                return true;
            }
        }

        return false;
    }

    private boolean isSameLevel(List<String> key, List<String> categories) {
        String course = categories.get(0);
        String level = categories.get(1);
        return key.contains(course) && key.contains(level);
    }

    private boolean checkDuplicatePair(List<List<String>> pairs, List<List<String>> existPairs) {
         for (List<String> pair : threePairToTwoPairs(pairs)) {
             if (threePairToTwoPairs(existPairs).contains(pair)) {
                 return true;
             }
         }

         return false;
    }

    private List<List<String>> threePairToTwoPairs(List<List<String>> pairs) {
        List<String> threePair = pairs.get(pairs.size() - 1);
        if (threePair.size() == 3) {
            pairs.add(Arrays.asList(threePair.get(0), threePair.get(1)));
            pairs.add(Arrays.asList(threePair.get(1), threePair.get(2)));
            pairs.add(Arrays.asList(threePair.get(2), threePair.get(0)));

            return pairs;
        }

        return pairs;
    }
}
