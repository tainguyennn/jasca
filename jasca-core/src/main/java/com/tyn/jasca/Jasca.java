package com.tyn.jasca;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tyn.jasca.analyzer.Analyzer;
import com.tyn.jasca.analyzer.Analyzer.AnalyzerEngine;
import com.tyn.jasca.engine.findbugs.FindBugsAnalyzerFactory;
import com.tyn.jasca.engine.pmd.PmdAnalyzerFactory;

/**
 * 
 * @author S.J.H.
 */
public class Jasca {
	
	/**
	 * 
	 */
	private static final Logger log = LoggerFactory.getLogger(Jasca.class);
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JascaConfiguration jascaConfiguration = null;
		if ((jascaConfiguration = processCommandine(args)) != null) {
			
			/**
			 * execute JASCA
			 */
			Jasca jasca = new Jasca(jascaConfiguration);
			jasca.start();
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	private static JascaConfiguration processCommandine(String[] args) {
		JascaConfiguration jascaConfiguration = new JascaConfiguration();
		
		Options options = new Options();
		options.addOption("verbose",	false,	"�м����� ����� ���");
		options.addOption("progress",	false,	"�м� ����� ǥ��");
		options.addOption("level",		true,	"�м� �ɰ��� ���� [high|medium(default)|low]");
		options.addOption("format",		true,	"����Ʈ ���� [html(default)|xml|xls]");
		options.addOption("output",		true,	"����Ʈ ��� ���� ���� ��");
		
		try {
			CommandLineParser parser = new BasicParser();
			CommandLine command = parser.parse(options, args);
			
			/**
			 * JascaConfiguration setup
			 */
			JascaConfiguration.setup(jascaConfiguration, command);
		}
		catch (ParseException pe) {
			PrintWriter pw = new PrintWriter(System.out);
			pw.write(IOUtils.LINE_SEPARATOR);
			pw.write("�Է¿���! : ");
			pw.write(pe.getMessage());
			pw.write(IOUtils.LINE_SEPARATOR);
			pw.write(IOUtils.LINE_SEPARATOR);
			pw.flush();
			
			HelpFormatter help = new HelpFormatter();
			help.printHelp("jasca [options] [�м���� ���� �Ǵ� ���丮]", options);
			
			return null;		// exit
		}
		
		return jascaConfiguration;
	}
	
	/**
	 * 
	 */
	private JascaConfiguration jascaConfiguration = null;
	
	/**
	 * 
	 * @param jascaConfiguration
	 */
	public Jasca(JascaConfiguration jascaConfiguration) {
		this.jascaConfiguration = jascaConfiguration;
	}
	
	/**
	 * 
	 */
	public void start() {
		Results results = Results.getInstance();
		results.create();
		
		try {
			List<Analyzer> analyzerList = getEnableAnalyzer();
			for (Analyzer analyzer : analyzerList) {
				analyzer.execute();
			}
			
			ReportBuilder.build(
					results,
					new DefaultConverter(),
					jascaConfiguration.createFormatter(),
					jascaConfiguration.getTarget(),
					jascaConfiguration.getOutput());
			
			log.info("�м� ����");
		}
		catch (JascaException je) {
			log.error("�м� ���� : {}", je.getMessage());
		}
		catch (Exception e) {
			log.error("�м� ����", e);
		}
		finally {
			results.clear();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private List<Analyzer> getEnableAnalyzer() {
		List<Analyzer> analyzers = new ArrayList<Analyzer>();
		
		if (jascaConfiguration.getEnableEngines().get(AnalyzerEngine.FINDBUGS)) {
			analyzers.add(FindBugsAnalyzerFactory.getInstance().getAnalyzer(jascaConfiguration));
		}
		if (jascaConfiguration.getEnableEngines().get(AnalyzerEngine.PMD)) {
			analyzers.add(PmdAnalyzerFactory.getInstance().getAnalyzer(jascaConfiguration));
		}
		
		return analyzers;
	}
}
