package co.crisi.crisiroutine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import co.crisi.crisiroutine.exceptions.NonExistentElementException;

public class Routine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TreeMap<String, WeeklyRoutine> weeks;
	private TreeMap<String, TreeMap<String, WeeklyRoutine>> routines;
	private Mode mode;

	public Routine() {
		this(Mode.BULKING);
	}

	public Routine(Mode mode) {
		this.mode = mode;
		weeks = new TreeMap<String, WeeklyRoutine>();
		routines = new TreeMap<String, TreeMap<String, WeeklyRoutine>>();
		initDefaultMonth();
	}

	public TreeMap<String, WeeklyRoutine> getWeeks() {
		return weeks;
	}

	public void setWeeks(TreeMap<String, WeeklyRoutine> weeks) {
		this.weeks = weeks;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public TreeMap<String, TreeMap<String, WeeklyRoutine>> getRoutines() {
		return routines;
	}

	public void setRoutines(TreeMap<String, TreeMap<String, WeeklyRoutine>> routines) {
		this.routines = routines;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mode == null) ? 0 : mode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Routine other = (Routine) obj;
		if (mode != other.mode)
			return false;
		return true;
	}

	public void initDefaultMonth() {
		for (int i = 0; i < 4; i++) {
			weeks.put((i + 1) + "", new WeeklyRoutine((1 + i) + "", this));
		}
	}

	public ArrayList<WeeklyRoutine> getWeeklyRoutineInList() {
		ArrayList<WeeklyRoutine> weeklyRoutine = new ArrayList<WeeklyRoutine>();
		Iterator<String> iterator = weeks.keySet().iterator();
		while (iterator.hasNext()) {
			weeklyRoutine.add(weeks.get(iterator.next()));
		}
		return weeklyRoutine;
	}

	public void generateNew() {
		weeks.clear();
		for (int i = 0; i < 4; i++) {
			weeks.put((i + 1) + "", new WeeklyRoutine((1 + i) + "", this));
		}
	}

	@SuppressWarnings("unchecked")
	public boolean saveRoutine(String name, TreeMap<String, WeeklyRoutine> weeks) {
		boolean isSave = false;
		if (!routines.containsKey(name)) {
			routines.put(name, (TreeMap<String, WeeklyRoutine>) weeks.clone());
			isSave = true;
		}
		return isSave;
	}

	public void setRoutine(String name) throws NonExistentElementException {
		if (!routines.containsKey(name)) {
			throw new NonExistentElementException("La rutina: " + name + " no está guardada.");
		}
		setWeeks(routines.get(name));
	}

}
