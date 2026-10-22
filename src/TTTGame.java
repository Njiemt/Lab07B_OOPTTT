import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TTTGame
{
    private JFrame frame;
    private JPanel boardPanel;
    private JPanel controlPanel;
    private JButton quitButton;
    private TTTBoard board;
    private TTTTileButton[][] tiles;
    private String currentPlayer;
    private int moveCount;

    public TTTGame()
    {
        board = new TTTBoard();
        tiles = new TTTTileButton[TTTBoard.ROW][TTTBoard.COL];
        currentPlayer = "X";
        moveCount = 0;
        buildGui();
    }

    public void startGame()
    {
        frame.setVisible(true);
    }

    private void buildGui()
    {
        frame = new JFrame("Tic Tac Toe");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(TTTBoard.ROW, TTTBoard.COL));

        for(int row = 0; row < TTTBoard.ROW; row++)
        {
            for(int col = 0; col < TTTBoard.COL; col++)
            {
                tiles[row][col] = new TTTTileButton(row, col);
                tiles[row][col].addActionListener(new TileListener());
                boardPanel.add(tiles[row][col]);
            }
        }

        controlPanel = new JPanel();
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?",
                        "Quit", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        controlPanel.add(quitButton);

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);
    }

    private class TileListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            TTTTileButton tile = (TTTTileButton) ae.getSource();
            int row = tile.getRow();
            int col = tile.getCol();

            if(!board.isValidMove(row, col))
            {
                JOptionPane.showMessageDialog(frame, "That space is already taken.");
                return;
            }

            board.setMove(row, col, currentPlayer);
            tile.setMark(currentPlayer);
            moveCount++;

            if(board.isWin(currentPlayer))
            {
                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                askPlayAgain();
            }
            else if(board.isTie())
            {
                JOptionPane.showMessageDialog(frame, "It's a tie!");
                askPlayAgain();
            }
            else
            {
                switchPlayer();
            }
        }
    }

    private void switchPlayer()
    {
        if(currentPlayer.equals("X"))
        {
            currentPlayer = "O";
        }
        else
        {
            currentPlayer = "X";
        }
    }

    private void askPlayAgain()
    {
        int choice = JOptionPane.showConfirmDialog(frame, "Play again?", "Game Over",
                JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION)
        {
            resetGame();
        }
        else
        {
            System.exit(0);
        }
    }

    private void resetGame()
    {
        board.clearBoard();
        for(int row = 0; row < TTTBoard.ROW; row++)
        {
            for(int col = 0; col < TTTBoard.COL; col++)
            {
                tiles[row][col].resetTile();
            }
        }
        currentPlayer = "X";
        moveCount = 0;
    }
}