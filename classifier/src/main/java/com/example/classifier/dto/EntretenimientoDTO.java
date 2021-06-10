package com.example.classifier.dto;

import java.util.ArrayList;

public class EntretenimientoDTO {
    private ArrayList<TitleUrlDTO> Entretenimiento;

    public EntretenimientoDTO() {
    }

    public ArrayList<TitleUrlDTO> getEntretenimiento() {
        return Entretenimiento;
    }

    public void setEntretenimiento(ArrayList<TitleUrlDTO> entretenimiento) {
        Entretenimiento = entretenimiento;
    }


}
