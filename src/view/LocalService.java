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

import model.dominio.Local;
import model.persistencia.LocalDao;




@Path("/LocalService")
public class LocalService {
	LocalDao dao = new LocalDao();
	
	@POST
	@Path("/locais")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Local> getLocais(){
		return dao.getLocais();
	}
	
	@POST
	@Path("/aindansei")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void mandaLugares(
		@FormParam("lugar") Local local,
		@Context HttpServletResponse servletResponse) throws IOException{
		
	}
	
}
