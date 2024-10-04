package BDD;

import com.tictactoe.Field;
import com.tictactoe.Sign;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicTacToeStepDefinitions {

    private Field field;
    private int result;

    @Given("the game board contains two noughts in a row and one empty cell")
    public void the_game_board_contains_two_noughts_in_a_row_and_one_empty_cell() {
        field = new Field();
        field.getField().put(0, Sign.NOUGHT);
        field.getField().put(1, Sign.NOUGHT);
        field.getField().put(2, Sign.EMPTY);
    }

    @When("the getEmptyFieldIndex method is called")
    public void the_getEmptyFieldIndex_method_is_called() {
        result = field.getEmptyFieldIndex();
    }

    @Then("the index of the empty cell for winning with noughts is returned")
    public void the_index_of_the_empty_cell_for_winning_with_noughts_is_returned() {
        assertEquals(2, result);
    }

    @Given("a playing field contains two consecutive crosses and one empty cell in a winning combination")
    public void a_playing_field_contains_two_consecutive_crosses_and_one_empty_cell_in_a_winning_combination() {
        field = new Field();
        field.getField().put(0, Sign.CROSS);
        field.getField().put(1, Sign.CROSS);
        field.getField().put(2, Sign.EMPTY);
    }

    @Then("the index of an empty cell is returned to block the winning of crosses")
    public void the_index_of_an_empty_cell_is_returned_to_block_the_winning_of_crosses() {
        assertEquals(2, result);
    }

    @Given("the playing field does not contain winning combinations")
    public void the_playing_field_does_not_contain_winning_combinations() {
        field = new Field();
        field.getField().put(0, Sign.CROSS);
        field.getField().put(1, Sign.NOUGHT);
        field.getField().put(2, Sign.CROSS);
        field.getField().put(3, Sign.NOUGHT);
        field.getField().put(4, Sign.EMPTY);
    }

    @Then("the index of the first empty cell in the field is returned")
    public void the_index_of_the_first_empty_cell_in_the_field_is_returned() {
        assertEquals(4, result);
    }

    @Given("all the cells in the field are filled with crosses and noughts")
    public void all_the_cells_in_the_field_are_filled_with_crosses_and_noughts() {
        field = new Field();
        field.getField().put(0, Sign.CROSS);
        field.getField().put(1, Sign.CROSS);
        field.getField().put(2, Sign.NOUGHT);
        field.getField().put(3, Sign.NOUGHT);
        field.getField().put(4, Sign.CROSS);
        field.getField().put(5, Sign.CROSS);
        field.getField().put(6, Sign.NOUGHT);
        field.getField().put(7, Sign.NOUGHT);
        field.getField().put(8, Sign.CROSS);
    }

    @Then("there are no empty cells and return")
    public void there_are_no_empty_cells_and_return() {
        assertEquals(-1, result);
    }
}
