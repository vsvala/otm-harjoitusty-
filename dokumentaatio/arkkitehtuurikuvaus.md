# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkaus - ja luokkarakenne on seuraava:

<img src="https://github.com/vsvala/otm-harjoitustyo/blob/dev/dokumentaatio/kuvat/pakkaus.png" >

Pakkaus _fitme.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _fitme.domain_ sovelluslogiikan ja _fitme.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää neljä erillistä näkymää
- kirjautuminen
- uuden käyttäjän luominen
- ruokapäiväkirjan sivu
- yhteenvetosivu

jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa fitme.ui.FitMeUi.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _DiaryServicen_ metodeja.

Kun sovelluksen päiväkirjan sivun tilanne muuttuu, eli uusi käyttäjä kirjautuu, tai sisältöä poistetaan tai luodaan, kutsutaan sovelluksen metodia redrawView joka renderöi päiväkirjannäkymän uudelleen sovelluslogiikalta saamansa näytettävien Diary listan perusteella.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat User ja Diary, jotka kuvaavat käyttäjiä ja käyttäjän ruokapäiväkirjasivua:
<img src="https://github.com/vsvala/otm-harjoitustyo/blob/dev/dokumentaatio/kuvat/tietokanta%20(1).png" >

Toiminnallisista kokonaisuuksista vastaa luokan DiaryService ainoa olio. Luokka tarjoaa kaikille käyttäliittymän toiminnoille oman metodin. Näitä ovat esim.
- boolean login(String username)
- List<Diary> getDiaryByDate() 
- boolean createDiary(String content, int kcal) 
- boolean delete(String id) 

_DiaryService_ pääsee käsiksi käyttäjiin ja Päiväkirjaan tietojen tallennuksesta vastaavan pakkauksessa _fitme.dao_ sijaitsevien rajapinnat  Diary_Dao_ ja _UserDao_ toteuttavien luokkien kautta. Luokkien toteutuksen injektoidaan sovelluslogiikalle konstruktorikutsun yhteydessä.

DiaryServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:
<img src="https://github.com/vsvala/otm-harjoitustyo/blob/dev/dokumentaatio/kuvat/luokka_pakkausKaavio.png" >

## Tietojen pysyväistallennus

Pakkauksen _fitme.dao_ luokat _DataDiaryoDao_ ja _DataserDao_ huolehtivat tietojen tallettamisesta tietokantaan.

Luokat noudattavat Data Access Object-suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat onkin eristetty rajapintojen _DiaryoDao_ ja _UserDao_ taakse ja sovelluslogiikka ei käytä luokkia suoraan.


### Tietokanta

Sovellus tallettaa käyttäjien ja Diaryjen tiedot fitme.db tietokantaan User ja Diary tauluihin.

Käynnistettäessä ohjelmaa esim jar tiedostosta, Database luoka luo tietokantataulut, jos niitä ei ole olemassa

Sovelluksen tietokantaan tiedot on tallennettu seuraavasti:

CREATE TABLE User(
username varchar(10) PRIMARY KEY,
name varchar (30)
 );
 
CREATE TABLE Diary(
id integer PRIMARY KEY,
user_username varchar,  
day String,
content varchar(100),
kcal Integer (5)
FOREIGN KEY (user_username) REFERENCES User(username)
);


### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaaviona.

#### käyttäjän kirjaantuminen

Kun kirjautumisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja klikataan painiketta loginButton etenee sovelluksen kontrolli seuraavasti:

<img src="https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/login_sekvenssikaavio%20(2).png">

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu sovelluslogiikan diaryService metodia login antaen parametriksi kirjautuneen käyttäjätunnuksen. Sovelluslogiikka selvittää userDao:n ja databasen avulla onko käyttäjätunnus olemassa. Jos on, eli kirjautuminen onnistuu, on seurauksena se että käyttöliittymä vaihtaa näkymäksi diaryScenen, eli sovelluksen varsinaisen päänäkymän ja renderöi näkymään kirjautuneen käyttäjän diaryt eli  ruokapäiväkirjamerkinnät.

#### uuden käyttäjän luominen

Kun uuden käyttäjän luomisnäkymässä on syötetty käyttäjätunnus joka ei ole jo käytössä sekä nimi ja klikataan painiketta createUser etenee sovelluksen kontrolli seuraavasti:

<img src="https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/createUser_sekvens.png">
Tapahtumakäsittelijä kutsuu sovelluslogiikan metodia createUser antaen parametriksi luotavan käyttäjän tiedot. Sovelluslogiikka selvittää userDao:n avulla onko käyttäjätunnus olemassa. Jos ei, eli uuden käyttäjän luominen on mahdollista, luo sovelluslogiikka User-olion ja tallettaa sen kutsumalla userDao:n metodia saveOrUpdate. Tästä seurauksena on se, että käyttöliittymä vaihtaa näkymäksi loginScenen eli kirjautumisnäkymän.


#### Päiväkirjamerkinnän luominen

Uuden todon luovan createTodo-painikkeen klikkaamisen jälkeen sovelluksen kontrolli eteneeseuraavasti:


Tapahtumakäsittelijä kutsuu sovelluslogiikan metodia createDiary antaen parametriksi luotavan työn tiedot. Sovelluslogiikka luo uuden Diary-olion ja tallettaa sen kutsumalla DiaryDao:n metodia saveOrUpdate. Tästä seurauksena on se, että käyttöliittymä päivittää näytettävät päiväkirjamerkinnät kutsumalla omaa metodiaan redrawView.


#### Päiväkirjamerkintöjen hakeminen


#### Muut toiminnallisuudet


## Ohjelman rakenteeseen jääneet heikkoudet

Käyttöliittymä FitMeUi luokka on pitkä. Olisin halunnut jakaa sitä osiin esimerkiksi näkymien mukaan, mutta en saanut sitä  toimimaan, enkä ehtinyt saamaan siihen tarpeeksi ohjeistusta.


