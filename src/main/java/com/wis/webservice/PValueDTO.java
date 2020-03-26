
package com.wis.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>pValueDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取isSound属性的值。
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
     * 设置isSound属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSound(String value) {
        this.isSound = value;
    }

    /**
     * 获取pid属性的值。
     * 
     */
    public long getPid() {
        return pid;
    }

    /**
     * 设置pid属性的值。
     * 
     */
    public void setPid(long value) {
        this.pid = value;
    }

    /**
     * 获取pname属性的值。
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
     * 设置pname属性的值。
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
     * 获取ptime属性的值。
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
     * 设置ptime属性的值。
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
     * 获取ptype属性的值。
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
     * 设置ptype属性的值。
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
     * 获取pvalue属性的值。
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
     * 设置pvalue属性的值。
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
     * 获取status属性的值。
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
     * 设置status属性的值。
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
     * 获取unit属性的值。
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
     * 设置unit属性的值。
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
     * 获取warning属性的值。
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
     * 设置warning属性的值。
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
