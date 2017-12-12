package com.example.ongenae.gestionparclille1.database;

import com.example.ongenae.gestionparclille1.TypeEnum;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Avast on 07/12/2017.
 */

@DatabaseTable(tableName="issues")
public class Issue implements Serializable {

    public static final String ISSUE_ID = "id";
    public static final String ISSUE_TYPE = "type";
    public static final String ISSUE_LATITUDE = "latitude";
    public static final String ISSUE_LONGITUDE = "longitude";
    public static final String ISSUE_ADRESSE = "adresse";
    public static final String ISSUE_DETAILS = "details";

    @DatabaseField(columnName = ISSUE_ID, generatedId = true)
    private Integer mId;

    @DatabaseField(columnName = ISSUE_TYPE)
    private TypeEnum mType;

    @DatabaseField(columnName = ISSUE_LATITUDE)
    private Double mLatitude;

    @DatabaseField(columnName = ISSUE_LONGITUDE)
    private Double mLongitude;

    @DatabaseField(columnName = ISSUE_ADRESSE)
    private String mAdresse;

    @DatabaseField(columnName = ISSUE_DETAILS)
    private String mDetails;


    /**
     * Constructeur vide pour l'orm
     */
    public Issue() {

    }

    /**
     * Constructeur avec tous les attributs nécessaires (donc sans l'id)
     * @param sType, le type provenant de l'énum
     * @param sLatitude, la latitude
     * @param sLongitude, la longitude
     * @param sAdresse, l'adresse
     * @param sDetails, les détails
     */
    public Issue(TypeEnum sType, Double sLatitude, Double sLongitude, String sAdresse, String sDetails) {
        this.mType = sType;
        this.mLatitude = sLatitude;
        this.mLongitude = sLongitude;
        this.mAdresse = sAdresse;
        this.mDetails = sDetails;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public TypeEnum getmType() {
        return mType;
    }

    public void setmType(TypeEnum mType) {
        this.mType = mType;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getmAdresse() {
        return mAdresse;
    }

    public void setmAdresse(String mAdresse) {
        this.mAdresse = mAdresse;
    }

    public String getmDetails() {
        return mDetails;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    @Override
    public String toString() {
        return this.getmType() + " " + this.getmDetails();
    }
}
