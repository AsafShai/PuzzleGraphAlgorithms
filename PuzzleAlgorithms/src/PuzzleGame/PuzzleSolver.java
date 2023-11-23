package PuzzleGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

import utils.Util;

public class PuzzleSolver {
	private static Scanner scanner = new Scanner(System.in);

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
		PuzzleNode solvedPuzzleNode = Util.getSolvedBySize((int) Math.sqrt(size));

		PuzzleNode resultPuzzleNode = solvedPuzzleNode;
		Random random = new Random();
		for (int i = 0; i < randomMoves; i++) {
			List<PuzzleNode> neighbors = resultPuzzleNode.getNeighbors();
			resultPuzzleNode = neighbors.get(random.nextInt(neighbors.size()));
		}
		return resultPuzzleNode;
	}

	public static List<PuzzleNode> bfs(PuzzleNode startNode) {
		Queue<PuzzleNode> queue = new LinkedList<>();
		Set<PuzzleNode> visited = new HashSet<>();
		Map<PuzzleNode, PuzzleNode> parentMap = new HashMap<>();

		queue.add(startNode);
		visited.add(startNode);
		parentMap.put(startNode, null);

		while (!queue.isEmpty()) {
			PuzzleNode currentNode = queue.poll();
			if (currentNode.equals(Util.getSolvedBySize(currentNode.getBoard().length))) {
				return reconstructPath(parentMap, currentNode);
			}

			List<PuzzleNode> neighbors = currentNode.getNeighbors();
			for (PuzzleNode neighbor : neighbors) {
				if (!visited.contains(neighbor)) {
					queue.add(neighbor);
					visited.add(neighbor);
					parentMap.put(neighbor, currentNode);
				}
			}
		}
		return Collections.emptyList();
	}

	public static List<PuzzleNode> aStar(PuzzleNode startNode, Function<PuzzleNode, Integer> heuristic) {
		//FIXME: not working properly because we regenerate the same neighbors and f and g values don't stay
		PriorityQueue<PuzzleNode> openSet = new PriorityQueue<>(Comparator.comparingInt(PuzzleNode::getF));

		Set<PuzzleNode> closedSet = new HashSet<>();
		Map<PuzzleNode, PuzzleNode> parentMap = new HashMap<>();

		openSet.add(startNode);
		parentMap.put(startNode, null);

		while (!openSet.isEmpty()) {
			PuzzleNode currentNode = openSet.poll();

			if (currentNode.equals(Util.getSolvedBySize(currentNode.getBoard().length))) {
				return reconstructPath(parentMap, currentNode);
			}

			closedSet.add(currentNode);

			for (PuzzleNode neighbor : currentNode.getNeighbors()) {
				if (closedSet.contains(neighbor)) {
					continue;
				}

				int g = currentNode.getG() + 1;
				int h = heuristic.apply(neighbor);
				int f = g + h;

				if (!openSet.contains(neighbor) || f < neighbor.getF()) {
					neighbor.setG(g);
					neighbor.setF(f);
					parentMap.put(neighbor, currentNode);

					if (!openSet.contains(neighbor)) {
						openSet.add(neighbor);
					}
				}
			}
		}
		
		return Collections.emptyList();
	}

	private static List<PuzzleNode> reconstructPath(Map<PuzzleNode, PuzzleNode> parentMap, PuzzleNode currentNode) {
		List<PuzzleNode> path = new ArrayList<>();
		while (currentNode != null) {
			path.add(currentNode);
			currentNode = parentMap.get(currentNode);
		}
		Collections.reverse(path);
		return path;
	}

}
