package br.com.dimed.busIntegration.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dimed.busIntegration.model.Itinerary;
import br.com.dimed.busIntegration.service.ItineraryService;

@Service
public class ItineraryServiceImpl implements ItineraryService {

	private static final String URL_WEBSERVICE = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";

	@Override
	public Itinerary getItineraryByCodigo(String codigoLinha) {
		return connectToWebServiceAndReturnList(codigoLinha);
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
			String response = sb.toString();

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = response;
			Itinerary myObj = mapper.readValue(jsonString, mapper.getTypeFactory().constructType(Itinerary.class));

			return myObj;

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			connection.disconnect();
		}

	}
}
