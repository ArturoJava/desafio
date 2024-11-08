package com.aluracursos.desafio.principal;

import com.aluracursos.desafio.model.Datos;
import com.aluracursos.desafio.model.DatosLibros;
import com.aluracursos.desafio.service.ConsumoAPI;
import com.aluracursos.desafio.service.ConvierteDatos;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class Principal {
    private static final String URL_BASE ="https://gutendex.com/books/" ;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor= new ConvierteDatos();

    public void muestraElMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE );
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);
        //Top 10 Libros mas descargados -sin enumerar
        System.out.println("Top 10 libros mas descargados");
//        datos.resultados().stream()
//                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
//                .limit(10)
//                .map(l->l.titulo().toUpperCase())
//                .forEach(System.out::println);
        // Top 10 Libros mas descargados - Enumerados
        AtomicInteger contador = new AtomicInteger(1);

        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(titulo -> System.out.println(contador.getAndIncrement() + ". " + titulo));
    }
}
