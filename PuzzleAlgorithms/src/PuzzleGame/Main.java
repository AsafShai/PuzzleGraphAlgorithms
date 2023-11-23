package PuzzleGame;
import java.util.List;
import java.util.Scanner;

import HeuristicFunctions.ManhattanDistance;
import HeuristicFunctions.MisplacedTiles;
import HeuristicFunctions.ZeroDistance;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	
	public static void printPath(List<PuzzleNode> path) {
//		for (PuzzleNode puzzleNode : path) {
//			System.out.println(puzzleNode);
//		}
		System.out.println(path.size());
	}

	public static void main(String[] args) {
       PuzzleNode node = PuzzleSolver.getInitialBoardRandom(25, 30);
       System.out.println(node);
       List<PuzzleNode> path = PuzzleSolver.aStar(node, new MisplacedTiles());
       printPath(path);
       PuzzleNode node2 = new PuzzleNode(node.getBoard());
       PuzzleNode node3 = new PuzzleNode(node.getBoard());
       List<PuzzleNode> path2 = PuzzleSolver.aStar(node2, new ManhattanDistance());
       printPath(path2);
       List<PuzzleNode> path3 = PuzzleSolver.aStar(node3, new ZeroDistance());
       printPath(path3);

	}
	
}
