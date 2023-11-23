package HeuristicFunctions;

import java.util.function.Function;

import PuzzleGame.PuzzleNode;

public class MisplacedTiles implements Function<PuzzleNode, Integer> {

	@Override
	public Integer apply(PuzzleNode puzzleNode) {
		int misplaced = 0;
		for (int i = 0; i < puzzleNode.getBoard().length; i++) {
			for (int j = 0; j < puzzleNode.getBoard()[i].length; j++) {
				int value = puzzleNode.getBoard()[i][j];
				int goalRow = (value - 1) / puzzleNode.getBoard().length;
				int goalCol = (value - 1) % puzzleNode.getBoard().length;
				if (goalRow != i || goalCol != j)
					misplaced++;
			}
		}
		return misplaced;
	}

}
