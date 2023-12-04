package solvingStrategies;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

import PuzzleGame.PuzzleNode;
import utils.Util;

public class BFSSolvingStrategy extends SolvingStrartegy {
	
	private static final String NAME = "BFS";
	
	public BFSSolvingStrategy() {
		openedSet = new HashSet<PuzzleNode>();
	}


	@Override
	public List<PuzzleNode> solvePuzzle(PuzzleNode startNode) {
		Queue<PuzzleNode> queue = new LinkedList<>();
		Set<PuzzleNode> visited = new HashSet<>();
		Map<PuzzleNode, PuzzleNode> parentMap = new HashMap<>();

		queue.add(startNode);
		visited.add(startNode);
		parentMap.put(startNode, null);

		while (!queue.isEmpty()) {
			PuzzleNode currentNode = queue.poll();
			openedSet.add(currentNode);
			if (currentNode.equals(Util.getSolvedBySize(currentNode.getBoard().length))) {
				return reconstructPath(parentMap, currentNode);
			}

			List<PuzzleNode> neighbors = currentNode.getNeighbors();
			for (PuzzleNode neighbor : neighbors) {
				openedSet.add(neighbor);
				if (!visited.contains(neighbor)) {
					queue.add(neighbor);
					visited.add(neighbor);
					parentMap.put(neighbor, currentNode);
				}
			}
		}
		return Collections.emptyList();
	}

	@Override
	public String getAlgorithmName() {
		return NAME;
	}

}
