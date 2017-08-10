package control;

import java.util.ArrayList;

import model.dominio.Local;
import model.persistencia.LocalDao;

public class LocalControl {
	
	public static void main(String[] args) {
		LocalControl local = new LocalControl();
		ArrayList<Local> lugares = local.getLocaisPorTags("familia calmo historico ");
	}
	
	private LocalDao dao = new LocalDao();
	
	public ArrayList<Local> getLocaisPorTags(String tags){
		String[] array = tags.split(" ");
		return dao.getLocaisPorTag(array);
	}
	
	public ArrayList<Local> getUltimosLugares(int id){
		return dao.getUltimosLugares(id);
	}
	
	public boolean sendLugaresComNotas(int id, String lugares, String notas, String todosLocais) {
		String[] nomeLugares = lugares.split("/");
		String[] arrayNotas = notas.split("/");
		String[] arrayTags = todosLocais.split("/");
		return true;
	}
}
