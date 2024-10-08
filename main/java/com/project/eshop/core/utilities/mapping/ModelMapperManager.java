/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.eshop.core.utilities.mapping;



/**
 *
 * @author Emre Yıldırım
 */

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperService {

	private final ModelMapper modelMapper;

	public ModelMapperManager(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}


        @Override
	public ModelMapper forDto() {
	 this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
	 return modelMapper;
	}
	
        @Override
	public ModelMapper forRequest() {
		 this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		 return modelMapper;
	}

	@Override
	public <T> T map(Object source, Class<T> destinationType) {
		return modelMapper.map(source, destinationType);
	}
}

