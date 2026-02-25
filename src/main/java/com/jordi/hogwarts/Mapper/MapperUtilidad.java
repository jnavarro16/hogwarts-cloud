package com.jordi.hogwarts.Mapper;

public class MapperUtilidad
{
    public static String nombreCompleto (String nombre, String apellido) {
        String name = (nombre == null) ? "": nombre.trim();
        String cognom = (apellido == null) ? "": apellido.trim();
        return (name+" "+cognom).trim();
    }
}
