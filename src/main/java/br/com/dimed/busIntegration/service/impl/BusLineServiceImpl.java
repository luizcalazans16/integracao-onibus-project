package br.com.dimed.busIntegration.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import br.com.dimed.busIntegration.constants.Constants;
import br.com.dimed.busIntegration.exceptions.BusinessException;
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
				.url(Constants.DEFAULT_URL.concat(Constants.ENDPOINT_FIND_LINE_BY_NAME)
						.concat(busLineName)).get()
				.build();

		try {
			response = httpClient.newCall(request).execute();

			String returnString = response.body().string();

			JSONArray jsonArray = new JSONArray(returnString);

			JSONObject jsonObject = null;
			for (int i = 0; jsonArray.length() > i; i++) {
				jsonObject = jsonArray.getJSONObject(i);

				list.add(new BusLine(jsonObject.getLong("id"),
						jsonObject.getString("codigo"),
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
	public BusLine findBusLineByCode(String code) {
		OkHttpClient httpClient = new OkHttpClient();
		Response response = null;
		// List<BusLine> busLineList = new ArrayList<BusLine>();
		BusLine busLine = null;

		Request request = new Request.Builder()
				.url(Constants.DEFAULT_URL.concat(Constants.ENDPOINT_FIND_LINE_BY_CODE)
						.concat(code)).get()
				.build();

		try {
			response = httpClient.newCall(request).execute();
			String returnString = response.body().string();

			JSONArray jsonArray = new JSONArray(returnString);
			JSONObject jsonObject = new JSONObject();

			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);

				busLine = new BusLine(jsonObject.getLong("id"), jsonObject.getString("codigo"),
						jsonObject.getString("nome"));
			}

			return busLine;

		} catch (Exception e) {
			throw new BusinessException("Linha de ônibus não encontrada.");
		}
	}
	
	@Override
	public List<BusLine> findBusLinesByRoute(final Double latitudeMin, final Double latitudeMax,
			final Double longitudeMin, final Double longitudeMax) {

		List<BusLine> busLineList = new ArrayList<BusLine>();
		BusLine busLine = null;

		OkHttpClient httpClient = new OkHttpClient();
		Response response = null;
		String returnString = null;

        Request request = new Request.Builder()
                .url(Constants.DEFAULT_URL.concat(Constants.ENDPOINT_LIST_LINES_ROUTES.concat("((").concat(latitudeMin.toString()).concat(",")
                		.concat(longitudeMin.toString()).concat("),").concat("(").concat(
                				latitudeMax.toString()).concat(",").concat(longitudeMax.toString()).concat(")))")))
////                .url(Constantes.URL_BASE + Constantes.ENDPOINT_LIST_LINHAS_ROTA + "((-30.14296222668432,-51.87917968750003),(-29.79200328961529,-50.56082031250003))))")
                .get()
                .build();
        
        try {
            response = httpClient.newCall(request).execute();

            returnString = response.body().string();

            if (returnString.contains("encontrada")) {
                return Collections.emptyList();
            }

            JSONArray jsonArrayRouter = new JSONArray(returnString);

            JSONArray jsonArrayLinhas = null;
            JSONObject jsonObjectLinha = null;
            JSONObject json = null;
            for (int i = 0; jsonArrayRouter.length() > i; i++) {
                json = jsonArrayRouter.getJSONObject(i);

                jsonArrayLinhas = json.getJSONArray("linhas");

                if (jsonArrayLinhas.length() > 0) {
                    for(int j = 0; jsonArrayLinhas.length() > j; j++) {
                        jsonObjectLinha = jsonArrayLinhas.getJSONObject(j);
                        busLine = new BusLine();

                        busLine.setId(Long.valueOf(jsonObjectLinha.get("idLinha").toString()));
                        busLine.setCode(jsonObjectLinha.get("codigoLinha").toString());
                        busLine.setName(jsonObjectLinha.get("nomeLinha").toString());

                        busLineList.add(busLine);
                    }
                }
            }
        } catch (Exception e) {
            return Collections.emptyList();
        }

        return busLineList;
    }
	

}
