
public class Driver {

	public static void main(String[] args) {
		SudukoSolverAgain sudoku = new SudukoSolverAgain();
		try {
			sudoku.inputBoard();
			if(!sudoku.solveSudoku()) {
				System.out.println("Cannot solve.");
			} else {
				sudoku.printBoard();
			}
		} catch (Exception e) {
			System.out.println("Invalid Board Size");
		}
	}
}
