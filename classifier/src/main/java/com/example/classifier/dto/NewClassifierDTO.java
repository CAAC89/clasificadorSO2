package com.example.classifier.dto;

import java.util.ArrayList;

public class NewClassifierDTO {
	private ArrayList<ResponseDTO> responseDTOList =new ArrayList<ResponseDTO>();

	public ArrayList<ResponseDTO> getResponseDTOList() {
		return responseDTOList;
	}

	public void setResponseDTOList(ArrayList<ResponseDTO> responseDTOList) {
		this.responseDTOList = responseDTOList;
	}

	@Override
	public String toString() {
		return "{responseDTOList=" + responseDTOList + "}";
	}
	
}
