package co.simplon.simplonclick.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
	 * Ajouter une ressource à un savoir
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
	
}
