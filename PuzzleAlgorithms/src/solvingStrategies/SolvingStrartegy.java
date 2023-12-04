package solvingStrategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import PuzzleGame.PuzzleNode;

public abstract class SolvingStrartegy {
	protected Set<PuzzleNode> openedSet;

	public abstract List<PuzzleNode> solvePuzzle(PuzzleNode startNode);
	
	protected List<PuzzleNode> reconstructPath(Map<PuzzleNode, PuzzleNode> parentMap, PuzzleNode currentNode) {
		List<PuzzleNode> path = new ArrayList<>();
		while (currentNode != null) {
			path.add(currentNode);
			currentNode = parentMap.get(currentNode);
		}
		Collections.reverse(path);
		return path;
	}
	
	public int countOpenedNodes() {
		return openedSet.size();
	}
	
	public abstract String getAlgorithmName();

}
