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

    // Array holding the values of the letters in the sequence at letterVals[ASCII value of letter]
    private static int[] letterVals = new int[26];


    public static int STRCount(String sequence, String STR) {

        // Set the values of the letters in letterVals
        // A = 0, C = 1, G = 2, T = 3
        letterVals[0] = 0;
        letterVals[2] = 1;
        letterVals[6] = 2;
        letterVals[19] = 3;

        // Integer to hold length of STR
        int STRlength = STR.length();
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
                // If the current String is a match, add 1 to the currentStreak
                currentStreak++;
                // String to hold the next few characters to skip (not check for STR)
                String nextSTR = "";
                // Make sure nextSTR won't go out of range of sequence
                if (i + STRlength < sequence.length()) {
                    // Set nextSTR to the next few characters to skip
                    nextSTR = sequence.substring(i + 1, i + STRlength);
                }
                // Increment i according to the length of nextSTR
                i += STRlength - 1;
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
        // Return the longest streak of STRs in the sequence
        return longestStreak;
    }


    // Return the numerical value of the inputted char
    public static int getCharVal(char c) {
        // A = 0, C = 1, G = 2, T = 3
        return letterVals[(int)c % 65 % 32];
    }
}
