DROP TABLE IF EXISTS Classement, Partie, Joueur, Route, CarteDestination, Ville;

CREATE TABLE Joueur (
idJoueur serial PRIMARY KEY,
nom text UNIQUE NOT NULL,
nbVictoires int NOT NULL DEFAULT 0
);

CREATE TABLE Partie (
idPartie serial PRIMARY KEY,
nbJoueurs int NOT NULL CHECK(nbJoueurs < 6 AND nbJoueurs > 1),
duree numeric(4,2) NOT NULL DEFAULT 0
);

CREATE TABLE Classement (
idJoueur int REFERENCES Joueur,
idPartie int REFERENCES Partie,
PRIMARY KEY(idJoueur, idPartie),
score int NOT NULL CHECK(score >= 0)
);

CREATE TABLE Ville (
nom text PRIMARY KEY
);

CREATE TABLE Route (
idRoute int AUTO_INCREMENT,
villeA text REFERENCES Ville(nom),
villeB text REFERENCES Ville(nom),
taille int NOT NULL CHECK (taille > 1),
couleur text NOT NULL,
PRIMARY KEY (idRoute)
);

CREATE TABLE CarteDestination (
points int NOT NULL,
villeA text REFERENCES Ville(nom),
villeB text REFERENCES Ville(nom),
PRIMARY KEY (villeA, villeB)
);

INSERT INTO Joueur (nom) VALUES ('Dorian'), ('Anthony');
INSERT INTO Partie (nbJoueurs, duree) VALUES (2, 12.42);
INSERT INTO Classement VALUES (1, 1, 56), (2, 1, 50);


INSERT INTO Ville VALUES ('Whitehorse'), ('Fort Good Hope'), ('Tungstene'), 
('Fort Saint-Jean'), ('Prince George'), ('Echo Bay'), ('Yellowknife'), 
('Fort Smith'), ('Fort Vermilion'), ('Edmonton'), ('Vancouver'), ('Victoria'),
('Calgary'), ('Uranium City'), ('Saskatoon'), ('Regina'), ('Thompson'), ('Churchill'),
('Baker Lake'), ('Resolute'), ('Pond Inlet'), ('Winnipeg'), ('Thunder Bay'),
('Ivujivik'), ('Inukjuak'), ('Eastmain'), ('Hearst'), ('Sudbury'), ('Windsor'),
('Oshawa'), ('Ottawa'), ('Amos'), ('Chibougama'), ('Quebec'), ('Labrador City'),
('Crand Sault'), ('Halifax'), ('Sydney'), ('Millepertuis');

INSERT INTO CarteDestination VALUES
(4, 'Tungstene', 'Fort Saint-Jean'),
(5, 'Halifax', 'Crand Sault'),
(6, 'Yellowknife', 'Baker Lake'),
(7, 'Saskatoon', 'Churchill'),
(7, 'Uranium City', 'Echo Bay'),
(8, 'Inukjuak', 'Quebec'),
(8, 'Whitehorse', 'Fort Smith'),
(8, 'Calgary', 'Thompson'),
(9, 'Baker Lake', 'Inukjuak'),
(9, 'Regina', 'Yellowknife'),
(9, 'Echo Bay', 'Pond Inlet'),
(9, 'Whitehorse', 'Victoria'),
(10, 'Whitehorse', 'Uranium City'),
(10, 'Fort Good Hope', 'Resolute'),
(11, 'Churchill', 'Ivujivik'),
(11, 'Saskatoon', 'Echo Bay'),
(11, 'Regina', 'Baker Lake'),
(11, 'Windsor', 'Halifax'),
(12, 'Prince George', 'Thunder Bay'),
(12, 'Thompson', 'Chibougama'),
(13, 'Regina', 'Oshawa'),
(13, 'Hearst', 'Labrador City'),
(13, 'Tungstene', 'Resolute'),
(16, 'Uranium City', 'Ivujivik'),
(17, 'Echo Bay', 'Hearst'),
(20, 'Resolute', 'Hearst'),
(20, 'Thunder Bay', 'Millepertuis'),
(21, 'Vancouver', 'Pond Inlet'),
(22, 'Winnipeg', 'Millepertuis');

INSERT INTO Route (villeA, villeB, taille, couleur) VALUES
('Whitehorse', 'Fort Good Hope', 5, "bleu"),
('Whitehorse', 'Prince George', 5, "rose"),
('Whitehorse', 'Prince George', 5, "vert"),
('Whitehorse', 'Tungstene', 2, "rouge"),
('Prince George', 'Victoria', 4, "jaune"),
('Victoria', 'Calgary', false),
('Calgary', 'Edmonton', true),
('Vancouver', 'Prince George', false),
('Prince George', 'Fort Saint-Jean', true),
('Fort Saint-Jean', 'Tungstene', false),
('Tungstene', 'Fort Good Hope', false),
('Tungstene', 'Yellowknife', false),
('Yellowknife', 'Echo Bay', true),
('Echo Bay', 'Baker Lake', false),
('Yellowknife', 'Baker Lake', false),
('Yellowknife', 'Fort Smith', false),
('Fort Smith', 'Fort Vermilion', true),
('Fort Vermilion', 'Fort Saint-Jean', false),
('Prince George', 'Calgary', false),
('Fort Saint-Jean', 'Edmonton', false),
('Fort Vermilion', 'Edmonton', false),
('Vancouver', 'Calgary', false),
('Fort Good Hope', 'Echo Bay', true),
('Calgary', 'Saskatoon', false),
('Edmonton', 'Saskatoon', false),
('Fort Vermilion', 'Saskatoon', false),
('Echo Bay', 'Resolute', true),
('Fort Smith', 'Uranium City', false),
('Uranium City', 'Saskatoon', false),
('Saskatoon', 'Regina', true),
('Uranium City', 'Thompson', false),
('Regina', 'Winnipeg', false),
('Regina', 'Thompson', false),
('Thompson', 'Churchill', true),
('Baker Lake', 'Churchill', true),
('Resolute', 'Pond Inlet', true),
('Pond Inlet', 'Baker Lake', false),
('Winnipeg', 'Thunder Bay', false),
('Thompson', 'Thunder Bay', false),
('Pond Inlet', 'Ivujivik', false),
('Baker Lake', 'Ivujivik', false),
('Thompson', 'Hearst', false),
('Hearst', 'Churchill', false),
('Hearst', 'Thunder Bay', false),
('Thunder Bay', 'Windsor', false),
('Hearst', 'Sudbury', false),
('Inukjuak', 'Ivujivik', false),
('Inukjuak', 'Eastmain', false),
('Eastmain', 'Hearst', false),
('Sudbury', 'Windsor', false),
('Inukjuak', 'Labrador City', true),
('Eastmain', 'Chibougama', false),
('Sudbury', 'Amos', false),
('Amos', 'Chibougama', false),
('Windsor', 'Oshawa', false),
('Sudbury', 'Ottawa', false),
('Amos', 'Ottawa', false),
('Ottawa', 'Chibougama', false),
('Quebec', 'Chibougama', false),
('Quebec', 'Oshawa', false),
('Labrador City', 'Quebec', false),
('Halifax', 'Quebec', true),
('Labrador City', 'Crand Sault', false),
('Labrador City', 'Sydney', true),
('Halifax', 'Sydney', false),
('Crand Sault', 'Sydney', false),
('Millepertuis', 'Sydney', false),
('Crand Sault', 'Millepertuis', false);
