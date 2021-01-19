package br.com.dimed.busIntegration.service;

import java.util.List;

import br.com.dimed.busIntegration.model.Itinerary;

public interface ItineraryService {

	List<Itinerary> getItineraryByCodigo(String codigoLinha);
}
