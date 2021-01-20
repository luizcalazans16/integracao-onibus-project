package br.com.dimed.busIntegration.response;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import br.com.dimed.busIntegration.model.Location;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ItineraryResponse.ItineraryResponseBuilder.class)
public class ItineraryResponse {
	
	private String idLinha;
	private String codigo;
	private String nome;
	private List<Location> locationList;
	private String returnMessage;
	

	@JsonPOJOBuilder(withPrefix = "")
	public static final class ItineraryResponseBuilder {
		
	}
}
