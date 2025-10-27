README
#  TripTracker
---

Scopul proiectului TripTracker este de a realiza o aplicatie functionala care sa poata fi utilizata pentru planificarea si urmarirea eficienta a excursiilor.

##  Funcționalități

* CRUD complet pentru User, Trip, Accommodation, Transporation si ItineraryItem
* Filtrare tripuri după destinație sau număr de zile
* Calcul automat preț total
* Validări DTO și documentație Swagger 

Creare trip basic si avansat
Listare trip-uri in format basic sau avansat, dupa id, cautare dupa numar de zile (</>), cautare dupa locatie.
Calcularea pretului total in functie de inregistrarile facute in cele 3 entitati inglobate.

Itineray: CRUD ||
Accommodation : CRUD ||
Transporation : CRUD
---

##  Structură

* **controller** – REST Controllers (User, Trip)
* **dto** – Obiecte pentru request/response
* **entities** – entitati cu salvare in baza de date. ( Accommodation, ItineraryItem, Transportation, TransportationTypes, Trip, User).
* **mapper** – Conversie Entity ↔ DTO
* **service** – Logica aplicației
* **repository** – Acces la bază de date

---


##  Tehnologii

* Java 24
* Spring Boot 3.5.5 (Web, Data JPA, Validation)
* PostgreSQL / MySQL
* Hibernate, Lombok
* Swagger UI (springdoc-openapi)
* JUnit 5 / Mockito

---
## Folosire API 

NOTA: API-ul este structurat astfel incat prima operatie este crearea unui user. Fara un user nu se poate crea un trip, iar fara trip nu se pot creea structurile Accommodation, Transportation si ItineraryItem.


Dupa pornirea aplicatie se acceseaza Swagger pe portul http://localhost:8080/swagger-ui/index.html 
Prima operatie care trebuie realizata este creeare unui user. Dupa aceea apare posibilitatea de a inregistra mai multe tripuri diferite fiecare cu Accommodation, ItineraryItem si Transporation specifice.
ATENTIE !!! Stergerea unui user implica stergerea definitiva a tuturor datelor inregistrate sub incidenta acestuia.

1. Creează mai întâi un **User**.
2. Creează apoi unul sau mai multe **Tripuri** asociate acelui user.
3. Adaugă pentru fiecare trip cazări, itinerarii și transporturi.

>  Ștergerea unui user șterge toate datele asociate.

Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

##  UserController

* **POST** `/api/users/createUser` – Creează user
* **GET** `/api/users` – Listează toți userii
* **GET** `/api/users/findById/{id}` – Caută user
* **PUT** `/api/users/{id}/updateUser` – Actualizează user
* **DELETE** `/api/users/{id}/deleteUser` – Șterge user

---

##  TripController

* **POST** `/api/trips/users/{userId}/createFullTrip` – Creează trip complet
* **POST** `/api/trips/users/{userId}/createBaseTrip` – Creează trip simplu
* **GET** `/api/trips` – Listează tripuri
* **GET** `/api/trips/lightResponse/{id}` – Trip scurt
* **GET** `/api/trips/FullResponse/{id}` – Trip complet
* **GET** `/api/trips/findByLocation/{destination}` – Caută după destinație
* **PUT** `/api/trips/{id}/updateTrip` – Actualizează trip
* **DELETE** `/api/trips/{id}/deleteTrip` – Șterge trip

### Sub-rute

* `/createAccommodation`, `/createItineraryItem`, `/createTransportation` – adaugă entități în trip

---






