package com.example.jersey_sample.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author manhnt
 */

@Path("/report")
public class DailyReport {

	private HashMap<String, String> success = new HashMap<String, String>() {{
		put("message", "success");
	}};
	private HashMap<String, String> fail = new HashMap<String, String>() {{
		put("message", "fail");
	}};
	
	private static java.nio.file.Path getReportFile(LocalDate date) {
		if (date == null) date = LocalDate.now();
		return Paths.get("report", "report-" + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + ".txt");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> submit(
			@FormParam("backlogID") String backlogID,
			@FormParam("backlogName") String backlogName,
			@FormParam("taskID") String taskID,
			@FormParam("taskName") String taskName,
			@FormParam("status") String status,
			@FormParam("reporter") String reporter,
			@FormParam("startedDate") LocalDate startedDate,
			@FormParam("endDate") LocalDate endDate,
			@FormParam("issue") String issue
	) {
		try {
			int nextId = 1;
			java.nio.file.Path todayFile = getReportFile(null);
			try {
				nextId = Files.readAllLines(todayFile, Charset.forName("UTF-8")).size() + 1;
			} catch (IOException e) {
				Log.REPORT_LOG.info(e.getMessage());
			}
			
			String reportEntry = String.format(nextId + "\t%1$s\t%2$s\t%3$s\t%4$s\t%5$s\t%6$s\t%7$s\t%8$s\t%9$s\n",
					normalize(backlogID),
					normalize(backlogName),
					normalize(taskID),
					normalize(taskName),
					normalize(status),
					normalize(reporter),
					startedDate,
					endDate,
					normalize(issue == null? "": issue));
			
			Files.createDirectories(todayFile.getParent());
			Files.write(todayFile, reportEntry.getBytes("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			
			return success;
		} catch (IOException e) {
			e.printStackTrace();
			
			return fail;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Report> getReport(@QueryParam("date") LocalDate date) {
		try {
			List<String> lines = Files.readAllLines(getReportFile(date), Charset.forName("UTF-8"));
			return lines.stream().flatMap(Report::valueOf).collect(Collectors.toList());
		} catch (IOException e) {
			Log.REPORT_LOG.warn(e.getMessage());
			return new LinkedList<>();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public HashMap<String, String> delete(@QueryParam("ids") String ids, @QueryParam("date") LocalDate date) {
		List<Integer> deletingIds = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		List<String> lines;
		java.nio.file.Path reportFile = getReportFile(date);
		try {
			lines = Files.readAllLines(reportFile, Charset.forName("UTF-8"));
		} catch (IOException e) {
			return fail;
		}
		
		List<String> editedLines = new LinkedList<>();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (deletingIds.contains(i + 1) && !line.startsWith("@")) {
				editedLines.add("@" + line);
			} else {
				editedLines.add(line);
			}
		}
		
		try {
			Files.write(reportFile, editedLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
			return success;
		} catch (IOException e) {
			Log.REPORT_LOG.error("Error while override report", e.getMessage());
			return fail;
		}
	}
	
	private static String normalize(String s) {
		return s.replace('\t', ' ').replace('\n', ' '); //normalize spaces
	}
	
	public static class Report {
		public final int id;
		public final String backlogID;
		public final String backlogName;
		public final String taskID;
		public final String taskName;
		public final String status;
		public final String reporter;
		public final LocalDate startedDate;
		public final LocalDate endDate;
		public final String issue;
		
		private Report(int id, String backlogID, String backlogName, String taskID, String taskName, String status, String reporter, LocalDate startedDate, LocalDate endDate, String issue) {
			this.id = id;
			this.backlogID = backlogID;
			this.backlogName = backlogName;
			this.taskID = taskID;
			this.taskName = taskName;
			this.status = status;
			this.reporter = reporter;
			this.startedDate = startedDate;
			this.endDate = endDate;
			this.issue = issue;
		}
		
		public static Stream<Report> valueOf(String s) {
			if (s.startsWith("@")) return Stream.empty();
			try {
				String[] tokens = s.split("\t");
				return Stream.of(new Report(Integer.parseInt(tokens[0]),tokens[1], tokens[2],
						tokens[3], tokens[4],
						tokens[5], tokens[6],
						LocalDate.parse(tokens[7], DateTimeFormatter.ISO_LOCAL_DATE), LocalDate.parse(tokens[8], DateTimeFormatter.ISO_LOCAL_DATE),
						tokens.length == 9 ? "" : tokens[9]));
			} catch (Exception e) {
				return Stream.empty();
			}
		}
	}
}
