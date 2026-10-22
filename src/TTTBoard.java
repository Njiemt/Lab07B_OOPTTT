public class TTTBoard
{
    public static final int ROW = 3;
    public static final int COL = 3;
    private String[][] board = new String[ROW][COL];

    public TTTBoard()
    {
        clearBoard();
    }

    public void clearBoard()
    {
        for(int row = 0; row < ROW; row++)
        {
            for(int col = 0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    public boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    public void setMove(int row, int col, String player)
    {
        board[row][col] = player;
    }

    public String getMark(int row, int col)
    {
        return board[row][col];
    }

    public boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private boolean isColWin(String player)
    {
        for(int col = 0; col < COL; col++)
        {
            if(board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isRowWin(String player)
    {
        for(int row = 0; row < ROW; row++)
        {
            if(board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player)
    {
        if(board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player))
        {
            return true;
        }
        if(board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player))
        {
            return true;
        }
        return false;
    }

    public boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;

        for(int row = 0; row < ROW; row++)
        {
            if(board[row][0].equals("X") || board[row][1].equals("X") || board[row][2].equals("X"))
            {
                xFlag = true;
            }
            if(board[row][0].equals("O") || board[row][1].equals("O") || board[row][2].equals("O"))
            {
                oFlag = true;
            }
        }
        if(!(xFlag && oFlag))
        {
            return false;
        }

        boolean full = true;
        for(int row = 0; row < ROW; row++)
        {
            for(int col = 0; col < COL; col++)
            {
                if(board[row][col].equals(" "))
                {
                    full = false;
                }
            }
        }
        return full;
    }
}