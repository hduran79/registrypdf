package business.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ramajudicial.dto.DirectoryDTO;
import ramajudicial.service.IDirectoryService;

/**
 * https://jsoup.org/cookbook/extracting-data/example-list-links
 * 
 * @author hduran
 *
 */
@ApplicationScoped
public class HtmlBusiness {

	private static final String HOME = "https://www.ramajudicial.gov.co/portal/inicio";
	private static final String TYPE = "a[href]";
	
	private IDirectoryService directory;

	HtmlBusiness() {
        /* for CDI */
    }

    @Inject
    public HtmlBusiness(IDirectoryService directory) {
        Objects.requireNonNull(directory);
        this.directory = directory;
    }
    
    public String codeOtp(String html) throws IOException {

		Elements links = optionHref(HOME, TYPE);

		List<String> menu = new ArrayList<String>();
		menu.add("Tribunales Administrativos");
//		menu.add("Tribunales Superiores");
//		menu.add("Juzgados Administrativos");
//		menu.add("Juzgados del Circuito");
//		menu.add("Juzgados de Ejecución de Penas y Medidas de Seguridad");
//		menu.add("Juzgados Municipales");
//		menu.add("Juzgados de Ejecución");

		for (Element link : links) {
			String url = "";
			if (menu.contains(link.text())) {
				
				System.out.println(String.format(" * a: <%s> (%s)", link.attr("abs:href"), link.text(), 35));
				url = link.attr("abs:href");
				Elements subWeb = optionHref(url, TYPE);
				
				for (Element subLink : subWeb) {
					System.out.println(String.format(" * %s - %s", subLink.attr("abs:href"), subLink.text(), 35));
					List<DirectoryDTO> dto = directory.serviceDirectory();
					System.out.println(dto);
				}
			}
		}

		return "URL";
	}

	public Elements optionHref(String url, String type) throws IOException {
		Document doc = Jsoup.connect(url).get();
		return doc.select(type);
	}
}
