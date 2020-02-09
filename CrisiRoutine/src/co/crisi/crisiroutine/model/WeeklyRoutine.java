package co.crisi.crisiroutine.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import co.crisi.crisiroutine.persistence.RoutineFiles;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WeeklyRoutine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameOfMonth;
	private TreeMap<String, DailyRoutine> weeks;
	private Routine routineAssociated;

	public WeeklyRoutine() {
		this("1", new Routine());
	}

	public WeeklyRoutine(String nameOfMonth, Routine routineAssociated) {
		this.nameOfMonth = nameOfMonth;
		this.routineAssociated = routineAssociated;
		weeks = new TreeMap<String, DailyRoutine>();
		initWeek();
	}

	public String getNameOfMonth() {
		return nameOfMonth;
	}

	public void setNameOfMonth(String nameOfMonth) {
		this.nameOfMonth = nameOfMonth;
	}

	public TreeMap<String, DailyRoutine> getWeeks() {
		return weeks;
	}

	public void setWeeks(TreeMap<String, DailyRoutine> weeks) {
		this.weeks = weeks;
	}

	public Routine getRoutineAssociated() {
		return routineAssociated;
	}

	public void setRoutineAssociated(Routine routineAssociated) {
		this.routineAssociated = routineAssociated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameOfMonth == null) ? 0 : nameOfMonth.hashCode());
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
		WeeklyRoutine other = (WeeklyRoutine) obj;
		if (nameOfMonth == null) {
			if (other.nameOfMonth != null)
				return false;
		} else if (!nameOfMonth.equals(other.nameOfMonth))
			return false;
		return true;
	}

	/**
	 * Testear This method allows to create a default week
	 */
	public void initWeek() {
		weeks.put("MONDAY", new DailyRoutine("MONDAY", this));
		DailyRoutine monday = weeks.get("MONDAY");
		MuscleGroup[] muscleGroups = { MuscleGroup.BACK, MuscleGroup.BICEPS };
		RoutineFiles.createDailyRoutine("exercises\\TopExercises.txt", monday, muscleGroups);

		weeks.put("TUESDAY", new DailyRoutine("TUESDAY", this));
		DailyRoutine tuesday = weeks.get("TUESDAY");
		muscleGroups[0] = MuscleGroup.SHOULDERS;
		muscleGroups[1] = MuscleGroup.TRICEPS;
		RoutineFiles.createDailyRoutine("exercises\\TopExercises.txt", tuesday, muscleGroups);

		weeks.put("WEDNESDAY", new DailyRoutine("WEDNESDAY", this));
		DailyRoutine wednesday = weeks.get("WEDNESDAY");
		muscleGroups[0] = MuscleGroup.BACK_LEGS;
		muscleGroups[1] = MuscleGroup.BACK_LEGS;
		RoutineFiles.createDailyRoutine("exercises\\LegExercises.txt", wednesday, muscleGroups);

		weeks.put("THURSDAY", new DailyRoutine("THURSDAY", this));
		DailyRoutine thursday = weeks.get("THURSDAY");
		muscleGroups[0] = MuscleGroup.PECTORALS;
		muscleGroups[1] = MuscleGroup.PECTORALS;
		RoutineFiles.createDailyRoutine("exercises\\TopExercises.txt", thursday, muscleGroups);

		weeks.put("FRIDAY", new DailyRoutine("FRIDAY", this));
		DailyRoutine friday = weeks.get("FRIDAY");
		muscleGroups[0] = MuscleGroup.FRONT_LEGS;
		muscleGroups[1] = MuscleGroup.FRONT_LEGS;
		RoutineFiles.createDailyRoutine("exercises\\LegExercises.txt", friday, muscleGroups);

	}

	public StringProperty day1Info() {
		DailyRoutine day1 = weeks.get("MONDAY");
		return new SimpleStringProperty(day1.infoProperty().get());
	}

	public StringProperty day2Info() {
		DailyRoutine day2 = weeks.get("TUESDAY");
		return new SimpleStringProperty(day2.infoProperty().get());
	}

	public StringProperty day3Info() {
		DailyRoutine day3 = weeks.get("WEDNESDAY");
		return new SimpleStringProperty(day3.infoProperty().get());
	}

	public StringProperty day4Info() {
		DailyRoutine day4 = weeks.get("THURSDAY");
		return new SimpleStringProperty(day4.infoProperty().get());
	}

	public StringProperty day5Info() {
		DailyRoutine day5 = weeks.get("FRIDAY");
		return new SimpleStringProperty(day5.infoProperty().get());
	}

	public ArrayList<DailyRoutine> getWeeksInList() {
		ArrayList<DailyRoutine> weeksInList = new ArrayList<DailyRoutine>();
		Iterator<String> iterator = weeks.keySet().iterator();
		while (iterator.hasNext()) {
			weeksInList.add(weeks.get(iterator.next()));
		}
		return weeksInList;
	}

	@Override
	public String toString() {
		return "" + hashCode();
	}


}
