package com.koley.kaustav.covid19.CovidTracker.service;

import javax.annotation.PostConstruct;
import static com.koley.kaustav.covid19.CovidTracker.constants.Constants.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.koley.kaustav.covid19.CovidTracker.model.StateStatistics;

@Service
public class ConfirmedCases {
	
	protected List<StateStatistics> currentList = new ArrayList<StateStatistics>();
	
	@PostConstruct
	@Scheduled(cron = "10 * * * * *")
	public void getConfirmedCases() throws IOException, InterruptedException {
		
		List<StateStatistics> localCurrentList = new ArrayList<StateStatistics>();
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest httpRequest = HttpRequest.newBuilder()
									.uri(URI.create(ConfirmedCasesUrl)).build();
		HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

		Reader in = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		
		for (CSVRecord record : records) {
			StateStatistics stateStatistics = new StateStatistics();
		    stateStatistics.setState(record.get("Province/State"));
		    stateStatistics.setCountry(record.get("Country/Region"));
		    stateStatistics.setTotalCasesByToday(Integer.parseInt(record.get(record.size() - 1)));
		    int prevDayCase = Integer.parseInt(record.get(record.size() - 2));
		    stateStatistics.setDiffFromPrevDay(Integer.parseInt(record.get(record.size() - 1)) - prevDayCase);
		    localCurrentList.add(stateStatistics);
		    
		}
		
		currentList = localCurrentList;
		//currentList.forEach(System.out::println);
	}

	public List<StateStatistics> getCurrentList() {
		return currentList;
	}

	public void setCurrentList(List<StateStatistics> currentList) {
		this.currentList = currentList;
	}

}
