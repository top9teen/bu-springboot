package com.it.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.it.app.model.req.DataGoogleDetailsModelStr;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

@Service
public class ReportComplete {

	
	public JRXlsExporter exportExcel(HttpServletResponse response, List<DataGoogleDetailsModelStr> DataList) throws JRException, IOException {
		InputStream inputStream = null;
		JasperReport jasperReport = null;
		JRXlsExporter exporter = new JRXlsExporter();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(DataList);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
		String dateString = format.format(new Date());
		String name = "Export"+dateString;
		response.setContentType("application/x-xls");
		response.setHeader("Content-Disposition", String.format("attachment; filename=" + name+".xls"));
		inputStream = this.getClass().getResourceAsStream("/jasper/completeTaskexcel.jrxml");
		jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

		OutputStream out = response.getOutputStream();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
		configuration.setOnePagePerSheet(false); // true => ตัดหน้า  
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		configuration.setWhitePageBackground(false);
		configuration.setRemoveEmptySpaceBetweenRows(true);
		exporter.setConfiguration(configuration);

		return exporter;
	}
	
	public 	void exportPDF(HttpServletResponse response, List<DataGoogleDetailsModelStr> DataList, String name2) throws JRException, IOException {
		InputStream inputStream = null;
		JasperReport jasperReport = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);

		String dateString = format.format(new Date());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(DataList);
		Map<String, Object> param = new HashMap<>();
		param.put("nameH", name2);
		
		String name = "Export"+dateString;
		response.setContentType("application/pdf; charset=UTF-8");
		response.setHeader("Content-Disposition", String.format("attachment; filename=" + name+".pdf"));
		inputStream = this.getClass().getResourceAsStream("/jasper/completeTaskpdf.jrxml");
		jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	}
	
	
}
