# Analyse du developpement

## Qualité de développement

### 1. Sujet : 


#### Contexte :

Alain Dupont est un enseignant en informatique dont l’adresse mail est alain.dupont@iut.fr. Il saisit
les notes des étudiants d’un groupe TP dans un tableau (on peut considérer un tableau d’entiers).
Quand il a fini la saisie, il obtient la moyenne des étudiants et la médiane du groupe. Le programme
affiche le nombre d’étudiants notés (taille du tableau), la moyenne ainsi que la médiane. Enfin, Alain
Dupont veut stocker ce résultat en y ajoutant ses identifiants sous la forme :
Prénom, nom, email, date de l’examen, nombre d’étudiants, la moyenne et la médiane.


#### Travail à réaliser :

- Créer un projet Maven
- Exécuter le code fourni
- Créer un dépôt Git local (ou distant) de votre projet
- Concevoir la structure de votre application et modifier le code, par exemple :

  o Créer une classe UtilisateurTab, n’oubliez pas de vérifier que le format de l’adresse
  mail est valide.
  o Séparer la classe Principale avec main() de la classe CalculTab
  o Coder proprement la classe CalculTab (utiliser StringBuilder pour ToString, …)
  o Changer votre tableau fixe tab[] par un ArrayList (ou autre collection)
  o Compléter votre classe Principale et faites fonctionner l’ensemble

- Ajouter une méthode de tri de votre tableau puis calculer la médiane
  Qualité de développement :
  o Bien réfléchir à votre application et à sa conception ! (Ecrire le scénario)
  o Adopter un nommage clair et lisible
  o Faire les tests unitaires afin de s’assurer d’une couverture d’au moins 50%
  o Mettre des annotations avancées, utiliser AssertJ
  o N’oubliez pas de documenter votre code
  o Travail Git :

   Faire des commits de votre projet, regarder l’historique de votre dépôt Git
   Regarder la différence entre deux versions (outil compare files)
   Tester « revert commit » pour revenir à une version précédente.


Vous pouvez tenter une approche TDD, c’est-à-dire un développement conduit par les tests.

#### Pour les tests :
  Nommage avec le « camel case » ( lettresEnMajusculesEtEnMinusculesSansUnderscore ).
  Décrire chaque étape de Arrange/Act/Assert ou Given/When/Then.
  Faire du « camel case » pour chaque élément à décrire et les lier par des caractères underscore.
  Exemples de styles :
  · MethodName_StateUnderTest_ExpectedBehavior
  o Exemple : add_twoPositiveIntegers_returnsTheirSum()
  o Variante : Add_TwoPositiveIntegers_ReturnsTheirSum()
  Note : Ceci n’est pas du “camel case”, vous pouvez donc décider si vous mettez une
  capitale à chaque nouvel élément ou non.
  · MethodName_ExpectedBehavior_StateUnderTest
  o Exemple : add_returnsTheSum_ofTwoPositiveIntegers()
  o Variante : Add_ReturnsTheSum_OfTwoPositiveIntegers()
  · givenStateUnderTest_whenMethodAction_thenExpectedBehavior
  o Exemple : givenTwoPostiveIntegers_whenAdded_thenTheyShouldBeSummed
  o Variante : givenTwoPositiveIntegerWhenAddedThenTheyShouldBeSummed()
  Vous devez communiquer clairement sur ce sur quoi portent vos tests.

   Utiliser aussi l’attribut @DisplayName pour mieux nommer vos tests.
  

### 2. Analyse du développement 

#### 2.1. Contexte

Le développement de l'application a été réalisé en suivant les étapes suivantes :

1. **Création du projet Maven** : Un projet Maven a été créé pour gérer les dépendances et la structure du projet.

2. **Création du dépôt Git** : Un dépôt Git local a été initialisé pour suivre les modifications du code. (source : Help.md)

3. **Conception de l'application** : La structure de l'application a été conçue en séparant les différentes classes et en respectant les principes de la programmation orientée objet.

3.1. Comment repartir le code ?

Dans le package 'com.calcultableau' : 

com.calcultableau
|- model
    |- UtilisateurTab.java (représentation des données)
|- service
    |- CalculTab.java (logique de calcul)
    |- CalculService.java (orchestration)
    |- DisplayService.java (affichage)
|- CalcultableauApplication.java (point d'entrée)

Pour le trie des des données, on a utilisé la méthode de tri rapide (QuickSort) pour trier le tableau avant de calculer la médiane.

#### 2.2. Conception de l'application

La conception de l'application a été réalisée en suivant les principes de la programmation orientée objet. Les classes ont été organisées de manière à respecter le principe de séparation des préoccupations. La classe `UtilisateurTab` représente les données de l'utilisateur, tandis que la classe `CalculTab` contient la logique de calcul pour la moyenne et la médiane. La classe `CalculService` orchestre les différentes opérations et la classe `DisplayService` est responsable de l'affichage des résultats.
Un Json a été utilisé pour stocker les résultats de l'application. Le fichier JSON contient les informations suivantes :

Je souhaite stocker dans un Json un moyen mot de pass pseudo pour les enseigants. 

```json
{
  "enseignants": [
    {
      "prenom": "Alain",
      "nom": "Dupont",
      "email": "alain.dupont@iut.fr",
      "password": "motdepasse123" (le mot de passe sera bien sur crypté)
    }
    ]
}
```

Pour les etudiants 

```json
{
  "etudiants": [
    {
      "id": 1,
      "prenom": "Jean",
      "nom": "Dupont",
     }
    {
      "id": 2,
      "prenom": "Marie",
      "nom": "Dupont",
     }
    ]
}
```

Pour une classe 

```json
{
  "classes": [
    {
      "id": 1,
      "nom": "Classe A",
      "etudiants": [
        {
          "id": 1,
          "prenom": "Jean",
          "nom": "Dupont"
        },
        {
          "id": 2,
          "prenom": "Marie",
          "nom": "Dupont"
        }
      ]
    }
  ]
}
```

