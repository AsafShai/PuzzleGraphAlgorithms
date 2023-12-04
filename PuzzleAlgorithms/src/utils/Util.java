package utils;

import java.util.List;

import PuzzleGame.PuzzleNode;

public class Util {
	private static final int[][] SOLVED_15_BOARD = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
			{ 13, 14, 15, 0 } };

	private static final int[][] SOLVED_24_BOARD = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 },
			{ 16, 17, 18, 19, 20 }, { 21, 22, 23, 24, 0 } };
	
	
	public static PuzzleNode getSolvedBySize(int dimensions) {
		if (dimensions == 4)
			return new PuzzleNode(SOLVED_15_BOARD);
		else if (dimensions == 5)
			return new PuzzleNode(SOLVED_24_BOARD);
		int[][] emptyBoard = { { 0 } };
		return new PuzzleNode(emptyBoard);
	}
	
	public static void printPath(List<PuzzleNode> path) {
		for (PuzzleNode puzzleNode : path) {
			System.out.println(puzzleNode);
		}
	}
}
