package com.example.mensajes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import me.tell.mensajes.BorrarRequest;
import me.tell.mensajes.BorrarResponse;
import me.tell.mensajes.BuscarSaludosResponse;
import me.tell.mensajes.SaludarRequest;
import me.tell.mensajes.SaludarResponse;
import me.tell.mensajes.ModificarRequest;
import me.tell.mensajes.ModificarResponse;

@Endpoint
public class SaludadoresEndPoint {
    @Autowired
    private Isaludadores isaludadores;
    @PayloadRoot(namespace = "http://tell.me/mensajes", localPart = "SaludarRequest")
    @ResponsePayload
    public SaludarResponse dameSaludo(@RequestPayload SaludarRequest peticion) {
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("hola " + peticion.getNombre());
        //salvar a la BD
        Saludadores saludadores = new Saludadores();
        saludadores.setNombre(peticion.getNombre());
        isaludadores.save(saludadores);
        return respuesta;
    }

    @PayloadRoot(namespace = "http://tell.me/mensajes", localPart = "BuscarSaludosRequest")
    @ResponsePayload
    public BuscarSaludosResponse dameSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        Iterable<Saludadores> listaSaludadores = isaludadores.findAll();
        for(Saludadores ls : listaSaludadores){
            BuscarSaludosResponse.Saludador e= new BuscarSaludosResponse.Saludador();
            e.setId(ls.getId());
            e.setNombre(ls.getNombre());
            respuesta.getSaludador().add(e);
        }
        return respuesta;
    }

    @PayloadRoot(namespace = "http://tell.me/mensajes", localPart = "ModificarRequest")
    @ResponsePayload
    public ModificarResponse updateNombre(@RequestPayload ModificarRequest peticion){
        ModificarResponse respuesta = new ModificarResponse();
        int id = peticion.getId();//id existente
        String nombre2 = peticion.getNombre2();//nombre nuevo
        
        Saludadores saludador = isaludadores.findById(id).orElse(null);
        saludador.setNombre(nombre2);
        isaludadores.save(saludador);
        respuesta.setRespuesta("Nombre nuevo: " + nombre2);
        return respuesta;
    }

    @PayloadRoot(namespace = "http://tell.me/mensajes", localPart = "BorrarRequest")
    @ResponsePayload
    public BorrarResponse deleteNombre(@RequestPayload BorrarRequest peticion){
        BorrarResponse respuesta = new BorrarResponse();
        int id = peticion.getId();//id existente
        
        Saludadores saludador = isaludadores.findById(id).orElse(null);
        isaludadores.delete(saludador);
        respuesta.setRespuesta("El id " + id + " ha sido eliminado de la BD");
        //return ResponseEntity.ok().build();

        return respuesta;
    }


}