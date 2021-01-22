/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * This project will simulate John Conway's famous Game of Life, where cells either live,      *
 * die, or repopulate based on a pre-defined set of rules.									   *
 *                                                                                             *
 * 1) Any live cell with less than two live neighbors dies due to underpopulation              *
 * 2) Any live cell with two or three live neighbors continues to live                         *
 * 3) Any live cell with more than three live neighbors dies due to overpopulation             *
 * 4) Any dead cell with exactly three live neighbors becomes a live cell due to re-population *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

public class GameOfLife {
    static int width;
    static int height;
    int[][] board;

    public GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public static void main(String[] args) {
    /*
        Scanner s = new Scanner(System.in);   // gets user input for width and height
        System.out.print("Please input width: ");
        width = s.nextInt();
        System.out.print("Please input height: ");
        height = s.nextInt();

        // board states are stored in 2D arrays
        int[][] state = randomState(width, height);   // create a state where each cell is randomized
        render(state, width, height);   // displays state in command line

        int[][] newState = nextBoardState(state);
        render(newState, width, height);
    }*/
        GameOfLife gameOfLife = new GameOfLife(10, 10);
        gameOfLife.board = gameOfLife.randomState();
        gameOfLife.render();
        gameOfLife.board = gameOfLife.nextBoardState();
        gameOfLife.render();
    }
    // returns board with cells randomized as 0 or 1
    public int[][] randomState() {
        int[][] randomState = new int[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                randomState[i][j] = (int)Math.round(Math.random());
            }
        }
        return randomState;
    }

    // display board in command line
    public void render() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (this.board[i][j] == 1)
                    System.out.print("O ");   // living cell
                else
                    System.out.print("X ");   // dead cell
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    // uses current board state to calculate and return next board state based on rules defined above
    public int[][] nextBoardState() {
        int[][] newState = emptyState();   // cannot update currState, or next state of cells will be wrongly changed

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int amount = checkNeighbors(i, j);
                if (board[i][j] == 0) {  // cell is currently dead
                    if (amount == 3)
                        newState[i][j] = 1;   // cell becomes alive
                }
                else if(board[i][j] == 1) {  // cell is currently alive
                    if (amount == 0 || amount == 1)
                        newState[i][j] = 0;   // cell becomes dead
                    else if (amount == 2 || amount == 3)
                        newState[i][j] = 1;   // cell continues to be alive
                    else if (amount > 3)
                        newState[i][j] = 0;   // cell becomes dead
                }
            }
        }
        return newState;
    }

    // returns a board of correct size and with all cells set to 0
    public int[][] emptyState() {
        int[][] emptyBoard = new int[width][height];
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                emptyBoard[i][j] = 0;
            }
        }
        return emptyBoard;
    }

    // takes in board and checks a cell's neighbors, returning the number of living neighbors
    public int checkNeighbors(int i, int j) {
        int count = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x != 0 || y != 0) && checkCell(board, i + x, j + y))
                    ++count;
            }
        }
        return count;
    }

    //
    public static boolean checkCell(int[][] board,int x, int y) {
        return (x >= 0 && y >= 0 && x < width && y < height && board[x][y] == 1);
    }

    // sets a cell to be alive
    public void setLiveCell(int x, int y) {
        this.board[x][y] = 1;
    }

    public boolean getState(int x, int y) {
        if (this.board[x][y] == 1)
            return true;
        else
            return false;
    }



}