package HeuristicFunctions;

import java.util.Random;
import java.util.function.Function;

import PuzzleGame.PuzzleNode;

public class RandomDistance implements Function<PuzzleNode, Integer> {
    private static final Random random = new Random();
    private static final int RANDOM_RANGE = 10;

	@Override
	public Integer apply(PuzzleNode puzzleNode) {
		return random.nextInt(RANDOM_RANGE);
	}
	
	@Override
	public String toString() {
		return "Random distance";
	}

}
