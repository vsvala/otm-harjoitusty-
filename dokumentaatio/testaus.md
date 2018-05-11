# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testien ytimen moudostavat sovelluslogiikkaa, eli pakkauksen [fitme.domain](https://github.com/vsvala/otm-harjoitustyo/tree/master/FitMe/src/main/java/fitme/domain) luokkia testaavat integraatiotestit [DiaryServiceUserTest](https://github.com/vsvala/otm-harjoitustyo/blob/master/FitMe/src/test/java/DiaryServiceTest.java) joiden määrittelevät testitapaukset simuloivat käyttöliittymän [DiaryService](https://github.com/vsvala/otm-harjoitustyo/blob/master/FitMe/src/main/java/fitme/domain/DiaryService.java)-olin avulla suorittamia toiminnallisuuksia.
DataDiaryDaon toiminnallisuuksin testaamiseen myös [UseDaoTest](https://github.com/vsvala/otm-harjoitustyo/blob/master/FitMe/src/test/java/UserDaoTest.java)

Integraatiotestit käyttävät datan pysyväistallennukseen DAO-rajapintojen keskusmuistitoteutuksia [DataDiaryDao](https://github.com/vsvala/otm-harjoitustyo/blob/master/FitMe/src/main/java/fitme/domain/DiaryService.java) ja [DataUserDao](https://github.com/vsvala/otm-harjoitustyo/blob/master/FitMe/src/main/java/fitme/dao/DataUserDao.java)

Sovelluslogiikkakerroksen luokille [User](https://github.com/vsvala/otm-harjoitustyo/blob/master/FitMe/src/main/java/fitme/domain/User.java) ja [Diary](https://github.com/vsvala/otm-harjoitustyo/blob/master/FitMe/src/main/java/fitme/domain/Diary.java) on tehty muutamia yksikkötestejä kattamaan tapaukset, joita integraatiotestit eivät kata (mm. olioiden _equals_-metodit ja kostruktorien luonnit).

### DAO-luokat

DAO-luokkan toiminnallisuuksien testaamisessa on käytössä oma testitietokanta, joka luodaan testien aluksi.

### Database

DAO-luokkan toiminnallisuuksien testaamisessa on käytössä oma testitietokanta, joka luodaan testien aluksi.


### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 94% ja haarautumakattavuus 96%

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/t-1.png" width="800">

Testaamatta jäivät tilanteet, joissa käyttäjät tai tehtävät tallettavia tiedostoja ei ole, tai niihin ei ole luku- ja kirjoitusoikeutta.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja kanfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla sekä OSX- että Linux-ympäristöön siten, että sovelluksen käynnistyshakemistossa on ollut käyttöohjeen kuvauksen mukainen _config.properties_-tiedosto.

Sovellusta on testattu sekä tilanteissa, joissa käyttäjät ja työt tallettavat tiedostot ovat olleet olemassa ja joissa niitä ei ole ollut jolloin ohjelma on luonut ne itse.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/vaatimusmaarittely.md#perusversion-tarjoama-toiminnallisuus) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä.

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna tällä hetkellä järkeviä virheilmoituksia, seuraavissa tilanteissa
- konfiguraation määrittelemiin tiedostoihin ei ole luku/kirjoitusoikeuksia
