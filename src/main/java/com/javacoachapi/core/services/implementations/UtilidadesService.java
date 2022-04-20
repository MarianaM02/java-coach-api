package com.javacoachapi.core.services.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
import com.javacoachapi.core.models.dto.catalogo.ConceptoDTO;
import com.javacoachapi.core.models.dto.mail.FormMailRequest;
import com.javacoachapi.core.repository.ICapituloRepository;
import com.javacoachapi.core.services.IConceptoService;
import com.javacoachapi.core.services.IUtilidadesService;

@Service
public class UtilidadesService implements IUtilidadesService {
	private static final Logger LOGGER=LoggerFactory.getLogger(UtilidadesService.class);
	
	@Autowired
	ICapituloRepository capituloRepo;
	@Autowired
	CatalogoCapituloDTOConverter catCapituloDtoConverter;
	@Autowired
	IConceptoService conceptoServ;
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public CatalogoDTO traerCatalogoDTO() {
		LOGGER.info("Trayendo catálogo completo...");
		CatalogoDTO catalogo = new CatalogoDTO();
		catalogo.setCapitulos(
				capituloRepo.findAll().stream().map(catCapituloDtoConverter::convertir).collect(Collectors.toList()));
		LOGGER.info("Catálogo Obtenido.");
		return catalogo;
	}

	@Override
	public void crearPDF(String mail) throws IOException {
		LOGGER.info("Creando Reporte PDF...");
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
		// Añado parrafo al documento
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

			document.add(new Paragraph("Capítulo " + numeroCapitulo + ": " + nombreCapitulo).setFont(font)
					.setFontSize(12).setBold());
			document.add(new Paragraph("Dificultad: " + nombreDificultad).setFont(font).setFontSize(12));
			document.add(lista);
			document.add(new Paragraph(""));
		}

		// Close document
		document.close();
		LOGGER.info("Creación de Reporte exitosa.");
	}

	
	@Override
	public void mandarMailConJavaMailSender(FormMailRequest form) throws IOException, MailException {
		LOGGER.info("Creando Email...");
		ConceptoDTO concepto = conceptoServ.traerConceptoAleatorio();
		
		String to = form.getMail();
		String subject = "JCA - Tema de hoy!";
		final String template = this.htmlToString("./src/main/resources/static/MailTemplate.html")
				.replaceAll("NOMBRE", form.getNombre())
				.replaceAll("CONCEPTO", concepto.getNombre())
				.replaceAll("CONTENIDO", concepto.getContenido());

		// Para Mensaje de Texto Solamente
		/*
		 * SimpleMailMessage message = new SimpleMailMessage();
		 * message.setFrom("javacoachapi@gmail.com"); message.setTo(to);
		 * message.setSubject(subject); message.setText(template);
		 * emailSender.send(message);
		 */
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
	        
            public void prepare(MimeMessage mimeMessage) throws Exception {
        
                mimeMessage.setRecipient(Message.RecipientType.TO, 
                        new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress("javacoachapi@gmail.com"));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(template, "UTF-8", "html");
            }
        };
        LOGGER.info("Email creado.");
        
        LOGGER.info("Enviando Email...");
        this.emailSender.send(preparator);
        LOGGER.info("Email enviado exitosamente.");
	}

	private String htmlToString(String ruta) throws FileNotFoundException {
		String str = "";
		Scanner sc = new Scanner(new File(ruta));
		while (sc.hasNext()) {
			str += sc.nextLine();
		}
		sc.close();
		return str;
	}
}
