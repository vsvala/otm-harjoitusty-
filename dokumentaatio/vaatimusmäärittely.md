# Vaatimusmäärittely

## Soveluksen tarkoitus

FitMe sovelluksen avulla käyttäjien on mahdollista pitää ruokapäiväkirjaa syödyistä aterioista. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttäjän, joilla kaikilla on oma yksilöllinen päiväkirjansa.

## Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli _normaali käyttäjä_. Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu _pääkäyttäjä_.

## Käyttöliittymäluonnos

Sovellus neljästä eri näkymästä

<img src="https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/kl_luonnos.JPG" width="500">

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen kirjautumisen yhteydessä kirjaantuneen käyttäjän päiväkirjasivulle. 

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 4 merkkiä

- käyttäjä voi kirjautua järjestelmään
  - kirjautunut käyttäjä näkee kyseisen päivän päiväkirjan pohjan  ja sen päivän merkinnät 

-Kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle
  - jos käyttäjää ei olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

- käyttäjä näkee kyseisen päivän päiväkirjan pohjan jossa näkyy kyseisen päivän merkinnät

- käyttäjä voi kirjoittaa ylös aterioitaan sekä arvioidun kalorimäärän 

- Sovellus laskee yhteen päivän kalorit

- luotu päiväkirja näkyy ainoastaan sen luoneelle käyttäjälle

- käyttäjä voi lisätä ja poistaa aterioita

- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla

- viikottainen / kuukausittainen yhteenveto tekstinä /kuvana ruoasta ja liikunnasta
- päiväkirjan tietojen editointi
- tietojen järjestely tärkeysjärjestykseen
- käyttäjätiimit, jotka näkevät toisten ruokailut ja voivat kommentoida tai antaa tähtiä 
- lisätään päiväkirjaan kenttä, johon on mahdollista merkitä tarkempia tietoja päivän aterioista/liikunnasta
- käyttäjien yhteyteen salasana, joka vaaditaan kirjautuessa
- käyttäjätunnuksen (ja siihen liittyvien päiväkirjatiedon) poisto
- päiväkirja voisi hakea reseptejä netistä ja arpoa sen päivän aterianehdotuksen
- päiväkirjaan voidaan asettaa tavoite kalorit ja se kertoo paljonko ollaan minuksella tai plussalla
- päiväkirjaan voi lisätä päivittäisen liikunnan lajin ja keston
- päiväkirjaan voi tehdä hakuja päivämäärän perusteella
