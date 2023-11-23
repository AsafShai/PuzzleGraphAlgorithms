package PuzzleGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleNode {

	private int[][] board;
	private int f;
	private int g;

	public PuzzleNode(int[][] board) {
		this.board = board;
		f = 0;
		g = 0;
	}

	public int[][] getBoard() {
		return board;
	}
	
	

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public List<PuzzleNode> getNeighbors() {
		int blankRow = -1, blankCol = -1;

		// Find the position of the blank tile
		outerloop: for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j] == 0) {
					blankRow = i;
					blankCol = j;
					break outerloop;
				}
			}
		}

		return generateNeighbors(blankRow, blankCol);
	}

	/**
	 * @param neighbors
	 * @param blankRow
	 * @param blankCol
	 * @return
	 */
	private List<PuzzleNode> generateNeighbors(int blankRow, int blankCol) {
		List<PuzzleNode> neighbors = new ArrayList<>();
		int[][] moves = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		for (int[] move : moves) {
			int newRow = blankRow + move[0];
			int newCol = blankCol + move[1];
			if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board.length) {
				int[][] newBoard = copyBoard();
				newBoard[blankRow][blankCol] = newBoard[newRow][newCol];
				newBoard[newRow][newCol] = 0;
				neighbors.add(new PuzzleNode(newBoard));
			}
		}
		return neighbors;
	}

	private int[][] copyBoard() {
		int[][] newBoard = new int[board.length][];
		for (int i = 0; i < board.length; i++) {
			newBoard[i] = board[i].clone();
		}
		return newBoard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuzzleNode other = (PuzzleNode) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int[] row : board) {
			for (int tile : row) {
				sb.append(String.format("%2d ", tile));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
