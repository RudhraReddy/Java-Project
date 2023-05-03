package FindMatch.controller;

import FindMatch.model.MatchValidator;
import FindMatch.view.GameView;

import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GameController implements ControllerInterface {
	private int gridSize;
	private GameView view;
	private MatchValidator match;

	public GameController() {
		this.view = new GameView(this);
	}

<<<<<<< HEAD
	public int getGridSize() {
		return gridSize;
	}

    
}
=======
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
		match = new MatchValidator(gridSize);
		view.showGrid();
	}
>>>>>>> a55c4a1cd76be3d1637c3a90c670b3d6d1a727c1

}
