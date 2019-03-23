package persistantdata;

import mediatheque.Utilisateur;

public class PUtilisateur implements Utilisateur {

	private String login;
	private String password;
	private boolean Bibliothecaire;

	public PUtilisateur(String login, String password, int bibliothecaire) {
		this(login);
		this.password = password;
		this.Bibliothecaire = (bibliothecaire == 1 ? true : false);

	}

	public PUtilisateur(String login) {
		this.login = login;
	}

	public String getlogin() {
		return login;
	}

	public String getpassword() {
		return password;
	}

	@Override
	public boolean isBibliothecaire() {
		// TODO Auto-generated method stub
		return Bibliothecaire;
	}
	

}
