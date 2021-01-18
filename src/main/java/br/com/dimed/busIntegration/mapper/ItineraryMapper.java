package br.com.dimed.busIntegration.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.dimed.busIntegration.dto.ItineraryDto;
import br.com.dimed.busIntegration.dto.LatitudeLongitudeDto;
import br.com.dimed.busIntegration.model.Itinerary;
import br.com.dimed.busIntegration.model.LatitudeLongitude;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItineraryMapper {

	public static ItineraryDto map(Itinerary entity) {
		if(entity == null) {
			return null;
		}
		
		ItineraryDto itineraryDto = ItineraryDto.builder()
				.idLinha(entity.getLineId())
				.codigo(entity.getCode())
				.latitudeLongitude(mapLatitudeLongitudeList(entity.getLatitudeLongitude()))
				.build();
		
		return itineraryDto;
				
	}

	private List<LatitudeLongitudeDto> mapLatitudeLongitudeList(List<LatitudeLongitude> entity) {
		List<LatitudeLongitudeDto> dtoList = new ArrayList<>();
		for (LatitudeLongitude latLong : entity) {
			LatitudeLongitudeDto dto = mapLatitudeLongitude(latLong);
			dtoList.add(dto);
		}
		return dtoList;

	}

	private LatitudeLongitudeDto mapLatitudeLongitude(LatitudeLongitude entity) {
		return (entity == null) ? null
				: LatitudeLongitudeDto.builder()
				.latitude(entity.getLatitude())
				.longitude(entity.getLongitude())
				.build();
	}
}
