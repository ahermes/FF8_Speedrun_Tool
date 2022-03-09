package com.speedrun.utilities.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.speedrun.utilities.GlobalValues;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.google.gson.Gson;
import com.speedrun.cardPattern.object.CardPatternFullGameObject;
import com.speedrun.cardPattern.object.CardPatternOpponentDeck;
import com.speedrun.cardPattern.object.CardPatternRngResult;


public class CSVHelper {
	public final static char CSV_SEPARATOR = ',';


	public static Map<String, CSVRecord> getMapCsvFromFile(Path csvFile)
			throws Exception {
		return getMapCsvFromFile(csvFile, CSV_SEPARATOR, null);
	}

	public static Map<String, CSVRecord> getMapCsvFromFile(Path csvFile, char _separator, Enum<?> key) throws Exception {	
		BufferedReader reader = Files.newBufferedReader(csvFile, StandardCharsets.UTF_8);
		return getMapCsv(reader, _separator, key);
	}

	private static Map<String, CSVRecord> getMapCsv(BufferedReader reader, char _separator, Enum<?> key) throws Exception {			
		Map<String, CSVRecord> recordsMap = new HashMap<String, CSVRecord>();
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withDelimiter(_separator).parse(reader);
		for (CSVRecord record : records) {
			String additionalKey = "";
			if (key != null) {
				additionalKey = record.get(key);
			}
			recordsMap.put(additionalKey + record.getRecordNumber(), record);
		}

		return recordsMap;
	}

	public static boolean isRngExistfromCsvFile(int index) throws Exception {
		boolean actualRes = false;
		ArrayList<Path> csvFiles = new ArrayList<Path>();
		csvFiles.add(Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.FULL_GAME_PATTERNS_FILE));
		csvFiles.add(Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.OPPONENT_DECK_GAME_FILE));
		csvFiles.add(Paths.get(GlobalValues.ACCESSIBLE_RESSOURCES_FILES + GlobalValues.RNG_RESULT_GAME_FILE));
		for(Path csvFile : csvFiles) {
			actualRes = false;
			List<List<String>> listData = getListDataFromCsv(csvFile);
			for (List<String> datas : listData) {
				if (Integer.valueOf(datas.get(1)) == index) {
					actualRes = true;
				}
			}
			if(!actualRes){
				return false;
			}
		}
		return true;
	}

	public static CardPatternFullGameObject getListFullGameObjectFromCsvFile(int index, Path csvFile) throws Exception {
		List<List<String>> listData = getListDataFromCsv(csvFile);
		for(List<String> datas : listData) {
			if(Integer.valueOf(datas.get(1)) == index) {
				return new CardPatternFullGameObject(datas.get(0), Integer.valueOf(datas.get(1)), datas.subList(2, datas.size()));
			}
		}
		return null;
	}

	public static CardPatternOpponentDeck getListOpponentObjectFromCsvFile(int index, Path csvFile) throws Exception {
		List<List<String>> listData = getListDataFromCsv(csvFile);
		for(List<String> datas : listData) {
			if(Integer.valueOf(datas.get(1)) == index) {
				return new CardPatternOpponentDeck(datas.get(0), Integer.valueOf(datas.get(1)), datas.get(3), datas.subList(3, datas.size()));
			}
		}
		return null;
	}

	public static CardPatternRngResult getListRngResultsObjectFromCsvFile(int index, Path csvFile) throws Exception {
		List<List<String>> listData = getListDataFromCsv(csvFile);
		for(List<String> datas : listData) {
			if(Integer.valueOf(datas.get(1)) == index) {
				return new CardPatternRngResult(datas.get(0), Integer.valueOf(datas.get(1)), datas.subList(2, datas.size()));
			}
		}
		return null;
	}

	public static Map<Integer,CardPatternFullGameObject> getAllListFullGameObjectFromCsvFile(Path csvFile) throws Exception {
		List<List<String>> listData = getListDataFromCsv(csvFile);
		Map<Integer,CardPatternFullGameObject> res = new HashMap<Integer,CardPatternFullGameObject>();
		for(List<String> datas : listData) {
			res.put(Integer.valueOf(datas.get(1)), new CardPatternFullGameObject(datas.get(0), Integer.valueOf(datas.get(1)), datas.subList(2, datas.size())));
		}
		return res;
	}

	public static Map<Integer,CardPatternOpponentDeck> getAllListOpponentObjectFromCsvFile(Path csvFile) throws Exception {
		List<List<String>> listData = getListDataFromCsv(csvFile);
		Map<Integer,CardPatternOpponentDeck> res = new HashMap<Integer,CardPatternOpponentDeck>();
		for(List<String> datas : listData) {
			res.put(Integer.valueOf(datas.get(1)),new CardPatternOpponentDeck(datas.get(0), Integer.valueOf(datas.get(1)), datas.get(2), datas.subList(3, datas.size())));
		}
		return res;
	}

	public static Map<Integer,CardPatternRngResult> getAllListRngResultsObjectFromCsvFile(Path csvFile) throws Exception {
		List<List<String>> listData = getListDataFromCsv(csvFile);
		Map<Integer,CardPatternRngResult> res = new HashMap<Integer,CardPatternRngResult>();
		for(List<String> datas : listData) {
			res.put(Integer.valueOf(datas.get(1)),new CardPatternRngResult(datas.get(0), Integer.valueOf(datas.get(1)), datas.subList(2, datas.size())));
		}
		return res;
	}

	private static List<List<String>>  getListDataFromCsv(Path csvFile) throws Exception {
		List<List<String>> resList = new ArrayList<List<String>>();
		Map<String, CSVRecord> map = getMapCsvFromFile(csvFile, CSV_SEPARATOR, null);
		for (Map.Entry<String, CSVRecord> data : map.entrySet()) {
			ArrayList<String> listData = new ArrayList<String>();
			data.getValue().iterator().forEachRemaining(listData::add);
			if (listData.get(1).matches("-?\\d+")) {
				resList.add(listData);
			}
		}
		return resList;
	}

}
