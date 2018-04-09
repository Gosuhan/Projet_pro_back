
INSERT INTO `simplonclick`.`membre` (`pseudo`, `password`,`nom`, `prenom`, `admin`, `email`, `pseudo_slack`, `image`, `fonction`, `niveau_general`, `disponibilite_habituelle`, `disponibilite_actuelle`) VALUES ('Gafa', 'TEST', 'Gallan', 'Fabien', TRUE, 'test@test.com', 'Gafan', 'imagetest', 'apprenant', 'novice', 'Lundi 18h', TRUE);
INSERT INTO `simplonclick`.`membre` (`pseudo`, `password`,`nom`, `prenom`, `admin`, `email`, `pseudo_slack`, `image`, `fonction`, `niveau_general`, `disponibilite_habituelle`, `disponibilite_actuelle`) VALUES ('Tost', 'TEST', 'Toby', 'Stephan', FALSE, '', 'test@test.com', 'Toste', 'imagetest', 'apprenant', 'intermediaire', 'Mardi 18h', TRUE);
INSERT INTO `simplonclick`.`membre` (`pseudo`, `password`,`nom`, `prenom`, `admin`, `email`, `pseudo_slack`, `image`, `fonction`, `niveau_general`, `disponibilite_habituelle`, `disponibilite_actuelle`) VALUES ('Chama', 'TEST', 'Charru', 'Marc', FALSE, 'ADMIN', 'test@test.com', 'Chaman', 'imagetest', 'formateur', 'expert', 'Mercredi 18h', FALSE);
INSERT INTO `simplonclick`.`membre` (`pseudo`, `password`,`nom`, `prenom`, `admin`, `email`, `pseudo_slack`, `image`, `fonction`, `niveau_general`, `disponibilite_habituelle`, `disponibilite_actuelle`) VALUES ('Malo', 'TEST', 'Louve', 'Marie', TRUE, 'ADMIN', 'test@test.com', 'Malou', 'imagetest', 'apprenant', 'confirme', 'Vendredi 18h', TRUE);

INSERT INTO `simplonclick`.`categorie_savoir` (`nom_categorie_savoir`) VALUES ('Langages');
INSERT INTO `simplonclick`.`categorie_savoir` (`nom_categorie_savoir`) VALUES ('Notions et Concepts informatiques');
INSERT INTO `simplonclick`.`categorie_savoir` (`nom_categorie_savoir`) VALUES ('Autres');

INSERT INTO `simplonclick`.`savoir` (`nom_savoir`) VALUES ('Java');
INSERT INTO `simplonclick`.`savoir` (`nom_savoir`) VALUES ('PHP');
INSERT INTO `simplonclick`.`savoir` (`nom_savoir`) VALUES ('Fonctions');
INSERT INTO `simplonclick`.`savoir` (`nom_savoir`) VALUES ('UML');
INSERT INTO `simplonclick`.`savoir` (`nom_savoir`) VALUES ('Anglais');
INSERT INTO `simplonclick`.`savoir` (`nom_savoir`) VALUES ('HTML');

INSERT INTO `simplonclick`.`ressource` (`url`, `nom_ressource`) VALUES ('httpsressource1', 'OpenClassRoom');
INSERT INTO `simplonclick`.`ressource` (`url`, `nom_ressource`) VALUES ('httpsressource1', 'Codecademy');
INSERT INTO `simplonclick`.`ressource` (`url`, `nom_ressource`) VALUES ('httpsressource1', 'wikipedia');

INSERT INTO `simplonclick`.`type_inscription` (`type_inscription`) VALUES ('passeur');
INSERT INTO `simplonclick`.`type_inscription` (`type_inscription`) VALUES ('receveur');

INSERT INTO `simplonclick`.`niveau_savoir` (`niveau_savoir`) VALUES ('debutant');
INSERT INTO `simplonclick`.`niveau_savoir` (`niveau_savoir`) VALUES ('intermediaire');
INSERT INTO `simplonclick`.`niveau_savoir` (`niveau_savoir`) VALUES ('confirme');