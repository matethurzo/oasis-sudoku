/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.oasis.sudoku;

import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Mate Thurzo
 */
@Service
public class SudokuSolver {

    private boolean isValid(int[][] board, int row, int col, int num) {
        return isRowValid(board, row, num) && isColValid(board, col, num) && isBoxValid(board, row, col, num);
    }

    private boolean isRowValid(int[][] board, int row, int num) {
        for (int j = 0; j < board[row].length; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        return true;
    }

    private static boolean isColValid(int[][] board, int col, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        return true;
    }

    private static boolean isBoxValid(int[][] board, int row, int col, int num) {
        int rowAligned = row - row % 3;
        int colALigned = col - col % 3;

        for (int i = rowAligned; i < rowAligned + 3; i++) {
            for (int j = colALigned; j < colALigned + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public int[][] solve(int[][] board) {
        int[][] tempBoard = Arrays.copyOf(board, board.length);

        if (isValidBoard(tempBoard)) {
            return tempBoard;
        } else {
            return board;
        }
    }

    private boolean isValidBoard(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                int item = board[row][col];

                if (item == 0) {
                    for (int num = 1; num <= board.length; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (isValidBoard(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

}
