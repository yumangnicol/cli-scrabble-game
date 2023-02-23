import org.junit.jupiter.api.Test;
import pij.main.Move;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {
    @Test
    public void testMoveInvalidFormat(){
        String move = "GIT,f8,r,asd";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Move(move));
        String expectedMessage = "Move not accepted! Invalid string format";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testMoveSpecialCharactersOnPlayedWord(){
        String move = "!GIT,f8,r";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Move(move));
        String expectedMessage = "Move not accepted! Played word should not contain special characters";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testMoveColumnUpperCase(){
        String move = "GIT,F8,r";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Move(move));
        String expectedMessage = "Move not accepted! Column should be in lower-case";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testMoveRowNotInt(){
        String move = "GIT,fD8,r";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Move(move));
        String expectedMessage = "Move not accepted! Row should be in correct int format";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testMoveInvalidDirection(){
        String move = "GIT,f8,R";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Move(move));
        String expectedMessage = "Move not accepted! Direction should be in either r - right or d - down only";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testToString(){
        String move = "GIT,g8,r";
        Move newMove = new Move(move);

        String expectedMessage = "The move is:    Word: GIT at position: g8, direction: right";
        String actualMessage = newMove.toString();

        assertEquals(actualMessage, expectedMessage);
    }
}
