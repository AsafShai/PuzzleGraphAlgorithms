package puzzleGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import solvingAlgorithms.PuzzleSolvingAlgorithm;
import utils.Util;

public class PuzzleSolver {
	private static final Scanner scanner = new Scanner(System.in);

	public static PuzzleNode getInitialBoardManual(int size) {
		System.out.println("Choose " + size + "numbers");
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
		List<PuzzleNode> randomBoards = new ArrayList<>();
		for (int i = 0; i < numberOfLoops; i++) {
			randomBoards.add(PuzzleSolver.getInitialBoardRandom(puzzleSize, randomSteps));
		}
		for (PuzzleSolvingAlgorithm solvingAlgorithm : puzzleSolvingAlgorithms) {
			analyseAlgorithm(solvingAlgorithm, randomBoards);
			System.out.println();
		}
	}

	private static void analyseAlgorithm(PuzzleSolvingAlgorithm puzzleSolvingAlgorithm, List<PuzzleNode> puzzleNodes) {
		long totalMilliseconds = 0;
		int totalPathLength = 0;
		int totalNodesDiscovered = 0;

		int numberOfLoops = puzzleNodes.size();

		for (PuzzleNode puzzleNode: puzzleNodes) {
			long startTime = System.currentTimeMillis();
			List<PuzzleNode> resultPath = puzzleSolvingAlgorithm.solvePuzzle(puzzleNode);
			long endTime = System.currentTimeMillis();
			totalMilliseconds += endTime - startTime;
			totalPathLength += resultPath.size();
			totalNodesDiscovered += puzzleSolvingAlgorithm.countOpenedNodes();
		}
		System.out.println("Algorithm: " + puzzleSolvingAlgorithm.getAlgorithmName());
		System.out.println("Average time milliseconds: " + totalMilliseconds / numberOfLoops);
		System.out.println("Average discovered nodes: " + totalNodesDiscovered /  numberOfLoops);
		System.out.println("Average path length: " + totalPathLength / numberOfLoops);
	}

}
