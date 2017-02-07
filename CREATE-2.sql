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
idRoute serial PRIMARY KEY,
villeA text REFERENCES Ville(nom),
villeB text REFERENCES Ville(nom),
taille int NOT NULL CHECK (taille > 0),
couleur text NOT NULL
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
('Grand Sault'), ('Halifax'), ('Sydney'), ('Millepertuis');

INSERT INTO CarteDestination VALUES
(4, 'Tungstene', 'Fort Saint-Jean'),
(5, 'Halifax', 'Grand Sault'),
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
('Whitehorse', 'Fort Good Hope', 5, 'bleu'),
('Whitehorse', 'Fort Good Hope', 5, 'gris'),
('Whitehorse', 'Prince George', 5, 'rose'),
('Whitehorse', 'Prince George', 5, 'vert'),
('Whitehorse', 'Tungstene', 2, 'rouge'),
('Prince George', 'Victoria', 4, 'jaune'),
('Victoria', 'Calgary', 4, 'rose'),
('Calgary', 'Edmonton', 1,'gris'),
('Calgary', 'Edmonton', 1,'gris'),
('Vancouver', 'Prince George', 3,'blanc'),
('Prince George', 'Fort Saint-Jean', 1,'blanc'),
('Prince George', 'Fort Saint-Jean', 1,'gris'),
('Fort Saint-Jean', 'Tungstene', 4,'noir'),
('Tungstene', 'Fort Good Hope', 3,'orange'),
('Tungstene', 'Yellowknife', 4,'gris'),
('Yellowknife', 'Echo Bay', 3,'rouge'),
('Yellowknife', 'Echo Bay', 3,'gris'),
('Echo Bay', 'Baker Lake', 6,'jaune'),
('Yellowknife', 'Baker Lake', 6,'blanc'),
('Yellowknife', 'Fort Smith', 2,'noir'),
('Fort Smith', 'Fort Vermilion', 1,'gris'),
('Fort Smith', 'Fort Vermilion', 1,'gris'),
('Fort Vermilion', 'Fort Saint-Jean', 2,'rose'),
('Prince George', 'Calgary', 4,'rouge'),
('Fort Saint-Jean', 'Edmonton', 3,'gris'),
('Fort Vermilion', 'Edmonton', 3,'vert'),
('Vancouver', 'Calgary', 3,'gris'),
('Fort Good Hope', 'Echo Bay', 4,'gris'),
('Fort Good Hope', 'Echo Bay', 4,'noir'),
('Calgary', 'Saskatoon', 3,'bleu'),
('Edmonton', 'Saskatoon', 2,'jaune'),
('Fort Vermilion', 'Saskatoon', 6,'bleu'),
('Echo Bay', 'Resolute', 6,'gris'),
('Echo Bay', 'Resolute', 6,'orange'),
('Fort Smith', 'Uranium City', 2,'vert'),
('Uranium City', 'Saskatoon', 4,'orange'),
('Saskatoon', 'Regina', 1,'gris'),
('Saskatoon', 'Regina', 1,'gris'),
('Uranium City', 'Thompson', 4,'blanc'),
('Regina', 'Winnipeg', 3,'rouge'),
('Regina', 'Thompson', 4,'gris'),
('Thompson', 'Churchill', 2,'gris'),
('Thompson', 'Churchill', 2,'gris'),
('Baker Lake', 'Churchill', 5,'rouge'),
('Baker Lake', 'Churchill', 5,'gris'),
('Resolute', 'Pond Inlet', 3,'noir'),
('Resolute', 'Pond Inlet', 4,'bleu'),
('Pond Inlet', 'Baker Lake', 6,'rose'),
('Winnipeg', 'Thunder Bay', 2,'orange'),
('Thompson', 'Thunder Bay', 6,'rose'),
('Pond Inlet', 'Ivujivik', 6,'gris'),
('Baker Lake', 'Ivujivik', 6,'noir'),
('Thompson', 'Hearst', 6,'vert'),
('Hearst', 'Churchill', 6,'gris'),
('Hearst', 'Thunder Bay', 2,'gris'),
('Thunder Bay', 'Windsor', 5,'noir'),
('Hearst', 'Sudbury', 3,'jaune'),
('Inukjuak', 'Ivujivik', 3,'bleu'),
('Inukjuak', 'Eastmain', 4,'gris'),
('Eastmain', 'Hearst', 4,'bleu'),
('Sudbury', 'Windsor', 2,'blanc'),
('Inukjuak', 'Labrador City', 5,'orange'),
('Inukjuak', 'Labrador City', 5,'jaune'),
('Eastmain', 'Chibougama', 2,'vert'),
('Sudbury', 'Amos', 2,'rose'),
('Amos', 'Chibougama', 2,'gris'),
('Windsor', 'Oshawa', 3,'rose'),
('Sudbury', 'Ottawa', 2,'vert'),
('Amos', 'Ottawa', 1,'gris'),
('Ottawa', 'Chibougama', 3,'noir'),
('Quebec', 'Chibougama', 2,'orange'),
('Quebec', 'Oshawa', 3,'jaune'),
('Labrador City', 'Quebec', 6,'rouge'),
('Halifax', 'Quebec', 4,'jaune'),
('Halifax', 'Quebec', 4,'blanc'),
('Labrador City', 'Grand Sault', 5,'vert'),
('Labrador City', 'Sydney', 5,'blanc'),
('Labrador City', 'Sydney', 5,'gris'),
('Halifax', 'Sydney', 2,'rouge'),
('Grand Sault', 'Sydney', 3,'orange'),
('Millepertuis', 'Sydney', 4,'gris'),
('Grand Sault', 'Millepertuis', 2,'bleu');
