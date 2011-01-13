package org.mimp.dom.kml.scanner;

import java.math.BigDecimal;

import org.mimp.dom.ParsedObject;
import org.mimp.dom.kml.CoordinatesType;
import org.mimp.dom.kml.DocumentType;
import org.mimp.dom.kml.KmlType;
import org.mimp.dom.kml.PlacemarkType;
import org.mimp.dom.kml.StyleType;
import org.w3c.dom.Element;

import android.graphics.Color;

/**
 *
 * @author hellhand
 */
public class KmlScanner {
    /**
     * org.w3c.dom.Document document
     */
    org.w3c.dom.Document document;
    KmlType kmlType;

    /**
     * Create new KmlScanner with org.w3c.dom.Document.
     */
    public KmlScanner(org.w3c.dom.Document document) {
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
        if ((element != null) && element.getTagName().equals("Style")) {
            visitElement_Style(element);
        }
        if ((element != null) && element.getTagName().equals("IconStyle")) {
            visitElement_IconStyle(element);
        }
        if ((element != null) && element.getTagName().equals("Icon")) {
            visitElement_Icon(element);
        }
        if ((element != null) && element.getTagName().equals("href")) {
            visitElement_href(element);
        }
        if ((element != null) && element.getTagName().equals("LineStyle")) {
            visitElement_LineStyle(element);
        }
        if ((element != null) && element.getTagName().equals("color")) {
            visitElement_color(element);
        }
        if ((element != null) && element.getTagName().equals("width")) {
            visitElement_width(element);
        }
        if ((element != null) && element.getTagName().equals("Placemark")) {
            visitElement_Placemark(element);
        }
        if ((element != null) && element.getTagName().equals("styleUrl")) {
            visitElement_styleUrl(element);
        }
        if ((element != null) && element.getTagName().equals("Point")) {
            visitElement_Point(element);
        }
        if ((element != null) && element.getTagName().equals("coordinates")) {
            visitElement_coordinates(element);
        }
        if ((element != null) && element.getTagName().equals("ExtendedData")) {
            visitElement_ExtendedData(element);
        }
        if ((element != null) && element.getTagName().equals("Data")) {
            visitElement_Data(element);
        }
        if ((element != null) && element.getTagName().equals("value")) {
            visitElement_value(element);
        }
        if ((element != null) && element.getTagName().equals("LineString")) {
            visitElement_LineString(element);
        }
        if ((element != null) && element.getTagName().equals("tessellate")) {
            visitElement_tessellate(element);
        }
        if ((element != null) && element.getTagName().equals("Folder")) {
            visitElement_Folder(element);
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
                    if (nodeElement.getTagName().equals("Style")) {
                        visitElement_Style(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("Placemark")) {
                        visitElement_Placemark(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("Folder")) {
                        visitElement_Folder(nodeElement);
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
     * Scan through org.w3c.dom.Element named Style.
     */
    void visitElement_Style(org.w3c.dom.Element element) {
        kmlType.getDocument().getStyles().add(new StyleType());
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("id")) {
                kmlType.getDocument().getLastStyle().setId(attr.getValue());
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
                    if (nodeElement.getTagName().equals("IconStyle")) {
                        visitElement_IconStyle(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("LineStyle")) {
                        visitElement_LineStyle(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named IconStyle.
     */
    void visitElement_IconStyle(org.w3c.dom.Element element) {
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("Icon")) {
                        visitElement_Icon(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named Icon.
     */
    void visitElement_Icon(org.w3c.dom.Element element) {
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("href")) {
                        visitElement_href(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named href.
     */
    void visitElement_href(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if(element.getChildNodes().getLength() == 0)
            return;
        if ("Icon".equals(parentNode)) {
            kmlType.getDocument().getLastStyle().getIconstyle().getIcon().setHref(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named LineStyle.
     */
    void visitElement_LineStyle(org.w3c.dom.Element element) {
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("color")) {
                        visitElement_color(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("width")) {
                        visitElement_width(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named color.
     */
    void visitElement_color(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("LineStyle".equals(parentNode)) {
            int color;
            try {
                color = Color.parseColor(element.getChildNodes().item(0).getNodeValue());
            }
            catch (Exception e) {
                return;
            }
            kmlType.getDocument().getLastStyle().getLinestyle().setColor(color);
        }
    }

    /**
     * Scan through org.w3c.dom.Element named width.
     */
    void visitElement_width(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("LineStyle".equals(parentNode)) {
            kmlType.getDocument().getLastStyle().getLinestyle().setWidth(Float.valueOf(element.getChildNodes().item(0).getNodeValue()));
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
                    if (nodeElement.getTagName().equals("styleUrl")) {
                        visitElement_styleUrl(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("Point")) {
                        visitElement_Point(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("ExtendedData")) {
                        visitElement_ExtendedData(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("LineString")) {
                        visitElement_LineString(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named styleUrl.
     */
    void visitElement_styleUrl(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("Placemark".equals(parentNode)) {
            kmlType.getDocument().getLastPlacemark().setStyleUrl(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named Point.
     */
    void visitElement_Point(org.w3c.dom.Element element) {
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("coordinates")) {
                        visitElement_coordinates(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named coordinates.
     */
    void visitElement_coordinates(org.w3c.dom.Element element) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Coordinates <<<<<<<<<<<<<<<<<<<<");
        String parentNode = element.getParentNode().getNodeName();
        String coordinates = element.getChildNodes().item(0).getNodeValue();
        if ("Point".equals(parentNode)) {            
            String[] coord = coordinates.split(",");
            CoordinatesType coords = new CoordinatesType(
                    new BigDecimal(coord[1].trim()),
                    new BigDecimal(coord[0].trim()),
                    new BigDecimal(coord[2].trim()));
            kmlType.getDocument().getLastPlacemark().getPoint().setCoordinates(coords);
        }
        if ("LineString".equals(parentNode)) {
            String[] pairs = coordinates.split(" |\\r?\\n");
            System.out.println("pairs : " + pairs.length);
            try {
                for (int i = 1; i < pairs.length-1; i++) {
                    System.out.println("\t group : " + i + " " + pairs[i]);
                    String[] coord = pairs[i].split(",| |\\r?\\n");
                    CoordinatesType coords = new CoordinatesType(
                            new BigDecimal(coord[1].trim()),
                            new BigDecimal(coord[0].trim()),
                            new BigDecimal(coord[2].trim()));
                    kmlType.getDocument().getLastPlacemark().getLine().getCoordinates().add(coords);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named ExtendedData.
     */
    void visitElement_ExtendedData(org.w3c.dom.Element element) {
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("Data")) {
                        visitElement_Data(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named Data.
     */
    void visitElement_Data(org.w3c.dom.Element element) {
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("name")) {
                
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
                    if (nodeElement.getTagName().equals("value")) {
                        visitElement_value(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named value.
     */
    void visitElement_value(org.w3c.dom.Element element) {
        
    }

    /**
     * Scan through org.w3c.dom.Element named LineString.
     */
    void visitElement_LineString(org.w3c.dom.Element element) {
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("coordinates")) {
                        visitElement_coordinates(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("tessellate")) {
                        visitElement_tessellate(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named tessellate.
     */
    void visitElement_tessellate(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("LineString".equals(parentNode)) {
            kmlType.getDocument().getLastPlacemark().getLine().setTesselate(Integer.parseInt(element.getChildNodes().item(0).getNodeValue()));
        }
    }
    
    private void visitElement_Folder(Element element) {
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("Placemark")) {
                        visitElement_Placemark(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("Folder")) {
                        visitElement_Folder(nodeElement);
                    }    
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    break;
            }
        }
    }
}
