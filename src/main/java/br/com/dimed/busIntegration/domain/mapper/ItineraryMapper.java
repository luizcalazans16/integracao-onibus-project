package br.com.dimed.busIntegration.domain.mapper;

import br.com.dimed.busIntegration.api.dto.ItineraryDto;
import br.com.dimed.busIntegration.domain.model.Itinerary;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItineraryMapper {

	public static ItineraryDto map(Itinerary entity) {
		if(entity == null) {
			return null;
		}
		
		ItineraryDto itineraryDto = ItineraryDto.builder()
				.id(entity.getId())
				.codigo(entity.getCode())
				.build();
		
		return itineraryDto;
				
	}
}
