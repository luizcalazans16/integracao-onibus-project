package br.com.dimed.busIntegration.domain.service;

import java.util.List;

import br.com.dimed.busIntegration.domain.model.TransportUnit;

public interface TransportUnitService {

	List<TransportUnit> findTransportUnit(final String code, final Double latitude, final Double longitude, final Double radius);
}
