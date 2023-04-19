package mvc.controller;

import mvc.model.GameModel;
import mvc.model.MemoryMatcher;
import mvc.view.GameView;

import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
	private int gridSize;
    private GameModel model;
    private GameView view;
    private MemoryMatcher memory;

    
    public GameController(GameModel model) {
        this.model = model;
		this.view = new GameView(this);

        
        //this.view.addLevelButtonListener(new LevelButtonListener());
    }
    
    public void start() {
        view.show();
    }
	public void setGridSize(int gridSize)
	{
		this.gridSize = gridSize;
		memory =new MemoryMatcher(gridSize);
        view.showGrid();
	}

}

