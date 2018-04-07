/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diaryJavaFXapp.ui;

/**
 *
 * @author svsv
 */
class Henkilo {
    private Henkilo lisattava;
    private String nimi;
    private String password;

    public Henkilo(String nimi, String password) {
        this.nimi = nimi;
        this.password = password;

    }

  public  Henkilo(Henkilo lisattava) {
        this.lisattava = lisattava;
        this.password = password;

    }

}
