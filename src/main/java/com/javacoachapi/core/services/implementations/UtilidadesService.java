package com.javacoachapi.core.services.implementations;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.javacoachapi.core.models.converters.CatalogoCapituloDTOConverter;
import com.javacoachapi.core.models.dto.catalogo.CatalogoCapituloDTO;
import com.javacoachapi.core.models.dto.catalogo.CatalogoDTO;
import com.javacoachapi.core.repository.ICapituloRepository;
import com.javacoachapi.core.services.IUtilidadesService;

@Service
public class UtilidadesService implements IUtilidadesService {

	@Autowired
	ICapituloRepository capituloRepo;
	@Autowired
	CatalogoCapituloDTOConverter catCapituloDtoConverter;

	@Override
	public CatalogoDTO traerCatalogoDTO() {
		CatalogoDTO catalogo = new CatalogoDTO();
		catalogo.setCapitulos(
				capituloRepo.findAll().stream().map(catCapituloDtoConverter::convertir).collect(Collectors.toList()));
		return catalogo;
	}

	@Override
	public void crearPDF(Object data) throws IOException {

		String rutaBanner = "./src/main/resources/static/Java Coach API Reporte Banner.png";
		String rutaArchivo = "ReporteJCA.pdf";

		// Creo el Documento
		PdfWriter writer = new PdfWriter(rutaArchivo);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);

		PdfFont font = PdfFontFactory.createFont(FontConstants.COURIER);

		// Creo imagen con la ruta
		Image banner = new Image(ImageDataFactory.create(rutaBanner));
		// Añado imagen al documento
		document.add(banner);
		//  Añado parrafo al documento
		document.add(new Paragraph(""));

		CatalogoDTO catalogo = traerCatalogoDTO();
		List lista;
		int numeroCapitulo;
		String nombreCapitulo;
		String nombreDificultad;
		int numeroConceptos;
		int numeroPreguntas;
		int numeroEjemplos;

		for (CatalogoCapituloDTO cap : catalogo.getCapitulos()) {
			numeroCapitulo = cap.getNumero();
			nombreCapitulo = cap.getNombre();
			nombreDificultad = cap.getNivelNombre();
			numeroConceptos = cap.getConceptos().size();
			numeroPreguntas = cap.getConceptos().stream().mapToInt(c -> c.getPreguntas().size()).sum();
			numeroEjemplos = cap.getConceptos().stream().mapToInt(c -> c.getEjemplos().size()).sum();

			lista = new List().setSymbolIndent(10).setListSymbol("\u2022").setFont(font);
			lista.add(new ListItem("Número de Conceptos: " + numeroConceptos))
				.add(new ListItem("Número de Preguntas: " + numeroPreguntas))
				.add(new ListItem("Número de Ejemplos: " + numeroEjemplos));

			document.add(new Paragraph("Capítulo " + numeroCapitulo + ": " + nombreCapitulo).setFont(font).setFontSize(12).setBold());
			document.add(new Paragraph("Dificultad: " + nombreDificultad).setFont(font).setFontSize(12));
			document.add(lista);
			document.add(new Paragraph(""));
		}
		
		// Close document
		document.close();

	}

	@Override
	public void mandarMail(Object data) {
		// TODO Auto-generated method stub

	}

}
