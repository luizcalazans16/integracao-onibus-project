package br.com.dimed.busIntegration.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import br.com.dimed.busIntegration.constants.Constants;
import br.com.dimed.busIntegration.model.BusLine;
import br.com.dimed.busIntegration.service.BusLineService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class BusLineServiceImpl implements BusLineService {

	@Override
	public List<BusLine> listBusLines() {
		OkHttpClient httpClient = new OkHttpClient();
		Response response = null;
		List<BusLine> list = new ArrayList<BusLine>();

		Request request = new Request.Builder()
				.url(Constants.DEFAULT_URL.concat(Constants.ENDPOINT_LIST_LINES)).get()
				.build();

		try {
			response = httpClient.newCall(request).execute();

			String returnString = response.body().string();

			JSONArray jsonArray = new JSONArray(returnString);
			
			JSONObject jsonObject = null;
			for (int i = 0; jsonArray.length() > i; i++) {
				jsonObject = jsonArray.getJSONObject(i);

				list.add(new BusLine(jsonObject.getLong("id"), jsonObject.getString("codigo"),
						jsonObject.getString("nome")));
			}

		} catch (Exception e) {
			if (list.size() == 0) {
				return Collections.emptyList();
			}
		}

		return list;
	}
	
	@Override
	public List<BusLine> findBusLineByName(String busLineName) {
		OkHttpClient httpClient = new OkHttpClient();
		Response response = null;
		List<BusLine> list = new ArrayList<BusLine>();

		Request request = new Request.Builder()
				.url(Constants.DEFAULT_URL.concat(Constants.ENDPOINT_FIND_LINE_BY_NAME).concat(busLineName)).get()
				.build();

		try {
			response = httpClient.newCall(request).execute();

			String returnString = response.body().string();

			JSONArray jsonArray = new JSONArray(returnString);

			JSONObject jsonObject = null;
			for (int i = 0; jsonArray.length() > i; i++) {
				jsonObject = jsonArray.getJSONObject(i);

				list.add(new BusLine(jsonObject.getLong("id"), jsonObject.getString("codigo"),
						jsonObject.getString("nome")));
			}

		} catch (Exception e) {
			if (list.size() == 0) {
				return Collections.emptyList();
			}
		}

		return list;
	}
}
