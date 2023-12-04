package PuzzleGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import HeuristicFunctions.ManhattanDistance;
import HeuristicFunctions.RandomDistance;
import HeuristicFunctions.ZeroDistance;
import solvingStrategies.AStarSolvingStrategy;
import solvingStrategies.BFSSolvingStrategy;
import solvingStrategies.SolvingStrartegy;

public class Main {
	static Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		List<SolvingStrartegy> algrorithms = getAlogrithmsList();
		int numberOfLoops = 5;
		int puzzleSize = 16;
		int randomSteps = 10;
		anaylyseAlgorithms2(algrorithms, numberOfLoops, puzzleSize, randomSteps);
		puzzleSize = 25;
		anaylyseAlgorithms2(algrorithms, numberOfLoops, puzzleSize, randomSteps);

		System.out.println("--------------------------------------");
		System.out.println("--------------------------------------");

		
		numberOfLoops = 50;
		puzzleSize = 16;
		anaylyseAlgorithms2(algrorithms, numberOfLoops, puzzleSize, randomSteps);
		puzzleSize = 25;
		anaylyseAlgorithms2(algrorithms, numberOfLoops, puzzleSize, randomSteps);
		
//		List<PuzzleNode> randomPuzzle15Nodes = new ArrayList<PuzzleNode>();
//		for (int i = 0; i < numberOfLoops; i++) {
//			randomPuzzle15Nodes.add(PuzzleSolver.getInitialBoardRandom(16, 10));
//		}
////		PuzzleNode puzzle15Node = PuzzleSolver.getInitialBoardRandom(16, 10);
////		analyseAlgorithms(puzzle15Node, numberOfLoops);
//		
//		System.out.println("----------------");
//		
//		List<PuzzleNode> randomPuzzle24Nodes = new ArrayList<PuzzleNode>();
//		for (int i = 0; i < numberOfLoops; i++) {
//			randomPuzzle24Nodes.add(PuzzleSolver.getInitialBoardRandom(25, 10));
//		}
//		
//		PuzzleNode puzzle24Node = PuzzleSolver.getInitialBoardRandom(25, 10);
//		analyseAlgorithms(puzzle24Node, numberOfLoops);
//		
//		
//		
//		System.out.println("----------------");
//		System.out.println("----------------");
//		System.out.println("----------------");
//
//		
//		
//		numberOfLoops = 50;
	}

	private static void anaylyseAlgorithms2(List<SolvingStrartegy> algrorithms, int numberOfLoops, int puzzleSize,
			int randomSteps) {
		for (SolvingStrartegy solvingStrartegy : algrorithms) {
			anaylyseAlgorithm2(solvingStrartegy, numberOfLoops, puzzleSize, randomSteps);
			System.out.println();
		}
		
	}

	private static void anaylyseAlgorithm2(SolvingStrartegy solvingStrartegy, int numberOfLoops, int puzzleSize,
			int randomSteps) {
		long totalMilliseconds = 0;
		int totalPathLength = 0;
		int totalNodesDiscovered = 0;
		
		for (int i = 0; i < numberOfLoops; i++) {
			PuzzleNode node = PuzzleSolver.getInitialBoardRandom(puzzleSize, randomSteps);
			long startTime = System.currentTimeMillis();
			List<PuzzleNode> resultPath = solvingStrartegy.solvePuzzle(node);
			long endTime = System.currentTimeMillis();
			totalMilliseconds += endTime - startTime;
			totalPathLength += resultPath.size();
			totalNodesDiscovered += solvingStrartegy.countOpenedNodes();
		}
		System.out.println("Algorithm: " + solvingStrartegy.getAlgorithmName());
		System.out.println("Average time milliseconds: " + totalMilliseconds / numberOfLoops);
		System.out.println("Average discoverd nodes: " + totalNodesDiscovered /  numberOfLoops);
		System.out.println("Average path length: " + totalPathLength / numberOfLoops);
		
	}

	/**
	 * 
	 */
	private static List<SolvingStrartegy> getAlogrithmsList() {
		List<SolvingStrartegy> algorithms = new ArrayList<SolvingStrartegy>();
		algorithms.add(new BFSSolvingStrategy());
		algorithms.add(new AStarSolvingStrategy(new ZeroDistance()));
		algorithms.add(new AStarSolvingStrategy(new ManhattanDistance()));
		algorithms.add(new AStarSolvingStrategy(new RandomDistance()));
		return algorithms;
	}

	/**
	 * @param node
	 */
	private static void analyseAlgorithms(PuzzleNode node, int numberOfLoops) {
		AStarSolvingStrategy aStar = new AStarSolvingStrategy(new ZeroDistance());
		BFSSolvingStrategy bfs = new BFSSolvingStrategy();
		analyseAlgorithm(node, bfs, numberOfLoops);
		
		System.out.println();
		analyseAlgorithm(node, aStar, numberOfLoops);
		
		System.out.println();
		aStar.setHeuristic(new ManhattanDistance());
		analyseAlgorithm(node, aStar, numberOfLoops);
		
		System.out.println();
		aStar.setHeuristic(new RandomDistance());
		analyseAlgorithm(node, aStar, numberOfLoops);
	}

	/**
	 * @param node
	 * @param alogrithm
	 */
	private static void analyseAlgorithm(PuzzleNode node, SolvingStrartegy algorithm, int numberOfLoops) {
		long totalMilliseconds = 0;
		int totalPathLength = 0;
		int totalNodesDiscovered = 0;
		
		for (int i = 0; i < numberOfLoops; i++) {
			long startTime = System.currentTimeMillis();
			List<PuzzleNode> resultPath = algorithm.solvePuzzle(node);
			long endTime = System.currentTimeMillis();
			totalMilliseconds += endTime - startTime;
			totalPathLength += resultPath.size();
			totalNodesDiscovered += algorithm.countOpenedNodes();
		}
		System.out.println("Algorithm: " + algorithm.getAlgorithmName());
		System.out.println("Average time milliseconds: " + totalMilliseconds / numberOfLoops);
		System.out.println("Average discoverd nodes: " + totalNodesDiscovered /  numberOfLoops);
		System.out.println("Average path length: " + totalPathLength / numberOfLoops);
	}

}
