package com.zhang.util;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {
    
	private String  filePath = null;
	
	private  Document dom = null;
	
	public XMLUtil(String filePath) throws DocumentException {
		this.filePath = filePath;
		this.dom = this.getDocument();
	}
	
	private Document getDocument() throws DocumentException {
		 if (this.filePath == null) {
			 return null;
		 }
		 SAXReader reader = new SAXReader();   
         Document   document = reader.read(new File(this.filePath));
         return document;
	}
	
	private Element getElement(Document dom, String elem) {
		Element rootElem = dom.getRootElement();
		return rootElem.element(elem);
	}
	
	public String getElementText(String elem) throws DocumentException {
		return getElement(dom, elem).getText();
	}
	
	public String getElementAttribute(String elem, String attr) throws DocumentException {
		return getElement(dom, elem).attribute(attr).getText();
	}
}
