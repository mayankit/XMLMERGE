package com.wegilant.xml;

import java.io.FileWriter;
import java.util.List;
import java.util.logging.Logger;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.wegilant.App;

/**
 * @author mayank.kumar This class is used to merge two maven based
 *         configuration files
 */

public class XmlReaderMerge {

	private static final Logger LOGGER = Logger.getLogger(XmlReaderMerge.class
			.getName());

	/**
	 * @param existing
	 * @param downloaded
	 * @param merged
	 * 
	 *            It merge two maven based configuration files
	 */
	public static void mergeXml(String existing, String downloaded,
			String merged) {

		SAXBuilder jdomBuilder = new SAXBuilder();
		LOGGER.info("XmlReaderMerge::mergeXml --> Inside mergeXml");
		LOGGER.info("XmlReaderMerge::mergeXml --> ExistingFile: " + existing);
		LOGGER.info("XmlReaderMerge::mergeXml --> downloadedFile: "
				+ downloaded);
		LOGGER.info("XmlReaderMerge::mergeXml --> mergedFile: " + merged);
		try {

			Document firstdocument = jdomBuilder.build(existing);
			Document seconddocument = jdomBuilder.build(downloaded);

			Namespace ns = Namespace
					.getNamespace("http://maven.apache.org/POM/4.0.0");

			Element firstRoot = firstdocument.getRootElement();

			Element dependenciesNodes = firstRoot.getChild("dependencies", ns);
			// get all the child nodes of dependencies
			List<Element> dependenciesRows = dependenciesNodes.getChildren();

			Document thirdDocument = seconddocument.clone();

			for (int i = 0; i < dependenciesRows.size(); i++) {
				Element row = (Element) dependenciesRows.get(i);
				// add all the child nodes of dependencies from first file to
				// another
				thirdDocument.getRootElement().getChild("dependencies", ns)
				.addContent(row.detach());

			}

			Element buildNode = firstRoot.getChild("build", ns);
			Element pluginsNode = buildNode.getChild("plugins", ns);
			// get all the child nodes of build plugins
			List<Element> pluginsRows = pluginsNode.getChildren();

			for (int i = 0; i < pluginsRows.size(); i++) {
				Element row = (Element) pluginsRows.get(i);
				// add all the child nodes of build plugins from first file to
				// another
				thirdDocument.getRootElement().getChild("build", ns)
				.getChild("plugins", ns).addContent(row.detach());

			}
			new XMLOutputter(Format.getPrettyFormat()).output(thirdDocument,
					new FileWriter(merged));
			LOGGER.info("XmlReaderMerge::mergeXml --> merged file generated");
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
		}
	}

}
