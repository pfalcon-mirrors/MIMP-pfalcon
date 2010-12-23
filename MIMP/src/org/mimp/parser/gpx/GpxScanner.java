/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mimp.parser.gpx;

/**
 *
 * @author hellhand
 */
public class GpxScanner {
    /**
     * org.w3c.dom.Document document
     */
    org.w3c.dom.Document document;

    /**
     * Create new GpxScanner with org.w3c.dom.Document.
     */
    public GpxScanner(org.w3c.dom.Document document) {
        this.document = document;
    }

    /**
     * Scan through org.w3c.dom.Document document.
     */
    public void visitDocument() {
        org.w3c.dom.Element element = document.getDocumentElement();
        if ((element != null) && element.getTagName().equals("xsd:schema")) {
            visitElement_xsd_schema(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:annotation")) {
            visitElement_xsd_annotation(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:documentation")) {
            visitElement_xsd_documentation(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:element")) {
            visitElement_xsd_element(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:complexType")) {
            visitElement_xsd_complexType(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:sequence")) {
            visitElement_xsd_sequence(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:attribute")) {
            visitElement_xsd_attribute(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:any")) {
            visitElement_xsd_any(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:simpleType")) {
            visitElement_xsd_simpleType(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:restriction")) {
            visitElement_xsd_restriction(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:minInclusive")) {
            visitElement_xsd_minInclusive(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:maxInclusive")) {
            visitElement_xsd_maxInclusive(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:maxExclusive")) {
            visitElement_xsd_maxExclusive(element);
        }
        if ((element != null) && element.getTagName().equals("xsd:enumeration")) {
            visitElement_xsd_enumeration(element);
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:schema.
     */
    void visitElement_xsd_schema(org.w3c.dom.Element element) {
        // <xsd:schema>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("elementFormDefault")) {
                // <xsd:schema elementFormDefault="???">
                // attr.getValue();
            }
            if (attr.getName().equals("targetNamespace")) {
                // <xsd:schema targetNamespace="???">
                // attr.getValue();
            }
            if (attr.getName().equals("xmlns:xsd")) {
                // <xsd:schema xmlns:xsd="???">
                // attr.getValue();
            }
            if (attr.getName().equals("xmlns")) {
                // <xsd:schema xmlns="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("xsd:annotation")) {
                        visitElement_xsd_annotation(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:element")) {
                        visitElement_xsd_element(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:complexType")) {
                        visitElement_xsd_complexType(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:simpleType")) {
                        visitElement_xsd_simpleType(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:annotation.
     */
    void visitElement_xsd_annotation(org.w3c.dom.Element element) {
        // <xsd:annotation>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("xsd:documentation")) {
                        visitElement_xsd_documentation(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:documentation.
     */
    void visitElement_xsd_documentation(org.w3c.dom.Element element) {
        // <xsd:documentation>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
                case org.w3c.dom.Node.TEXT_NODE:
                    // ((org.w3c.dom.Text)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:element.
     */
    void visitElement_xsd_element(org.w3c.dom.Element element) {
        // <xsd:element>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("maxOccurs")) {
                // <xsd:element maxOccurs="???">
                // attr.getValue();
            }
            if (attr.getName().equals("minOccurs")) {
                // <xsd:element minOccurs="???">
                // attr.getValue();
            }
            if (attr.getName().equals("type")) {
                // <xsd:element type="???">
                // attr.getValue();
            }
            if (attr.getName().equals("name")) {
                // <xsd:element name="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("xsd:annotation")) {
                        visitElement_xsd_annotation(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:complexType.
     */
    void visitElement_xsd_complexType(org.w3c.dom.Element element) {
        // <xsd:complexType>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("name")) {
                // <xsd:complexType name="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("xsd:annotation")) {
                        visitElement_xsd_annotation(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:sequence")) {
                        visitElement_xsd_sequence(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:attribute")) {
                        visitElement_xsd_attribute(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:sequence.
     */
    void visitElement_xsd_sequence(org.w3c.dom.Element element) {
        // <xsd:sequence>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("xsd:element")) {
                        visitElement_xsd_element(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:any")) {
                        visitElement_xsd_any(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:attribute.
     */
    void visitElement_xsd_attribute(org.w3c.dom.Element element) {
        // <xsd:attribute>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("fixed")) {
                // <xsd:attribute fixed="???">
                // attr.getValue();
            }
            if (attr.getName().equals("use")) {
                // <xsd:attribute use="???">
                // attr.getValue();
            }
            if (attr.getName().equals("type")) {
                // <xsd:attribute type="???">
                // attr.getValue();
            }
            if (attr.getName().equals("name")) {
                // <xsd:attribute name="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("xsd:annotation")) {
                        visitElement_xsd_annotation(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:any.
     */
    void visitElement_xsd_any(org.w3c.dom.Element element) {
        // <xsd:any>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("maxOccurs")) {
                // <xsd:any maxOccurs="???">
                // attr.getValue();
            }
            if (attr.getName().equals("minOccurs")) {
                // <xsd:any minOccurs="???">
                // attr.getValue();
            }
            if (attr.getName().equals("processContents")) {
                // <xsd:any processContents="???">
                // attr.getValue();
            }
            if (attr.getName().equals("namespace")) {
                // <xsd:any namespace="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("xsd:annotation")) {
                        visitElement_xsd_annotation(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:simpleType.
     */
    void visitElement_xsd_simpleType(org.w3c.dom.Element element) {
        // <xsd:simpleType>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("name")) {
                // <xsd:simpleType name="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("xsd:annotation")) {
                        visitElement_xsd_annotation(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:restriction")) {
                        visitElement_xsd_restriction(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:restriction.
     */
    void visitElement_xsd_restriction(org.w3c.dom.Element element) {
        // <xsd:restriction>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("base")) {
                // <xsd:restriction base="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    if (nodeElement.getTagName().equals("xsd:minInclusive")) {
                        visitElement_xsd_minInclusive(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:maxInclusive")) {
                        visitElement_xsd_maxInclusive(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:maxExclusive")) {
                        visitElement_xsd_maxExclusive(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("xsd:enumeration")) {
                        visitElement_xsd_enumeration(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:minInclusive.
     */
    void visitElement_xsd_minInclusive(org.w3c.dom.Element element) {
        // <xsd:minInclusive>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("value")) {
                // <xsd:minInclusive value="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:maxInclusive.
     */
    void visitElement_xsd_maxInclusive(org.w3c.dom.Element element) {
        // <xsd:maxInclusive>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("value")) {
                // <xsd:maxInclusive value="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:maxExclusive.
     */
    void visitElement_xsd_maxExclusive(org.w3c.dom.Element element) {
        // <xsd:maxExclusive>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("value")) {
                // <xsd:maxExclusive value="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named xsd:enumeration.
     */
    void visitElement_xsd_enumeration(org.w3c.dom.Element element) {
        // <xsd:enumeration>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("value")) {
                // <xsd:enumeration value="???">
                // attr.getValue();
            }
        }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
                    // ((org.w3c.dom.CDATASection)node).getData();
                    break;
                case org.w3c.dom.Node.ELEMENT_NODE:
                    org.w3c.dom.Element nodeElement = (org.w3c.dom.Element) node;
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

}
