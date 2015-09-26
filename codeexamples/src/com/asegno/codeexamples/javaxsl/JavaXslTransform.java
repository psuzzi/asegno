package com.asegno.codeexamples.javaxsl;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Simple class to demonstrate XML Transform with Java
 *
 * @author p.suzzi
 *
 */
public class JavaXslTransform {


	public static void main(String[] args) {
		transformStrings();
	}

	/** XSL Transform using string readers */
	public static void transformStrings(){

		String xmlData = "<books>"
				+ "    <book isbn='1' title='Master XML' pdate='24/09/2010'/>"
				+ "    <book isbn='2' title='Master Java' pdate='24/09/2010'/>"
				+ "    <book isbn='3' title='Eclipse' pdate='21/09/2010'/>"
				+ "</books>";

		String xslStyle = "<xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform'"
				+ "    version='1.0'>"
				+ "    <xsl:output method='xml' version='1.0' encoding='UTF-8' indent='yes'/>"
				+ "    <xsl:template match='/books'>"
				+ "		<h1>Data</h1>"
				+ "		<xsl:for-each select='book' >"
				+ "			<xsl:value-of select='@title'/> ( <xsl:value-of select='@isbn'/> ) " + "<br/>"
				+ "		</xsl:for-each>"
				+ "    </xsl:template>"
				+ "</xsl:stylesheet>";

		String out = "";

		StringReader xmlRead = new StringReader(xmlData);
		StringReader xslRead = new StringReader(xslStyle);
		StringWriter outWriter = new StringWriter();

		// transform!
		transform(new StreamSource(xmlRead), new StreamSource(xslRead), new StreamResult(outWriter), null);

		System.out.println(outWriter.toString());
	}

	/** XSL Transform using file readers */
	public static void transformFiles(){
		// working dir
		String uDir = System.getProperty("user.dir");
		File baseDir = new File(uDir, "2011/xsl-to-xhtml");
		File xmlFile = new File(baseDir, "books.xml");
		File xslFile = new File(baseDir, "books.to.xhtml.xsl");
		File outFile = new File(baseDir, "books.out.generated.xhtml");

		// transform!
		transform(new StreamSource(xmlFile), new StreamSource(xslFile), new StreamResult(outFile), null);

		System.out.println("File generated: " + outFile);
	}

	private static final String ERR_XSL_CONFIGURATION = "Error in XSL transform configuration";
	private static final String ERR_XSL_TRANSFORM = "Error in XSL transform";

	/** XSL Transform with input, output and parameters. */
	@SuppressWarnings("unchecked")
	public static void transform(Source xml, Source xsl, Result out, HashMap<String, ?> params) {

		// factory init
		TransformerFactory factory = TransformerFactory.newInstance();

		// Transformer object init
		Transformer t;
		try {
			t = factory.newTransformer(xsl);
		} catch (TransformerConfigurationException e) {
			// if any XSL error
			throw new RuntimeException(ERR_XSL_CONFIGURATION, e);
		}

		// insert parameters (if any)
		if (params != null && params.size() > 0) {
			Iterator<?> i = params.entrySet().iterator();
			Entry<String, ?> me;
			while (i.hasNext()) {
				me = (Entry<String, ?>) i.next();
				t.setParameter(me.getKey(), me.getValue());
			}
		}

		// Transformation execution
		try {
			t.transform(xml, out);
		} catch (TransformerException e) {
			// if any Transformation error
			throw new RuntimeException(ERR_XSL_TRANSFORM, e);
		}
	}

}
