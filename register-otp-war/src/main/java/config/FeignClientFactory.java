package config;

import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import feign.Feign.Builder;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import ramajudicial.annotation.ApiDirectory;
import ramajudicial.annotation.Directory;
import ramajudicial.annotation.Pdf;
import ramajudicial.service.IDirectoryService;
import ramajudicial.service.IServiceApiDirectory;
import ramajudicial.service.IServiceApiPDF;

@ApplicationScoped
public class FeignClientFactory {

    @Produces
    @Directory
    public Builder createDirectory() {

        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("JudicialBranchDirectory");

        FeignDecorators decorators = FeignDecorators.builder().withCircuitBreaker(circuitBreaker).build();

        return Resilience4jFeign.builder(decorators).contract(new JAXRSContract()).encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, false));
    }

    @Produces
    @RequestScoped
    public IDirectoryService targetDirectory(@Directory Builder builder) {
        return builder.target(IDirectoryService.class,
                "https://www.ramajudicial.gov.co/directorioPortal-portlet/api/jsonws/servicioapidirectorio/get-distritos.17");
    }

    @Produces
    @ApiDirectory
    public Builder createService() {

        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("ServiceDirectoryAPI");

        FeignDecorators decorators = FeignDecorators.builder().withCircuitBreaker(circuitBreaker).build();

        return Resilience4jFeign.builder(decorators).contract(new JAXRSContract()).encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, false));
    }

    @Produces
    @RequestScoped
    public IServiceApiDirectory targetService(@ApiDirectory Builder builder) {
        return builder.target(IServiceApiDirectory.class,
                "https://www.ramajudicial.gov.co/directorioPortal-portlet/api/jsonws/servicioapidirectorio/get-despacho-distritos.18/distrito/00053");
    }
    
    @Produces
    @Pdf
    public Builder createPDF() {

        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("JudicilaBranchPDF");

        FeignDecorators decorators = FeignDecorators.builder().withCircuitBreaker(circuitBreaker).build();

        return Resilience4jFeign.builder(decorators).contract(new JAXRSContract()).encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, false));
    }

    @Produces
    @RequestScoped
    public IServiceApiPDF targetPDF(@Pdf Builder builder) {
        return builder.target(IServiceApiPDF.class,
                "https://www.ramajudicial.gov.co/documents/2202878/99027036/023AutoVinculaAT.pdf/009f6e1e-745f-4716-a5f1-9dea3ac41d66");
    }
}
