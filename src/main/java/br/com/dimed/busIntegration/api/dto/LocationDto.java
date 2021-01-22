package br.com.dimed.busIntegration.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lat",
        "lng"
})
@Value
@JsonDeserialize(builder = LocationDto.LocationDtoBuilder.class)
public class LocationDto {

    @JsonProperty("lat")
    public String lat;
    
    @JsonProperty("lng")
    public String lng;

    public Long idLinha;
    
    @JsonPOJOBuilder(withPrefix = "")
    public static final class LocationDtoBuilder {
    	
    }
}
