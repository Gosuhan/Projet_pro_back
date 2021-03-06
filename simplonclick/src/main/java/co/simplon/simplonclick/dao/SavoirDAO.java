package co.simplon.simplonclick.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.simplon.simplonclick.model.CategorieSavoir;
import co.simplon.simplonclick.model.Inscription;
import co.simplon.simplonclick.model.Membre;
import co.simplon.simplonclick.model.Ressource;

@Repository
public class SavoirDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataSource dataSource;

	@Autowired
	public SavoirDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	/**
	 * Rechercher les ressources liées à un savoir
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Ressource> recupererRessourcesDeSavoir(Long id) throws Exception {
		Ressource ressource;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Ressource> listeRessources = new ArrayList<Ressource>();

		try {
			// Requete SQL
			sql = "SELECT ressource.*\r\n" + 
					"FROM ressource\r\n" + 
					"WHERE id_ressource \r\n" + 
					"IN (\r\n" + 
					"SELECT id_ressource\r\n" + 
					"FROM ressource\r\n" + 
					"WHERE savoir_id_savoir = ?);";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				ressource = recupererRessourceRS(rs);
				listeRessources.add(ressource);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeRessources;
	}

	private Ressource recupererRessourceRS(ResultSet rs) throws SQLException {
		Ressource ressource = new Ressource();
		ressource.setId_ressource(rs.getLong("id_ressource"));
		ressource.setUrl(rs.getString("url"));
		ressource.setNom_ressource(rs.getString("nom_ressource"));

		return ressource;
	}

	private void logSQL(PreparedStatement pstmt) {
		String sql;

		if (pstmt == null)
			return;

		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}
	
	/**
	 * Rechercher la catégorie liée à un savoir
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CategorieSavoir recupererCategorieSavoirDeSavoir(Long id) throws Exception {
		CategorieSavoir categorieSavoir = new CategorieSavoir();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			// Requete SQL
			sql = "SELECT categorie_savoir.*\r\n" + 
					"FROM categorie_savoir\r\n" + 
					"WHERE id_categorie_savoir \r\n" + 
					"IN (\r\n" + 
					"SELECT categorie_savoir_id_categorie_savoir\r\n" + 
					"FROM savoir\r\n" + 
					"WHERE id_savoir = ?);";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				categorieSavoir = recupererCategorieSavoirRS(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return categorieSavoir;
	}

	private CategorieSavoir recupererCategorieSavoirRS(ResultSet rs) throws SQLException {
		CategorieSavoir categorieSavoir = new CategorieSavoir();
		categorieSavoir.setId_categorie_savoir(rs.getLong("id_categorie_savoir"));
		categorieSavoir.setNom_categorie_savoir(rs.getString("nom_categorie_savoir"));

		return categorieSavoir;
	}

	
	/**
	 * Rechercher les inscriptions liées à un savoir
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Inscription> recupererInscriptionsDeSavoir(Long id) throws Exception {
		Inscription inscription;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Inscription> listeInscriptions = new ArrayList<Inscription>();

		try {
			// Requete SQL
			sql = "SELECT inscription.*\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE id_inscription\r\n" + 
					"IN (\r\n" + 
					"SELECT id_inscription\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?);";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				inscription = recupererInscriptionRS(rs);
				listeInscriptions.add(inscription);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeInscriptions;
	}

	private Inscription recupererInscriptionRS(ResultSet rs) throws SQLException {
		Inscription inscription = new Inscription();
		inscription.setId_inscription(rs.getLong("id_inscription"));
		inscription.setNom_inscription(rs.getString("nom_inscription"));

		return inscription;
	}
	
	/**
	 * Rechercher les membres liés à un savoir
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Membre> recupererMembresDeSavoir(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.* FROM membre \r\n" + 
					"WHERE id_membre \r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?)";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				membre = recupererMembreRS(rs);
				listeMembres.add(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMembres;
	}
	
	public List<Membre> recupererMembresPasseursDeSavoir(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.* FROM membre \r\n" + 
					"WHERE id_membre \r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?\r\n" +
					"AND type_inscription_id_type_inscription = 1)";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				membre = recupererMembreRS(rs);
				listeMembres.add(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMembres;
	}
	
	public List<Membre> recupererMembresPasseursDebutantsDeSavoir(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.* FROM membre \r\n" + 
					"WHERE id_membre \r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?\r\n" +
					"AND type_inscription_id_type_inscription = 1\r\n" +
					"AND niveau_savoir_id_niveau_savoir = 1)";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				membre = recupererMembreRS(rs);
				listeMembres.add(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMembres;
	}
	
	public List<Membre> recupererMembresPasseursIntermediairesDeSavoir(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.* FROM membre \r\n" + 
					"WHERE id_membre \r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?\r\n" +
					"AND type_inscription_id_type_inscription = 1\r\n" +
					"AND niveau_savoir_id_niveau_savoir = 2)";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				membre = recupererMembreRS(rs);
				listeMembres.add(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMembres;
	}
	
	public List<Membre> recupererMembresPasseursConfirmesDeSavoir(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.* FROM membre \r\n" + 
					"WHERE id_membre \r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?\r\n" +
					"AND type_inscription_id_type_inscription = 1\r\n" +
					"AND niveau_savoir_id_niveau_savoir = 3)";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				membre = recupererMembreRS(rs);
				listeMembres.add(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMembres;
	}
	
	public List<Membre> recupererMembresReceveursDeSavoir(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.* FROM membre \r\n" + 
					"WHERE id_membre \r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?\r\n" +
					"AND type_inscription_id_type_inscription = 2)";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				membre = recupererMembreRS(rs);
				listeMembres.add(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMembres;
	}
	
	public List<Membre> recupererMembresReceveursDebutantsDeSavoir(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.* FROM membre \r\n" + 
					"WHERE id_membre \r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?\r\n" +
					"AND type_inscription_id_type_inscription = 2\r\n" +
					"AND niveau_savoir_id_niveau_savoir = 1)";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				membre = recupererMembreRS(rs);
				listeMembres.add(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMembres;
	}
	
	public List<Membre> recupererMembresReceveursIntermediairesDeSavoir(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.* FROM membre \r\n" + 
					"WHERE id_membre \r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?\r\n" +
					"AND type_inscription_id_type_inscription = 2\r\n" +
					"AND niveau_savoir_id_niveau_savoir = 2)";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				membre = recupererMembreRS(rs);
				listeMembres.add(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMembres;
	}
	
	public List<Membre> recupererMembresReceveursConfirmesDeSavoir(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.* FROM membre \r\n" + 
					"WHERE id_membre \r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE savoir_id_savoir = ?\r\n" +
					"AND type_inscription_id_type_inscription = 2\r\n" +
					"AND niveau_savoir_id_niveau_savoir = 3)";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				membre = recupererMembreRS(rs);
				listeMembres.add(membre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeMembres;
	}

	private Membre recupererMembreRS(ResultSet rs) throws SQLException {
		Membre membre = new Membre();
		membre.setId_membre(rs.getLong("id_membre"));
		membre.setPseudo(rs.getString("pseudo"));
		membre.setPassword(rs.getString("password"));
		membre.setNom(rs.getString("nom"));
		membre.setPrenom(rs.getString("prenom"));
		membre.setAdmin(rs.getBoolean("admin"));
		membre.setEmail(rs.getString("email"));
		membre.setPseudo_slack(rs.getString("pseudo_slack"));
		membre.setImage(rs.getString("image"));
		membre.setFonction(rs.getString("fonction"));
		membre.setNiveau_general(rs.getString("niveau_general"));
		membre.setDisponibilite_habituelle(rs.getString("disponibilite_habituelle"));
		membre.setDisponibilite_actuelle(rs.getBoolean("disponibilite_actuelle"));

		return membre;
	}
	
	/**
	 * Ajouter un savoir à une categorie de savoirs
	 * 
	 * @param id_savoir
	 * @param categorie_savoir_id_categorie_savoir
	 * @throws Exception
	 */
	public void lierSavoiraCategorieSavoir(long id_savoir, long categorie_savoir_id_categorie_savoir) throws Exception {
		PreparedStatement pstmt = null;
		String sql;
		try {
			// Requete SQL
			sql = "UPDATE savoir SET categorie_savoir_id_categorie_savoir = ? where id_savoir = ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, categorie_savoir_id_categorie_savoir);
			pstmt.setLong(2, id_savoir);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
	}
	
	/**
	 * Retirer un Savoir à d'une Catégorie de Savoir
	 * 
	 * @param categorie_savoir_id_categorie_savoir
	 * @param id_savoir
	 * @throws Exception
	 */
	public void delierSavoiraCategorieSavoir(long id_savoir) throws Exception {

		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = " UPDATE savoir SET categorie_savoir_id_categorie_savoir = null where id_savoir = ?; ";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id_savoir);
			int result = pstmt.executeUpdate();
			if(result != 1) {
				throw new Exception("No entry found in database !");
			} else {
				System.out.println("Record is deleted!");
			}
			System.out.println("Result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
	}
	
}
