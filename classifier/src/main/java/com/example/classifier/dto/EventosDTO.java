package com.example.classifier.dto;

import java.util.ArrayList;

public class EventosDTO {
    private ArrayList<TitleUrlDTO> Eventos;

    public EventosDTO() {
    }

    public ArrayList<TitleUrlDTO> getEventos() {
        return Eventos;
    }

    public void setEventos(ArrayList<TitleUrlDTO> eventos) {
        Eventos = eventos;
    }


}
