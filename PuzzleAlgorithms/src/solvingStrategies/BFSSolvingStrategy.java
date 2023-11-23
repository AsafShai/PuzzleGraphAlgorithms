package solvingStrategies;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import PuzzleGame.PuzzleNode;
import utils.Util;

public class BFSSolvingStrategy extends SolvingStrartegy {

	@Override
	public void solvePuzzle(PuzzleNode startNode) {
		Queue<PuzzleNode> queue = new LinkedList<>();
		Set<PuzzleNode> visited = new HashSet<>();
		Map<PuzzleNode, PuzzleNode> parentMap = new HashMap<>();

		queue.add(startNode);
		visited.add(startNode);
		parentMap.put(startNode, null);

		while (!queue.isEmpty()) {
			PuzzleNode currentNode = queue.poll();
			if (currentNode.equals(Util.getSolvedBySize(currentNode.getBoard().length))) {
				// TODO: return solved
			}
			// Process the current node as needed

			List<PuzzleNode> neighbors = currentNode.getNeighbors();
			for (PuzzleNode neighbor : neighbors) {
				if (!visited.contains(neighbor)) {
					queue.add(neighbor);
					visited.add(neighbor);
					parentMap.put(neighbor, currentNode);
				}
			}
		}
	}

}
