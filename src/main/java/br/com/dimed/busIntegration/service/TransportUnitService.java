package br.com.dimed.busIntegration.service;

import java.util.List;

import br.com.dimed.busIntegration.model.TransportUnit;

public interface TransportUnitService {

	List<TransportUnit> findTransportUnit(final String code, final Double latitude, final Double longitude, final Double radius);
}
