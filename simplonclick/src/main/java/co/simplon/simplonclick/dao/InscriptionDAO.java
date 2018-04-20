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

import co.simplon.simplonclick.model.Membre;
import co.simplon.simplonclick.model.NiveauSavoir;
import co.simplon.simplonclick.model.Savoir;
import co.simplon.simplonclick.model.TypeInscription;

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
	public Membre recupererMembreDeInscription(Long id) throws Exception {
		Membre membre = new Membre();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			// Requete SQL
			sql = "SELECT membre.*\r\n" + 
					"FROM membre\r\n" + 
					"WHERE id_membre\r\n" + 
					"= (\r\n" + 
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return membre;
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
	
	/**
	 * Rechercher un savoir à partir d'une inscription
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Savoir recupererSavoirDeInscription(Long id) throws Exception {
		Savoir savoir = new Savoir();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			// Requete SQL
			sql = "SELECT savoir.*\r\n" + 
					"FROM savoir\r\n" + 
					"WHERE id_savoir\r\n" + 
					"= (\r\n" + 
					"SELECT savoir_id_savoir\r\n" + 
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
	
	/**
	 * Rechercher un type d'inscription à partir d'une inscription
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TypeInscription recupererTypeInscriptionDeInscription(Long id) throws Exception {
		TypeInscription typeInscription = new TypeInscription();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			// Requete SQL
			sql = "SELECT type_inscription.*\r\n" + 
					"FROM type_inscription\r\n" + 
					"WHERE id_type_inscription\r\n" + 
					"= (\r\n" + 
					"SELECT type_inscription_id_type_inscription\r\n" + 
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
				typeInscription = recupererTypeInscriptionRS(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return typeInscription;
	}

	private TypeInscription recupererTypeInscriptionRS(ResultSet rs) throws SQLException {
		TypeInscription typeInscription = new TypeInscription();
		typeInscription.setId_type_inscription(rs.getLong("id_type_inscription"));
		typeInscription.setType_inscription(rs.getString("type_inscription"));

		return typeInscription;
	}
	
	/**
	 * Rechercher un niveau de savoir à partir d'une inscription
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public NiveauSavoir recupererNiveauSavoirDeInscription(Long id) throws Exception {
		NiveauSavoir niveauSavoir = new NiveauSavoir();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;

		try {
			// Requete SQL
			sql = "SELECT niveau_savoir.*\r\n" + 
					"FROM niveau_savoir\r\n" + 
					"WHERE id_niveau_savoir\r\n" + 
					"= (\r\n" + 
					"SELECT niveau_savoir_id_niveau_savoir\r\n" + 
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
				niveauSavoir = recupererNiveauSavoirRS(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return niveauSavoir;
	}

	private NiveauSavoir recupererNiveauSavoirRS(ResultSet rs) throws SQLException {
		NiveauSavoir niveauSavoir = new NiveauSavoir();
		niveauSavoir.setId_niveau_savoir(rs.getLong("id_niveau_savoir"));
		niveauSavoir.setNiveau_savoir(rs.getString("niveau_savoir"));

		return niveauSavoir;
	}
	
	/**
	 * Ajouter une inscription à un membre
	 * 
	 * @param id_inscription
	 * @param membre_id_membre
	 * @throws Exception
	 */
	public void lierInscriptionaMembre(long id_inscription, long membre_id_membre) throws Exception {
		PreparedStatement pstmt = null;
		String sql;
		try {
			// Requete SQL
			sql = "UPDATE inscription SET membre_id_membre = ? where id_inscription = ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, membre_id_membre);
			pstmt.setLong(2, id_inscription);
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
	 * Ajouter une inscription à un niveau de savoir
	 * 
	 * @param id_inscription
	 * @param niveau_savoir_id_niveau_savoir
	 * @throws Exception
	 */
	public void lierInscriptionaNiveauSavoir(long id_inscription, long niveau_savoir_id_niveau_savoir) throws Exception {
		PreparedStatement pstmt = null;
		String sql;
		try {
			// Requete SQL
			sql = "UPDATE inscription SET niveau_savoir_id_niveau_savoir = ? where id_inscription = ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, niveau_savoir_id_niveau_savoir);
			pstmt.setLong(2, id_inscription);
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
	 * Ajouter une inscription à un type d'inscription
	 * 
	 * @param id_inscription
	 * @param type_inscription_id_type_inscription
	 * @throws Exception
	 */
	public void lierInscriptionaTypeInscription(long id_inscription, long type_inscription_id_type_inscription) throws Exception {
		PreparedStatement pstmt = null;
		String sql;
		try {
			// Requete SQL
			sql = "UPDATE inscription SET type_inscription_id_type_inscription = ? where id_inscription = ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, type_inscription_id_type_inscription);
			pstmt.setLong(2, id_inscription);
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
	 * Ajouter une inscription à un savoir
	 * 
	 * @param id_inscription
	 * @param savoir_id_savoir
	 * @throws Exception
	 */
	public void lierInscriptionaSavoir(long id_inscription, long savoir_id_savoir) throws Exception {
		PreparedStatement pstmt = null;
		String sql;
		try {
			// Requete SQL
			sql = "UPDATE inscription SET savoir_id_savoir = ? where id_inscription = ?;";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setLong(1, savoir_id_savoir);
			pstmt.setLong(2, id_inscription);
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
