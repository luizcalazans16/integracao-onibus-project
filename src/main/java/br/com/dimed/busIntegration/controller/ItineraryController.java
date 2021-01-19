package br.com.dimed.busIntegration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.busIntegration.model.Itinerary;
import br.com.dimed.busIntegration.service.ItineraryService;

@RestController
@RequestMapping("/itinerary")
public class ItineraryController {

	@Autowired
	private ItineraryService itineraryService;
	
	@GetMapping("/{code}")
	public List<Itinerary> getItineraryByCode(@PathVariable String code) {
		return itineraryService.getItineraryByCodigo(code);
	}
	
}
