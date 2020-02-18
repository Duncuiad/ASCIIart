import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;

/** Salva la schermata corrente in un file di nome AsciiArt (X).txt. Attivato da un click del mouse
 * Sovrascrive il file precedente se click destro
 * Scrive su un altro file se click sinistro
 *
 */
public final class Salva extends Strumento {

	//ATTRIBUTI

	/** Conta il numero di nuovi file scritti
	 * (non viene modificato se lo strumento sovrascrive un file precedente) */
	private static int fileCount = -1;

	/** Legge il click del mouse */
	private MouseClick click;

	//COSTRUTTORI
	/**	@see Strumento#Strumento(Canvas)
	 */
	public Salva(Canvas canvas) {
		super(canvas);
	}

	//METODI
	@Override
	public void ricevi(EventoDiMouse e) {

		if (e instanceof MouseClick) {
			click = (MouseClick) e;

			if ( (click.rightClick())) { // click destro
				// sovrascrivi
				if (fileCount == -1) {
					fileCount++;
				}
			} else {
				fileCount++;
			}

			File fileDiTesto = new File("ASCIIArt" + (fileCount != 0 ?  " (" + String.valueOf(fileCount)  + ")" : "") + ".txt");
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(fileDiTesto));

				out.write(canvas.toString(), 0, (canvas.getC()+1)*(canvas.getR()));

				out.close();
			} catch (IOException eccezione) {
				System.err.println(eccezione.toString());
			}

		}

	}

	@Override
	protected void reset() {
		click = null;
	}

}
