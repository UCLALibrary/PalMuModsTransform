package edu.ucla.library.dep.PalMuModsTransform;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.Xslt30Transformer;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Document document;
    public static void main( String[] args ) throws IOException, SAXException, TransformerException, ParserConfigurationException, SaxonApiException
    {
    	File directoryPath = new File("\\\\svm-netapp-dlib.in.library.ucla.edu\\DLIngest\\palmu");
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	File[] children = directoryPath.listFiles();
    	File xsl = new File("idep.xsl");
    	Processor processor = new Processor(false);
		   XsltCompiler compiler = processor.newXsltCompiler();
		   XsltExecutable stylesheet = compiler.compile(new StreamSource(xsl));
    	// Use a Transformer for output
    	/*TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    StreamSource style = new StreamSource(xsl);
	    Transformer transformer = transformerFactory.newTransformer(style);*/
    	for (File file : children) {
			if(file.isFile()) {
				
				   Serializer out = processor.newSerializer((new File("\\\\svm-netapp-dlib.in.library.ucla.edu\\DLIngest\\palmu\\transform\\"+file.getName())));
				   out.setOutputProperty(Serializer.Property.METHOD, "xml");
				   out.setOutputProperty(Serializer.Property.INDENT, "yes");
				   Xslt30Transformer transformer = stylesheet.load30();
				   transformer.transform(new StreamSource(file), out);
				/*DocumentBuilder builder = factory.newDocumentBuilder();
			    document = builder.parse(file);
			    

			    
			    
			 
			    DOMSource source = new DOMSource(document);
			    FileWriter writer = new FileWriter(new File("\\\\svm-netapp-dlib.in.library.ucla.edu\\DLIngest\\palmu\\transform\\"+file.getName()));
			    StreamResult result = new StreamResult(writer);
			    transformer.transform(source, result);*/
			}
		}
    	
    }
}
