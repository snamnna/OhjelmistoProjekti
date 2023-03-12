package simulaattori.view;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import simulaattori.MainApp;
import simulaattori.model.TapahtumanTyyppi;
import simulaattori.model.util.IPalvelupiste;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SimulaattoriController {

	private MainApp mainApp;
	private GraphicsContext gc;
	private Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet;
	private List<TapahtumanTyyppi> tyypit;

	@FXML
	private AnchorPane parentPane;
	@FXML
	private Canvas canvas;

	@FXML
	private void initialize() {
		gc = canvas.getGraphicsContext2D();
		tyypit = Arrays.asList(TapahtumanTyyppi.ARR, TapahtumanTyyppi.YLARR,
				TapahtumanTyyppi.ELARR, TapahtumanTyyppi.LABRA_ARRIVAL);
	}

	public void tyhjennaNaytto() {
		gc.setFill(Color.rgb(18, 18, 18));
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public void visualisoi() {
		// visualisoidaan palvelupisteet bufferi canvakseen ensin
		// jotta käyttöliittymän canvakseen ei tule keskeneräisiä visualisointeja
		Canvas buffer = new Canvas(canvas.getWidth(), canvas.getHeight());
		GraphicsContext bufferGC = buffer.getGraphicsContext2D();

		bufferGC.setFill(Color.rgb(18, 18, 18));
		bufferGC.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		bufferGC.setFill(Color.RED);

		int palvelupisteCount = 0;
		for (int i = 0; i < tyypit.size(); i++) {
			palvelupisteCount += palvelupisteet.get(tyypit.get(i)).size();
		}

		double width = canvas.getWidth() / palvelupisteCount;
		int count = 0;
		for (TapahtumanTyyppi tyyppi : tyypit) {
			List<IPalvelupiste> palvelupisteLista = palvelupisteet.get(tyyppi);
			if (palvelupisteLista == null) {
				continue;
			}

			for (IPalvelupiste palvelupiste : palvelupisteLista) {
				double x = count * width;
				double y = 20;

				String jononTyyppi = palvelupiste.getTyyppi().toString();
				bufferGC.setFill(Color.rgb(128, 128, 128));
				bufferGC.setTextAlign(TextAlignment.CENTER);
				bufferGC.fillText(jononNimi(jononTyyppi), x + width / 2, y - 10);
				bufferGC.setFill(getJononVari(jononTyyppi));

				for (int i = 0; i < palvelupiste.getAsiakasMaaraJonossa(); i++) {
					bufferGC.fillOval(x + width / 2 - 5, y, 10, 10);
					y += 20;
					if (y >= canvas.getHeight()) {
						y = 20;
					}
				}
				y += 20;
				count++;
			}
		}

		// piirretään bufferoitu kuva käyttöliittymään
		gc.drawImage(buffer.snapshot(null, null), 0, 0);
	}

	private String jononNimi(String tyyppi) {
		switch (tyyppi.toString()) {
		case "ARR":
			return "Sairaanhoitaja";
		case "YLARR":
			return "Yleislääkäri";
		case "ELARR":
			return "Erikoislääkäri";
		case "LABRA_ARRIVAL":
			return "Laboratorio";
		default:
			return "?";
		}
	}

	private static Color getJononVari(String tyyppi) {
		switch (tyyppi) {
		case "ARR":
			return Color.GHOSTWHITE;
		case "YLARR":
			return Color.CYAN;
		case "ELARR":
			return Color.DARKCYAN;
		case "LABRA_ARRIVAL":
			return Color.YELLOW;
		default:
			return Color.WHITE;
		}
	}

	public void alustukset() {
		tyhjennaNaytto();
		this.palvelupisteet = mainApp.haePalvelupisteet();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
