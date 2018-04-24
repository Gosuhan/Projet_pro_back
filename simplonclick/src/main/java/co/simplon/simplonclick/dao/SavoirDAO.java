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

		return inscription;
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
