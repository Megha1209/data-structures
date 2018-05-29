package com.career_cup;

/**
 * You are given with an array of 1s and 0s. And you are given with an integer m, which signifies number of flips allowed.
 * <p>
 * find the position of zeros which when flipped will produce maximum continuous series of 1s.
 * <p>
 * e.g.
 * input:
 * arr={1 1 0 1 1 0 0 1 1 1 } m=1
 * output={1 1 1 1 1 0 0 1 1 1} position=2
 * <p>
 * arr={1 1 0 1 1 0 0 1 1 1 } m=2
 * output={1 1 0 1 1 1 1 1 1 1} position=5,6
 */
public class FindZerosToBeFlipped_Sliding_Window {

    public static void main(String[] args) {
        findZerosToBeFlippedToGetMaximumConsecutiveOnes(new int[]{1, 0, 0, 1, 1, 0, 1, 0, 1, 1}, 2);
    }

    public static void findZerosToBeFlippedToGetMaximumConsecutiveOnes(int[] arr, int maxZerosToBeFlipped) {

        int wL = 0; // Left Side of Window
        int wR = 0; // Right Side of Window
        int nZero = 0; // Current Zeros under window, this should always be less than maxZerosToBeFlipped
        int BEST_WINDOW_WIDTH = -1; // Best window at any point of time
        int best_wL = -1;
        int best_wR = -1;

        while (wR < arr.length) {

            // Expand the Widow to the right, and update the '0' Count
            if (nZero <= maxZerosToBeFlipped) {
                if (arr[wR] == 0) {
                    nZero += 1;
                }
                wR++;
            }

            // Shrink the window from the left and update the '0' Count
            if (nZero > maxZerosToBeFlipped) {
                if (arr[wL] == 0) {
                    nZero -= 1;
                }
                wL++;
            }

            // Let's check out bestWindowWidth
            if (wR - wL > BEST_WINDOW_WIDTH) {
                BEST_WINDOW_WIDTH = wR - wL;
                best_wL = wL;
                best_wR = wR;
            }
        }

        for (int i = best_wL; i < best_wR; i++) {
            if (arr[i] == 0) {
                System.out.print(i + ",");
            }
        }
        System.out.println();
        System.out.println("And the max consecutive ones after flipping is " + BEST_WINDOW_WIDTH);
    }
}
