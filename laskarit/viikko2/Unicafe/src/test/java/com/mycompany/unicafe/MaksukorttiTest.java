package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate paate;

    @Before//luodaan kortti alustuksena ennen jokaista testiä 
    public void setUp() {
        kortti = new Maksukortti(1000);
        paate = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {

        String vastaus = kortti.toString();

        assertEquals("saldo: 10.0", vastaus);
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(500);
        assertEquals("saldo: 15.0", kortti.toString());
    }

    @Test
    public void rahanOttaminenVähentääSaldoaOikeinJosRahaa() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }

    @Test
    public void rahanOttaminenVähentääSaldoaOikeinJosEiRahaa() {
        kortti.otaRahaa(15000);
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void riittääköRahatTrueFalse() {

        assertEquals(true, kortti.otaRahaa(900));
        assertEquals(false, kortti.otaRahaa(11000));
    }

    //KAssapäätetestit///////////////////////////////////////////////
    @Test
    public void luotuKassapääteOnOlemassa() {
        assertTrue(paate != null);
    }

    @Test
    public void syöEdullisestiLisaaKassaanOikeinRahaaTarpeeksi() {
        paate.syoEdullisesti(400);
        assertEquals(100240, paate.kassassaRahaa());

    }
        @Test
    public void syöEdullisestiEilisaaKassaanJosRahaaLiianVahan() {
        paate.syoEdullisesti(200);
        assertEquals(100000, paate.kassassaRahaa());

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
         @Test
    public void syöMaukkaastiEilisaaKassaanJosRahaaLiianVahan() {
        paate.syoMaukkaasti(200);
        assertEquals(100000, paate.kassassaRahaa());

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
    public void booleanSyöEdulllisestivahentaaKortiltaOikeinKunOnSaldoa() {//????????????
  
        assertEquals(true, kortti.otaRahaa(240));

    }
      @Test
    public void booleanSyöEsullisestiOtaRahaaKortiltaKunEiSaldoa() {//????????????
      kortti.otaRahaa(1000);
        assertEquals(false, paate.syoEdullisesti(kortti));

    }

    @Test
    public void booleanSyöMaukkaastiOttaaRahaaKortiltaKunOnSaldoa() {//????????????
       
        assertEquals(true,  kortti.otaRahaa(400));

    }
        @Test
    public void booleanSyöMaukkaastiEiOtaRahaaKortiltaKunEiSaldoa() {//????????????
        kortti.otaRahaa(900);
        assertEquals(false, paate.syoMaukkaasti(kortti));

    }

    @Test
    public void rahanLatausKortilleKasvattaaKassaaJaKorttiaOikeinKunPositiivinen() {

        paate.lataaRahaaKortille(kortti, 400);
        assertEquals(100400, paate.kassassaRahaa());

    }

    @Test
    public void rahanLatausKortilleKasvattaaKassaaJaKorttiaOikeinKunNegatiivinen() {

        paate.lataaRahaaKortille(kortti, -400);
        assertEquals(100000, paate.kassassaRahaa());

    }

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

//MAin////////////////////////////////////////////////
   @Test
    public void mainLuoUudenKassapäätteen() {
       
        assertTrue(paate!= null);  

    }

//       @Test
//    public void mainLuoUudenMaksuKortin() {
//       
//        assertTrue(kortti!= null);  
//
//    }


}
