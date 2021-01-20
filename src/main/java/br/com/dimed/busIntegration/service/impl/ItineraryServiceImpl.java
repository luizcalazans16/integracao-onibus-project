package br.com.dimed.busIntegration.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dimed.busIntegration.constants.Constants;
import br.com.dimed.busIntegration.model.Location;
import br.com.dimed.busIntegration.response.ItineraryResponse;
import br.com.dimed.busIntegration.service.ItineraryService;
import br.com.dimed.busIntegration.utils.JSONUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class ItineraryServiceImpl implements ItineraryService {


	@Override
	public ItineraryResponse getItineraryByCodigo(String lineCode) {

		OkHttpClient httpClient = new OkHttpClient();
		Response response = null;
		String result = null;
		List<Location> locationList = new ArrayList<Location>();

		Request request = new Request.Builder()
				.url(Constants.DEFAULT_URL.concat(Constants.ENDPOINT_LIST_ITINERARY_BY_LINE).concat(lineCode)).get()
				.build();

		try {
			response = httpClient.newCall(request).execute();
			result = JSONUtil.unescape(response.body().string());

			JSONObject jsonObjectLine = null;
			JSONObject jsonObjectRouter = null;
			JSONObject jsonObjectLineName = null;
			JSONObject jsonObejctLineCode = null;
			JSONArray jsonArrayRouter = new JSONArray();
			Map<String, Object> map = null;
			
			
			TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};

			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(result, typeRef);
			
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				
				if(key.equals("nome")) {
					jsonObjectLineName = new JSONObject();
					jsonObjectLineName.put(key, entry.getValue());
				}
				
				if(key.equals("codigo")) {
					jsonObejctLineCode = new JSONObject();
					jsonObejctLineCode.put(key, entry.getValue());
				}
				

				if (jsonObjectRouter == null
						&& (!key.equals("idlinha") && !key.equals("codigo") && !key.equals("nome"))) {
					jsonObjectRouter = new JSONObject();

					jsonObjectRouter.put(key, entry.getValue());
					jsonArrayRouter.put(jsonObjectRouter);

					jsonObjectRouter = null;
				} 
				
				if (entry.getValue() instanceof LinkedHashMap) {
					LinkedHashMap<String, Object> value = (LinkedHashMap<String, Object>) entry.getValue();

					for (Map.Entry<String, Object> inner : value.entrySet()) {
						String innerKey = inner.getKey();
						Object values = inner.getValue();

						if (innerKey.equals("lat") || innerKey.equals("lng")) {
							if (jsonObjectRouter == null) {
								jsonObjectRouter = new JSONObject();
							}

							jsonObjectRouter.put(innerKey, values);
							jsonArrayRouter.put(jsonObjectRouter);

							jsonObjectRouter = null;
						}
					}
				}
			}

			if (jsonArrayRouter != null) {
				jsonObjectLine = new JSONObject();

				jsonObjectLine.put("idlinha", lineCode);
				jsonObjectLine.put("Routers", jsonArrayRouter);
			}

			JSONObject jsonObject = null;
			Location location = null;
			
			for (int i = 0; jsonArrayRouter.length() > i; i++) {
				jsonObject = jsonArrayRouter.getJSONObject(i);

				if (location == null) {
					location = new Location();
				}

				if (jsonObject.has("lat")) {
					location.setLatitude(jsonObject.getDouble("lat"));
				}

				if (jsonObject.has("lng")) {
					location.setLongitude(jsonObject.getDouble("lng"));
				}
				
				
				if (!jsonObject.has("lat") && !jsonObject.has("lng")) {
					String str = jsonObject.names().get(0).toString();
					Long id = Long.valueOf(str) + 1;
					location.setId(id);
				}

				if (location.getLatitude() != null && location.getLongitude() != null) {
					locationList.add(location);

					location = null;
				}
			}
			 
			 ItineraryResponse itineraryResponse = ItineraryResponse.builder()
					 .idLinha(jsonObjectLine.getString("idlinha"))
					 .nome(jsonObjectLineName != null ? jsonObjectLineName.getString("nome") : "-")
					 .codigo(jsonObejctLineCode != null ? jsonObejctLineCode.getString("codigo") : "-")
					 .locationList(locationList)
					 .build();
			 
			 return itineraryResponse;
		} catch (Exception e) {
			ItineraryResponse itineraryResponse = ItineraryResponse.builder()
					 .returnMessage("Dados não encontrados. Erro: ".concat(e.getLocalizedMessage()))
					 .build();
			return itineraryResponse;
		}

	}
}
