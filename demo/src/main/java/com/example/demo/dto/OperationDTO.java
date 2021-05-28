package com.example.demo.dto;

public class OperationDTO {

    private float valueA;
    private float valueB;

    public OperationDTO() {
    }

    public OperationDTO(float valueA, float valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public float getValueA() {
        return valueA;
    }

    public void setValueA(float valueA) {
        this.valueA = valueA;
    }

    public float getValueB() {
        return valueB;
    }

    public void setValueB(float valueB) {
        this.valueB = valueB;
    }

    public float getDivide(){
        return getValueA()/getValueB();
    }
    public float getMultiply(){
        return getValueA()*getValueB();
    }
}
