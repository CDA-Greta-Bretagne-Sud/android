package vannes.nantes.sqlite;
/*
 * Classe metier membre
 */
public class Membre {
	private String nom;
	private String prenom;
	private String email;
	
	public Membre(String nom, String prenom,String mail) {

		this.nom = nom;
		this.prenom = prenom;
		this.email=mail;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "membre [nom=" + nom + ", prenom=" + prenom + ",email:"+ email +"]";
	}

	
}
