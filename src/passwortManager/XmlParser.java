package passwortManager;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {

	private static final String XML_FILE_PATH = "exchange.xml";

	public static class Tags {
		public static final String ROOT = "root";
		public static final String[] MAILS = { "Mid", "Mail", "Passwort" };
		public static final String[] KEYS = { "Kid", "Username", "Passwort", "Mid", "Web" };
	}

	public static void export(String masterPassword) {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element root = document.createElement(Tags.ROOT);
			document.appendChild(root);
			Element mails = document.createElement("Mails");
			Element keys = document.createElement("Keys");
			root.appendChild(mails);
			root.appendChild(keys);
			String[][] m = sqlClasses.Read.readTableMails(masterPassword);
			for (int i = 0; i < m.length; i++) {
				Element oe = document.createElement("Entry");
				mails.appendChild(oe);
				for (int k = 0; k < m[0].length; k++) {
					Element e = document.createElement(Tags.MAILS[k]);
					e.appendChild(document.createTextNode(m[i][k]));
					oe.appendChild(e);
				}
			}
			String[][] keyArr = sqlClasses.Read.readTableKeys(masterPassword);
			for (int i = 0; i < keyArr.length; i++) {
				Element oe = document.createElement("Entry");
				keys.appendChild(oe);
				for (int k = 0; k < keyArr[0].length; k++) {
					Element e = document.createElement(Tags.KEYS[k]);
					e.appendChild(document.createTextNode(keyArr[i][k]));
					oe.appendChild(e);
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(XML_FILE_PATH));
			transformer.transform(domSource, streamResult);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static void inport(String masterPassword) {
		try {
			File file = new File(XML_FILE_PATH);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList mails = doc.getElementsByTagName("Mails");
			NodeList keys = doc.getElementsByTagName("Keys");
			// mails
			Node node = mails.item(0);
			Element nne = (Element) node;
			NodeList entryList = nne.getElementsByTagName("Entry");
			int size = entryList.getLength();
			String[][] mailTable = new String[size][Tags.MAILS.length];
			for (int i = 0; i < size; i++) {
				Node entry = entryList.item(i);
				if (entry.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) entry;
					for (int k = 0; k < Tags.MAILS.length; k++) {
						mailTable[i][k] = eElement.getElementsByTagName(Tags.MAILS[k]).item(0).getTextContent();
					}
				}
			}
			for (String[] arr : mailTable) {
				sqlClasses.Insert.addNewMail(arr[1], arr[2], masterPassword);
			}
			// Keys
			Node nodek = keys.item(0);
			Element nnek = (Element) nodek;
			NodeList entryListKey = nnek.getElementsByTagName("Entry");
			int sizeKey = entryListKey.getLength();
			String[][] keyTable = new String[sizeKey][Tags.KEYS.length];
			for (int i = 0; i < sizeKey; i++) {
				Node entry = entryListKey.item(i);
				if (entry.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) entry;
					for (int k = 0; k < Tags.KEYS.length; k++) {
						keyTable[i][k] = eElement.getElementsByTagName(Tags.KEYS[k]).item(0).getTextContent();
					}
				}
			}
			for (String[] arr : keyTable) {
				sqlClasses.Insert.writeInKeys(Integer.parseInt(arr[0]), arr[1], enAndDecryption.MainPort.encrypt(arr[2], masterPassword), Integer.parseInt(arr[3]), arr[4]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}