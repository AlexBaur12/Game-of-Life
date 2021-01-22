// controls the implementation of the "toolbar" in the Game of Life GUI

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

    private MainView mainView;

    public Toolbar(MainView mainView) {
        this.mainView = mainView;

        // create the buttons in the toolbar
        Button iterate = new Button("Iterate");
        iterate.setOnAction(this::handleIterate);
        Button clear = new Button("Clear");
        clear.setOnAction(this::handleClear);
        Button randomize = new Button("randomize");
        randomize.setOnAction(this::handleRandomize);

        this.getItems().addAll(iterate, clear, randomize);
    }

    // controls the action of the iterate button
    private void handleIterate(ActionEvent actionEvent) {
        this.mainView.getGameOfLife().board = this.mainView.getGameOfLife().nextBoardState();
        this.mainView.draw();
    }

    // controls the action of the clear button
    private void handleClear(ActionEvent actionEvent) {
        this.mainView.getGameOfLife().board = this.mainView.getGameOfLife().emptyState();
        this.mainView.draw();
    }

    // controls the action of the randomize button
    private void handleRandomize(ActionEvent actionEvent) {
        this.mainView.getGameOfLife().board = this.mainView.getGameOfLife().randomState();
        this.mainView.draw();
    }
}
