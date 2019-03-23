package persistantdata;


import mediatheque.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public abstract class ABDocument<utilisateur> implements Document {

	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost:8889/scriptJEE?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

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

	private String titreDocument;

	private int numDocument;

	Mediatheque med = Mediatheque.getInstance();
	private String auteur;
	private PUtilisateur utilisateur;
	private String caracteristiques;

	public ABDocument(int numDocument, String titreDocument, String auteur, PUtilisateur utilisateur,String caracteristiques) {

		this.numDocument = numDocument;
		this.titreDocument = titreDocument;
		this.auteur = auteur;
		this.utilisateur = utilisateur;
		this.caracteristiques = caracteristiques;
	}

	public String getTitreDocument() {
		return titreDocument;
	}

	public String getauteurDocument() {
		return auteur;
	}

	public int getnumDocument() {
		return numDocument;
	}

	public String getCaracteristiques() {
		return caracteristiques;
	}

	public Object[] affiche() {

		String typeDocument = this.getClass().getSimpleName(); 
		String user = utilisateur == null ? " " : utilisateur.getlogin();
		Object[] document = { getnumDocument(), getTitreDocument(), getauteurDocument(), user,typeDocument,getCaracteristiques() };
		return document;

	}

	public void emprunter(Utilisateur a) throws EmpruntException {
		// TODO Auto-generated method stub
		this.utilisateur = (PUtilisateur) a;

		try {
			connexion();
			String reqEmprunterDoc = "UPDATE Document SET utilisateur=? WHERE idDocument= ? ";
			pstmt = conn.prepareStatement(reqEmprunterDoc);
			pstmt.setString(1, ((PUtilisateur) a).getlogin());
			pstmt.setInt(2, this.numDocument);
			pstmt.execute();
			deconnexion();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();

		}
	}

	public void retour() {
		try {
			connexion();
			String reqRetourDoc = "UPDATE Document SET utilisateur='' where idDocument=?";
			pstmt = conn.prepareStatement(reqRetourDoc);
			pstmt.setInt(1, this.numDocument);
			pstmt.execute();
			deconnexion();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	

}
