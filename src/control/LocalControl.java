package control;

import java.util.ArrayList;

import model.dominio.Local;
import model.persistencia.LocalDao;

public class LocalControl {
	
	public static void main(String[] args) {
		LocalControl local = new LocalControl();
		ArrayList<Local> lugaresTagsUsuario = local.getLocaisPorTags("praia");
		ArrayList<Local> lugaresComNota = local.getUltimosLugares(1);
		local.sendLugaresComNotas(1,"Fernando de Noronha/Garopaba/Morro de São Paulo/Fortaleza/Ilha Bela/", "5.0/3.0/1.0/4.0/5.0/", "Tibau do Sul/São Miguel do Gostoso/Fernando de Noronha/Tamandaré/Fortaleza/Trancoso/Península de Maraú/Morro de São Paulo/São Miguel dos Milagres/Maceió/Ubatuba/Maragogi/Ilha Bela/Angra dos Reis/Arraial do Cabo/Garopaba/Imbituba/");
		for(int i=0; i<lugaresTagsUsuario.size();i++) {
			System.out.println(lugaresTagsUsuario.get(i).getCidade());
		};
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
		
		SlopeOne slopeOne = new SlopeOne();
		String query = slopeOne.getObjetos(id, nomeLugares, arrayNotas, arrayTags);
		dao.insertAvaliacao(query);
		return true;
	}
}
