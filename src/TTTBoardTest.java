import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TTTBoardTest
{
    private TTTBoard board;

    @BeforeEach
    void setUp()
    {
        board = new TTTBoard();
    }

    @Test
    void newBoardIsEmpty()
    {
        assertTrue(board.isValidMove(0, 0));
        assertFalse(board.isWin("X"));
        assertFalse(board.isTie());
    }

    @Test
    void occupiedSquareIsInvalid()
    {
        board.setMove(1, 1, "X");
        assertFalse(board.isValidMove(1, 1));
    }

    @Test
    void rowWin()
    {
        board.setMove(0, 0, "X");
        board.setMove(0, 1, "X");
        board.setMove(0, 2, "X");
        assertTrue(board.isWin("X"));
    }

    @Test
    void columnWin()
    {
        board.setMove(0, 1, "O");
        board.setMove(1, 1, "O");
        board.setMove(2, 1, "O");
        assertTrue(board.isWin("O"));
    }

    @Test
    void diagonalWin()
    {
        board.setMove(0, 0, "X");
        board.setMove(1, 1, "X");
        board.setMove(2, 2, "X");
        assertTrue(board.isWin("X"));
    }

    @Test
    void tieGame()
    {
        String[][] moves = {{"X","O","X"},{"X","O","O"},{"O","X","X"}};
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board.setMove(row, col, moves[row][col]);
            }
        }
        assertFalse(board.isWin("X"));
        assertFalse(board.isWin("O"));
        assertTrue(board.isTie());
    }

    @Test
    void clearBoardResetsEverything()
    {
        board.setMove(0, 0, "X");
        board.clearBoard();
        assertTrue(board.isValidMove(0, 0));
    }
}