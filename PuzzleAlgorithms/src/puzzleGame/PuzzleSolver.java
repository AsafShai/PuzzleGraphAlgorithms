package puzzleGame;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import solvingAlgorithms.PuzzleSolvingAlgorithm;
import utils.Util;

public class PuzzleSolver {
	private static final Scanner scanner = new Scanner(System.in);

	public static PuzzleNode getInitialBoardManual(int size) {
		int dimensions = (int) (Math.sqrt(size));
		int[][] board = new int[dimensions][dimensions];
		for (int i = 0; i < dimensions; i++) {
			for (int j = 0; j < dimensions; j++) {
				board[i][j] = scanner.nextInt();
			}
		}
		return new PuzzleNode(board);
	}

	public static PuzzleNode getInitialBoardRandom(int size, int randomMoves) {
		PuzzleNode resultPuzzleNode =  Util.getSolvedBySize((int) Math.sqrt(size));
		Random random = new Random();
		for (int i = 0; i < randomMoves; i++) {
			List<PuzzleNode> neighbors = resultPuzzleNode.getNeighbors();
			resultPuzzleNode = neighbors.get(random.nextInt(neighbors.size()));
		}
		return resultPuzzleNode;
	}

	public static void analyseAlgorithms(List<PuzzleSolvingAlgorithm> puzzleSolvingAlgorithms, int numberOfLoops, int puzzleSize,
										 int randomSteps) {
		for (PuzzleSolvingAlgorithm solvingStrartegy : puzzleSolvingAlgorithms) {
			analyseAlgorithm(solvingStrartegy, numberOfLoops, puzzleSize, randomSteps);
			System.out.println();
		}
	}

	private static void analyseAlgorithm(PuzzleSolvingAlgorithm puzzleSolvingAlgorithms, int numberOfLoops, int puzzleSize,
										   int randomSteps) {
		long totalMilliseconds = 0;
		int totalPathLength = 0;
		int totalNodesDiscovered = 0;

		for (int i = 0; i < numberOfLoops; i++) {
			PuzzleNode node = PuzzleSolver.getInitialBoardRandom(puzzleSize, randomSteps);
			long startTime = System.currentTimeMillis();
			List<PuzzleNode> resultPath = puzzleSolvingAlgorithms.solvePuzzle(node);
			long endTime = System.currentTimeMillis();
			totalMilliseconds += endTime - startTime;
			totalPathLength += resultPath.size();
			totalNodesDiscovered += puzzleSolvingAlgorithms.countOpenedNodes();
		}
		System.out.println("Algorithm: " + puzzleSolvingAlgorithms.getAlgorithmName());
		System.out.println("Average time milliseconds: " + totalMilliseconds / numberOfLoops);
		System.out.println("Average discovered nodes: " + totalNodesDiscovered /  numberOfLoops);
		System.out.println("Average path length: " + totalPathLength / numberOfLoops);
	}
}
