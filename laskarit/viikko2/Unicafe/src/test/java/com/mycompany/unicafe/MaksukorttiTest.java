package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate paate;

    @Before//luodaan kortti alustuksena ennen jokaista testiä 
    public void setUp() {
        kortti = new Maksukortti(10000);
        paate = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {

        String vastaus = kortti.toString();

        assertEquals("saldo: 100.0", vastaus);
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(500);
        assertEquals("saldo: 105.0", kortti.toString());
    }

    @Test
    public void rahanOttaminenVähentääSaldoaOikeinJosRahaa() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 95.0", kortti.toString());
    }

    @Test
    public void rahanOttaminenVähentääSaldoaOikeinJosEiRahaa() {
        kortti.otaRahaa(15000);
        assertEquals("saldo: 100.0", kortti.toString());
    }

    @Test
    public void riittääköRahatTrueFalse() {

        assertEquals(true, kortti.otaRahaa(900));
        assertEquals(false, kortti.otaRahaa(11000));
    }

    @Test
    public void luotuKassapääteOnOlemassa() {
        assertTrue(paate != null);
    }

    @Test
    public void syöEdullisestiLisaaKassaanOikein() {
        paate.syoEdullisesti(400);
        assertEquals(100240, paate.kassassaRahaa());

    }

    public void syöEdullisestiKassaPalauttaaOikeinMaksusta() {
        paate.syoEdullisesti(400);
        assertEquals(160, paate.kassassaRahaa());
    }

    @Test
    public void syöMaukkaastiLisaaKassaanOikein() {
        paate.syoMaukkaasti(600);
        assertEquals(100400, paate.kassassaRahaa());

    }

    public void syömaukkaastiKassaPalauttaaOikeinMaksusta() {
        paate.syoMaukkaasti(600);
        assertEquals(200, paate.kassassaRahaa());
    }

    @Test
    public void syöEdullisestivahentaaKortiltaOikein() {//????????????
        kortti.otaRahaa(240);
        assertEquals(true, paate.syoEdullisesti(kortti));

    }

    @Test
    public void syöMaukkaastivahentaaKortiltaOikein() {//????????????
        kortti.otaRahaa(400);
        assertEquals(true, paate.syoMaukkaasti(kortti));

    }
//            @Test
//    public void syöMaukkaastivahentaaKortiltaOikeinJosEiRahaaTarpeeksi() {//????????????
//       kortti.otaRahaa(1200);
//        assertEquals(false, paate.syoMaukkaasti(kortti));
//      
//    }

    @Test
    public void rahanLatausKortilleKasvattaaKassaaOikein() {//????????????
        kortti.otaRahaa(400);
        assertEquals(true, paate.syoMaukkaasti(kortti));

    }
//            @Test
//    public void rahanLatausKortilleKasvattaaKassaaJaKorttiaOikein() {
//        kortti.lataaRahaa(400);
//        assertEquals(1400, paate.);
//      
//    }

    @Test
    public void maukkaitaLounaitamyyty() {//????????????
        paate.syoMaukkaasti(400);
        paate.syoMaukkaasti(400);
        assertEquals(2, paate.maukkaitaLounaitaMyyty());

    }

    @Test
    public void edullisiaLounaitamyyty() {//????????????
        paate.syoEdullisesti(240);
        paate.syoEdullisesti(240);   
        paate.syoEdullisesti(240);
        assertEquals(3, paate.edullisiaLounaitaMyyty());

    }
}
