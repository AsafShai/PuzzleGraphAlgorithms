package puzzleGame;

import java.util.ArrayList;
import java.util.List;

import heuristicFunctions.ManhattanDistance;
import heuristicFunctions.RandomDistance;
import heuristicFunctions.ZeroDistance;
import solvingAlgorithms.AStar;
import solvingAlgorithms.BFS;
import solvingAlgorithms.PuzzleSolvingAlgorithm;

public class Main {

	public static void main(String[] args) {
		runAlgorithms();
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
	 * 
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
