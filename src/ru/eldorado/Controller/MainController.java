package ru.eldorado.Controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ru.eldorado.XMLParsing.CalculateXML;
import ru.eldorado.XMLParsing.CheckXSD;
/**
 * 
 * @author Brega
 *
 */

@Controller
@RequestMapping("/next.html")
public class MainController {


	private String saveDirectoryXML;
	private String saveDirectoryXSD;
	
	private String message;
	private String currency = " рублей";

	private String fnm; // filename

	private String fullpathXML;
	private String fullpathXSD;
	
	private CheckXSD cXSD = new CheckXSD();
	private String checkfileXSD;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.POST)
	public String onFileUpload(HttpServletRequest req,
			@RequestParam("xmlFile") CommonsMultipartFile xmlFile, Model model)
			throws Exception {

		CalculateXML xm = new CalculateXML();
		fnm = xmlFile.getOriginalFilename();

		model.addAttribute("file", fnm);
		
		saveDirectoryXML = req.getRealPath("/") + "files/";
		saveDirectoryXSD = req.getRealPath("/") + "xsd/";
		
		
		fullpathXML = saveDirectoryXML + fnm;
		fullpathXSD = saveDirectoryXSD + "custom.xsd";
		
		if (xmlFile != null && fnm.matches("(.)+(\\.xml)$") && !fnm.equals("")  ) {

			if (cXSD.checkXSD(fullpathXSD, fullpathXML) == true){
				
			checkfileXSD = "XSD-схема корректна";	
			System.out.println("Saving file: " + fnm + fullpathXML);
			xmlFile.transferTo(new File(fullpathXML));
			// xm.pars(fnm);
			xm.pars(fullpathXML);
			
			
			model.addAttribute("middleOrder",  xm.getFormatNumber() + currency); 
			model.addAttribute("minOrder",  xm.getMinValueOrder() + currency); //макс. сумма заказов
			model.addAttribute("maxOrder",  xm.getMaxValueOrder() + currency); // мин. сумма заказов
			model.addAttribute("countOrder",  xm.getCountOrder() + " шт.");
			model.addAttribute("totalSum",  xm.getTotal() +currency );
			model.addAttribute("maxVal",  xm.getMaxValCustomer() + currency); //клиент с максимальной суммой заказов
			
			message = "Файл загружен успешно";

			model.addAttribute("checkfileXSD", checkfileXSD);
			model.addAttribute("response", message);
			} else {
				checkfileXSD = "Неверная XSD-схема";
				model.addAttribute("checkfileXSD", checkfileXSD);
			}
		} else {
			message = "Файл  не загружен";
			model.addAttribute("response", message);
			System.out.println("Выберите расширение файла .xml");
		}

		// Парсинг

		return "next";

	}

}
