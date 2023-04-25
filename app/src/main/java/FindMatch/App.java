package FindMatch;


import FindMatch.model.MatchValidator;
import FindMatch.controller.GameController;

public class App {
    public static void main(String[] args) {
		MatchValidator model = new MatchValidator();
        GameController controller = new GameController();
    }
}
