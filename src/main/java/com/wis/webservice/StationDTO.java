
package com.wis.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>stationDTO complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="stationDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fiveOutStationPressure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fourOutStationPressure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="inStationPressure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="manifoldPressureOne" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="manifoldPressureTwo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oneOutStationPressure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pselected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pvalues" type="{http://scada.ws.qjyj.com/}pValueDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sid" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="statName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stationParameterDTOs" type="{http://scada.ws.qjyj.com/}stationParameterDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="stationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="threeOutStationPressure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="timeCreated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="twoOutStationPressure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stationDTO", propOrder = {
    "fiveOutStationPressure",
    "fourOutStationPressure",
    "inStationPressure",
    "manifoldPressureOne",
    "manifoldPressureTwo",
    "oneOutStationPressure",
    "pselected",
    "pvalues",
    "sid",
    "statName",
    "stationParameterDTOs",
    "stationType",
    "status",
    "threeOutStationPressure",
    "timeCreated",
    "twoOutStationPressure"
})
public class StationDTO {

    protected String fiveOutStationPressure;
    protected String fourOutStationPressure;
    protected String inStationPressure;
    protected String manifoldPressureOne;
    protected String manifoldPressureTwo;
    protected String oneOutStationPressure;
    protected String pselected;
    @XmlElement(nillable = true)
    protected List<PValueDTO> pvalues;
    protected long sid;
    protected String statName;
    @XmlElement(nillable = true)
    protected List<StationParameterDTO> stationParameterDTOs;
    protected String stationType;
    protected String status;
    protected String threeOutStationPressure;
    protected String timeCreated;
    protected String twoOutStationPressure;

    /**
     * 获取fiveOutStationPressure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiveOutStationPressure() {
        return fiveOutStationPressure;
    }

    /**
     * 设置fiveOutStationPressure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiveOutStationPressure(String value) {
        this.fiveOutStationPressure = value;
    }

    /**
     * 获取fourOutStationPressure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFourOutStationPressure() {
        return fourOutStationPressure;
    }

    /**
     * 设置fourOutStationPressure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFourOutStationPressure(String value) {
        this.fourOutStationPressure = value;
    }

    /**
     * 获取inStationPressure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInStationPressure() {
        return inStationPressure;
    }

    /**
     * 设置inStationPressure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInStationPressure(String value) {
        this.inStationPressure = value;
    }

    /**
     * 获取manifoldPressureOne属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManifoldPressureOne() {
        return manifoldPressureOne;
    }

    /**
     * 设置manifoldPressureOne属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManifoldPressureOne(String value) {
        this.manifoldPressureOne = value;
    }

    /**
     * 获取manifoldPressureTwo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManifoldPressureTwo() {
        return manifoldPressureTwo;
    }

    /**
     * 设置manifoldPressureTwo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManifoldPressureTwo(String value) {
        this.manifoldPressureTwo = value;
    }

    /**
     * 获取oneOutStationPressure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOneOutStationPressure() {
        return oneOutStationPressure;
    }

    /**
     * 设置oneOutStationPressure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOneOutStationPressure(String value) {
        this.oneOutStationPressure = value;
    }

    /**
     * 获取pselected属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPselected() {
        return pselected;
    }

    /**
     * 设置pselected属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPselected(String value) {
        this.pselected = value;
    }

    /**
     * Gets the value of the pvalues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pvalues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPvalues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PValueDTO }
     * 
     * 
     */
    public List<PValueDTO> getPvalues() {
        if (pvalues == null) {
            pvalues = new ArrayList<PValueDTO>();
        }
        return this.pvalues;
    }

    /**
     * 获取sid属性的值。
     * 
     */
    public long getSid() {
        return sid;
    }

    /**
     * 设置sid属性的值。
     * 
     */
    public void setSid(long value) {
        this.sid = value;
    }

    /**
     * 获取statName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatName() {
        return statName;
    }

    /**
     * 设置statName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatName(String value) {
        this.statName = value;
    }

    /**
     * Gets the value of the stationParameterDTOs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stationParameterDTOs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStationParameterDTOs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StationParameterDTO }
     * 
     * 
     */
    public List<StationParameterDTO> getStationParameterDTOs() {
        if (stationParameterDTOs == null) {
            stationParameterDTOs = new ArrayList<StationParameterDTO>();
        }
        return this.stationParameterDTOs;
    }

    /**
     * 获取stationType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationType() {
        return stationType;
    }

    /**
     * 设置stationType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationType(String value) {
        this.stationType = value;
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
     * 获取threeOutStationPressure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThreeOutStationPressure() {
        return threeOutStationPressure;
    }

    /**
     * 设置threeOutStationPressure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThreeOutStationPressure(String value) {
        this.threeOutStationPressure = value;
    }

    /**
     * 获取timeCreated属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeCreated() {
        return timeCreated;
    }

    /**
     * 设置timeCreated属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeCreated(String value) {
        this.timeCreated = value;
    }

    /**
     * 获取twoOutStationPressure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTwoOutStationPressure() {
        return twoOutStationPressure;
    }

    /**
     * 设置twoOutStationPressure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTwoOutStationPressure(String value) {
        this.twoOutStationPressure = value;
    }

}
