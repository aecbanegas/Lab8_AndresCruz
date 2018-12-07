/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8_andrescruz;

/**
 *
 * @author MBanegas
 */
public class Estudiante {
    String nombre;
    int edad;
    String cuenta;
    Parada parada;

    public Estudiante(String nombre, int edad, String cuenta, Parada parada) {
        this.nombre = nombre;
        this.edad = edad;
        this.cuenta = cuenta;
        this.parada = parada;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }

    @Override
    public String toString() {
        return "Estudiante= " + nombre;
    }
    
    
}
