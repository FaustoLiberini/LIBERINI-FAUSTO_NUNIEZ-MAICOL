package com.backend.parcial.entity;

public class Odontologo {
    private String nombre;
    private String apellido;
    private int matricula;
    private int id;

    public Odontologo(String nombre, String apellido, int matricula, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
