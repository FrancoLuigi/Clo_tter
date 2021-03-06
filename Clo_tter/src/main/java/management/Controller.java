package management;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import base.Association;
import base.Change;
import base.ClassClone;
import base.Clone;
import base.Commit;
import base.Committer;
import base.File1;
import base.Range;
import gui.DBConnectionSettingsFrame;
import gui.FrameAssociationClone;
import gui.FrameAssociationCommit;
import gui.FrameProgress;
import gui.FrameSelection;
import gui.FrameVisualizzaClassClones;
import gui.FrameVisualizzaCloni;
import gui.FrameVisualizzaCommits;
import gui.FrameVisualizzaCommitters;
import gui.HomeFrame;

public class Controller {

	public static Controller getInstance()
			throws ParseException, ParserConfigurationException, SAXException, IOException {
		if (singleControllerInstance == null) {
			singleControllerInstance = new Controller();
		}
		return singleControllerInstance;
	}

	private Controller() throws ParseException, ParserConfigurationException, SAXException, IOException {

		if (USE_DATABASE) {

			gestoreDB = GestoreDB.getInstance();
			DBSettings.readSettingsFromFile();
			gestoreDB.connectDB(DBSettings.host, DBSettings.port, DBSettings.user, DBSettings.pass);

			Scanner scanner = new Scanner(System.in);
			while (!gestoreDB.isConnected()) {
				System.out.println("\nInserisci i dati di configurazione per riprovare a connetterti.");
				System.out.print("Inserisci l'host (in genere: localhost): ");
				String host = scanner.nextLine();
				System.out.print("Inserisci la porta (in genere: 3306): ");
				String port = scanner.nextLine();
				System.out.print("Inserisci l'username:");
				String user = scanner.nextLine();
				System.out.print("Inserisci la password:");
				String pass = scanner.nextLine();
				DBSettings.updateSettingsInFile(host, port, user, pass);
				gestoreDB.connectDB(DBSettings.host, DBSettings.port, DBSettings.user, DBSettings.pass);
			}

			scanner.close();

		}

	

	}

	public void visualizzaHomeFrame() {
		HomeFrame.main(null);

	}

	public void visualizzaFrameImpostazioniDB() {
		DBConnectionSettingsFrame.main(null);
	}

	public void visualizzaProgress() {
		FrameProgress.main(null);
		// FramePrenotazione.main(null);
	}

	public void visualizzaFormAssociationCommit() {
		FrameAssociationCommit.main(null);
		// FramePrenotazione.main(null);
	}

	public void visualizzaFormAssociationClone() {
		FrameAssociationClone.main(null);
		// FramePrenotazione.main(null);
	}

	public void visualizzaFormSelection() {
		FrameSelection.main(null);
	}

	public void elaboraDati(String f) throws ParserConfigurationException, SAXException, IOException {

		gestoreDB.createDatabase(f);
		gestoreDB.createTableCommitters(f);
		gestoreDB.createTableClassClones(f);
		gestoreDB.createTableCommits(f);
		gestoreDB.createTableFiles(f);
		gestoreDB.createTableChanges(f);
		gestoreDB.createTableRanges(f);
		gestoreDB.createTableClones(f);
		gestoreDB.createTableAssociations(f);

		switch (f) {

		case "Dnsjava":

			Scanner sc1 = new Scanner(new File("files/" + f + "-2.1.6"+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestoreCo1 = new GestoreCommits(sc1, "2.1.6");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestoreCl1 = new GestoreClones("files/" + f + "-2.1.6" + ".xml", "2.1.6");

			HashMap<String, Commit> commits1 = gestoreCo1.getCommits();

			HashMap<String, Clone> clones1 = gestoreCl1.getClones();

			HashMap<String, File1> files1 = gestoreCo1.getFiles();

			HashMap<String, Committer> committers1 = gestoreCo1.getCommitters();
			HashMap<String, ClassClone> classClone1 = gestoreCl1.getClassi();
			HashMap<String, Range> ranges1 = gestoreCo1.getRanges();
			HashMap<String, Change> changes1 = gestoreCo1.getChanges();

			GestoreAssociation g1 = new GestoreAssociation(commits1, clones1);
			HashMap<String, Association> associations1 = g1.getAssociation();

			gestoreDB.insertFiles(files1);

			gestoreDB.insertCommitters(committers1);
			gestoreDB.insertClassClone(classClone1);
			gestoreDB.insertCommits(commits1);
			gestoreDB.insertChanges(changes1);

			gestoreDB.insertClones(clones1);

			gestoreDB.insertRanges(ranges1);

			gestoreDB.insertAssociations(associations1);

			Scanner sc2 = new Scanner(new File("files/" + f + "-2.1.7"+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestoreCo2 = new GestoreCommits(sc2, "2.1.7");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestoreCl2 = new GestoreClones("files/" + f + "-2.1.7" + ".xml", "2.1.7");

			HashMap<String, Commit> commits2 = gestoreCo2.getCommits();

			HashMap<String, Clone> clones2 = gestoreCl2.getClones();

			HashMap<String, File1> files2 = gestoreCo2.getFiles();

			HashMap<String, Committer> committers2 = gestoreCo2.getCommitters();
			HashMap<String, ClassClone> classClone2 = gestoreCl2.getClassi();
			HashMap<String, Range> ranges2 = gestoreCo2.getRanges();
			HashMap<String, Change> changes2 = gestoreCo2.getChanges();
			GestoreAssociation g2 = new GestoreAssociation(commits2, clones2);
			HashMap<String, Association> associations2 = g2.getAssociation();

			gestoreDB.insertFiles(files2);

			gestoreDB.insertCommitters(committers2);
			gestoreDB.insertClassClone(classClone2);
			gestoreDB.insertCommits(commits2);
			gestoreDB.insertChanges(changes2);

			gestoreDB.insertClones(clones2);

			gestoreDB.insertRanges(ranges2);

			gestoreDB.insertAssociations(associations2);

			Scanner sc3 = new Scanner(new File("files/" + f + "-2.1.8"+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestoreCo3 = new GestoreCommits(sc3, "2.1.8");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestoreCl3 = new GestoreClones("files/" + f + "-2.1.8" + ".xml", "2.1.8");

			HashMap<String, Commit> commits3 = gestoreCo3.getCommits();

			HashMap<String, Clone> clones3 = gestoreCl3.getClones();

			HashMap<String, File1> files3 = gestoreCo3.getFiles();

			HashMap<String, Committer> committers3 = gestoreCo3.getCommitters();
			HashMap<String, ClassClone> classClone3 = gestoreCl3.getClassi();
			HashMap<String, Range> ranges3 = gestoreCo3.getRanges();
			HashMap<String, Change> changes3 = gestoreCo3.getChanges();

			GestoreAssociation g3 = new GestoreAssociation(commits3, clones3);

			HashMap<String, Association> associations3 = g3.getAssociation();

			gestoreDB.insertFiles(files3);

			gestoreDB.insertCommitters(committers3);
			gestoreDB.insertClassClone(classClone3);
			gestoreDB.insertCommits(commits3);
			gestoreDB.insertChanges(changes3);

			gestoreDB.insertClones(clones3);

			gestoreDB.insertRanges(ranges3);

			gestoreDB.insertAssociations(associations3);

			System.err.println("Inseriment completed");

			break;

		case "Flink":

			Scanner sc4 = new Scanner(new File("files/" + f + "-1.3.2"+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestoreCo4 = new GestoreCommits(sc4, "1.3.2");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestoreCl4 = new GestoreClones("files/" + f + "-1.3.2" + ".xml", "1.3.2");

			HashMap<String, Commit> commits4 = gestoreCo4.getCommits();

			HashMap<String, Clone> clones4 = gestoreCl4.getClones();

			HashMap<String, File1> files4 = gestoreCo4.getFiles();

			HashMap<String, Committer> committers4 = gestoreCo4.getCommitters();
			HashMap<String, ClassClone> classClone4 = gestoreCl4.getClassi();
			HashMap<String, Range> ranges4 = gestoreCo4.getRanges();
			HashMap<String, Change> changes4 = gestoreCo4.getChanges();

			GestoreAssociation g4 = new GestoreAssociation(commits4, clones4);

			HashMap<String, Association> associations4 = g4.getAssociation();

			gestoreDB.insertFiles(files4);

			gestoreDB.insertCommitters(committers4);
			gestoreDB.insertClassClone(classClone4);
			gestoreDB.insertCommits(commits4);
			gestoreDB.insertChanges(changes4);

			gestoreDB.insertClones(clones4);

			gestoreDB.insertRanges(ranges4);

			gestoreDB.insertAssociations(associations4);

			Scanner sc5 = new Scanner(new File("files/" + f + "-1.4.3"+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestoreCo5 = new GestoreCommits(sc5, "1.4.3");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestoreCl5 = new GestoreClones("files/" + f + "-1.4.3" + ".xml", "1.4.3");

			HashMap<String, Commit> commits5 = gestoreCo5.getCommits();

			HashMap<String, Clone> clones5 = gestoreCl5.getClones();

			HashMap<String, File1> files5 = gestoreCo5.getFiles();

			HashMap<String, Committer> committers5 = gestoreCo5.getCommitters();
			HashMap<String, ClassClone> classClone5 = gestoreCl5.getClassi();
			HashMap<String, Range> ranges5 = gestoreCo5.getRanges();
			HashMap<String, Change> changes5 = gestoreCo5.getChanges();

			GestoreAssociation g5 = new GestoreAssociation(commits5, clones5);

			HashMap<String, Association> associations5 = g5.getAssociation();

			gestoreDB.insertFiles(files5);

			gestoreDB.insertCommitters(committers5);
			gestoreDB.insertClassClone(classClone5);
			gestoreDB.insertCommits(commits5);
			gestoreDB.insertChanges(changes5);

			gestoreDB.insertClones(clones5);

			gestoreDB.insertRanges(ranges5);

			gestoreDB.insertAssociations(associations5);

			Scanner sc6 = new Scanner(new File("files/" + f + "-1.5.0"+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestoreCo6 = new GestoreCommits(sc6, "1.5.0");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestoreCl6 = new GestoreClones("files/" + f + "-1.5.0" + ".xml", "1.5.0");

			HashMap<String, Commit> commits6 = gestoreCo6.getCommits();

			HashMap<String, Clone> clones6 = gestoreCl6.getClones();

			HashMap<String, File1> files6 = gestoreCo6.getFiles();

			HashMap<String, Committer> committers6 = gestoreCo6.getCommitters();
			HashMap<String, ClassClone> classClone6 = gestoreCl6.getClassi();
			HashMap<String, Range> ranges6 = gestoreCo6.getRanges();
			HashMap<String, Change> changes6 = gestoreCo6.getChanges();

			GestoreAssociation g6 = new GestoreAssociation(commits6, clones6);

			HashMap<String, Association> associations6 = g6.getAssociation();

			gestoreDB.insertFiles(files6);

			gestoreDB.insertCommitters(committers6);
			gestoreDB.insertClassClone(classClone6);
			gestoreDB.insertCommits(commits6);
			gestoreDB.insertChanges(changes6);

			gestoreDB.insertClones(clones6);

			gestoreDB.insertRanges(ranges6);

			gestoreDB.insertAssociations(associations6);

			System.err.println("Inseriment completed");

			break;

		case "Groovy":

			Scanner sc7 = new Scanner(new File("files/" + f + "-2.4.15"+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestoreCo7 = new GestoreCommits(sc7, "2.4.15");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestoreCl7 = new GestoreClones("files/" + f + "-2.4.15" + ".xml", "2.4.15");

			HashMap<String, Commit> commits7 = gestoreCo7.getCommits();

			HashMap<String, Clone> clones7 = gestoreCl7.getClones();

			HashMap<String, File1> files7 = gestoreCo7.getFiles();

			HashMap<String, Committer> committers7 = gestoreCo7.getCommitters();
			HashMap<String, ClassClone> classClone7 = gestoreCl7.getClassi();
			HashMap<String, Range> ranges7 = gestoreCo7.getRanges();
			HashMap<String, Change> changes7 = gestoreCo7.getChanges();

			GestoreAssociation g7 = new GestoreAssociation(commits7, clones7);

			HashMap<String, Association> associations7 = g7.getAssociation();

			gestoreDB.insertFiles(files7);

			gestoreDB.insertCommitters(committers7);
			gestoreDB.insertClassClone(classClone7);
			gestoreDB.insertCommits(commits7);
			gestoreDB.insertChanges(changes7);

			gestoreDB.insertClones(clones7);

			gestoreDB.insertRanges(ranges7);

			gestoreDB.insertAssociations(associations7);

			Scanner sc8 = new Scanner(new File("files/" + f + "-2.5"+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestoreCo8 = new GestoreCommits(sc8, "2.5");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestoreCl8 = new GestoreClones("files/" + f + "-2.5" + ".xml", "2.5");

			HashMap<String, Commit> commits8 = gestoreCo8.getCommits();

			HashMap<String, Clone> clones8 = gestoreCl8.getClones();

			HashMap<String, File1> files8 = gestoreCo8.getFiles();

			HashMap<String, Committer> committers8 = gestoreCo8.getCommitters();
			HashMap<String, ClassClone> classClone8 = gestoreCl8.getClassi();
			HashMap<String, Range> ranges8 = gestoreCo8.getRanges();
			HashMap<String, Change> changes8 = gestoreCo8.getChanges();

			GestoreAssociation g8 = new GestoreAssociation(commits8, clones8);

			HashMap<String, Association> associations8 = g8.getAssociation();

			gestoreDB.insertFiles(files8);

			gestoreDB.insertCommitters(committers8);
			gestoreDB.insertClassClone(classClone8);
			gestoreDB.insertCommits(commits8);
			gestoreDB.insertChanges(changes8);

			gestoreDB.insertClones(clones8);

			gestoreDB.insertRanges(ranges8);

			gestoreDB.insertAssociations(associations8);

			Scanner sc9 = new Scanner(new File("files/" + f + "-3.0"+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestoreCo9 = new GestoreCommits(sc9, "3.0");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestoreCl9 = new GestoreClones("files/" + f + "-3.0" + ".xml", "3.0");

			HashMap<String, Commit> commits9 = gestoreCo9.getCommits();

			HashMap<String, Clone> clones9 = gestoreCl9.getClones();

			HashMap<String, File1> files9 = gestoreCo9.getFiles();

			HashMap<String, Committer> committers9 = gestoreCo9.getCommitters();
			HashMap<String, ClassClone> classClone9 = gestoreCl9.getClassi();
			HashMap<String, Range> ranges9 = gestoreCo9.getRanges();
			HashMap<String, Change> changes9 = gestoreCo9.getChanges();

			GestoreAssociation g9 = new GestoreAssociation(commits9, clones9);

			HashMap<String, Association> associations9 = g9.getAssociation();

			gestoreDB.insertFiles(files9);

			gestoreDB.insertCommitters(committers9);
			gestoreDB.insertClassClone(classClone9);
			gestoreDB.insertCommits(commits9);
			gestoreDB.insertChanges(changes9);

			gestoreDB.insertClones(clones9);

			gestoreDB.insertRanges(ranges9);

			gestoreDB.insertAssociations(associations9);

			System.err.println("Inseriment completed");

			break;

		default:
			Scanner sc = new Scanner(new File("files/" + f+".txt"));
			// Scanner sc1=new Scanner(new File("test"));
			GestoreCommits gestore = new GestoreCommits(sc, "0");

			// GestoreCommits gestore2=new GestoreCommits(sc1);
			GestoreClones gestore1 = new GestoreClones("files/" + f + ".xml", "0");

			HashMap<String, Commit> commits = gestore.getCommits();

			HashMap<String, Clone> clones = gestore1.getClones();

			HashMap<String, File1> files = gestore.getFiles();

			HashMap<String, Committer> committers = gestore.getCommitters();
			HashMap<String, ClassClone> classClone = gestore1.getClassi();
			HashMap<String, Range> ranges = gestore.getRanges();
			HashMap<String, Change> changes = gestore.getChanges();

			GestoreAssociation g = new GestoreAssociation(commits, clones);

			HashMap<String, Association> associations = g.getAssociation();

			gestoreDB.insertFiles(files);

			gestoreDB.insertCommitters(committers);
			gestoreDB.insertClassClone(classClone);
			gestoreDB.insertCommits(commits);
			gestoreDB.insertChanges(changes);

			gestoreDB.insertClones(clones);

			gestoreDB.insertRanges(ranges);

			gestoreDB.insertAssociations(associations);

			System.err.println("Inseriment completed");

			break;

		}

	}

	public void visualizzaCloni() throws ParserConfigurationException, SAXException, IOException, ParseException {
		new FrameVisualizzaCloni();
	}

	public void visualizzaCommits() throws ParserConfigurationException, SAXException, IOException, ParseException {
		new FrameVisualizzaCommits();
	}

	public void visualizzaCommitters() throws ParserConfigurationException, SAXException, IOException, ParseException {
		new FrameVisualizzaCommitters();
	}

	public void visualizzaClassClones() throws ParserConfigurationException, SAXException, IOException, ParseException {
		new FrameVisualizzaClassClones();
	}

	public void chiudiFrame(JFrame frameToClose) {
		frameToClose.dispose();
	}

	public void visualizzaErrore(String errore) {
		JOptionPane.showMessageDialog(null, errore, "Errore", JOptionPane.ERROR_MESSAGE);
	}

	public void visualizzaNotifica(String notifica) {
		JOptionPane.showMessageDialog(null, notifica);
	}

	public boolean checkDBConnection() {
		return gestoreDB.checkConnectionAndReconnect();
	}

	public void setImpostazioniDB(String host, String port, String user, String password) {
		DBSettings.updateSettingsInFile(host, port, user, password);
		gestoreDB.connectDB(DBSettings.host, DBSettings.port, DBSettings.user, DBSettings.pass);
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public static boolean USE_DATABASE = false;
	public static boolean SETTINGS_UPDATED;

	private static Controller singleControllerInstance;
	private String file;
	private GestoreDB gestoreDB;
	
}
