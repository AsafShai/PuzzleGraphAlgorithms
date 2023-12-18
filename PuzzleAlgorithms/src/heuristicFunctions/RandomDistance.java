package heuristicFunctions;

import java.util.Random;
import java.util.function.Function;

import puzzleGame.PuzzleNode;

public class RandomDistance implements Function<PuzzleNode, Integer> {
    private static final Random random = new Random();
    private static final int RANDOM_RANGE = 9;

	@Override
	public Integer apply(PuzzleNode puzzleNode) {
		return random.nextInt(RANDOM_RANGE);
	}
	
	@Override
	public String toString() {
		return "Random distance";
	}

}
