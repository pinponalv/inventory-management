package com.pinpon.inventory.management.user.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//se va transformar en este orden
@JsonPropertyOrder({"email", "message","status"})
public record AuthResponseDTO (String email, String message, String jwt, boolean status){
    /*
     * Va hacer casi lo mismo que el AuthLoginRequestDTO
     * En la respuesta vamos a tener que mandar el nombre de usuario
     * Un mensaje
     * El jwt y un status(opcional, solo si hay front hay usar para que el front sepa cual fue el resultado y validar)
     * */
}
