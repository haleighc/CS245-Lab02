//Haleigh Cole
//2/10/2022
//CS245 Lab 02 -- Sudoku
//For the time complexity: O(n^m) n = 9 because 9x9 and m is blank spaces

import java.util.Arrays;
import java.util.Scanner;

public class SudukoSolverAgain {
	
	private int board[][] ;
	
	public SudukoSolverAgain () {
		board = new int[9][9];
	}
	
	public SudukoSolverAgain(int board[][]) {
		this.board = board;
	}

	public void inputBoard() throws Exception {					//scan & iterate
																//throw an exception
		Scanner scan = new Scanner(System.in);
		for(int i = 0; i < 9; i++) {
			System.out.println("Enter 9 numbers for row " + (i + 1) + ":");  //iterate to new row for input
			
			String str = scan.nextLine();						//scan the next line
			if(str.length() != 9) {								//if the input length is more than 9
				scan.close();									//throw the exception
				throw new Exception();
			}
			
			char[] ch = str.toCharArray(); 						//changing string we got to a char
			
			
			for(int j = 0; j < 9; j++) {						//adding it to the board
				if(ch[j] == '.') {								
					ch[j] = '.';
				}
				board[i][j] = (int)ch[j] - '.';
			}
		}
		scan.close();
		
		
		if(board.length != 9 || board[0].length != 9) {
			throw new Exception();
		}
		
	}

	public boolean checkRow(int row, int num) {
										//need a basic loop to see if the num exists in that row
										//check each cell in each row to see if value is present in row
										//if so return true, otherwise return false
										//is char == to the num passed in
										//iterate through row
										//check if cell value is = to num being passed in
				
		for(int i = 0; i < 9; i++) {	//using i to identify cols
			if(board[row][i] == num) {	//if the row & cols(i) contains the num then true
				return true;			//using[row][i] gives the specific coordinates of location on board
			}
		}
		return false;					//otherwise false
	}
	
	public boolean checkCol(int col, int num) {	//same loop process as for checking the row
		for(int i = 0; i < 9; i++) {			//using i to identify rows
			if(board[i][col] == num) {			//if the col & row(i) contains the num then true
				return true;					//using [i][col] gives specific coordinates
			}
		}
		return false;							//otherwise false
	}
	
	public boolean smallBoard(int row, int col, int num) {
		int smallBoard_Row = row - row % 3;		//using % to get the remainder 1 -1, with a remainder of 3
		int smallBoard_Col = col - col % 3;		//gives the smallBoard_Row to be 0
		
		
		for(int i = smallBoard_Row; i < smallBoard_Row + 3; i++) {		//iterate row
			for(int j = smallBoard_Col; j < smallBoard_Col + 3; j++) {	//iterate cols
				if(board[i][j] == num) {								//if the board contains the num we are looking for
					return true;										//return true
				}
			}
		}
		return false;													//otherwise false
	}
	
	public boolean isValid(int row, int col, int num) {					//checking to see if a num can be in the 3x3
		return !(smallBoard(row, col , num) || checkCol(col, num) || checkRow(row, num));	//if its true, the num cant be there
	}
	
	public boolean solveSudoku () {										//loop through the puzzle
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] == 0) {									//if the space on the board is = 0
					for(int tester = 1; tester < 10; tester++) {		//test a number
						if(isValid(i, j, tester)) {						//call isValid
							board[i][j] = tester;						//if the num isnt valid, move to the next num to test
							
							if(solveSudoku()) {
								return true;
							} else {
								board[i][j] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	public void printBoard() {
		for(int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
}