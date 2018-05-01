# Käyttöohje

Lataa tiedosto [FitMe.jar](https://github.com/vsvala/otm-harjoitustyo/releases)

## Konfigurointi
Ohjelma luo tietokantataulut, jos niitä ei ole olemassa.

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

Näkymä mahdollistaa olemassaolevien merkintöjen postamisen painikkeella _delete_ sekä uusien merkintöje  luomisen kirjoittamalla syötekenttään food: syödyn ruoan ja kcal: kalorimäärän ja painamalla _add_. 

Sovellus laskee näkyville päivän aikana yhteensä syödyt kalorit

Klikkaamalla näkymän oikean ylänurkan painiketta _logout_, käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään. 

Painamalla  painiketta _summary_, käyttäjä pääsee tarkasteleman yhteenvetosivua, jossa voi tarkastella viimeisen 7 päivän, 30 päivän tai haluamansa ajanjakson ruokia ja kaloreita.    

todo: kuva ja lisää näkymän käytöstä
