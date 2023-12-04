package solvingStrategies;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Function;

import PuzzleGame.PuzzleNode;
import utils.Util;

public class AStarSolvingStrategy extends SolvingStrartegy {
	private static final String NAME = "A*";
	private Function<PuzzleNode, Integer> heuristic;
	private Map<PuzzleNode, Integer> gScore;
	private Map<PuzzleNode, Integer> fScore;

	public AStarSolvingStrategy(Function<PuzzleNode, Integer> heuristic) {
		this.heuristic = heuristic;
		gScore = new HashMap<PuzzleNode, Integer>();
		fScore = new HashMap<PuzzleNode, Integer>();
		openedSet = new HashSet<PuzzleNode>();
	}

	private void clearScores() {
		gScore.clear();
		fScore.clear();
		openedSet.clear();
	}

	public void setHeuristic(Function<PuzzleNode, Integer> heuristic) {
		this.heuristic = heuristic;
	}

	@Override
	public List<PuzzleNode> solvePuzzle(PuzzleNode startNode) {
		clearScores();
		PriorityQueue<PuzzleNode> openSet = new PriorityQueue<>(Comparator.comparingInt(fScore::get));

		Set<PuzzleNode> closedSet = new HashSet<>();
		Map<PuzzleNode, PuzzleNode> parentMap = new HashMap<>();

		openSet.add(startNode);
		parentMap.put(startNode, null);

		while (!openSet.isEmpty()) {
			PuzzleNode currentNode = openSet.poll();
			openedSet.add(currentNode);

			if (currentNode.equals(Util.getSolvedBySize(currentNode.getBoard().length))) {
				return reconstructPath(parentMap, currentNode);
			}

			closedSet.add(currentNode);

			for (PuzzleNode neighbor : currentNode.getNeighbors()) {
				openedSet.add(neighbor);
				if (closedSet.contains(neighbor)) {
					continue;
				}

				int g = gScore.getOrDefault(currentNode, Integer.MAX_VALUE) + 1;
				int h = heuristic.apply(neighbor);
				int f = g + h;

				if (!openSet.contains(neighbor) || f < fScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
					gScore.put(neighbor, g);
					fScore.put(neighbor, f);
					parentMap.put(neighbor, currentNode);

					if (!openSet.contains(neighbor)) {
						openSet.add(neighbor);
					}
				}
			}
		}
		return Collections.emptyList();
	}

	@Override
	public String getAlgorithmName() {
		return NAME + " " + heuristic.toString();
	}

}
