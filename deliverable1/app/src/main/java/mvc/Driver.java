package mvc;

import mvc.model.GameModel;
import mvc.view.GameView;
import mvc.controller.GameController;

public class Driver {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameController controller = new GameController(model);
        controller.start();
    }
}
