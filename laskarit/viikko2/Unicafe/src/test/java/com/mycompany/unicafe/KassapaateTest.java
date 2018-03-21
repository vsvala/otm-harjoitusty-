/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author svsv
 */
public class KassapaateTest {
    Kassapaate paate;
    Maksukortti kortti = new Maksukortti(1000);

    @Before//luodaan kortti alustuksena ennen jokaista testiä 
    public void setUp() {
        paate = new Kassapaate();
    }

    @Test
    public void kassapaateOlemassa() {
        assertTrue(paate!= null);
    }

    @Test
    public void konstruktoriAsettaaRahamaaranOikeinKunLounaitaMyyty0() {
   //if((paate.maukkaitaLounaitaMyyty()+paate.edullisiaLounaitaMyyty())==0){
      assertEquals(100000, paate.kassassaRahaa());
    }


    @Test
    public void syöEdullisestiLisaaKassaanOikeinJosRahaaTarpeeksi() {
        paate.syoEdullisesti(400);
        paate.syoEdullisesti(100);
        assertEquals(100240, paate.kassassaRahaa());

    }
       public void syöEdullisestiKassaPalauttaaOikeinVaihtorahan() {
        assertEquals(760, paate.syoEdullisesti());
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
    public void syöMaukkaastiLisaaKassaanOikeinJOsRahaaTarpeeksi() {
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
    public void JosMaksuRiittäväMaukkaidenLounaidenLkmKasvaa() {
        paate.syoMaukkaasti(400);
        paate.syoMaukkaasti(400);
        paate.syoMaukkaasti(200);
        assertEquals(2, paate.maukkaitaLounaitaMyyty());

    }

    @Test
  public void JosMaksuRiittäväEdullistennLounaidenLkmKasvaa() {
        paate.syoEdullisesti(240);
        paate.syoEdullisesti(240);
        paate.syoMaukkaasti(240);
        paate.syoEdullisesti(100);
        assertEquals(2, paate.edullisiaLounaitaMyyty());

    }

    
    
    
    
    
    
    
    
    

//
//    @Test
//    public void rahanLataaminenKasvattaaSaldoaOikein() {
//        kortti.lataaRahaa(500);
//        assertEquals("saldo: 15.0", kortti.toString());
//    }
//
//    @Test
//    public void rahanOttaminenVähentääSaldoaOikeinJosRahaa() {
//        kortti.otaRahaa(500);
//        assertEquals("saldo: 5.0", kortti.toString());
//    }
//
//    @Test
//    public void rahanOttaminenVähentääSaldoaOikeinJosEiRahaa() {
//        kortti.otaRahaa(15000);
//        assertEquals("saldo: 10.0", kortti.toString());
//    }
//
//    @Test
//    public void riittääköRahatTrueFalse() {
//
//        assertEquals(true, kortti.otaRahaa(900));
//        assertEquals(false, kortti.otaRahaa(11000));
//    }
//
//    //KAssapäätetestit///////////////////////////////////////////////
//    @Test
//    public void luotuKassapääteOnOlemassa() {
//        assertTrue(paate != null);
//    }
//
//    @Test
//    public void syöEdullisestiLisaaKassaanOikeinRahaaTarpeeksi() {
//        paate.syoEdullisesti(400);
//        assertEquals(100240, paate.kassassaRahaa());
//
//    }
//        @Test
//    public void syöEdullisestiEilisaaKassaanJosRahaaLiianVahan() {
//        paate.syoEdullisesti(200);
//        assertEquals(100000, paate.kassassaRahaa());
//
//    }
//
//    public void syöEdullisestiKassaPalauttaaOikeinMaksusta() {
//        paate.syoEdullisesti(400);
//        assertEquals(160, paate.kassassaRahaa());
//    }
//
//    @Test
//    public void syöMaukkaastiLisaaKassaanOikein() {
//        paate.syoMaukkaasti(600);
//        assertEquals(100400, paate.kassassaRahaa());
//
//    }
//        @Test
//    public void syöVähentääKortiltaOikein() {
//        paate.syoMaukkaasti(kortti);
//        assertEquals(600, kortti.saldo());
//
//    }
//         @Test
//    public void syöMaukkaastiEilisaaKassaanJosRahaaLiianVahan() {
//        paate.syoMaukkaasti(200);
//        assertEquals(100000, paate.kassassaRahaa());
//
//    }
//
//
//    public void syömaukkaastiKassaPalauttaaOikeinMaksusta() {
//        paate.syoMaukkaasti(600);
//        assertEquals(200, paate.kassassaRahaa());
//    }
//
//    @Test
//    public void syöEdullisestivahentaaKortiltaOikein() {//????????????
//        kortti.otaRahaa(240);
//        assertEquals(true, paate.syoEdullisesti(kortti));
//
//    }
//
//    @Test
//    public void booleanSyöEdulllisestivahentaaKortiltaOikeinKunOnSaldoa() {//????????????
//  
//        assertEquals(true, kortti.otaRahaa(240));
//
//    }
//      @Test
//    public void booleanSyöEsullisestiOtaRahaaKortiltaKunEiSaldoa() {//????????????
//      kortti.otaRahaa(1000);
//        assertEquals(false, paate.syoEdullisesti(kortti));
//
//    }
//
//    @Test
//    public void booleanSyöMaukkaastiOttaaRahaaKortiltaKunOnSaldoa() {//????????????
//       
//        assertEquals(true,  kortti.otaRahaa(400));
//
//    }
//        @Test
//    public void booleanSyöMaukkaastiEiOtaRahaaKortiltaKunEiSaldoa() {//????????????
//        kortti.otaRahaa(900);
//        assertEquals(false, paate.syoMaukkaasti(kortti));
//
//    }
//
//    @Test
//    public void rahanLatausKortilleKasvattaaKassaaJaKorttiaOikeinKunPositiivinen() {
//
//        paate.lataaRahaaKortille(kortti, 400);
//        assertEquals(100400, paate.kassassaRahaa());
//
//    }
//
//    @Test
//    public void rahanLatausKortilleKasvattaaKassaaJaKorttiaOikeinKunNegatiivinen() {
//
//        paate.lataaRahaaKortille(kortti, -400);
//        assertEquals(100000, paate.kassassaRahaa());
//
//    }
//
//    @Test
//    public void maukkaitaLounaitamyyty() {//????????????
//        paate.syoMaukkaasti(400);
//        paate.syoMaukkaasti(400);
//        assertEquals(2, paate.maukkaitaLounaitaMyyty());
//
//    }
//
//    @Test
//    public void edullisiaLounaitamyyty() {//????????????
//        paate.syoEdullisesti(240);
//        paate.syoEdullisesti(240);
//        paate.syoEdullisesti(240);
//        assertEquals(3, paate.edullisiaLounaitaMyyty());
//
    }
