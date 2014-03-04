package com.bestbuy.digital.uiconstants;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bestbuy.digital.selenium.util.ScreenException;

/**
 * Class that loads the xml files
 */

public class ReadXMLFile {

	private static Element eElement;
	private Log log = LogFactory.getLog(getClass());

	private static final String DIGITALUI_XML = "./src/main/resources/DigitalUIIdentifiers.xml";	
	private static final String DIGITALENV_XML = "./src/main/resources/env-config.xml";
	private static final String DIGITALDATA_XML = "./src/main/resources/DigitalUIData.xml";
	private static final String USERDATA_XML = "./src/main/resources/UserInfo.xml";
	private static final String UIIDENTIFIERS = "uiidentifiers";
	private static final String ENVCONFIGURATION = "environment";
	private static final String DIGITALUIDATA = "digitaluidata";
	private static final String DIGITALUSERDATA = "userData";

	public ReadXMLFile() throws ScreenException {
		log.info("Loading Data from XML");

	}

	/**
	 * Implementation of the Method to load the data from xml's
	 * 
	 * @param properties
	 *            properties is the variable that stores the xml file
	 * 
	 * @param tagname
	 *            tagname is the name of the root tag read from the xml
	 * 
	 * @throws ScreenException
	 *             ScreenException is an exception to suppress warnings
	 */

	public void loadDigitalConstansts(String properties,String tagname) throws ScreenException {

		try {
			File fXmlFile = new File(properties);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			NodeList nList = doc.getElementsByTagName(tagname);

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					eElement = (Element) nNode;

				}
			}
		} catch (Exception e) {
			log.info("Exception in loadDigitalConstansts"+e.getMessage());
		}
	}



	public void loadDigitalUIEnvConstansts() throws ScreenException {
		loadDigitalConstansts(DIGITALENV_XML,ENVCONFIGURATION);
	}

	public void loadDigitalUIConstants() throws ScreenException {		
		loadDigitalConstansts(DIGITALUI_XML,UIIDENTIFIERS);
	}

	public void loadDigitalUIData() throws ScreenException {
		loadDigitalConstansts(DIGITALDATA_XML,DIGITALUIDATA);
	}

	public void loadDigitalUserData() throws ScreenException {
		loadDigitalConstansts(USERDATA_XML,DIGITALUSERDATA);
	}

	/**
	 * Method to compare and get the value for each tag in the xml
	 * 
	 * @param elementName
	 *            Param to hold the value of the element
	 * 
	 * @return returns the value of the element
	 */
	public String getValue(String elementName) {

		NodeList nlList = eElement.getElementsByTagName(elementName).item(0)
		.getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null) {
			return null;
		}

		return nValue.getNodeValue();
	}

}
