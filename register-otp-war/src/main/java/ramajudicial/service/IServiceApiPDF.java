package ramajudicial.service;

import java.io.File;

import javax.ws.rs.GET;

public interface IServiceApiPDF {

    @GET
    File getPDF();

}
