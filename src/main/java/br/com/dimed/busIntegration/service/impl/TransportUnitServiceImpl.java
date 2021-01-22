package br.com.dimed.busIntegration.service.impl;

import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dimed.busIntegration.constants.Constants;
import br.com.dimed.busIntegration.model.TransportUnit;
import br.com.dimed.busIntegration.response.ItineraryResponse;
import br.com.dimed.busIntegration.service.ItineraryService;
import br.com.dimed.busIntegration.service.TransportUnitService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class TransportUnitServiceImpl implements TransportUnitService {

	private static final String RETORNO_BUSCA_SEM_RESULTADOS = "encontradas";

	@Autowired
	private ItineraryService itineraryService;

	@Override
	public List<TransportUnit> findTransportUnit(String code, Double latitude, Double longitude,
			Double radius) {
		
		List<TransportUnit> transportUnitResponse = new ArrayList<TransportUnit>();
		ItineraryResponse itinerary = itineraryService.getItineraryByCode(code);
		
		if (itinerary != null && !itinerary.getLocationList().isEmpty()) {
			Double earthRadius = 6371.0;
			Double r = (radius / earthRadius);

			Double latitudeMin = latitude - r;
			Double latitudeMax = latitude + r;

			Double ArcoLng = asin(sin(r) / cos(latitude));

			Double longitudeMin = longitude - ArcoLng;
			Double longitudeMax = longitude + ArcoLng;
			
			transportUnitResponse = findTransportUnitByRoute(latitudeMin, latitudeMax, longitudeMin, longitudeMax);
			
			return transportUnitResponse;
		} else {
			return Collections.emptyList();
		}
	}

	private List<TransportUnit> findTransportUnitByRoute(final Double latitudeMin, final Double latitudeMax,
			final Double longitudeMin, final Double longitudeMax) {
		

		List<TransportUnit> transportUnitList = new ArrayList<TransportUnit>();
		TransportUnit transportUnit = null;
	
		OkHttpClient httpClient = new OkHttpClient();
		Response response = null;
		String returnString = null;
		
        Request request = new Request.Builder()
                .url(Constants.DEFAULT_URL.concat(Constants.ENDPOINT_LIST_LINES_ROUTES
                		.concat("((")
                		.concat(latitudeMin.toString()).concat(",")
                		.concat(longitudeMin.toString()).concat("),")
                		.concat("(")
                		.concat(latitudeMax.toString()).concat(",")
                		.concat(longitudeMax.toString()).concat(")))")))
                .get()
                .build();
        
        try {
			response = httpClient.newCall(request).execute();
			
			returnString = response.body().string();
			
			if (returnString.contains(RETORNO_BUSCA_SEM_RESULTADOS)) {
				return Collections.emptyList();
			}
			JSONArray jsonArrayRouter = new JSONArray(returnString);
			JSONObject json = null;
			String jsonCode = null;
			Double jsonLatitude = null;
			Double jsonLongitude = null;

			for (int i = 0; i < jsonArrayRouter.length(); i++) {
				json = jsonArrayRouter.getJSONObject(i);

				jsonCode = json.getString("codigo");
				jsonLatitude = json.getDouble("latitude");
				jsonLongitude = json.getDouble("longitude");

				transportUnit = new TransportUnit();
				transportUnit.setCodigoUnidade(jsonCode);
				transportUnit.setLatitude(jsonLatitude);
				transportUnit.setLongitude(jsonLongitude);

				transportUnitList.add(transportUnit);
			}

		} catch (Exception e) {
			return Collections.emptyList();
		}

		return transportUnitList;
	}
}

