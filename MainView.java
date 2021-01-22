// design of the GUI

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MainView extends VBox {
    private Canvas canvas;
    private GameOfLife gameOfLife;

    private Affine affine;

    public MainView() {
        this.canvas = new Canvas(400, 400);
        this.canvas.setOnMousePressed(this::pressMouse);
        this.canvas.setOnMouseDragged(this::pressMouse);

        Toolbar toolbar = new Toolbar(this);

        this.getChildren().addAll(toolbar, this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400 / 20f, 400 / 20f);

        this.gameOfLife = new GameOfLife(20, 20);
        //gameOfLife.board = gameOfLife.randomState();
    }

    private void pressMouse(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        try {
            Point2D coord = this.affine.inverseTransform(x, y);

            int xCoord = (int) coord.getX();
            int yCoord = (int) coord.getY();

            this.gameOfLife.setLiveCell(xCoord, yCoord);
            draw();

        } catch (NonInvertibleTransformException e) {
            System.out.println("Problem with inverseTransform");
        }
    }

    public void draw() {
        GraphicsContext graphics = this.canvas.getGraphicsContext2D();
        graphics.setTransform(this.affine);
        graphics.setFill(Color.LIGHTGRAY);
        graphics.fillRect(0, 0, 400, 400);

        graphics.setFill(Color.BLACK);
        for (int i = 0; i < this.gameOfLife.width; i++) {
            for (int j = 0; j < this.gameOfLife.height; j++) {
                if (this.gameOfLife.getState(i, j) == true) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        graphics.setStroke(Color.GRAY);
        graphics.setLineWidth(0.05f);
        for (int i = 0; i <= this.gameOfLife.width; i++) {
            //graphics.strokeLine(i, 0, i, 10);
            for (int j = 0; j <= this.gameOfLife.height; j++) {
                graphics.strokeLine(i, 0, i, 20);
                graphics.strokeLine(0, j, 20, j);
            }
        }

    }

    public GameOfLife getGameOfLife() {
        return this.gameOfLife;
    }
}
