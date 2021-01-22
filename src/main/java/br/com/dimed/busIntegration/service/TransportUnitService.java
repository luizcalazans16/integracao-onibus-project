package br.com.dimed.busIntegration.service;

import java.util.List;

import br.com.dimed.busIntegration.model.TransportUnit;

public interface TransportUnitService {

	List<TransportUnit> findTransportUnit(final String code, final Double latitude, final Double longitude, final Double radius);

//	List<TransportUnitResponse> findTransportUnitByRoute(final Double latitudeMin, final Double latitudeMax,
//			final Double longitudeMin, final Double longitudeMax);
}
