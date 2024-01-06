import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeGUI extends Application {
    private TicTacToe ticTacToe;
    private Button[][] buttons;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ticTacToe = new TicTacToe();
        buttons = new Button[3][3];

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create buttons and set their properties
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("-");
                button.setMinSize(80, 80);
                button.setStyle("-fx-font-size: 24");
                int row = i;
                int col = j;
                button.setOnAction(e -> handleButtonClick(row, col));
                gridPane.add(button, j, i);
                buttons[i][j] = button;
            }
        }

        Scene scene = new Scene(gridPane, 250, 250);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(int row, int col) {
        if (ticTacToe.isValidMove(row, col)) {
            ticTacToe.makeMove(row, col);
            buttons[row][col].setText(String.valueOf(ticTacToe.getCurrentPlayer()));

            if (ticTacToe.checkWin()) {
                displayResult("Player " + ticTacToe.getCurrentPlayer() + " wins!");
                resetGame();
            } else if (ticTacToe.isBoardFull()) {
                displayResult("It's a tie!");
                resetGame();
            } else {
                ticTacToe.switchPlayer();
            }
        }
    }

    private void resetGame() {
        ticTacToe = new TicTacToe();
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText("-");
            }
        }
    }

    private void displayResult(String message) {
        Stage resultStage = new Stage();
        resultStage.setTitle("Game Result");
        resultStage.setScene(new Scene(new javafx.scene.control.Label(message), 200, 100));
        resultStage.show();
    }
}
