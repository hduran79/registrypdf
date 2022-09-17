package config;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import dto.exception.GeneralExceptionDTO;
import util.Util;

@Provider
@ApplicationScoped
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

	private GeneralExceptionDTO error;
	
	public Response toResponse(Exception e) {
		if (e instanceof BusinessException) {
            BusinessException business = (BusinessException) e;
            String code = business.getCode();
            String[] parameters = business.getParameters();
            String message = Util.replaceString("Aqui va el mensaje parametro: #0", parameters);
            error = new GeneralExceptionDTO(code, message);
        } else {
            String code = "CODE GENERAL";
            String message = "ERROR GENERAL" + e.getMessage();
            error = new GeneralExceptionDTO(code, message);
        }

        return Response.status(Status.FORBIDDEN).type(MediaType.APPLICATION_JSON)
            .entity(error)
            .build();
	}

}
