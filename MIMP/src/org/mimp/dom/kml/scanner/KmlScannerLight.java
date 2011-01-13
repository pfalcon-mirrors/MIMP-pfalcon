package org.mimp.dom.kml.scanner;

import org.mimp.dom.ParsedObject;
import org.mimp.dom.kml.DocumentType;
import org.mimp.dom.kml.KmlType;
import org.mimp.dom.kml.PlacemarkType;

/**
 *
 * @author hellhand
 */
public class KmlScannerLight {
    /**
     * org.w3c.dom.Document document
     */
    org.w3c.dom.Document document;
    KmlType kmlType;

    /**
     * Create new KmlScanner with org.w3c.dom.Document.
     */
    public KmlScannerLight(org.w3c.dom.Document document) {
        this.document = document;
    }
    
    public ParsedObject getParsedObject() {
        return kmlType;
    }

    /**
     * Scan through org.w3c.dom.Document document.
     */
    public void visitDocument() {
        org.w3c.dom.Element element = document.getDocumentElement();
        if ((element != null) && element.getTagName().equals("kml")) {
            visitElement_kml(element);
        }
        if ((element != null) && element.getTagName().equals("Document")) {
            visitElement_Document(element);
        }
        if ((element != null) && element.getTagName().equals("name")) {
            visitElement_name(element);
        }
        if ((element != null) && element.getTagName().equals("description")) {
            visitElement_description(element);
        }
    }

    /**
     * Scan through org.w3c.dom.Element named kml.
     */
    void visitElement_kml(org.w3c.dom.Element element) {
        kmlType = new KmlType();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("xmlns")) {
                kmlType.setXmlns(attr.getValue());
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("Document")) {
                        visitElement_Document(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named Document.
     */
    void visitElement_Document(org.w3c.dom.Element element) {
        kmlType.setDocument(new DocumentType());
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("name")) {
                        visitElement_name(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("description")) {
                        visitElement_description(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("Placemark")) {
                        visitElement_Placemark(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named name.
     */
    void visitElement_name(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if(element.getChildNodes().getLength() == 0)
            return;
        if ("Document".equals(parentNode)) {
            kmlType.getDocument().setName(element.getChildNodes().item(0).getNodeValue());
        }
        if ("Placemark".equals(parentNode)) {
            kmlType.getDocument().getLastPlacemark().setName(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named description.
     */
    void visitElement_description(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if(element.getChildNodes().getLength() == 0)
            return;
        if ("Document".equals(parentNode)) {
            kmlType.getDocument().setDescription(element.getChildNodes().item(0).getNodeValue());
        }
        if ("Placemark".equals(parentNode)) {
            kmlType.getDocument().getLastPlacemark().setDescription(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named Placemark.
     */
    void visitElement_Placemark(org.w3c.dom.Element element) {
        kmlType.getDocument().getPlacemarks().add(new PlacemarkType());
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("name")) {
                        visitElement_name(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("description")) {
                        visitElement_description(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }
}
