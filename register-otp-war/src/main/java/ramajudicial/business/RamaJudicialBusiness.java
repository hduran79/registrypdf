package ramajudicial.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ramajudicial.dto.DirectoryDTO;
import ramajudicial.dto.ServiceApiDirectoryDTO;
import ramajudicial.service.IDirectoryService;
import ramajudicial.service.IServiceApiDirectory;
import ramajudicial.service.IServiceApiPDF;

/**
 * https://jsoup.org/cookbook/extracting-data/example-list-links
 * 
 * @author hduran
 *
 */
@ApplicationScoped
public class RamaJudicialBusiness {

    private static final String HOME = "https://www.ramajudicial.gov.co/portal/inicio";
    private static final String TYPE = "a[href]";
    private static final String SERVICIOAPIDIRECTORIO = "https://www.ramajudicial.gov.co/directorioPortal-portlet/api/jsonws/servicioapidirectorio/get-despacho-distritos.18/distrito/%s/entidad/23";

    private IDirectoryService directory;
    private IServiceApiDirectory serviceApi;
    private IServiceApiPDF servicePDF;

    RamaJudicialBusiness() {
        /* for CDI */
    }

    @Inject
    public RamaJudicialBusiness(IDirectoryService directory, IServiceApiDirectory serviceApi,
            IServiceApiPDF servicePDF) {

        Objects.requireNonNull(directory);
        Objects.requireNonNull(serviceApi);
        Objects.requireNonNull(servicePDF);

        this.directory = directory;
        this.serviceApi = serviceApi;
        this.servicePDF = servicePDF;
    }

    public String downloadPDF(String html) throws IOException {

        Elements links = optionHref(HOME, TYPE);

        List<String> menu = new ArrayList<String>();
        menu.add("Tribunales Administrativos");
        menu.add("Tribunales Superiores");
        // menu.add("Juzgados Administrativos");
        // menu.add("Juzgados del Circuito");
        // menu.add("Juzgados de Ejecución de Penas y Medidas de Seguridad");
        // menu.add("Juzgados Municipales");
        // menu.add("Juzgados de Ejecución");

//        HashMap<String, String> administrativeCourts = new HashMap<>();
//        administrativeCourts.put("Tribunales Administrativos",
//                "https://www.ramajudicial.gov.co/directorioPortal-portlet/api/jsonws/servicioapidirectorio/get-distritos.17/entidad/23");

        for (Element link : links) {

            String linkText = link.text();

            if (menu.contains(linkText)) {
                
                System.out.println(String.format(" * a: <%s> (%s)", link.attr("abs:href"), link.text(), 35));

//                String linkAdminCourts = administrativeCourts.get(linkText);
//                List<DirectoryDTO> dto = directory.serviceDirectory();

//                dto.stream().forEach(c -> {
//                    String id = c.getId();
//                    String apiDirectoryURL = String.format(SERVICIOAPIDIRECTORIO, id);
//
//                    List<ServiceApiDirectoryDTO> serviceApiDirectoryURL = serviceApi.serviceApiDirectoryURL();
//
//                    serviceApiDirectoryURL.stream().forEach(w -> {
//                        String group = w.getAgrupacion();
//                        if ("SECRETARÍA GENERAL".equals(group)) {
//                            String generalURL = "https://www.ramajudicial.gov.co/" + w.getUrl();
//                            try {
//                                Elements generalLinks = optionHref(generalURL, TYPE);
//
//                                generalLinks.stream().forEach(p -> {
//                                    System.out.println(
//                                            String.format(" * a: <%s> (%s)", p.attr("abs:href"), p.text(), 35));
//                                });
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                });
            }
        }
        
        return "URL";
    }

    public Elements optionHref(String url, String type) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.select(type);
    }

}
