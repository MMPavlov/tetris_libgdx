package com.epam.tetris;

import tetris.Controller;
import tetris.Model;
import tetris.View;

import java.awt.event.KeyEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TetrisStage extends Stage {
	
	public TetrisStage() {
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(true);
		setViewport(new ScreenViewport(camera));
	}
	
	public void init() {
		Model model = new Model();

		final Controller controller = new Controller();

		model.addListener(controller);

		View view = new View() {
			private Box[][] _boxes = new Box[Model.ROWS][Model.COLUMNS];

			@Override
			protected void drawBox(int colorIndex, int row, int col) {
//				System.out.println("drawbox");
				if (_boxes[row][col] == null) {
					Box box = new Box(colorIndex);
					_boxes[row][col] = box;
					TetrisStage.this.addActor(box);
					box.setBounds(col * Box.SIZE, row * Box.SIZE, Box.SIZE, Box.SIZE);
				}
				
				_boxes[row][col].setColor(colorIndex);
			}
		};
		controller.setView(view);
		controller.setModel(model);
		
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				controller.moveDown();
			}
		}, 1.0f, 0.2f);
		
		Gdx.input.setInputProcessor(this);
		
		addListener(new InputListener() {
			
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				switch(keycode) {
				case Input.Keys.LEFT:
					controller.moveLeft();
					break;
				case Input.Keys.RIGHT: 
					controller.moveRight();
					break;
				case Input.Keys.SPACE:
					controller.shuffle();
					break;
				}
				return true;
			}
			
		});

	}
	
	
	
	
}
