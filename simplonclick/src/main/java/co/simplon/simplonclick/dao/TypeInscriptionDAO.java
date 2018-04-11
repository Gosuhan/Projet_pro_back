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

import co.simplon.simplonclick.model.Inscription;

@Repository
public class TypeInscriptionDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataSource dataSource;

	@Autowired
	public TypeInscriptionDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	/**
	 * Rechercher les inscriptions liées à un type d'inscription
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Inscription> recupererInscriptionsDeTypeInscription(Long id) throws Exception {
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
					"WHERE type_inscription_id_type_inscription = ?);";
		
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
	
	private void logSQL(PreparedStatement pstmt) {
		String sql;

		if (pstmt == null)
			return;

		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}
	
}
