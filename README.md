
# <h1>OTM-harjoitustyö: FitMe sovellus
Ohjelmoinnin harjoitustyönä tehty Fit me sovellusprojekti sekä projektin dokumentaatio ja testaus.

## <h2>Dokumentaatio

[Käyttöohje](https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/vaatimusm%C3%A4%C3%A4rittely.md)

[Arkkitehtuurikuvaus](https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuurikuvaus

[Työaikakirjanpito](https://github.com/vsvala/otm-harjoitustyo/blob/master/dokumentaatio/ty%C3%B6aikakirjanpito)
## <h2>Dokumentaatio

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _OtmTodoApp-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
