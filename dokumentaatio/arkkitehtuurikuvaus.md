# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkaus - ja luokkarakenne on seuraava:

<img src="https://github.com/vsvala/otm-harjoitustyo/blob/dev/dokumentaatio/kuvat/pakkaus.png" >

Pakkaus _fitme.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _fitme.domain_ sovelluslogiikan ja _fitme.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kolme erillistä näkymää
- kirjautuminen
- uuden käyttäjän luominen
- ruokapäiväkirjan sivu

jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa fitme.ui.FitMeUi.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _DiaryServicen_ metodeja.

Kun sovelluksen päiväkirjan sivun tilanne muuttuu, eli uusi käyttäjä kirjautuu, tai sisältöä poistetaan tai luodaan, kutsutaan sovelluksen metodia redrawView joka renderöi päiväkirjannäkymän uudelleen sovelluslogiikalta saamansa näytettävien Diary listan perusteella.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat User ja Diary, jotka kuvaavat käyttäjiä ja käyttäjän ruokapäiväkirjasivua:
<img src="https://github.com/vsvala/otm-harjoitustyo/blob/dev/dokumentaatio/kuvat/tietokanta%20(1).png" >

Toiminnallisista kokonaisuuksista vastaa luokan DiaryService ainoa olio. Luokka tarjoaa kaikille käyttäliittymän toiminnoille oman metodin. Näitä ovat esim.
- boolean login(String username)
- List<Diary>getDiary()  
- void createDiary(String content, User user)
- void delete(int id)

_DiaryService_ pääsee käsiksi käyttäjiin ja Päiväkirjaan tietojen tallennuksesta vastaavan pakkauksessa _fitme.dao_ sijaitsevien rajapinnat  Diary_Dao_ ja _UserDao_ toteuttavien luokkien kautta. Luokkien toteutuksen injektoidaan sovelluslogiikalle konstruktorikutsun yhteydessä.

DiaryServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:
<img src="https://github.com/vsvala/otm-harjoitustyo/blob/dev/dokumentaatio/kuvat/luokka_pakkausKaavio.png" >

## Tietojen pysyväistallennus

Pakkauksen _fitme.dao_ luokat _DataDiaryoDao_ ja _DataserDao_ huolehtivat tietojen tallettamisesta tietokantaan.

Luokat noudattavat Data Access Object-suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat onkin eristetty rajapintojen _DiaryoDao_ ja _UserDao_ taakse ja sovelluslogiikka ei käytä luokkia suoraan.


### Tiedostot

Sovellus tallettaa käyttäjien ja Diaryjen tiedot tietokantaan User ja Diary tauluihin.

Sovelluksen juureen sijoitettu konfiguraatiotiedostoster määrittelee tietokannan nimen.

Sovelluksen tietokantaan tiedot on tallennettu seuraavasti:

CREATE TABLE User(
username varchar(10) PRIMARY KEY,
name varchar (30)
 );
 
CREATE TABLE Diary(
id integer PRIMARY KEY,
user_username varchar,  
day date,
content varchar(100),
kcal Integer (5)
FOREIGN KEY (user_username) REFERENCES User(username)
);


### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

#### käyttäjän kirjaantuminen

#### uuden käyttäjän luominen


#### Muut toiminnallisuudet


## Ohjelman rakenteeseen jääneet heikkoudet


