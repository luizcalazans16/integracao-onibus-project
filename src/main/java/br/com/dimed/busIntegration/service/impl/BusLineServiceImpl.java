package br.com.dimed.busIntegration.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dimed.busIntegration.model.BusLine;
import br.com.dimed.busIntegration.service.BusLineService;

@Service
public class BusLineServiceImpl implements BusLineService {

	private static final String URL_WEBSERVICE = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";

	@Override
	public List<BusLine> listBusLines() {
		return connectToWebServiceAndReturnList();
	}
	
	@Override
	public BusLine findBusLineByName(String busLineName) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<BusLine> connectToWebServiceAndReturnList() {
		
		HttpURLConnection connection = null;
		try {
			URL url = new URL(URL_WEBSERVICE);
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
			List<BusLine> myObj = mapper.readValue(jsonString,
					mapper.getTypeFactory().constructCollectionType(List.class, BusLine.class));

			return myObj;

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			connection.disconnect();
		}

	}

}
