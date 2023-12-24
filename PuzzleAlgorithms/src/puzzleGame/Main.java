package puzzleGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import heuristicFunctions.ManhattanDistance;
import heuristicFunctions.RandomDistance;
import heuristicFunctions.ZeroDistance;
import solvingAlgorithms.AStar;
import solvingAlgorithms.BFS;
import solvingAlgorithms.PuzzleSolvingAlgorithm;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
//		singlePuzzleRun();  // Running once on a single puzzle (manual or random)
		runAlgorithms(); // Running the algorithms based on the parameters from the assignment
	}

	private static void singlePuzzleRun() {
		PuzzleNode startNode = manageStartingBoard();
		if (startNode == null)
			return;
		analyseSingleNode(startNode);
	}

	private static PuzzleNode manageStartingBoard() {
		System.out.println("Manual or Random?");
		String inputType = scanner.next();
		System.out.println("Choose puzzle size");
		int puzzleSize = scanner.nextInt();
		if (puzzleSize != 25 && puzzleSize != 16) return null;
		if (inputType.equalsIgnoreCase("Manual")) {
			return PuzzleSolver.getInitialBoardManual(puzzleSize);
		} else if (inputType.equalsIgnoreCase("Random")) {
			System.out.println("Choose number of random steps");
			int randomMoves = scanner.nextInt();
			return PuzzleSolver.getInitialBoardRandom(puzzleSize, randomMoves);
		}
		return null;
	}

	private static void analyseSingleNode(PuzzleNode startNode) {
		List<PuzzleSolvingAlgorithm> algorithms = getAlogrithmsList();
		for (PuzzleSolvingAlgorithm puzzleSolvingAlgorithm: algorithms) {
			long totalMilliseconds = 0;
			int totalPathLength = 0;
			int totalNodesDiscovered = 0;
			long startTime = System.currentTimeMillis();
			List<PuzzleNode> resultPath = puzzleSolvingAlgorithm.solvePuzzle(startNode);
			long endTime = System.currentTimeMillis();
			totalMilliseconds += endTime - startTime;
			totalPathLength += resultPath.size();
			totalNodesDiscovered += puzzleSolvingAlgorithm.countOpenedNodes();
			System.out.println("Algorithm: " + puzzleSolvingAlgorithm.getAlgorithmName());
			System.out.println("Average time milliseconds: " + totalMilliseconds);
			System.out.println("Average discovered nodes: " + totalNodesDiscovered);
			System.out.println("Average path length: " + totalPathLength);
		}
	}

	private static void runAlgorithms() {
		List<PuzzleSolvingAlgorithm> algorithms = getAlogrithmsList();
		int numberOfLoops = 5;
		int puzzleSize = 16;
		int randomSteps = 10;
		PuzzleSolver.analyseAlgorithms(algorithms, numberOfLoops, puzzleSize, randomSteps);
		puzzleSize = 25;
		PuzzleSolver.analyseAlgorithms(algorithms, numberOfLoops, puzzleSize, randomSteps);

		System.out.println("--------------------------------------");
		System.out.println("--------------------------------------");


		numberOfLoops = 50;
		puzzleSize = 16;
		PuzzleSolver.analyseAlgorithms(algorithms, numberOfLoops, puzzleSize, randomSteps);
		puzzleSize = 25;
		PuzzleSolver.analyseAlgorithms(algorithms, numberOfLoops, puzzleSize, randomSteps);
	}

	/**
	 * returns a list of all the algorithms available
	 */
	private static List<PuzzleSolvingAlgorithm> getAlogrithmsList() {
		List<PuzzleSolvingAlgorithm> algorithms = new ArrayList<>();
		algorithms.add(new BFS());
		algorithms.add(new AStar(new ZeroDistance()));
		algorithms.add(new AStar(new ManhattanDistance()));
		algorithms.add(new AStar(new RandomDistance()));
		return algorithms;
	}

}
