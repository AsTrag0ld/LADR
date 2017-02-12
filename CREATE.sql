DROP TABLE IF EXISTS Classement, Partie, Joueur;

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

INSERT INTO Joueur (nom) VALUES ('Dorian'), ('Anthony');
INSERT INTO Partie (nbJoueurs, duree) VALUES (2, 12.42);
INSERT INTO Classement VALUES (1, 1, 56), (2, 1, 50);