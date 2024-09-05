/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.project.eshop.core.utilities.mapping;

/**
 *
 * @author Emre Yıldırım
 */

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	
	ModelMapper forDto();
	
	ModelMapper forRequest();

	<T> T map(Object source, Class<T> destinationType);

}


