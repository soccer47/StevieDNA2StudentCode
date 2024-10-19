import java.util.ArrayList;

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
        // Int to hold various temporary values
        int check;
        // Int to hold numerically converted sequence of length STRlength to be compared to the STR
        int posSTR = 0;
        // Int to hold numerical value of STR
        int numSTR = 0;
        // Number of unique characters in sequence
        int R = 4;
        // Length of current streak of STRs
        int currentStreak = 0;
        // Length of longest streak of STRs
        int longestStreak = 0;
        // Long representing power of R that first digit in sequence being checked will be multiplied by
        int maxR = (int)Math.pow(R, STRlength - 1);
        // ArrayList holding all the indexes where the STR appears in the sequence
        ArrayList<Integer> indexes = new ArrayList<>();


        // Set numSTR to the numerical value of STR
        for (int i = 0; i < STRlength; i++) {
            // Multiply numSTR by the radix, then add the new character to numSTR
            numSTR = numSTR * R + getCharVal(STR.charAt(i));
        }

        // Go through every letter in the sequence, convert it to an integer, then check to see if it matches STR
        for (int i = 0; i < sequence.length(); i++) {
            // Cut off the first integer of posSTR if STRlength digits have been added
            // Multiply the remaining digits by R to shift their indexes over by 1 place
            // No need to mod by prime number, because largest possible value of posSTR isn't high enough to overflow
            posSTR = posSTR - (posSTR / maxR) * maxR;

            // Add the next character's integer in the sequence to posSTR
            posSTR = posSTR * R + getCharVal(sequence.charAt(i));

            // If the number matches the numerical value of STR and their strings match...
            // No need to compare String value of posSTR to STR because there's no overflow
            if (posSTR == numSTR) {
                // Add the index of the STR match to the ArrayList
                indexes.add(i - STRlength);
                // If the current String is a match, add 1 to the currentStreak
                currentStreak++;
                // String to hold the next few characters to skip (not check for STR)
                String nextSTR = "";
                // Increment i according to the length of nextSTR
                i += STRlength - 1;
                // Make sure nextSTR won't go out of range of sequence
                if (i + STRlength < sequence.length()) {
                    // Set nextSTR to the next few characters to skip
                    nextSTR = sequence.substring(i - STRlength, i + 1);
                }
                // Skip the next few characters to see if the next possible STR is also the STR
                for (int j = 0; j < nextSTR.length(); j++) {
                    // Cut off the first integer of posSTR if STRlength digits have been added
                    // Multiply the remaining digits by R to shift their indexes over by 1 place
                    posSTR = posSTR - (posSTR / maxR) * maxR;
                    // Add the next character's integer in the sequence to posSTR
                    posSTR = posSTR * R + getCharVal(nextSTR.charAt(j));
                }
                // If the currentStreak is greater than the longestStreak, update longestStreak
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                }
            }
            // Otherwise end the streak of STRs
            else {
                currentStreak = 0;
            }
        }

        // Print out the indexes where the STR appears in the sequence
        System.out.println(longestStreak);

        // Return the longest streak of STRs in the sequence
        return longestStreak;
    }


    // Return the numerical value of the inputted char
    public static int getCharVal(char c) {
        // A = 0, C = 1, G = 2, T = 3
        if (c == 'A') {
            return 0;
        }
        else if (c == 'C') {
            return 1;
        }
        else if (c == 'G') {
            return 2;
        }
        else {
            return 3;
        }
    }
}
