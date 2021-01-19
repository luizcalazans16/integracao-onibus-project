package br.com.dimed.busIntegration.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dimed.busIntegration.constants.Constants;
import br.com.dimed.busIntegration.model.Itinerary;
import br.com.dimed.busIntegration.response.ItineraryResponse;
import br.com.dimed.busIntegration.service.ItineraryService;
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
		List<Itinerary> itineraryList = new ArrayList<Itinerary>();

		Request request = new Request.Builder()
				.url(Constants.DEFAULT_URL.concat(Constants.ENDPOINT_LIST_ITINERARY_BY_LINE).concat(lineCode)).get()
				.build();

		try {
			response = httpClient.newCall(request).execute();
			result = response.body().string().replaceAll("\\\\", "");
			System.out.println("Resultado obtido:".concat(result));

			JSONObject jsonObjectLine = null;
			JSONObject jsonObjectRouter = null;
			JSONArray jsonArrayRouter = new JSONArray();
			HashMap<String, Object> map = null;


			TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};

			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(result, typeRef);
			
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();

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
			Itinerary itinerary = null;
			for (int i = 0; jsonArrayRouter.length() > i; i++) {
				jsonObject = jsonArrayRouter.getJSONObject(i);

				if (itinerary == null) {
					itinerary = new Itinerary();
				}

//				itinerary.setId(jsonObjectLine.getString("idlinha"));
//				itinerary.setCode(jsonObjectLine.getString("idlinha"));
				if (jsonObject.has("lat")) {
					itinerary.setLatitude(jsonObject.getDouble("lat"));
				}

				if (jsonObject.has("lng")) {
					itinerary.setLongitude(jsonObject.getDouble("lng"));
				}
				
				
				if (!jsonObject.has("lat") && !jsonObject.has("lng")) {
					String str = jsonObject.names().get(0).toString();
					Long id = Long.valueOf(str) + 1;
					itinerary.setId(id.toString());
				}

				if (itinerary.getLatitude() != null && itinerary.getLongitude() != null) {
					itineraryList.add(itinerary);

					itinerary = null;
				}
			}
			 
			 ItineraryResponse itineraryResponse = ItineraryResponse.builder()
					 .idLinha(jsonObjectLine.getString("idlinha"))
					 .itineraryList(itineraryList)
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
