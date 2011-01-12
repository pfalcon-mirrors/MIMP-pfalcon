package org.mimp.dom.gpx.scanner;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.mimp.dom.ParsedObject;
import org.mimp.dom.gpx.GpxType;
import org.mimp.dom.gpx.RteType;
import org.mimp.dom.gpx.TrkType;
import org.mimp.dom.gpx.TrksegType;
import org.mimp.dom.gpx.WptType;

/**
 *
 * @author hellhand
 */
public class GpxScanner {
    /**
     * org.w3c.dom.Document document
     */
    org.w3c.dom.Document document;
    GpxType gpxType;

    /**
     * Create new GpxScanner.
     */
    public GpxScanner() {
        
    }
    
    /**
     * Create new GpxScanner with org.w3c.dom.Document.
     */
    public GpxScanner(org.w3c.dom.Document document) {
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
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
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
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
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
        if ("wpt".equals(parentNode)) {
            gpxType.getLastWpt().setName(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rte".equals(parentNode)) {
            gpxType.getLastRte().setName(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setName(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trk".equals(parentNode)) {
            gpxType.getLastTrk().setName(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setName(element.getChildNodes().item(0).getNodeValue());
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
        if ("wpt".equals(parentNode)) {
            gpxType.getLastWpt().setDesc(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rte".equals(parentNode)) {
            gpxType.getLastRte().setDesc(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setDesc(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trk".equals(parentNode)) {
            gpxType.getLastTrk().setDesc(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setDesc(element.getChildNodes().item(0).getNodeValue());
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
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
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
                if ("wpt".equals(parentNode)) {
                    gpxType.getLastWpt().getLastLink().setHref(attr.getValue());
                }
                if ("rte".equals(parentNode)) {
                    gpxType.getLastRte().getLastLink().setHref(attr.getValue());
                }
                if ("rtept".equals(parentNode)) {
                    gpxType.getLastRte().getLastRtept().getLastLink().setHref(attr.getValue());
                }
                if ("trk".equals(parentNode)) {
                    gpxType.getLastTrk().getLastLink().setHref(attr.getValue());
                }
                if ("trkpt".equals(parentNode)) {
                    gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().getLastLink().setHref(attr.getValue());
                }
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
                    if (nodeElement.getTagName().equals("text")) {
                        visitElement_text(nodeElement);
                    }
                    if (nodeElement.getTagName().equals("type")) {
                        visitElement_type(nodeElement);
                    }
                    break;
                case org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE:
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
        if ("wpt".equals(parentNode)) {
            gpxType.getLastWpt().getLastLink().setText(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rte".equals(parentNode)) {
            gpxType.getLastRte().getLastLink().setText(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().getLastLink().setText(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trk".equals(parentNode)) {
            gpxType.getLastTrk().getLastLink().setText(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().getLastLink().setText(element.getChildNodes().item(0).getNodeValue());
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
        if ("wpt".equals(parentNode)) {
            gpxType.getLastWpt().getLastLink().setType(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rte".equals(parentNode)) {
            gpxType.getLastRte().getLastLink().setType(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().getLastLink().setType(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trk".equals(parentNode)) {
            gpxType.getLastTrk().getLastLink().setType(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().getLastLink().setType(element.getChildNodes().item(0).getNodeValue());
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
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
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
        if("wpt".equals(parentNode)) {
            gpxType.getLastWpt().setTime(element.getChildNodes().item(0).getNodeValue());
        }
        if("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setTime(element.getChildNodes().item(0).getNodeValue());
        }
        if("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setTime(element.getChildNodes().item(0).getNodeValue());
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
        if ("wpt".equals(parentNode)) {
            gpxType.getLastWpt().setExtensions(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rte".equals(parentNode)) {
            gpxType.getLastRte().setExtensions(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setExtensions(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trk".equals(parentNode)) {
            gpxType.getLastTrk().setExtensions(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkseg".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().setExtensions(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setExtensions(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named wpt.
     */
    void visitElement_wpt(org.w3c.dom.Element element) {
        gpxType.getWpt().add(new WptType());
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("lon")) {
                gpxType.getLastWpt().setLon(new BigDecimal(attr.getValue()));
            }
            if (attr.getName().equals("lat")) {
                gpxType.getLastWpt().setLat(new BigDecimal(attr.getValue()));
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
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named ele.
     */
    void visitElement_ele(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setEle(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setEle(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setEle(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
    }

    /**
     * Scan through org.w3c.dom.Element named magvar.
     */
    void visitElement_magvar(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setMagvar(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setMagvar(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setMagvar(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
    }

    /**
     * Scan through org.w3c.dom.Element named geoidheight.
     */
    void visitElement_geoidheight(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setGeoidheight(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        } 
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setGeoidheight(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setGeoidheight(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
    }

    /**
     * Scan through org.w3c.dom.Element named cmt.
     */
    void visitElement_cmt(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setCmt(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rte".equals(parentNode)) {
            gpxType.getLastRte().setCmt(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setCmt(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trk".equals(parentNode)) {
            gpxType.getLastTrk().setCmt(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setCmt(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named src.
     */
    void visitElement_src(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setSrc(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rte".equals(parentNode)) {
            gpxType.getLastRte().setSrc(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setSrc(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trk".equals(parentNode)) {
            gpxType.getLastTrk().setSrc(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setSrc(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named sym.
     */
    void visitElement_sym(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setSym(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setSym(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setSym(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named fix.
     */
    void visitElement_fix(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setFix(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setFix(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setFix(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named sat.
     */
    void visitElement_sat(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setSat(new BigInteger(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setSat(new BigInteger(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setSat(new BigInteger(element.getChildNodes().item(0).getNodeValue()));
        }
    }

    /**
     * Scan through org.w3c.dom.Element named hdop.
     */
    void visitElement_hdop(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setHdop(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setHdop(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setHdop(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
    }

    /**
     * Scan through org.w3c.dom.Element named vdop.
     */
    void visitElement_vdop(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setVdop(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setVdop(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setVdop(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
    }

    /**
     * Scan through org.w3c.dom.Element named pdop.
     */
    void visitElement_pdop(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setPdop(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setPdop(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setPdop(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
    }

    /**
     * Scan through org.w3c.dom.Element named ageofdgpsdata.
     */
    void visitElement_ageofdgpsdata(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setAgeofdgpsdata(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setAgeofdgpsdata(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setAgeofdgpsdata(new BigDecimal(element.getChildNodes().item(0).getNodeValue()));
        }
    }

    /**
     * Scan through org.w3c.dom.Element named dgpsid.
     */
    void visitElement_dgpsid(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("wp".equals(parentNode)) {
            gpxType.getLastWpt().setDgpsid(element.getChildNodes().item(0).getNodeValue());
        }
        if ("rtept".equals(parentNode)) {
            gpxType.getLastRte().getLastRtept().setDgpsid(element.getChildNodes().item(0).getNodeValue());
        }
        if ("trkpt".equals(parentNode)) {
            gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setDgpsid(element.getChildNodes().item(0).getNodeValue());
        }
    }

    /**
     * Scan through org.w3c.dom.Element named rte.
     */
    void visitElement_rte(org.w3c.dom.Element element) {
        gpxType.getRte().add(new RteType());
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
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named number.
     */
    void visitElement_number(org.w3c.dom.Element element) {
        String parentNode = element.getParentNode().getNodeName();
        if ("rte".equals(parentNode)) {
            gpxType.getLastRte().setNumber(new BigInteger(element.getChildNodes().item(0).getNodeValue()));
        }
        if ("trk".equals(parentNode)) {
            gpxType.getLastTrk().setNumber(new BigInteger(element.getChildNodes().item(0).getNodeValue()));
        }
    }

    /**
     * Scan through org.w3c.dom.Element named rtept.
     */
    void visitElement_rtept(org.w3c.dom.Element element) {
        gpxType.getLastRte().getRtept().add(new WptType());
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("lon")) {
                gpxType.getLastRte().getLastRtept().setLon(new BigDecimal(attr.getValue()));
            }
            if (attr.getName().equals("lat")) {
                gpxType.getLastRte().getLastRtept().setLat(new BigDecimal(attr.getValue()));
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
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named trk.
     */
    void visitElement_trk(org.w3c.dom.Element element) {
        gpxType.getTrk().add(new TrkType());
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
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named trkseg.
     */
    void visitElement_trkseg(org.w3c.dom.Element element) {
        gpxType.getLastTrk().getTrkseg().add(new TrksegType());
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            switch (node.getNodeType()) {
                case org.w3c.dom.Node.CDATA_SECTION_NODE:
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
                    break;
            }
        }
    }

    /**
     * Scan through org.w3c.dom.Element named trkpt.
     */
    void visitElement_trkpt(org.w3c.dom.Element element) {
        gpxType.getLastTrk().getLastTrkseg().getTrkpt().add(new WptType());
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr) attrs.item(i);
            if (attr.getName().equals("lon")) {
                gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setLon(new BigDecimal(attr.getValue()));
            }
            if (attr.getName().equals("lat")) {
                gpxType.getLastTrk().getLastTrkseg().getLastTrkpt().setLat(new BigDecimal(attr.getValue()));
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
                    break;
            }
        }
    }
}