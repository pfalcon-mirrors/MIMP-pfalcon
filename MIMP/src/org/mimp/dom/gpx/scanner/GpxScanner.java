package org.mimp.dom.gpx.scanner;

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
        if ((element != null) && element.getTagName().equals("gpx")) {
            visitElement_gpx(element);
        }
        if ((element != null) && element.getTagName().equals("metadata")) {
            visitElement_metadata(element);
        }
        if ((element != null) && element.getTagName().equals("name")) {
            visitElement_name(element);
        }
        if ((element != null) && element.getTagName().equals("desc")) {
            visitElement_desc(element);
        }
        if ((element != null) && element.getTagName().equals("author")) {
            visitElement_author(element);
        }
        if ((element != null) && element.getTagName().equals("email")) {
            visitElement_email(element);
        }
        if ((element != null) && element.getTagName().equals("link")) {
            visitElement_link(element);
        }
        if ((element != null) && element.getTagName().equals("text")) {
            visitElement_text(element);
        }
        if ((element != null) && element.getTagName().equals("type")) {
            visitElement_type(element);
        }
        if ((element != null) && element.getTagName().equals("copyright")) {
            visitElement_copyright(element);
        }
        if ((element != null) && element.getTagName().equals("year")) {
            visitElement_year(element);
        }
        if ((element != null) && element.getTagName().equals("license")) {
            visitElement_license(element);
        }
        if ((element != null) && element.getTagName().equals("time")) {
            visitElement_time(element);
        }
        if ((element != null) && element.getTagName().equals("keywords")) {
            visitElement_keywords(element);
        }
        if ((element != null) && element.getTagName().equals("bounds")) {
            visitElement_bounds(element);
        }
        if ((element != null) && element.getTagName().equals("extensions")) {
            visitElement_extensions(element);
        }
        if ((element != null) && element.getTagName().equals("wpt")) {
            visitElement_wpt(element);
        }
        if ((element != null) && element.getTagName().equals("ele")) {
            visitElement_ele(element);
        }
        if ((element != null) && element.getTagName().equals("magvar")) {
            visitElement_magvar(element);
        }
        if ((element != null) && element.getTagName().equals("geoidheight")) {
            visitElement_geoidheight(element);
        }
        if ((element != null) && element.getTagName().equals("cmt")) {
            visitElement_cmt(element);
        }
        if ((element != null) && element.getTagName().equals("src")) {
            visitElement_src(element);
        }
        if ((element != null) && element.getTagName().equals("sym")) {
            visitElement_sym(element);
        }
        if ((element != null) && element.getTagName().equals("fix")) {
            visitElement_fix(element);
        }
        if ((element != null) && element.getTagName().equals("sat")) {
            visitElement_sat(element);
        }
        if ((element != null) && element.getTagName().equals("hdop")) {
            visitElement_hdop(element);
        }
        if ((element != null) && element.getTagName().equals("vdop")) {
            visitElement_vdop(element);
        }
        if ((element != null) && element.getTagName().equals("pdop")) {
            visitElement_pdop(element);
        }
        if ((element != null) && element.getTagName().equals("ageofdgpsdata")) {
            visitElement_ageofdgpsdata(element);
        }
        if ((element != null) && element.getTagName().equals("dgpsid")) {
            visitElement_dgpsid(element);
        }
        if ((element != null) && element.getTagName().equals("rte")) {
            visitElement_rte(element);
        }
        if ((element != null) && element.getTagName().equals("number")) {
            visitElement_number(element);
        }
        if ((element != null) && element.getTagName().equals("rtept")) {
            visitElement_rtept(element);
        }
        if ((element != null) && element.getTagName().equals("trk")) {
            visitElement_trk(element);
        }
        if ((element != null) && element.getTagName().equals("trkseg")) {
            visitElement_trkseg(element);
        }
        if ((element != null) && element.getTagName().equals("trkpt")) {
            visitElement_trkpt(element);
        }
    }

    /**
     * Scan through org.w3c.dom.Element named gpx.
     */
    void visitElement_gpx(org.w3c.dom.Element element) {
        // <gpx>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("creator")) {
                // <gpx creator="???">
                // attr.getValue();
            }
            if (attr.getName().equals("version")) {
                // <gpx version="???">
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
                    if (nodeElement.getTagName().equals("metadata")) {
                        visitElement_metadata(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("extensions")) {
                        visitElement_extensions(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("wpt")) {
                        visitElement_wpt(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("rte")) {
                        visitElement_rte(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("trk")) {
                        visitElement_trk(nodeElement);
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
     * Scan through org.w3c.dom.Element named metadata.
     */
    void visitElement_metadata(org.w3c.dom.Element element) {
        // <metadata>
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
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named name.
     */
    void visitElement_name(org.w3c.dom.Element element) {
        // <name>
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
     * Scan through org.w3c.dom.Element named desc.
     */
    void visitElement_desc(org.w3c.dom.Element element) {
        // <desc>
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
     * Scan through org.w3c.dom.Element named author.
     */
    void visitElement_author(org.w3c.dom.Element element) {
        // <author>
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
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named email.
     */
    void visitElement_email(org.w3c.dom.Element element) {
        // <email>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("domain")) {
                // <email domain="???">
                // attr.getValue();
            }
            if (attr.getName().equals("id")) {
                // <email id="???">
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
     * Scan through org.w3c.dom.Element named link.
     */
    void visitElement_link(org.w3c.dom.Element element) {
        // <link>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("href")) {
                // <link href="???">
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
                    if (nodeElement.getTagName().equals("text")) {
                        visitElement_text(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("type")) {
                        visitElement_type(nodeElement);
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
     * Scan through org.w3c.dom.Element named text.
     */
    void visitElement_text(org.w3c.dom.Element element) {
        // <text>
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
     * Scan through org.w3c.dom.Element named type.
     */
    void visitElement_type(org.w3c.dom.Element element) {
        // <type>
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
     * Scan through org.w3c.dom.Element named copyright.
     */
    void visitElement_copyright(org.w3c.dom.Element element) {
        // <copyright>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("author")) {
                // <copyright author="???">
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
                    if (nodeElement.getTagName().equals("year")) {
                        visitElement_year(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("license")) {
                        visitElement_license(nodeElement);
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
     * Scan through org.w3c.dom.Element named year.
     */
    void visitElement_year(org.w3c.dom.Element element) {
        // <year>
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
     * Scan through org.w3c.dom.Element named license.
     */
    void visitElement_license(org.w3c.dom.Element element) {
        // <license>
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
     * Scan through org.w3c.dom.Element named time.
     */
    void visitElement_time(org.w3c.dom.Element element) {
        // <time>
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
     * Scan through org.w3c.dom.Element named keywords.
     */
    void visitElement_keywords(org.w3c.dom.Element element) {
        // <keywords>
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
     * Scan through org.w3c.dom.Element named bounds.
     */
    void visitElement_bounds(org.w3c.dom.Element element) {
        // <bounds>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("maxlon")) {
                // <bounds maxlon="???">
                // attr.getValue();
            }
            if (attr.getName().equals("maxlat")) {
                // <bounds maxlat="???">
                // attr.getValue();
            }
            if (attr.getName().equals("minlon")) {
                // <bounds minlon="???">
                // attr.getValue();
            }
            if (attr.getName().equals("minlat")) {
                // <bounds minlat="???">
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
     * Scan through org.w3c.dom.Element named extensions.
     */
    void visitElement_extensions(org.w3c.dom.Element element) {
        // <extensions>
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
     * Scan through org.w3c.dom.Element named wpt.
     */
    void visitElement_wpt(org.w3c.dom.Element element) {
        // <wpt>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("lon")) {
                // <wpt lon="???">
                // attr.getValue();
            }
            if (attr.getName().equals("lat")) {
                // <wpt lat="???">
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
                    if (nodeElement.getTagName().equals("name")) {
                        visitElement_name(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("desc")) {
                        visitElement_desc(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("link")) {
                        visitElement_link(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("type")) {
                        visitElement_type(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("time")) {
                        visitElement_time(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("extensions")) {
                        visitElement_extensions(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("ele")) {
                        visitElement_ele(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("magvar")) {
                        visitElement_magvar(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("geoidheight")) {
                        visitElement_geoidheight(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("cmt")) {
                        visitElement_cmt(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("src")) {
                        visitElement_src(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("sym")) {
                        visitElement_sym(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("fix")) {
                        visitElement_fix(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("sat")) {
                        visitElement_sat(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("hdop")) {
                        visitElement_hdop(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("vdop")) {
                        visitElement_vdop(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("pdop")) {
                        visitElement_pdop(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("ageofdgpsdata")) {
                        visitElement_ageofdgpsdata(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("dgpsid")) {
                        visitElement_dgpsid(nodeElement);
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
     * Scan through org.w3c.dom.Element named ele.
     */
    void visitElement_ele(org.w3c.dom.Element element) {
        // <ele>
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
     * Scan through org.w3c.dom.Element named magvar.
     */
    void visitElement_magvar(org.w3c.dom.Element element) {
        // <magvar>
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
     * Scan through org.w3c.dom.Element named geoidheight.
     */
    void visitElement_geoidheight(org.w3c.dom.Element element) {
        // <geoidheight>
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
     * Scan through org.w3c.dom.Element named cmt.
     */
    void visitElement_cmt(org.w3c.dom.Element element) {
        // <cmt>
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
     * Scan through org.w3c.dom.Element named src.
     */
    void visitElement_src(org.w3c.dom.Element element) {
        // <src>
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
     * Scan through org.w3c.dom.Element named sym.
     */
    void visitElement_sym(org.w3c.dom.Element element) {
        // <sym>
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
     * Scan through org.w3c.dom.Element named fix.
     */
    void visitElement_fix(org.w3c.dom.Element element) {
        // <fix>
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
     * Scan through org.w3c.dom.Element named sat.
     */
    void visitElement_sat(org.w3c.dom.Element element) {
        // <sat>
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
     * Scan through org.w3c.dom.Element named hdop.
     */
    void visitElement_hdop(org.w3c.dom.Element element) {
        // <hdop>
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
     * Scan through org.w3c.dom.Element named vdop.
     */
    void visitElement_vdop(org.w3c.dom.Element element) {
        // <vdop>
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
     * Scan through org.w3c.dom.Element named pdop.
     */
    void visitElement_pdop(org.w3c.dom.Element element) {
        // <pdop>
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
     * Scan through org.w3c.dom.Element named ageofdgpsdata.
     */
    void visitElement_ageofdgpsdata(org.w3c.dom.Element element) {
        // <ageofdgpsdata>
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
     * Scan through org.w3c.dom.Element named dgpsid.
     */
    void visitElement_dgpsid(org.w3c.dom.Element element) {
        // <dgpsid>
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
     * Scan through org.w3c.dom.Element named rte.
     */
    void visitElement_rte(org.w3c.dom.Element element) {
        // <rte>
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
                    if (nodeElement.getTagName().equals("name")) {
                        visitElement_name(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("desc")) {
                        visitElement_desc(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("link")) {
                        visitElement_link(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("type")) {
                        visitElement_type(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("extensions")) {
                        visitElement_extensions(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("cmt")) {
                        visitElement_cmt(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("src")) {
                        visitElement_src(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("number")) {
                        visitElement_number(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("rtept")) {
                        visitElement_rtept(nodeElement);
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
     * Scan through org.w3c.dom.Element named number.
     */
    void visitElement_number(org.w3c.dom.Element element) {
        // <number>
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
     * Scan through org.w3c.dom.Element named rtept.
     */
    void visitElement_rtept(org.w3c.dom.Element element) {
        // <rtept>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("lon")) {
                // <rtept lon="???">
                // attr.getValue();
            }
            if (attr.getName().equals("lat")) {
                // <rtept lat="???">
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
                    if (nodeElement.getTagName().equals("name")) {
                        visitElement_name(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("desc")) {
                        visitElement_desc(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("link")) {
                        visitElement_link(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("type")) {
                        visitElement_type(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("time")) {
                        visitElement_time(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("extensions")) {
                        visitElement_extensions(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("ele")) {
                        visitElement_ele(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("magvar")) {
                        visitElement_magvar(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("geoidheight")) {
                        visitElement_geoidheight(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("cmt")) {
                        visitElement_cmt(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("src")) {
                        visitElement_src(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("sym")) {
                        visitElement_sym(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("fix")) {
                        visitElement_fix(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("sat")) {
                        visitElement_sat(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("hdop")) {
                        visitElement_hdop(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("vdop")) {
                        visitElement_vdop(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("pdop")) {
                        visitElement_pdop(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("ageofdgpsdata")) {
                        visitElement_ageofdgpsdata(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("dgpsid")) {
                        visitElement_dgpsid(nodeElement);
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
     * Scan through org.w3c.dom.Element named trk.
     */
    void visitElement_trk(org.w3c.dom.Element element) {
        // <trk>
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
                    if (nodeElement.getTagName().equals("name")) {
                        visitElement_name(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("desc")) {
                        visitElement_desc(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("link")) {
                        visitElement_link(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("type")) {
                        visitElement_type(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("extensions")) {
                        visitElement_extensions(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("cmt")) {
                        visitElement_cmt(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("src")) {
                        visitElement_src(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("number")) {
                        visitElement_number(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("trkseg")) {
                        visitElement_trkseg(nodeElement);
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
     * Scan through org.w3c.dom.Element named trkseg.
     */
    void visitElement_trkseg(org.w3c.dom.Element element) {
        // <trkseg>
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
                    if (nodeElement.getTagName().equals("extensions")) {
                        visitElement_extensions(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("trkpt")) {
                        visitElement_trkpt(nodeElement);
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
     * Scan through org.w3c.dom.Element named trkpt.
     */
    void visitElement_trkpt(org.w3c.dom.Element element) {
        // <trkpt>
        // element.getValue();
        // element.getValue();
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("lon")) {
                // <trkpt lon="???">
                // attr.getValue();
            }
            if (attr.getName().equals("lat")) {
                // <trkpt lat="???">
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
                    if (nodeElement.getTagName().equals("name")) {
                        visitElement_name(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("desc")) {
                        visitElement_desc(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("link")) {
                        visitElement_link(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("type")) {
                        visitElement_type(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("time")) {
                        visitElement_time(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("extensions")) {
                        visitElement_extensions(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("ele")) {
                        visitElement_ele(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("magvar")) {
                        visitElement_magvar(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("geoidheight")) {
                        visitElement_geoidheight(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("cmt")) {
                        visitElement_cmt(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("src")) {
                        visitElement_src(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("sym")) {
                        visitElement_sym(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("fix")) {
                        visitElement_fix(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("sat")) {
                        visitElement_sat(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("hdop")) {
                        visitElement_hdop(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("vdop")) {
                        visitElement_vdop(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("pdop")) {
                        visitElement_pdop(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("ageofdgpsdata")) {
                        visitElement_ageofdgpsdata(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("dgpsid")) {
                        visitElement_dgpsid(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
                    // ((org.w3c.dom.ProcessingInstruction)node).getTarget();
                    // ((org.w3c.dom.ProcessingInstruction)node).getData();
                    break;
            }
        }
    }

}
