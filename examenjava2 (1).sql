-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 10 juin 2024 à 20:23
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `examenjava2`
--

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `id_classe` int(11) NOT NULL,
  `filiere` int(11) DEFAULT NULL,
  `niveau` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id_classe`, `filiere`, `niveau`) VALUES
(2, 6, 0),
(3, 2, 1),
(4, 4, 2),
(5, 1, 1),
(6, 4, 1);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `matricule` int(11) NOT NULL,
  `nomComplet` varchar(50) DEFAULT NULL,
  `tuteur` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`matricule`, `nomComplet`, `tuteur`) VALUES
(1, 'Matar Sakho Dia', 'Adama Dia'),
(2, 'Na Khadidiatou Amy Man‚', 'ZOZOphine'),
(3, 'Khadija Mohamed Sadji', 'Khadim Sadji'),
(4, 'Ndeye Rokhaya Sall', 'TATA SALL'),
(10, 'Birane', 'Baila');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `id` int(11) NOT NULL,
  `anneScolaire` varchar(50) DEFAULT NULL,
  `matricule` int(11) DEFAULT NULL,
  `id_classe` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`id`, `anneScolaire`, `matricule`, `id_classe`) VALUES
(1, '2024', 1, NULL),
(5, '2024', 2, 2),
(6, '2023', 3, 6),
(7, '2025', 4, 4),
(8, '206', NULL, 4),
(9, '2026', NULL, 6),
(10, '2023', NULL, 6),
(11, '2024', 10, 6);

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

CREATE TABLE `professeur` (
  `id_professeur` int(11) NOT NULL,
  `nomcomplet` varchar(50) DEFAULT NULL,
  `grade` varchar(50) DEFAULT NULL,
  `nci` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `professeur`
--

INSERT INTO `professeur` (`id_professeur`, `nomcomplet`, `grade`, `nci`) VALUES
(1, 'Birane Baila Wane', 'Docteur', 1425678),
(2, 'Andre Souley Diabang', 'Docteur', 851515),
(3, 'Manga Ndiaye', 'INGENIEUR STATISTIQUES', 789546321);

-- --------------------------------------------------------

--
-- Structure de la table `professeurdeclasse`
--

CREATE TABLE `professeurdeclasse` (
  `id_professeurDeClasse` int(11) NOT NULL,
  `id_professeur` int(11) DEFAULT NULL,
  `id_classe` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `professeurdeclasse`
--

INSERT INTO `professeurdeclasse` (`id_professeurDeClasse`, `id_professeur`, `id_classe`) VALUES
(1, 1, 5),
(2, 1, 4),
(3, 2, 6),
(4, 2, 5),
(5, 3, 5),
(6, 3, 6);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`id_classe`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`matricule`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `matricule` (`matricule`),
  ADD KEY `id_classe` (`id_classe`);

--
-- Index pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD PRIMARY KEY (`id_professeur`),
  ADD UNIQUE KEY `nci` (`nci`);

--
-- Index pour la table `professeurdeclasse`
--
ALTER TABLE `professeurdeclasse`
  ADD PRIMARY KEY (`id_professeurDeClasse`),
  ADD KEY `id_professeur` (`id_professeur`),
  ADD KEY `id_classe` (`id_classe`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `id_classe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `matricule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `inscription`
--
ALTER TABLE `inscription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `id_professeur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `professeurdeclasse`
--
ALTER TABLE `professeurdeclasse`
  MODIFY `id_professeurDeClasse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `id_classe` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id_classe`),
  ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`matricule`) REFERENCES `etudiant` (`matricule`);

--
-- Contraintes pour la table `professeurdeclasse`
--
ALTER TABLE `professeurdeclasse`
  ADD CONSTRAINT `professeurdeclasse_ibfk_1` FOREIGN KEY (`id_professeur`) REFERENCES `professeur` (`id_professeur`),
  ADD CONSTRAINT `professeurdeclasse_ibfk_2` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id_classe`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
