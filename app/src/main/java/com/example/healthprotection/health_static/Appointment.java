package com.example.healthprotection.health_static;

import java.io.Serializable;

public class Appointment implements Serializable {
    String patientname;
    String patientDiagnosis;
    String diagnosisdescription;
    String time;
    String date;
    String status;
    String PatientId;
    String doctorID;

    Appointment() {

    }

    @Override
    public String toString() {
        return "Appointment" +
                "\npatientname          =" + patientname +
                "\npatientDiagnosis     =" + patientDiagnosis +
                "\ndiagnosisdescription =" + diagnosisdescription +
                "\ntime                 =" + time +
                "\ndate                 =" + date ;
    }

    public Appointment(String patientname, String patientDiagnosis, String diagnosisdescription, String time, String date, String status, String patientId, String doctorID) {
        this.patientname = patientname;
        this.patientDiagnosis = patientDiagnosis;
        this.diagnosisdescription = diagnosisdescription;
        this.time = time;
        this.date = date;
        this.status = status;
        PatientId = patientId;
        this.doctorID = doctorID;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getPatientDiagnosis() {
        return patientDiagnosis;
    }

    public void setPatientDiagnosis(String patientDiagnosis) {
        this.patientDiagnosis = patientDiagnosis;
    }

    public String getDiagnosisdescription() {
        return diagnosisdescription;
    }

    public void setDiagnosisdescription(String diagnosisdescription) {
        this.diagnosisdescription = diagnosisdescription;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }
}
