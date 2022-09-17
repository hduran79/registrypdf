package ramajudicial.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ramajudicial.dto.ServiceApiDirectoryDTO;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IServiceApiDirectory {

    @POST
    @Path("entidad/23")
    List<ServiceApiDirectoryDTO> serviceApiDirectoryURL();

}
