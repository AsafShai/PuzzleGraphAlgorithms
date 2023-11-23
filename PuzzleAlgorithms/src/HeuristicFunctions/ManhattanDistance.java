package HeuristicFunctions;

import java.util.function.Function;

import PuzzleGame.PuzzleNode;

public class ManhattanDistance implements Function<PuzzleNode, Integer>{

	@Override
	public Integer apply(PuzzleNode puzzleNode) {
		int distance = 0;
		for (int i = 0; i < puzzleNode.getBoard().length; i++) {
			for (int j = 0; j < puzzleNode.getBoard()[i].length; j++) {
				int value = puzzleNode.getBoard()[i][j];
				if (value != 0) { // Skip the blank tile
					int goalRow = (value - 1) / puzzleNode.getBoard().length;
					int goalCol = (value - 1) % puzzleNode.getBoard().length;
					distance += Math.abs(i - goalRow) + Math.abs(j - goalCol);
				}
			}
		}
		return distance;
	}

}
