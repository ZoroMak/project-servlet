import com.tictactoe.Field;
import com.tictactoe.Sign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TicTacToeTest {

    private Map<Integer, Sign> field;
    private Field ticTacToe;

    @BeforeEach
    public void setUp() {
        ticTacToe = Mockito.spy(new Field());
        field = ticTacToe.getField();
    }

    @Test
    public void testNoughtCanWin() {
        field.put(0, Sign.NOUGHT);
        field.put(1, Sign.NOUGHT);
        field.put(2, Sign.EMPTY);

        int result = ticTacToe.getEmptyFieldIndex();
        assertEquals(2, result);
    }

    @Test
    public void testCrossCanWin_BlockCross() {
        field.put(0, Sign.CROSS);
        field.put(1, Sign.CROSS);
        field.put(2, Sign.EMPTY);

        int result = ticTacToe.getEmptyFieldIndex();
        assertEquals(2, result);
    }

    @Test
    public void testFirstEmptyField() {
        field.put(0, Sign.CROSS);
        field.put(1, Sign.NOUGHT);
        field.put(2, Sign.CROSS);
        field.put(3, Sign.NOUGHT);
        field.put(4, Sign.EMPTY);

        int result = ticTacToe.getEmptyFieldIndex();
        assertEquals(4, result);
    }

    @Test
    public void testEmptyFieldWhenNoSignificantMove() {
        field.put(0, Sign.CROSS);
        field.put(1, Sign.NOUGHT);
        field.put(2, Sign.CROSS);
        field.put(3, Sign.NOUGHT);
        field.put(4, Sign.EMPTY);

        int result = ticTacToe.getEmptyFieldIndex();
        assertEquals(4, result);
    }

    @Test
    public void testNoEmptyField() {
        field.put(0, Sign.CROSS);
        field.put(1, Sign.CROSS);
        field.put(2, Sign.NOUGHT);
        field.put(3, Sign.NOUGHT);
        field.put(4, Sign.CROSS);
        field.put(5, Sign.CROSS);
        field.put(6, Sign.NOUGHT);
        field.put(7, Sign.NOUGHT);
        field.put(8, Sign.CROSS);

        int result = ticTacToe.getEmptyFieldIndex();
        assertEquals(-1, result);
    }
}
