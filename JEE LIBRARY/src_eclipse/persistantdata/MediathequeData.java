package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mediatheque.*;

public class MediathequeData implements PersistentMediatheque {
	
	
	
	/*
	 * static { Mediatheque.getInstance().setData(new MediathequeData());
	 * 
	 * //Connection � la BD String login,password,adresse; login = "fay"; password =
	 * "123456"; adresse = "jdbc:oracle:thin:@localhost";
	 * 
	 * try { Class.forName("oracle.jdbc.OracleDriver"); conn =
	 * DriverManager.getConnection(adresse,login,password); } catch (SQLException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
	 * (ClassNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
/*
 * Connexion a une base de donnnée sur phpmyadmin depuis xamp,wamp...
 * nom du script : scriptJEE
 */
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // oracle.jdbc.OracleDriver
	private final String DB_URL = "jdbc:mysql://localhost:8889/scriptJEE?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; //jdbc:oracle:thin:@localhost

	// Database credentials
	private final String USER = "root";
	private final String PASS = "root";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	void connexion() throws ClassNotFoundException, SQLException {
		// Register JDBC driver
		Class.forName(JDBC_DRIVER);
		// Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		// Create a Statement object and build table
		stmt = conn.createStatement();
	}

	void deconnexion() throws SQLException {
		// Clean-up environment
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {
	}

	/*
	 * (non-Javadoc)
	 * @see mediatheque.PersistentMediatheque#tousLesDocuments()
	 * Recupere toutes les requetes afin d'ajouter un document dans une liste 
	 */
	@Override
	public List<Document> tousLesDocuments() {
		// renvoie la liste de tous les documents de la bibliothèque
		ArrayList<Document> tousLesDocs = new ArrayList<Document>();

		try {
			connexion();
			String reqTousDocs = "SELECT * FROM Document ";
			pstmt = conn.prepareStatement(reqTousDocs);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			int type = rs.getInt("type");
			String utilisateur = rs.getString("utilisateur");
			PUtilisateur user = utilisateur == null ? null : new PUtilisateur(utilisateur);
			
			switch (type){
				case 1 :
				tousLesDocs.add(new Livre(rs.getInt("idDocument"), rs.getString("Nom"), rs.getString("auteur"), user, rs.getString("caracteristiques")));
				break;
				case 2 :
				tousLesDocs.add(new CD(rs.getInt("idDocument"), rs.getString("Nom"), rs.getString("auteur"), user, rs.getString("caracteristiques")));
				break;
				case 3 :
				tousLesDocs.add(new DVD(rs.getInt("idDocument"), rs.getString("Nom"), rs.getString("auteur"), user, rs.getString("caracteristiques")));
				break;
			}
			}
			deconnexion();
		} catch (SQLException | ClassNotFoundException e) {
		}
		return tousLesDocs;

	}
/*
 * (non-Javadoc)
 * @see mediatheque.PersistentMediatheque#getUser(java.lang.String, java.lang.String)
 * Permet de recupérer les utilisateurs de la base de donnée 
 */
	@Override
	public Utilisateur getUser(String login, String Password) {
		Utilisateur utilisateur = null;
		try {
			connexion();
			String requeteSQL = "SELECT * FROM Utilisateur WHERE login = ? AND Password = ?";
			pstmt = conn.prepareStatement(requeteSQL);
			pstmt.setString(1, login);
			pstmt.setString(2, Password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				utilisateur = new PUtilisateur(login, Password, rs.getInt("bibliothecaire"));
			}
			deconnexion();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;
	}
	
/*
 * (non-Javadoc)
 * @see mediatheque.PersistentMediatheque#getDocument(int)
 * Permet de recupérer les documents dans la base de donnée grace a leur numero de document
 */
	@Override
	public Document getDocument(int numDocument) {

		Document doc = null;
		try {
			connexion();
			String requeteSQL = "SELECT * FROM Document WHERE idDocument =? ";
			pstmt = conn.prepareStatement(requeteSQL);
			pstmt.setInt(1, numDocument);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String nom = rs.getString("Nom");
				String auteur = rs.getString("auteur");
				String utilisateur = rs.getString("utilisateur");
				PUtilisateur user = utilisateur == null ? null : new PUtilisateur(utilisateur);
				int type = rs.getInt("type");
				String caracteristiques = rs.getString("caracteristiques");
					switch (type) {
					case 1:
						doc = new Livre(numDocument, nom, auteur, user,caracteristiques);
						break;
					case 2:
						doc = new CD(numDocument, nom, auteur, user,caracteristiques);
						break;
					case 3:
						doc = new DVD(numDocument, nom, auteur, user,caracteristiques);
						break;
					}
			}
			deconnexion();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;

	}
	
	public Document deleteDocument(int numDocument) {

		Document doc = null;
		try {
			connexion();
			String requeteSQL = "DELETE * FROM Document WHERE idDocument =? ";
			pstmt = conn.prepareStatement(requeteSQL);
			pstmt.setInt(1, numDocument);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String nom = rs.getString("Nom");
				String auteur = rs.getString("auteur");
				String utilisateur = rs.getString("utilisateur");
				PUtilisateur user = utilisateur == null ? null : new PUtilisateur(utilisateur);
				int type = rs.getInt("type");
				String caracteristiques = rs.getString("caracteristiques");
					switch (type) {
					case 1:
						doc = new Livre(numDocument, nom, auteur, user,caracteristiques);
						break;
					case 2:
						doc = new CD(numDocument, nom, auteur, user,caracteristiques);
						break;
					case 3:
						doc = new DVD(numDocument, nom, auteur, user,caracteristiques);
						break;
					}
			}
			deconnexion();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;

	}
/*
 * (non-Javadoc)
 * @see mediatheque.PersistentMediatheque#nouveauDocument(int, java.lang.Object[])
 * Creation d'un nouveau document en lui definissant differents arguments 
 */
	@Override
	public void nouveauDocument(int type, Object... args) {

		try {
			connexion();
			String reqAjoutDoc = "INSERT INTO DOCUMENT(type,Nom,auteur,utilisateur,caracteristiques) VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(reqAjoutDoc);
			pstmt.setInt(1, type);
			pstmt.setString(2, (String) args[0]);
			pstmt.setString(3, (String) args[1]);
			pstmt.setString(4, (String) args[2]);
			pstmt.setString(5, (String) args[3]);
			pstmt.execute();
			deconnexion();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * private void ajouterTousLesLivres(ArrayList<Document> tousLesDocs) throws
	 * SQLException, EmpruntException { try { connexion(); String reqTousLivres =
	 * "SELECT * FROM Document WHERE idDocument=?"; pstmt =
	 * conn.prepareStatement(reqTousLivres); pstmt.executeQuery();
	 * 
	 * deconnexion(); } catch (SQLException | ClassNotFoundException e) {
	 * 
	 * }
	 * 
	 * }
	 */

	/*
	 * private void ajouterTousLesCDs(ArrayList<Document> tousLesDocs) throws
	 * SQLException, EmpruntException { try { connexion(); String reqTousCDs =
	 * "SELECT * FROM Document d, CD c WHERE d.idDocument=c.idDocument";
	 * PreparedStatement st = conn.prepareStatement(reqTousCDs); ResultSet res =
	 * st.executeQuery();
	 * 
	 * while (res.next()) { String utilisateur = res.getString("utilisateur");
	 * PUtilisateur user = utilisateur == null ? null : new
	 * PUtilisateur(utilisateur); tousLesDocs.add(new CD(res.getInt("idDocument"),
	 * res.getString("Nom"), res.getString("auteur"), user)); } deconnexion(); }
	 * catch (SQLException | ClassNotFoundException e) {
	 * 
	 * }
	 * 
	 * }
	 * 
	 * private void ajouterTousLesDVDs(ArrayList<Document> tousLesDocs) throws
	 * SQLException, EmpruntException { try { connexion(); String reqTousCDs =
	 * "SELECT * FROM Document d, DVD v WHERE d.idDocument=v.idDocument";
	 * PreparedStatement st = conn.prepareStatement(reqTousCDs); ResultSet res =
	 * st.executeQuery();
	 * 
	 * while (res.next()) { String utilisateur = res.getString("utilisateur");
	 * PUtilisateur user = utilisateur == null ? null : new
	 * PUtilisateur(utilisateur); tousLesDocs.add(new DVD(res.getInt("idDocument"),
	 * res.getString("Nom"), res.getString("auteur"), user)); } deconnexion(); }
	 * catch (SQLException | ClassNotFoundException e) {
	 * 
	 * }
	 * 
	 * }
	 */

}
