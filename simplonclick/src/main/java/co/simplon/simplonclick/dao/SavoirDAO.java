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

import co.simplon.simplonclick.model.Ressource;
import co.simplon.simplonclick.model.Savoir;

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
}
