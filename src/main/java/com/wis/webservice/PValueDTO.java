
package com.wis.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 *
 * <pre>
 * &lt;complexType name="pValueDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="isSound" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="pname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ptime" type="{http://scada.ws.qjyj.com/}timestamp" minOccurs="0"/&gt;
 *         &lt;element name="ptype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pvalue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="warning" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pValueDTO", propOrder = {
    "isSound",
    "pid",
    "pname",
    "ptime",
    "ptype",
    "pvalue",
    "status",
    "unit",
    "warning"
})
public class PValueDTO {

    protected String isSound;
    protected long pid;
    protected String pname;
    protected Timestamp ptime;
    protected String ptype;
    protected String pvalue;
    protected String status;
    protected String unit;
    protected String warning;

    /**
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIsSound() {
        return isSound;
    }

    /**
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIsSound(String value) {
        this.isSound = value;
    }

    public long getPid() {
        return pid;
    }

    /**
     *
     */
    public void setPid(long value) {
        this.pid = value;
    }

    /**
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPname() {
        return pname;
    }

    /**
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPname(String value) {
        this.pname = value;
    }

    /**
     *
     * @return
     *     possible object is
     *     {@link Timestamp }
     *
     */
    public Timestamp getPtime() {
        return ptime;
    }

    /**
     *
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *
     */
    public void setPtime(Timestamp value) {
        this.ptime = value;
    }

    /**
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPtype() {
        return ptype;
    }

    /**
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPtype(String value) {
        this.ptype = value;
    }

    /**
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPvalue() {
        return pvalue;
    }

    /**
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPvalue(String value) {
        this.pvalue = value;
    }

    /**
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUnit() {
        return unit;
    }

    /**
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getWarning() {
        return warning;
    }

    /**
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setWarning(String value) {
        this.warning = value;
    }

}
