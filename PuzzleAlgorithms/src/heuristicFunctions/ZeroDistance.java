package heuristicFunctions;

import java.util.function.Function;

import puzzleGame.PuzzleNode;

public class ZeroDistance implements Function<PuzzleNode, Integer> {

	@Override
	public Integer apply(PuzzleNode t) {
		return 0;
	}
	
	@Override
	public String toString() {
		return "Zero distance";
	}

}
