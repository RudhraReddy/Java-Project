package FindMatch.controller;

import FindMatch.model.MatchValidator;
import FindMatch.view.GameView;

import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class GameController implements ControllerInterface{
	private int gridSize;
    private GameView view;
    private MatchValidator match;

    
    public GameController() {
		this.view = new GameView(this);
    }
    
	public void setGridSize(int gridSize)
	{
		this.gridSize = gridSize;
		match = new MatchValidator(gridSize);
        view.showGrid();
	}

	public int getGridSize() {
		return gridSize;
	}    
}

