# Sujet ECF n°4 
#### Développement et sécurisation d'une API REST pour la gestion d'un observatoire astronomique virtuel. 🌌
" *Avec l'augmentation de l'intérêt pour l'astronomie et l'espace, une organisation veut créer une plateforme en ligne où les amateurs d'astronomie peuvent observer des phénomènes spatiaux, enregistrer leurs observations.*" 🌠

----------------------------
`Architecture et conception`

Choix d'une architecture **micro-services**

-> Facilement remplaçable pour permettre une évolutivité de l'application

-> Déployable indépendamment 

-> Son développement est indépendant

`Développement de l'API avec Spring`

-> + Utilisation JPA, MySQL et PostMan

`Consignes (endpoints, sécurisation, tests)`

`Les endpoints`

-> Lister différents objets célestes. 

-> Permettre aux utilisateurs de créer et de partager leurs observations (dates, descriptions, éventuellement des photos). 

-> Rechercher des observations par objet céleste, date ou utilisateur.

`Pour la sécurisation`

-> Mettez en œuvre JWT pour l'authentification et l'autorisation.

-> Seuls les utilisateurs authentifiés peuvent créer des observations.

-> Les observations peuvent être rendues publiques ou privées par les utilisateurs.

`Pour les tests`

-> Rédigez des tests unitaires pour les éléments essentiels. 

-> Atteignez une couverture de tests d'au moins 80%. ⭐

----------------------
### Etapes ✨

**1ʳᵉ étape : Déterminer les rôles de nos fonctionnalités**

→ Création de trois projets avec la création de trois projets distincts et indépendants : CelestialObject, SpaceObservation et Authentification Space 

-> Architecture basé sur le principe Controller-Service-Repository 

-> CelestialObject : A pour but de gérer à lui seul toute la fonctionnalité de l'objet céleste (création de l'objet et affichage liste d'objets célestes)

-> SpaceObservation : A pour but de gérer à lui seul toute la fonctionnalité lié aux observations de l'objet céleste : création d'observation (date d'observation, lieu d'observation, instrument utilisé, condition d'observation et description de l'observation) + et recherche par id/titre/utilisateur de l'objet céleste

-> AuthentificationSpace : A pour but de gérer à la fois l'authentification (avec aspect sécurité) + utilisateurs.

-> Création restClient : permettre aux différents projets de communiquer entre eux.

**2ᵉ étape : Projet micro-service Celestial Object**
→ Création de l'architecture. Test avec Postman si récupération des données ok. Optimisation de l'architecture avec création OAP + DTO + gestion d'exception. 


**3ᵉ étape : Projet micro-service Authentification**
→ Permet la création d'un utilisateur via la méthode register + se connecter via login. Création de token unique qui permettra de donner accès aux données/création uniquement aux personnes enregistrées.

**4ᵉ étape : Projet micro-service Observation**
→ Création d'observations + méthodes recherches (lieu/userId/objectId)'

**5ᵉ étape : Phase de tests**
