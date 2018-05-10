# Käyttöohje

Lataa tiedosto [FitMe.jar](https://github.com/vsvala/otm-harjoitustyo/releases)

## Konfigurointi
Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käyttäjät ja ruokapäiväkirjamerkinnät tallentavan tietokannan nimen. Tiedoston muoto on seuraava

```
usedDatabase=jdbc:sqlite:fitme.db
```

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar FitMe.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/SignIn.png" width="400">

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus syötekenttään ja painamalla _login_.

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään panikkeella _create new user_.

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja painamalla _create_

<img src="https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/CreateUser.png" width="400">

Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.

## Ruokapäiväkirjamerkintöjen luominen ja poistaminen

Onnistuneen kirjautumisen myötä siirrytään käyttäjän päiväkirjamerkintöjä listaavaan näkymään

<img src="https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/DiaryPage.png" width="600">

Näkymä mahdollistaa olemassaolevien merkintöjen postamisen painikkeella _delete_ sekä uusien päiväkirjamerkintöjen  luomisen. Merkintä luodaan kirjoittamalla ensin syötekenttään food: syöty ruoka sekä kenttään kcal: kalorimäärän ja sen jälkeen klikkaamalla _add_ painiketta. 

Sovellus näyttää kyseisen päiväm merkinnät sekä laskee päivän aikana yhteensä syödyt kalorit.

Klikkaamalla näkymän oikean ylänurkan painiketta _logout_, käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään. Ohjelman voi sulkea kirjautumisnäkymän yläkulman raksista.

<img src="https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/summary.png" width="600">

Painamalla  painiketta _summary_ , käyttäjä pääsee tarkasteleman yhteenvetosivua, jossa näkyy viimeisen 7 päivän ruokamerkinnät ja yhteenlaskettu kalorimäärä.

Sivun alalaidan hakukentään voi kirjoittaa haettavan päiväyksen dd.mm.yyyy muodossa ja klikkaamall _Search_ painiketta näytetään kyseisen päivän päiväkirjamerkinnät ja yhteenlasketut kalorit.

_Last 7 days_ painikkeesta pääsee takaisin viimeisen 7 päivän yhteenvetoon.


Klikkaamalla näkymän oikean ylänurkan painiketta _logout_, käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään

_diary_ painikkeen klikkaus puolestaan palauttaa takaisin päiväkirjamerkintöjen lisäämisnäkymään.
 



