package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 * @author neeraj on 2019-05-15
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NQueensProblem {

    public static void main(String[] args) {
        solveNQueensProblem(4);
    }

    public static void solveNQueensProblem(int boardSize) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> columnPlacements = new ArrayList<>();
        int currentRow = 0;
        solveNQueensProblemRecursively(boardSize, currentRow, columnPlacements, results);
//        LogUtil.printList(results);
        for (List<Integer> placement : results) {
            System.out.println("===============RESULT START========================");
            for (int i = 1; i <= placement.size(); i++) {
                System.out.println(i + " Row =====> " + (placement.get(i - 1) + 1));
            }
            System.out.println("===============RESULT END========================");
        }
    }

    private static void solveNQueensProblemRecursively(int boardSize, int currentRow, List<Integer> columnPlacements,
                                                       List<List<Integer>> results) {
        if (boardSize == currentRow) {
            /*
              All n queens have been placed in the n rows. We have
              reached the bottom of our recursion. We can now add
              the colPlacements to the result.
            */
            results.add(new ArrayList<>(columnPlacements));
        } else {
           /*
              Try ALL columns in the current row that we are making
              a choice on.
              The colPlacements list will hold the column we place a
              queen for the i'th row.
              So if I have [ 1, 3, 0, 2 ] that means:
              It is a 4 x 4 board.
              row index 0 has a queen placed in column index 1
              row index 1 has a queen placed in column index 3
              row index 2 has a queen placed in column index 0
              row index 3 has a queen placed in column index 2
            */
            for (int col = 0; col < boardSize; col++) {
                columnPlacements.add(col);
                if (isValidPlacement(columnPlacements)) {
                    solveNQueensProblemRecursively(boardSize, currentRow + 1, columnPlacements, results);
                }
                columnPlacements.remove(columnPlacements.size() - 1);
            }
        }
    }

    /*
      Check if a column placement that we just put in the columnPlacements
      list is actually valid to recurse on
    */
    private static boolean isValidPlacement(List<Integer> columnPlacements) {
        /*
          rowWeAreValidatingOn is the row that we just placed a queen on
          and we need to validate the placement
        */
        int rowWeAreValidatingOn = columnPlacements.size() - 1;

        // Now we will validate this with all previous queen.
        for (int ithQueenRow = 0; ithQueenRow < rowWeAreValidatingOn; ithQueenRow++) {

            int absoluteColumnDistance = Math.abs(columnPlacements.get(ithQueenRow) - columnPlacements.get(rowWeAreValidatingOn));

             /*
              1.) If the absolute difference in columns is 0 then we placed in a column being
              attacked by the i'th queen.
                absoluteColumnDistance == 0
              2.) If the absolute difference in columns equals the distance in rows from the
              i'th queen we placed then the queen we just placed is attacked diagonally.
                absoluteColumnDistance == rowWeAreValidatingOn - i
              For Constraint #2 imagine this:
              [
                ". . Q .",  <--- row 0 (Queen 1)
                "Q . . .",  <--- row 1 (Queen 2)
                ". Q . .",  <--- row 2 (Queen 3)
                ". . . ."
              ]
              Absolute Column Distance Between Queen 2 & 3 == 1.
              Queen 2 is in col 0, Queen 3 is in col 1. 1 - 0 = 1.
              Absolute Row Distance Between Queen 2 & 3 == 1
              Queen 2 is in row 1, Queen 3 is in row 2. 2 - 1 = 1.
            */
            if (absoluteColumnDistance == 0 || absoluteColumnDistance == rowWeAreValidatingOn - ithQueenRow) {
                return false;
            }
        }
        return true;
    }
}
