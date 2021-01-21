package br.com.dimed.busIntegration.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.busIntegration.dto.BusLineDto;
import br.com.dimed.busIntegration.mapper.BusLineMapper;
import br.com.dimed.busIntegration.service.BusLineService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bus-line")
@Slf4j
public class BusLinesController {

	@Autowired
	private BusLineService busLineService;

	@GetMapping("/list")
	public List<BusLineDto> listLines() {
		log.info("Listando linhas de ônibus");
		return busLineService.listBusLines()
				.stream()
				.map(BusLineMapper::map)
				.collect(Collectors.toList());
	}
	
	@GetMapping	
	public List<BusLineDto> findBusLineByName(@RequestParam final String busLineName) {
		log.info("Listando linhas de ônibus pelo parâmetro: busLineName[{}}", busLineName);
		return busLineService.findBusLineByName(busLineName)
				.stream()
				.map(BusLineMapper::map)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{code}")
	public BusLineDto findBusLineByCode(@PathVariable final String code) {
		log.info("Buscando linha de ônibus pelo código: [{}]", code);
		return BusLineMapper.map(busLineService.findBusLineByCode(code));
	}
	
	@GetMapping("/find-by-routes")
	public List<BusLineDto> findBusLinesByLatitudeAndLongitude(@RequestParam final Double latitudeMin,
			@RequestParam final Double latitudeMax, @RequestParam final Double longitudeMin,
			@RequestParam Double longitudeMax) {
		log.info(
				"Buscando as linhas de ônibus pelos parâmetros: latitudeMin[{}], latitudeMax[{}] longitudeMin[{}], longitudeMax[{}]",
				latitudeMin, latitudeMax, longitudeMin, longitudeMax);
		return busLineService.findBusLinesByRoute(latitudeMin, latitudeMax, longitudeMin, longitudeMax)
				.stream()
				.map(BusLineMapper::map)
				.collect(Collectors.toList());
	}
}
