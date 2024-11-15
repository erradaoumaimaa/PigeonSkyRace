# Gestion des Compétitions de Pigeons Voyageurs 🕊️

## Contexte du Projet
Ce projet vise à gérer les compétitions organisées par la **Fédération Marocaine des Pigeons Voyageurs**, impliquant plusieurs éleveurs participant à des courses réparties sur trois étapes : **vitesse**, **demi-fond** et **fond**.  
L'objectif est de garantir l'intégrité de la compétition grâce à une application respectant des règles strictes et permettant une gestion optimale des événements.

---

## Fonctionnalités Principales
### Pour les Éleveurs
- **Enregistrement** : Création de compte avec :
  - Nom unique de colombier.
  - Nom d’utilisateur et mot de passe.
  - Coordonnées GPS du colombier.
- **Gestion des pigeons** :
  - Ajout de pigeons (numéro de bague unique, sexe, âge, couleur, image).

### Pour l'Organisateur
- **Gestion des compétitions** :
  - Définir des compétitions (nom, coordonnées GPS du point de lâcher, date/heure de départ, distance prévisionnelle).
  - Ajouter des pigeons participants via leur numéro de bague.
  - Clôturer une compétition et déclencher le calcul des résultats.
- **Upload des données collectées** : Heure d'arrivée et numéro de bague.
- **Consultation et export des résultats** : Génération de fichiers PDF.

---

## Calcul des Résultats
1. **Collecte des données** :
   - Heure d’arrivée et numéro de bague des pigeons.
2. **Calcul de la distance** :
   - Utilisation de la **formule de Haversine** pour mesurer la distance entre deux points GPS.
3. **Calcul du temps de vol** :
   - Différence entre l’heure d’arrivée et l’heure de lâcher.
4. **Calcul de la vitesse** :
   - Vitesse = Distance parcourue / Temps de vol.
   - Ajustement avec un coefficient basé sur la distance moyenne des pigeons.
5. **Classement** :
   - Ordre décroissant des vitesses.
6. **Attribution des points** :
   - Basé sur le classement et le pourcentage d’admission.
   - Cumul des points des 5 premiers pigeons par colombier pour un classement général.

---

## Affichage des Résultats
- Résultats par course avec :
  - **Colombier**, **Numéro de bague**, **Heure d’arrivée**, **Distance**, **Vitesse**, **Points**.
- Classement général automatique pour consultation/export.

---

## Exigences Techniques
- **Backend** : Développement de l’API avec **Spring Boot**.
- **Base de données** : Utilisation de **MongoDB**.
- **Architecture** :
  - Application organisée en couches (Controller, Service, Repository).
- **Validation des données** : Obligatoire.
- **Gestion des exceptions** : Centralisée.
- **Tests unitaires** : Requis.
- **Configuration** : Fichier de configuration au format **YAML**.

---

## Installation et Lancement
### Prérequis
- **Java 17+**
- **Maven**
- **MongoDB**

### Étapes
1. Clonez le dépôt :
   ```bash
   git clone https://github.com/your-username/your-repo.git
