package ru.eldorado.XMLParsing;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class CheckXSD {

	
	
	public  boolean checkXSD( String pathXSD, String pathXML) throws Exception
	{
	
	File xsd = new File(pathXSD);
	
	if (!xsd.exists()) {
	
	System.out.println("Не найден XSD" + pathXSD);
	return false;
	}


	SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	Schema schema = factory.newSchema(new StreamSource(pathXSD));
	Validator validator = schema.newValidator();
	validator.validate(new StreamSource(pathXML));
	return true;
	
	}


	
	
}
