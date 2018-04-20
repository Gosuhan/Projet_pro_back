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
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.simplon.simplonclick.model.Ressource;
import co.simplon.simplonclick.model.Savoir;

@Repository
public class RessourceDAO {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataSource dataSource;

	@Autowired
	public RessourceDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	/**
	 * Rechercher le savoir liée à une ressource
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Savoir recupererSavoirDeRessource(Long id) throws Exception {
		Savoir savoir = new Savoir();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			// Requete SQL
			sql = "SELECT savoir.*\r\n" + 
					"FROM savoir\r\n" + 
					"WHERE id_savoir \r\n" + 
					"IN (\r\n" + 
					"SELECT savoir_id_savoir\r\n" + 
					"FROM ressource\r\n" + 
					"WHERE id_ressource = ?);";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				savoir = recupererSavoirRS(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return savoir;
	}

	private Savoir recupererSavoirRS(ResultSet rs) throws SQLException {
		Savoir savoir = new Savoir();
		savoir.setId_savoir(rs.getLong("id_savoir"));
		savoir.setNom_savoir(rs.getString("nom_savoir"));

		return savoir;
	}
	
	private void logSQL(PreparedStatement pstmt) {
		String sql;

		if (pstmt == null)
			return;

		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}
	
	/**
	 * Ajouter une Ressource à un Savoir
	 * 
	 * @param savoir_id_savoir
	 * @param id_ressource
	 * @throws Exception
	 */
	public void lierRessourceaSavoir(long id_ressource, long savoir_id_savoir) throws Exception {
		PreparedStatement pstmt = null;
		String sql;
		try {
			// Requete SQL
			sql = "UPDATE ressource SET savoir_id_savoir = ? where id_ressource = ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, savoir_id_savoir);
			pstmt.setLong(2, id_ressource);
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
	 * Retirer une Ressource à un Savoir
	 * 
	 * @param savoir_id_savoir
	 * @param id_ressource
	 * @throws Exception
	 */
	public void delierRessourceaSavoir(long id_ressource) throws Exception {

		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = " UPDATE ressource SET savoir_id_savoir = null where id_ressource = ?; ";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id_ressource);
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
	
	/**
	 * Rechercher les ressources avec un critère de recherche sur tous les champs
	 * 
	 * @param filtreRessource
	 * @return
	 * @throws Exception
	 */
	public List<Ressource> recupererRessourcesTriees(String rechercheRessource) throws Exception {
		List<Ressource> ressourcesTriees = new ArrayList<Ressource>();

		Ressource ressource;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			// Requete SQL
			sql = "SELECT * FROM ressource WHERE nom_ressource LIKE ? OR url LIKE ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setString(1, "%" + rechercheRessource + "%");
			pstmt.setString(2, "%" + rechercheRessource + "%");

			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				ressource = recupererRessourceRS(rs);
				ressourcesTriees.add(ressource);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return ressourcesTriees;
	}
	
	private Ressource recupererRessourceRS(ResultSet rs) throws SQLException {
		Ressource ressource = new Ressource();
		ressource.setId_ressource(rs.getLong("id_ressource"));
		ressource.setNom_ressource(rs.getString("nom_ressource"));
		ressource.setUrl(rs.getString("url"));

		return ressource;
	}
	
}
