import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Stevie K Halprin
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {

        // Integer to hold length of STR
        int STRlength = STR.length();
        // String to hold various temporary values
        String check;
        // String to hold String form of sequence in binary
        String toInt = "";
        // String to hold String form of STR in binary
        String BinSTR = "";

        // Go through every letter in the sequence, convert it to a binary value, then add it to toInt
        for (int i = 0; i < sequence.length(); i++) {
            // A = 00, C = 01, G = 10, T = 11
            if (sequence.charAt(i) == 'A') {
                check =  "00";
            }
            else if (sequence.charAt(i) == 'C') {
                check =  "01";
            }
            else if (sequence.charAt(i) == 'G') {
                check =  "10";
            }
            else {
                check =  "11";
            }
            // Add the String binary value to toInt
            toInt = toInt + check;
        }

        // Map to hold list of indexes that each string of letters in the sequense is found at
        // at index Integer.parseInt(binary string of given char sequence)
        ArrayList<Integer>[] map = new ArrayList[(int)Math.pow(4, STRlength)];

        // Initialize all lists in map
        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList();
        }

        // Add the index of every sequence of letters of length STRlength in the sequence to the map
        for (int i = 0; i < toInt.length() - STRlength * 2; i+=2) {
            check = toInt.substring(i, i + STRlength * 2);
            map[Integer.parseInt(check, 2)].add(i / 2);
        }

        // Set BinSTR to the String form of STR in binary
        for (int i = 0; i < STRlength; i++) {
            // A = 00, C = 01, G = 10, T = 11
            if (STR.charAt(i) == 'A') {
                check =  "00";
            }
            else if (STR.charAt(i) == 'C') {
                check =  "01";
            }
            else if (STR.charAt(i) == 'G') {
                check =  "10";
            }
            else {
                check =  "11";
            }
            BinSTR = BinSTR + check;
        }

        // Longest consecutive repeat of STR
        int maxLength = 0;
        // Length of current repeat of STR
        int currentLength = 0;
        // Next index in map[STR]
        int nextIndex = 1;
        // Current index in map[STR]
        int thisIndex = 0;
        // ArrayList representing every index STR is found at in String
        ArrayList<Integer> STRindexes = map[Integer.parseInt(BinSTR, 2)];

        while (thisIndex < STRindexes.size()) {
            if (currentLength == 0) {
                currentLength++;
            }
            if (nextIndex < STRindexes.size() && STRindexes.get(thisIndex) + STRlength == STRindexes.get(nextIndex)) {
                currentLength++;
            }
            else {
                if (maxLength < currentLength) {
                    maxLength = currentLength;
                }
            }
            thisIndex = nextIndex;
            nextIndex++;
        }


        return maxLength;
    }
}
