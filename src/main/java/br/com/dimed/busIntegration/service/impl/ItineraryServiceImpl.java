package br.com.dimed.busIntegration.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dimed.busIntegration.constants.Constants;
import br.com.dimed.busIntegration.model.Itinerary;
import br.com.dimed.busIntegration.service.ItineraryService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class ItineraryServiceImpl implements ItineraryService {

	private static final String URL_WEBSERVICE = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Itinerary> getItineraryByCodigo(String lineCode) {

		OkHttpClient httpClient = new OkHttpClient();
		Response response = null;
		String result = null;
		List<Itinerary> itineraryList = new ArrayList<Itinerary>();

		Request request = new Request.Builder()
				.url(Constants.DEFAULT_URL.concat(Constants.ENDPOINT_LIST_ITINERARY_BY_LINE).concat(lineCode)).get()
				.build();

		try {
			response = httpClient.newCall(request).execute();
			System.out.println(response.toString());
			result = response.body().string().replaceAll("\\\\", "");
			System.out.println(result);

			JSONObject jsonObjectLine = null;
			JSONObject jsonObjectRouter = null;
			JSONArray jsonArrayRouter = new JSONArray();
			HashMap<String, Object> map = null;

			TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
			};

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

				itinerary.setCode(jsonObjectLine.getString("idlinha"));
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

				if (itinerary.getCode() != null && itinerary.getLatitude() != null
						&& itinerary.getLongitude() != null) {
					itineraryList.add(itinerary);

					itinerary = null;
				}
			}
			 return itineraryList;
		} catch (Exception e) {
			return Collections.emptyList();
		}

	}

	public Itinerary connectToWebServiceAndReturnList(String codigoLinha) {

		HttpURLConnection connection = null;
		try {
			URL url = new URL(URL_WEBSERVICE.concat(codigoLinha));
			connection = (HttpURLConnection) url.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();

			String response = sb.toString().replace("\\", "");

			Itinerary itinerary = objectMapper.readValue(response, Itinerary.class);
			return itinerary;

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			connection.disconnect();
		}

	}
}
