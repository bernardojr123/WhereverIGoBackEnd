package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import control.LocalControl;
import model.dominio.Local;
import model.persistencia.LocalDao;




@Path("/LocalService")
public class LocalService {
	LocalDao dao = new LocalDao();
	LocalControl localControl = new LocalControl();
	
	@POST
	@Path("/locais")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Local> getLocais(){
		return dao.getLocais();
	}
	
	@POST
	@Path("/locaisportag")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ArrayList<Local> getLugaresPorTag(
		@FormParam("tags") String tags,
		@Context HttpServletResponse servletResponse) throws IOException{
		if (tags.length() > 1) {
			return localControl.getLocaisPorTags(tags);
		}
		return null;
	}
	
	@POST
	@Path("/locaisultimapesquisa")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ArrayList<Local> getUltimosLugares(
		@FormParam("id") int id,
		@Context HttpServletResponse servletResponse) throws IOException{
		return localControl.getUltimosLugares(id);
	}	
	
	@POST
	@Path("/locaiscomnota")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String sendLugaresComNotas(
		@FormParam("id") int id,
		@FormParam("lugares") String lugares,
		@FormParam("notas") String notas,
		@FormParam("todoslocais") String todosLocais,
		@Context HttpServletResponse servletResponse) throws IOException{
		if(localControl.sendLugaresComNotas(id, lugares, notas,todosLocais)) {
			return "sucess";
		}
		return "failure";
		
	}	
	
}
