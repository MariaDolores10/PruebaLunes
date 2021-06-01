
import java.awt.Color;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AlumnoDAW
 */
public interface Pintable {
    default void colorear(Color v){
        System.out.println(v.toString());
    } 
}
