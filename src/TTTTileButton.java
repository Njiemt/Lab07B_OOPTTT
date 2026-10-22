import javax.swing.JButton;
import java.awt.Font;

public class TTTTileButton extends JButton
{
    private int row;
    private int col;

    public TTTTileButton(int row, int col)
    {
        super(" ");
        this.row = row;
        this.col = col;
        setFont(new Font("Arial", Font.BOLD, 48));
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public void setMark(String mark)
    {
        setText(mark);
        setEnabled(false);
    }

    public void resetTile()
    {
        setText(" ");
        setEnabled(true);
    }
}