package br.com.dimed.busIntegration.mapper;

import br.com.dimed.busIntegration.dto.BusLineDto;
import br.com.dimed.busIntegration.model.BusLine;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BusLineMapper {

	public static BusLineDto map(BusLine entity) {
		if(entity == null) {
			return null;
		}
		
		BusLineDto busLineDto = BusLineDto.builder()
				.id(entity.getId())
				.codigo(entity.getCode())
				.nome(entity.getName())
				.build();
		
		return busLineDto;
	}
	
}
