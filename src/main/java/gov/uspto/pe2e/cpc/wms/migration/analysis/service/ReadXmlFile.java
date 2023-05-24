package gov.uspto.pe2e.cpc.wms.migration.analysis.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;

import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import gov.uspto.pe2e.cpc.wms.migration.analysis.entity.*;

@Service
public class ReadXmlFile extends DefaultHandler {

	private StringBuffer currentValue = new StringBuffer();
	Map<String, Task> taskMap = Collections.synchronizedMap(new HashMap<>());

	@Override
	public void startDocument() {
	 taskMap = Collections.synchronizedMap(new HashMap<>());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		currentValue.setLength(0);
		Task task;
		if (qName.equalsIgnoreCase("userTask")) {
			task = new Task();

			String taskDef = attributes.getValue("id");
			task.setTaskDef(taskDef);

			String taskName = attributes.getValue("name");
			task.setTaskName(taskName);

			taskMap.put(task.getTaskDef(), task);
		}

	}

	public Map<String, Task> getTaskMap() {
		return taskMap;
	}

	public void endElement(String uri, String localName, String qName) {
	}

	public void characters(char ch[], int start, int length) {

		currentValue.append(ch, start, length);

	}

	public Map<String, Task> run(File file) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		ReadXmlFile handler = new ReadXmlFile();
		InputStream inputStream = getXMLFileAsStream(file);
		try (InputStream is = inputStream) {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(is, handler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return handler.getTaskMap();
	}

	private static InputStream getXMLFileAsStream(File file) throws FileNotFoundException {
		InputStream fileInputStream = new FileInputStream(file);
		return fileInputStream;

	}

}
