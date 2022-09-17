package ramajudicial.service;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ramajudicial.business.RamaJudicialBusiness;

@Path("ramajudicial")
@Produces(MediaType.APPLICATION_JSON)
public class RamaJudicialService {

	@Inject
	private RamaJudicialBusiness business;
	
	@POST
	@Path("html")
	public String codeOtp(String html) throws IOException {		
		return business.downloadPDF(html);
	}
}
