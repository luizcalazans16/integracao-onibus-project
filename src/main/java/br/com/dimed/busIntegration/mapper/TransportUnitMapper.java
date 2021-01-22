package br.com.dimed.busIntegration.mapper;

import br.com.dimed.busIntegration.dto.TransportUnitDto;
import br.com.dimed.busIntegration.model.TransportUnit;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransportUnitMapper {

	
	public static TransportUnit map(TransportUnitDto dto) {
		if(dto == null) {
			return null;
		}
		TransportUnit entity = new TransportUnit();
		
		entity.setCodigoUnidade(dto.getCodigoUnidade());
		entity.setLatitude(dto.getLatitude());
		entity.setLongitude(dto.getLongitude());
		
		return entity;
	}
	
	public static TransportUnitDto map(TransportUnit entity) {
		return (entity == null) ? null
				: TransportUnitDto.builder()
				.codigoUnidade(entity.getCodigoUnidade())
				.latitude(entity.getLatitude())
				.longitude(entity.getLongitude())
				.build();
	}
}
