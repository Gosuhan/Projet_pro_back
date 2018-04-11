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

import co.simplon.simplonclick.model.Membre;

@Repository
public class InscriptionDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataSource dataSource;

	@Autowired
	public InscriptionDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	/**
	 * Rechercher un membre à partir d'une inscription
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Membre> recupererMembresDeInscription(Long id) throws Exception {
		Membre membre;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<Membre> listeMembres = new ArrayList<Membre>();

		try {
			// Requete SQL
			sql = "SELECT membre.*\r\n" + 
					"FROM membre\r\n" + 
					"WHERE id_membre\r\n" + 
					"IN (\r\n" + 
					"SELECT membre_id_membre\r\n" + 
					"FROM inscription\r\n" + 
					"WHERE id_inscription = ?);";
		
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

	private void logSQL(PreparedStatement pstmt) {
		String sql;

		if (pstmt == null)
			return;

		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}
}
