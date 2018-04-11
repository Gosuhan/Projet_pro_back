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

import co.simplon.simplonclick.model.Savoir;

@Repository
public class CategorieSavoirDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataSource dataSource;

	@Autowired
	public CategorieSavoirDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	/**
	 * Rechercher les savoirs liés à une catégorie de savoir(s)
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Savoir> recupererSavoirsDeCategorieSavoir(Long id) throws Exception {
		Savoir savoir;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Savoir> listeSavoirs = new ArrayList<Savoir>();

		try {
			// Requete SQL
			sql = "SELECT savoir.*\r\n" + 
					"FROM savoir\r\n" + 
					"WHERE id_savoir \r\n" + 
					"IN (\r\n" + 
					"SELECT id_savoir\r\n" + 
					"FROM savoir\r\n" + 
					"WHERE categorie_savoir_id_categorie_savoir = ?);";
		
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, id);
			// Log info
			logSQL(pstmt);
			// Lancement requete
			rs = pstmt.executeQuery();
			// resultat requete
			while (rs.next()) {
				savoir = recupererSavoirRS(rs);
				listeSavoirs.add(savoir);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return listeSavoirs;
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
}
