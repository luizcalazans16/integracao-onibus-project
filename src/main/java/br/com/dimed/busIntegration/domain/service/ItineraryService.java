package br.com.dimed.busIntegration.domain.service;

import br.com.dimed.busIntegration.api.response.ItineraryResponse;

public interface ItineraryService {

	ItineraryResponse getItineraryByCode(String codigoLinha);
}
