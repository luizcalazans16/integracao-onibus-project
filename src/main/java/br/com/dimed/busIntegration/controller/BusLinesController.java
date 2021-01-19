package br.com.dimed.busIntegration.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.busIntegration.dto.BusLineDto;
import br.com.dimed.busIntegration.mapper.BusLineMapper;
import br.com.dimed.busIntegration.service.BusLineService;

@RestController
@RequestMapping("/bus-line")
public class BusLinesController {

	@Autowired
	private BusLineService busLineService;

	@GetMapping("/list")
	public List<BusLineDto> listLines() {
		return busLineService.listBusLines().stream().map(BusLineMapper::map).collect(Collectors.toList());
	}
	
	@GetMapping
	public List<BusLineDto> findBusLineByName(@RequestParam String busLineName) {
		return busLineService.findBusLineByName(busLineName).stream()
				.map(BusLineMapper::map)
				.collect(Collectors.toList());
	}

}
