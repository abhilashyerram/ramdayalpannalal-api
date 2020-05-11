package org.bpcl.ramdayal.ramdayalpannalal;

/*  Problem Statement:

Given a list of reviews, a list of keywords and an integer k. Find the most popular k keywords in order of most to least frequently mentioned.

The comparison of strings is case-insensitive.
Multiple occurances of a keyword in a review should be considred as a single mention.
If keywords are mentioned an equal number of times in reviews, sort alphabetically.

Example 1:

Input:
k = 2
keywords = ["anacell", "cetracular", "betacellular"]
reviews = [
  "Anacell provides the best services in the city",
  "betacellular has awesome services",
  "Best services provided by anacell, everyone should use anacell",
]

Output:
["anacell", "betacellular"]

Explanation:
"anacell" is occuring in 2 different reviews and "betacellular" is only occuring in 1 review.

Example 2:

Input:
k = 2
keywords = ["anacell", "betacellular", "cetracular", "deltacellular", "eurocell"]
reviews = [
  "I love anacell Best services; Best services provided by anacell",
  "betacellular has great services",
  "deltacellular provides much better services than betacellular",
  "cetracular is worse than anacell",
  "Betacellular is better than deltacellular.",
]

Output:
["betacellular", "anacell"]

Explanation:
"betacellular" is occuring in 3 different reviews. "anacell" and "deltacellular" are occuring in 2 reviews, but "anacell" is lexicographically smaller.

*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopNKeyWords {

    public static void main(String[] args) {
       int k = 3;
        List<String> keywords = Arrays.asList("anacell", "betacellular", "cetracular", "deltacellular", "eurocell");
        List<String> reviews = Arrays.asList(
                "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services",
                "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell",
                "Betacellular is better than deltacellular.");

        System.out.println(topFrequentWords(reviews,keywords, k));
    }

    private static List<String> topFrequentWords(List<String> reviews, List<String> keywords, int noOfTopKeysToReturn){
        Map<String, Integer> keyCountMap = new HashMap<>();
        reviews.forEach(review -> {
            keywords.forEach(keyword -> {
                if(review.toLowerCase().contains(keyword.toLowerCase())){
                    keyCountMap.put(keyword, keyCountMap.getOrDefault(keyword,0)+1);
                }
            });
        });

        List<String> topKeys = new ArrayList<>(keyCountMap.keySet());
        Collections.sort(topKeys, (a,b) -> (keyCountMap.get(a).equals(keyCountMap.get(b)) ? a.compareTo(b) : keyCountMap.get(b) - keyCountMap.get(a)));

        return topKeys.subList(0, noOfTopKeysToReturn);
    }

}
