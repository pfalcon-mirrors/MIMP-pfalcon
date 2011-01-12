package org.mimp.dom.gpx.scanner;

import java.math.BigDecimal;

import org.mimp.dom.ParsedObject;
import org.mimp.dom.gpx.GpxType;

/**
 *
 * @author hellhand
 */
public class GpxScannerLight {
    /**
     * org.w3c.dom.Document document
     */
    org.w3c.dom.Document document;
    GpxType gpxType;

    /**
     * Create new GpxScanner.
     */
    public GpxScannerLight() {
        
    }
    
    /**
     * Create new GpxScanner with org.w3c.dom.Document.
     */
    public GpxScannerLight(org.w3c.dom.Document document) {
        this.document = document;
    }

    /**
     * Set org.w3c.dom.Document to GpxScanner
     */
    public void setDocument(org.w3c.dom.Document document) {
        this.document = document;
    }
    
    /**
     * Get the the created object
     */
    public ParsedObject getParsedObject() {
        return gpxType;
    }
    
    /**
     * Scan through org.w3c.dom.Document document.
     */
    public void visitDocument() {
        org.w3c.dom.Element element = document.getDocumentElement();
        if ((element != null) && element.getTagName().equals("gpx")) {
            visitElement_gpx(element);
        }
    }
    
    /**
     * Scan through org.w3c.dom.Element named gpx.
     */
    void visitElement_gpx(org.w3c.dom.Element element) {
        gpxType = new GpxType();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("creator")) {
                gpxType.setCreator(attr.getValue());
            }
            if (attr.getName().equals("version")) {
                gpxType.setVersion(attr.getValue());
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("metadata")) {
                        visitElement_metadata(nodeElement);
                    }
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named metadata.
     */
    void visitElement_metadata(org.w3c.dom.Element element) {
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("name")) {
                        visitElement_name(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("desc")) {
                        visitElement_desc(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("author")) {
                        visitElement_author(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("link")) {
                        visitElement_link(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("copyright")) {
                        visitElement_copyright(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("time")) {
                        visitElement_time(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("keywords")) {
                        visitElement_keywords(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("bounds")) {
                        visitElement_bounds(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("extensions")) {
                        visitElement_extensions(nodeElement);
                    }
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named name.
     */
    void visitElement_name(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if("metadata".equals(parentNode)) {
            gpxType.getMetadata().setName(element.getChildNodes().item(0).getNodeValue());
        }
        if ("author".equals(parentNode)) {
            gpxType.getMetadata().getAuthor().setName(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named desc.
     */
    void visitElement_desc(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if("metadata".equals(parentNode)) {
            gpxType.getMetadata().setDesc(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named author.
     */
    void visitElement_author(org.w3c.dom.Element element) {
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("name")) {
                        visitElement_name(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("email")) {
                        visitElement_email(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("link")) {
                        visitElement_link(nodeElement);
                    }
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named email.
     */
    void visitElement_email(org.w3c.dom.Element element) {
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("domain")) {
                gpxType.getMetadata().getAuthor().getEmail().setDomain(attr.getValue());
            }
            if (attr.getName().equals("id")) {
                gpxType.getMetadata().getAuthor().getEmail().setId(attr.getValue());
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named link.
     */
    void visitElement_link(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("href")) {
                if ("metadata".equals(parentNode)) {
                    gpxType.getMetadata().getLink().setHref(attr.getValue());
                }
                if ("author".equals(parentNode)) {
                    gpxType.getMetadata().getAuthor().getLink().setHref(attr.getValue());
                }
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("text")) {
                        visitElement_text(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("type")) {
                        visitElement_type(nodeElement);
                    }
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named text.
     */
    void visitElement_text(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getParentNode().getNodeName();
        if ("metadata".equals(parentNode)) {
            gpxType.getMetadata().getLink().setText(element.getChildNodes().item(0).getNodeValue());
        }
        if ("author".equals(parentNode)) {
            gpxType.getMetadata().getAuthor().getLink().setText(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named type.
     */
    void visitElement_type(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("metadata".equals(parentNode)) {
            gpxType.getMetadata().getLink().setType(element.getChildNodes().item(0).getNodeValue());
        }
        if ("author".equals(parentNode)) {
            gpxType.getMetadata().getAuthor().getLink().setType(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named copyright.
     */
    void visitElement_copyright(org.w3c.dom.Element element) {
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("author")) {
                gpxType.getMetadata().getCopyright().setAuthor(attr.getValue());
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("year")) {
                        visitElement_year(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("license")) {
                        visitElement_license(nodeElement);
                    }
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named year.
     */
    void visitElement_year(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("copyright".equals(parentNode)) {
            gpxType.getMetadata().getCopyright().setYear(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named license.
     */
    void visitElement_license(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("copyright".equals(parentNode)) {
            gpxType.getMetadata().getCopyright().setLicense(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named time.
     */
    void visitElement_time(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if("metadata".equals(parentNode)) {
            gpxType.getMetadata().setTime(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named keywords.
     */
    void visitElement_keywords(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if("metadata".equals(parentNode)) {
            gpxType.getMetadata().setKeywords(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named bounds.
     */
    void visitElement_bounds(org.w3c.dom.Element element) {
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("maxlon")) {
                gpxType.getMetadata().getBounds().setMaxlon(new BigDecimal(attr.getValue()));
            }
            if (attr.getName().equals("maxlat")) {
                gpxType.getMetadata().getBounds().setMaxlat(new BigDecimal(attr.getValue()));
            }
            if (attr.getName().equals("minlon")) {
                gpxType.getMetadata().getBounds().setMinlon(new BigDecimal(attr.getValue()));
            }
            if (attr.getName().equals("minlat")) {
                gpxType.getMetadata().getBounds().setMinlat(new BigDecimal(attr.getValue()));
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named extensions.
     */
    void visitElement_extensions(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("gpx".equals(parentNode)) {
            gpxType.setExtensions(element.getChildNodes().item(0).getNodeValue());
        }
        if ("metadata".equals(parentNode)) {
            gpxType.getMetadata().setExtensions(element.getChildNodes().item(0).getNodeValue());
        }
    }
}