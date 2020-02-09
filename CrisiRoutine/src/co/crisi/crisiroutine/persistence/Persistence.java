package co.crisi.crisiroutine.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistence {

	public static final String ROUTINE_PATH = "C:\\Users\\Crisi\\Desktop\\Proyectos_Java\\Personal\\CrisiRoutine\\saveFiles\\Routines.dat";

	public static void serialize(Object object) throws IOException {
		FileOutputStream out = new FileOutputStream(ROUTINE_PATH);
		ObjectOutputStream objectOut = new ObjectOutputStream(out);
		objectOut.writeObject(object);
		objectOut.flush();
		objectOut.close();
		out.close();
	}

	public static Object deserialize() throws IOException, ClassNotFoundException {
		FileInputStream in = new FileInputStream(ROUTINE_PATH);
		ObjectInputStream objectIn = new ObjectInputStream(in);
		Object object = objectIn.readObject();
		objectIn.close();
		in.close();
		return object;
	}

}
