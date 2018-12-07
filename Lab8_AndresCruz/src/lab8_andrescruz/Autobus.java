/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8_andrescruz;

import java.util.ArrayList;
import java.awt.Color;

/**
 *
 * @author MBanegas
 */
public class Autobus {
    String id;
    String placa;
    Color color;
    Double velocidad;
    ArrayList<Estudiante>estudiantes=new ArrayList();

    public Autobus(String id, String placa, Color color, Double velocidad) {
        this.id = id;
        this.placa = placa;
        this.color = color;
        this.velocidad = velocidad;
    }        
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return "Autobus{" + "id=" + id + ", placa=" + placa + ", color=" + color + ", velocidad=" + velocidad + ", estudiantes=" + estudiantes + '}';
    }
    
    
}
