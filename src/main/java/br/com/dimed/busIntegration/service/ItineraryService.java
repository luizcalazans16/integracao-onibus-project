package br.com.dimed.busIntegration.service;

import br.com.dimed.busIntegration.response.ItineraryResponse;

public interface ItineraryService {

	ItineraryResponse getItineraryByCodigo(String codigoLinha);
}
