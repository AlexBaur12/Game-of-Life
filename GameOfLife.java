/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * This project will simulate John Conway's famous Game of Life, where cells either live,      *
 * die, or repopulate based on a pre-defined set of rules.									   *
 *                                                                                             *
 * 1) Any live cell with less than two live neighbours dies due to underpopulation             *
 * 2) Any live cell with two or three live neighbours continues to live                        *
 * 3) Any live cell with more than three live neighbours dies due to overpopulation            *
 * 4) Any dead cell with exactly three live neighbours becomes a live cell due to repopulation *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*/

import java.util.Scanner;   // for getting user input

public class GameOfLife
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);   // gets user input for width and height
		System.out.print("Please input width: ");
		int x = s.nextInt();
		System.out.print("\n Please input height: ");
		int y = s.nextInt();

 		// board states are stored in 2D arrays
		int[][] state = randomState(x, y);   // create a state where each cell is randomized
		render(state, x, y);   // displays state in command line				  

	}
	// parameters for with and height, returns board with cells randomized as 0 or 1
	public static int[][] randomState(int width, int height)
	{
		int[][] randomState = new int[width][height];
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				randomState[i][j] = (int)Math.round(Math.random());
			}
		}
		return randomState;
	}

	// uses parameters for a board state, width, and height to display in command line
	public static void render(int[][] state, int x, int y)
	{
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				if(state[i][j] == 1)
					System.out.print("O ");   // living cell
				else
					System.out.print("X ");   // dead cell
			}
			System.out.print("\n");
		}
	}
}