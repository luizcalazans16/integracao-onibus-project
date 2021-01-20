package br.com.dimed.busIntegration.service;

import java.util.List;

import br.com.dimed.busIntegration.model.BusLine;

public interface BusLineService {

	List<BusLine> listBusLines();
	
	List<BusLine> findBusLineByName(String busLineName);

	List<BusLine> findBusLineByCode(String code);
	
}
