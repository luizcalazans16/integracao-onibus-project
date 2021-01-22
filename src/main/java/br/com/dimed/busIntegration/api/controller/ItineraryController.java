package br.com.dimed.busIntegration.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.busIntegration.api.response.ItineraryResponse;
import br.com.dimed.busIntegration.domain.service.ItineraryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/itinerary")
@Slf4j
public class ItineraryController {

	@Autowired
	private ItineraryService itineraryService;
	
	@GetMapping("/{code}")
	public ItineraryResponse getItineraryByCode(@PathVariable String code) {
		log.info("Buscando itinerário pelo código: [{}]", code);
		return itineraryService.getItineraryByCode(code);
	}
	
}
