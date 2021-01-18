package br.com.dimed.busIntegration.service;

import br.com.dimed.busIntegration.model.Itinerary;

public interface ItineraryService {

	Itinerary getItineraryByCodigo(String codigoLinha);
}
