package br.com.dimed.busIntegration.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.busIntegration.api.dto.TransportUnitDto;
import br.com.dimed.busIntegration.domain.mapper.TransportUnitMapper;
import br.com.dimed.busIntegration.domain.service.TransportUnitService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transport-unit")
@Slf4j
public class TransportUnitController {

	@Autowired
	private TransportUnitService transportUnitService;

	@GetMapping("/{code}")
	public List<TransportUnitDto> findTransportUnit(@PathVariable final String code,
			@RequestParam final Double latitude, @RequestParam final Double longitude,
			@RequestParam final Double radius) {
		log.info("Buscando unidades de transporte pelos parâmetros: latitude[{}], longitude[{}], radius[{}]", latitude,
				longitude, radius);
		return transportUnitService.findTransportUnit(code, latitude, longitude, radius)
				.stream()
				.map(TransportUnitMapper::map)
				.collect(Collectors.toList());
	}
}
