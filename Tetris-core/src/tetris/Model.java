package tetris;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	public static final int ROWS = 20;
	public static final int COLUMNS = 10;
	Logic _logic;
	
	public Model() {
		Field field = new Field(COLUMNS,ROWS);
		State state = new State();
		state.setField(field);
		state.setFigure(new Figure());
		_logic = new Logic(state);
	}
	
	
	List<ModelListener> _listeners = new ArrayList<ModelListener>();
	
	public void addListener(final ModelListener listener) {
		_listeners.add(listener);
	}
	
	public void removeListener(final ModelListener listener) {
		_listeners.remove(listener);
	}
	
	void fireChangedEvent() {
		for (ModelListener modelListener : _listeners) {
			modelListener.onChange(_logic.getState());
		}
	}

	public void moveLeft() {
		if (_logic.moveLeft()) {
			fireChangedEvent();
		}
	}
	
	public void moveRight() {
		if (_logic.moveRight()) {
			fireChangedEvent();
		}
	}
	
	public void moveDown() {
		if (_logic.moveDown()) {
			fireChangedEvent();
		}
	}
	
	public void shuffle(){
		if (_logic.shuffle()){
			fireChangedEvent();
		}
	}
}
