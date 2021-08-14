# Recipe Manager Application

### Program
A program feladata a receptek létrehozása, kezelése, úgy mint perzisztálás, adatok összekapcsolása, változtatása, törlése.

### Entitások:
* A recept készítője(beküldője)
* Recept
* Hozzávalók

### Creator
Attribútumai:
* Név (kötelező, max 50 karakter): a készítő neve
* Ssn (nem kötelező, max 20 karakter): a készítő "TAJ" száma, a mező egyedivé tételével igény esetén lehetőséget ad a készítők egyedi, nem id alapú azonosítására


| Http metódus | Végpont                        | Leírás                              |
| ------------ | ---------------------------    | ----------------------------------- |
| GET          | `"api/creators"`               | lekérdezi az összes készítőt        |
| GET          | `"api/creators/{id}"`          | lekérdez egy készítőt id alapján    |
| POST         | `"api/creators"`               | létrehoz egy készítőt               |
| PUT          | `"api/creators/{id}"`          | módosít egy készítőt id alapján     |
| DELETE       | `"api/creators/{id}"`          | töröl egy készítőt id alapján       |
| POST         | `"api/creators/{id}/recipes"`  | hozzáad egy receptet id alapján     |

### Recipe
Attribútumai:
* Név (kötelező, max 50 karakter): a recept neve
* Description (kötelező, max 2000 karakter): recept elkészítésének leírása

| Http metódus | Végpont                            | Leírás                                |
| ------------ | -------------------------------    | --------------------------------------|
| GET          | `"api/recipes"`                    | lekérdezi az összes receptet          |
| GET          | `"api/recipes/{id}"`               | lekérdez egy receptet id alapján      |
| POST         | `"api/recipes"`                    | létrehoz egy receptet                 |
| PUT          | `"api/recipes/{id}"`               | módosít egy receptet id alapján       |
| DELETE       | `"api/recipes/{id}"`               | töröl egy receptet id alapján         |
| POST         | `"api/recipes/{id}/ingredients"`   | hozzáad egy hozzávalót id alapján     |

### Ingredient
Attribútumai:
* Név (kötelező, max 255 karakter): a hozzávaló neve
* Mennyiségi egység (kötelező, max 20 karakter): a hozzávaló mértékegysége
* Mennyiség (nem lehet üres): a hozzávaló mennyisége

| Http metódus | Végpont                  | Leírás                             |
| ------------ | ---------------------    | --------------------------------   |
| GET          | `"api/ingredients"`      | lekérdezi az összes hozzávalót     |
| GET          | `"api/ingredients/{id}"` | lekérdez egy hozzávalót id alapján |
| POST         | `"api/ingredients"`      | létrehoz egy hozzávalót            |
| PUT          | `"api/ingredients/{id}"` | módosít egy hozzávalót id alapján  |
| DELETE       | `"api/ingredients/{id}"` | töröl egy hozzávalót id alapján    |

Adatbázis és felhasználó létrehozása
```sql
use mysql;
create schema if not exists recipes default character set utf8 collate utf8_hungarian_ci;

create user 'recipes'@'localhost' identified by 'recipes';
grant all on *.* to 'recipes'@'localhost';
```