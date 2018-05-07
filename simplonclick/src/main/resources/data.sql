
INSERT IGNORE INTO `simplonclick`.`membre` (`id_membre`, `pseudo`, `password`,`nom`, `prenom`, `admin`, `email`, `pseudo_slack`, `image`, `fonction`, `niveau_general`, `disponibilite_habituelle`, `disponibilite_actuelle`) VALUES (1, 'Gafa', 'TEST', 'Gallan', 'Fabien', TRUE, 'test@test.com', 'Gafan', 'https://i.redd.it/7if8lz40vo5z.jpg', 'Apprenant', 'Novice', 'Lundi 18h', TRUE);
INSERT IGNORE INTO `simplonclick`.`membre` (`id_membre`, `pseudo`, `password`,`nom`, `prenom`, `admin`, `email`, `pseudo_slack`, `image`, `fonction`, `niveau_general`, `disponibilite_habituelle`, `disponibilite_actuelle`) VALUES (2, 'Tost', 'TEST', 'Toby', 'Stephan', FALSE, 'test@test.com', 'Toste', 'https://img00.deviantart.net/e4a2/i/2016/096/f/7/avatar_icon_by_astrolink247-d9xxs6r.jpg', 'Apprenant', 'Intermediaire', 'Mardi 18h', TRUE);
INSERT IGNORE INTO `simplonclick`.`membre` (`id_membre`, `pseudo`, `password`,`nom`, `prenom`, `admin`, `email`, `pseudo_slack`, `image`, `fonction`, `niveau_general`, `disponibilite_habituelle`, `disponibilite_actuelle`) VALUES (3, 'Chama', 'TEST', 'Charru', 'Marc', FALSE, 'test@test.com', 'Chaman', 'http://www.pinoyps.com/download/file.php?avatar=20708_1323874640.jpg', 'Formateur', 'Expert', 'Mercredi 18h', FALSE);
INSERT IGNORE INTO `simplonclick`.`membre` (`id_membre`, `pseudo`, `password`,`nom`, `prenom`, `admin`, `email`, `pseudo_slack`, `image`, `fonction`, `niveau_general`, `disponibilite_habituelle`, `disponibilite_actuelle`) VALUES (4, 'Malo', 'TEST', 'Louve', 'Marie', TRUE, 'test@test.com', 'Malou', 'http://static-resource.np.community.playstation.net/avatar/3RD/UP10631306050_56AB93C358D10D687ECC_L.png', 'Apprenant', 'Confirme', 'Vendredi 18h', TRUE);

INSERT IGNORE INTO `simplonclick`.`categorie_savoir` (`id_categorie_savoir`, `nom_categorie_savoir`) VALUES (1, 'Langages');
INSERT IGNORE INTO `simplonclick`.`categorie_savoir` (`id_categorie_savoir`, `nom_categorie_savoir`) VALUES (2, 'Notions et Concepts informatiques');
INSERT IGNORE INTO `simplonclick`.`categorie_savoir` (`id_categorie_savoir`, `nom_categorie_savoir`) VALUES (3, 'Autres');

INSERT IGNORE INTO `simplonclick`.`savoir` (`id_savoir`, `nom_savoir`, `categorie_savoir_id_categorie_savoir`) VALUES (1, 'Java', 1);
INSERT IGNORE INTO `simplonclick`.`savoir` (`id_savoir`, `nom_savoir`, `categorie_savoir_id_categorie_savoir`) VALUES (2, 'PHP', 1);
INSERT IGNORE INTO `simplonclick`.`savoir` (`id_savoir`, `nom_savoir`, `categorie_savoir_id_categorie_savoir`) VALUES (3, 'Fonctions', 2);
INSERT IGNORE INTO `simplonclick`.`savoir` (`id_savoir`, `nom_savoir`, `categorie_savoir_id_categorie_savoir`) VALUES (4, 'UML', 2);
INSERT IGNORE INTO `simplonclick`.`savoir` (`id_savoir`, `nom_savoir`, `categorie_savoir_id_categorie_savoir`) VALUES (5, 'Anglais', 3);
INSERT IGNORE INTO `simplonclick`.`savoir` (`id_savoir`, `nom_savoir`, `categorie_savoir_id_categorie_savoir`) VALUES (6,'HTML', 2);

INSERT IGNORE INTO `simplonclick`.`ressource` (`id_ressource`, `url`, `nom_ressource`, `savoir_id_savoir`) VALUES (1, 'https://www.ressource1@openclassroom.com', 'OpenClassRoom', 1);
INSERT IGNORE INTO `simplonclick`.`ressource` (`id_ressource`, `url`, `nom_ressource`, `savoir_id_savoir`) VALUES (2, 'https://www.ressource2@codecademy.com', 'Codecademy', 2);
INSERT IGNORE INTO `simplonclick`.`ressource` (`id_ressource`, `url`, `nom_ressource`, `savoir_id_savoir`) VALUES (3, 'https://www.ressource3@wikipedia.com', 'Wikipedia', 6);

INSERT IGNORE INTO `simplonclick`.`inscription` (`id_inscription`, `nom_inscription`, `membre_id_membre`, `niveau_savoir_id_niveau_savoir`, `savoir_id_savoir`, `type_inscription_id_type_inscription`) VALUES (1, 'BesoinGafaJava', 1, 3, 1, 1);
INSERT IGNORE INTO `simplonclick`.`inscription` (`id_inscription`, `nom_inscription`, `membre_id_membre`, `niveau_savoir_id_niveau_savoir`, `savoir_id_savoir`, `type_inscription_id_type_inscription`) VALUES (2, 'TobyPHP', 2, 1, 2, 2);
INSERT IGNORE INTO `simplonclick`.`inscription` (`id_inscription`, `nom_inscription`, `membre_id_membre`, `niveau_savoir_id_niveau_savoir`, `savoir_id_savoir`, `type_inscription_id_type_inscription`) VALUES (3, 'LouveAnglais', 4, 3, 4, 1);
INSERT IGNORE INTO `simplonclick`.`inscription` (`id_inscription`, `nom_inscription`, `membre_id_membre`, `niveau_savoir_id_niveau_savoir`, `savoir_id_savoir`, `type_inscription_id_type_inscription`) VALUES (4, 'Gafa2HTML', 1, 1, 5, 2);

INSERT IGNORE INTO `simplonclick`.`type_inscription` (`id_type_inscription`, `type_inscription`) VALUES (1, 'Passeur');
INSERT IGNORE INTO `simplonclick`.`type_inscription` (`id_type_inscription`, `type_inscription`) VALUES (2, 'Receveur');

INSERT IGNORE INTO `simplonclick`.`niveau_savoir` (`id_niveau_savoir`, `niveau_savoir`) VALUES (1, 'Débutant');
INSERT IGNORE INTO `simplonclick`.`niveau_savoir` (`id_niveau_savoir`, `niveau_savoir`) VALUES (2, 'Intermediaire');
INSERT IGNORE INTO `simplonclick`.`niveau_savoir` (`id_niveau_savoir`, `niveau_savoir`) VALUES (3, 'Confirmé');