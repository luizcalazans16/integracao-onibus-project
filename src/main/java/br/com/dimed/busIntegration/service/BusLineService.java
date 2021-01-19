package br.com.dimed.busIntegration.service;

import java.util.List;

import br.com.dimed.busIntegration.model.BusLine;

public interface BusLineService {

	List<BusLine> listBusLines();
	
	BusLine findBusLineByName(String busLineName);
	
}
