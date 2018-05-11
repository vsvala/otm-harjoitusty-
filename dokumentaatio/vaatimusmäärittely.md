# Vaatimusmäärittely

## Soveluksen tarkoitus

FitMe sovelluksen avulla käyttäjien on mahdollista pitää ruokapäiväkirjaa syödyistä aterioista. Yhteenvetonäkymässä voi tarkastella viimeisen 7 päivän merkintöjä ja hakea aikaisempia merkintöjä hakupäivämäärän mukaan. Sovellusta on mahdollista käyttää useamman rekisteröityneen käyttäjän, joilla kaikilla on oma yksilöllinen päiväkirjansa.

## Käyttäjät

Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli _normaali käyttäjä_. Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu _pääkäyttäjä_.

## Käyttöliittymäluonnos

Sovellus neljästä eri näkymästä

<img src="https://https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/kuvat/kl_luonnos.JPG" width="500">

Sovellus aukeaa kirjautumisnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai onnistuneen kirjautumisen yhteydessä kirjaantuneen käyttäjän päiväkirjasivulle. 

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 4 merkkiä

- käyttäjä voi kirjautua järjestelmään   
  - Kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle
  - jos käyttäjää ei olemassa, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

- käyttäjä näkee ko. päivän päiväkirjamerkinnät sekä niiden yhteenlasketun kalorimäärän

- käyttäjä voi tehdä uuden päiväkirjamerkinnän syöttämällä tekstikenttiin aterian sekä sen arvioidun kalorimäärän 
  -luotu merkintä näkyy ainoastaan sen luoneelle käyttäjälle

- käyttäjä voi lisätä ja poistaa päiväkirjamerkintöjä

- Klikatessaan summary näkymää, käyttäjä näkee yhteenvedon viimeisen 7 päivän päiväkirjamerkinnöistä

- Summary näkymässä käyttäjä voi myös hakea haluamansa päivän päiväkirjamerkinnät näkyville.

- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää voitaisiin laajentaa esim. seuraavilla toiminnallisuuksilla

- päiväkirjaan voisi asettaa tavoite kalorit jolloin se kertoisi paljonko ollaan minuksella tai plussalla
- aikaisempien päivien päiväkirjamerkintöjen editointi ja poisto
- käyttäjien yhteyteen salasana, joka vaaditaan kirjautuessa
- käyttäjätunnuksen (ja siihen liittyvien päiväkirjatiedon) poisto
- viikottainen / kuukausittainen yhteenveto kaloreista kuvana tai diagrammina
- lisätään päiväkirjaan kenttä, johon on mahdollista merkitä tarkempia tietoja tai kommentteja päivän aterioista
- käyttäjätiimit, jotka näkevät toisten päiväkirja merkinnät ja voivat kommentoida tai antaa tsemppitähtiä 
- päiväkirja voisi hakea reseptejä netistä ja arpoa sen päivän aterianehdotuksen
- päiväkirjaan voisi lisätä päivittäisen liikunnan lajin ja keston kirjaamisen
- päiväkirjaan voisi lisätä päivittäisen tai viikottaisen painon seuraamisen
