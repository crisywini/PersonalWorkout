package co.crisi.crisitourine.application;

import java.util.TreeMap;

import co.crisi.crisiroutine.exceptions.NonExistentElementException;
import co.crisi.crisiroutine.model.Mode;
import co.crisi.crisiroutine.model.WeeklyRoutine;

public interface ICrisiRoutineControl {
	public void generateNew();

	public void setMode(Mode mode);

	public boolean saveRoutine(String name, TreeMap<String, WeeklyRoutine> weeks);

	public void setRoutine(String name) throws NonExistentElementException;

}
