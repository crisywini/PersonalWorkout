package co.crisi.crisiroutine.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

import co.crisi.crisiroutine.model.DailyRoutine;
import co.crisi.crisiroutine.model.MuscleGroup;
import co.crisi.crisiroutine.model.Priority;
import co.crisi.crisiroutine.model.Super;

public class RoutineFiles {

	public static ArrayList<String> readFile(String path) throws IOException {
		FileReader fileReader = new FileReader(path);
		BufferedReader directioner = new BufferedReader(fileReader);
		ArrayList<String> info = new ArrayList<String>();
		String line = "";
		while ((line = directioner.readLine()) != null) {
			info.add(line + "\n");
		}
		directioner.close();
		fileReader.close();
		return info;
	}

	public static String[] split(String info) {
		String[] out = null;
		StringTokenizer tokenizer = new StringTokenizer(info, "<>,", false);
		out = new String[tokenizer.countTokens()];
		int count = 0;
		while (tokenizer.hasMoreElements()) {
			out[count] = tokenizer.nextToken();
			count++;
		}
		return out;
	}

	public static void createDailyRoutine(String path, DailyRoutine dailyRoutine, MuscleGroup[] muscleGroup) {
		try {
			ArrayList<String> info = readFile(path);
			int randomExercises = getRandomInt(5, 7);
			String[] line = null;
			String priorityStr = "";
			String muscleGroupStr = "";
			String superStr = "";
			int counter = 0;
			if (muscleGroup[0] == MuscleGroup.BACK_LEGS) {
				randomExercises = getRandomInt(10, 13);
				while (counter < randomExercises) {
					line = split(info.get(getRandomInt(0, info.size())));
					priorityStr = line[1];
					muscleGroupStr = line[2];
					MuscleGroup muscleGroup2 = getMuscleGroup(muscleGroupStr);
					superStr = line[3];
					if (muscleGroup[0] == muscleGroup2) {

						if (dailyRoutine.addExercise(line[0], getPriority(priorityStr), getMuscleGroup(muscleGroupStr),
								getSuper(superStr))) {
							counter++;
						}
					}
				}
			} else {
				if (muscleGroup[0] == MuscleGroup.FRONT_LEGS) {
					randomExercises = getRandomInt(5, 8);
					while (counter < randomExercises) {
						line = split(info.get(getRandomInt(0, info.size())));
						priorityStr = line[1];
						muscleGroupStr = line[2];
						MuscleGroup muscleGroup2 = getMuscleGroup(muscleGroupStr);
						superStr = line[3];
						if (muscleGroup[0] == muscleGroup2) {

							if (dailyRoutine.addExercise(line[0], getPriority(priorityStr),
									getMuscleGroup(muscleGroupStr), getSuper(superStr))) {
								counter++;
							}
						}
					}
				} else {
					randomExercises = getRandomInt(5, 7);

					while (counter < randomExercises) {
						line = split(info.get(getRandomInt(0, info.size())));
						priorityStr = line[1];
						muscleGroupStr = line[2];
						MuscleGroup muscleGroup2 = getMuscleGroup(muscleGroupStr);
						superStr = line[3];
						if (muscleGroup[0] == muscleGroup2) {
							if (dailyRoutine.addExercise(line[0], getPriority(priorityStr),
									getMuscleGroup(muscleGroupStr), getSuper(superStr))) {
								counter++;
							}
						}
						if (muscleGroup[1] == muscleGroup2) {
							if (dailyRoutine.addExercise(line[0], getPriority(priorityStr),
									getMuscleGroup(muscleGroupStr), getSuper(superStr))) {
								counter++;
							}
						}
					}
				}
			}
			randomExercises = getRandomInt(2, 4);
			ArrayList<String> info2 = readFile("exercises\\AbsExercises.txt");
			Collections.shuffle(info2);
			String[] line2 = null;
			String priorityStr2 = "";
			String muscleGroupStr2 = "";
			String superStr2 = "";
			for (int i = 0; i < randomExercises; i++) {
				line2 = split(info2.get(i));
				priorityStr2 = line2[1];
				muscleGroupStr2 = line2[2];
				superStr2 = line2[3];
				dailyRoutine.addExercise(line2[0], getPriority(priorityStr2), getMuscleGroup(muscleGroupStr2),
						getSuper(superStr2));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Priority getPriority(String priorityStr) {
		if (priorityStr.equals("HIGH")) {
			return Priority.HIGH;
		}
		if (priorityStr.equals("MEDIUM")) {
			return Priority.MEDIUM;
		}
		return Priority.LOW;
	}

	public static MuscleGroup getMuscleGroup(String muscleGroupStr) {
		if (muscleGroupStr.equals("FRONT_LEGS")) {
			return MuscleGroup.FRONT_LEGS;
		}
		if (muscleGroupStr.equals("BACK_LEGS")) {
			return MuscleGroup.BACK_LEGS;
		}
		if (muscleGroupStr.equals("BACK")) {
			return MuscleGroup.BACK;
		}
		if (muscleGroupStr.equals("BICEPS")) {
			return MuscleGroup.BICEPS;
		}
		if (muscleGroupStr.equals("TRICEPS")) {
			return MuscleGroup.TRICEPS;
		}
		if (muscleGroupStr.equals("SHOULDERS")) {
			return MuscleGroup.SHOULDERS;
		}
		if (muscleGroupStr.equals("PECTORALS")) {
			return MuscleGroup.PECTORALS;
		}
		return MuscleGroup.ABS;
	}

	public static Super getSuper(String superStr) {
		if (superStr.equals("YES"))
			return Super.YES;
		return Super.NOT;
	}

	private static int getRandomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

}
