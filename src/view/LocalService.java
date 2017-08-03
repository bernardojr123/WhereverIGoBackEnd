package view;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.dominio.Local;
import model.persistencia.LocalDao;




@Path("/LocalService")
public class LocalService {
	LocalDao dao = new LocalDao();
	
	@POST
	@Path("/locais")
	@Produces(MediaType.APPLICATION_XML)
	public Local getUsers(){
		return dao.getLocais().get(0);
	}
}
