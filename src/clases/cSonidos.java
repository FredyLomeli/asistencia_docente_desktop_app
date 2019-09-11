/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.applet.AudioClip;

/**
 *
 * @author Frexal
 */
public class cSonidos {
    
    public void correcto(){
        AudioClip sound;
        sound = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/correcto.wav"));
        sound.play();
    }
    
    public void incorrecto(){
        AudioClip sound;
        sound = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/incorrecto.wav"));
        sound.play();
    }
}
