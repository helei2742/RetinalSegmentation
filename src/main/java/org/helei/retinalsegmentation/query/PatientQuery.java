package org.helei.retinalsegmentation.query;

import lombok.Data;

@Data
public class PatientQuery extends BaseQuery{
    private Long doctorId;
    private String patientName;
    private String nativeArea;
    private String idCard;
    private Boolean gender;
    private Integer diagnoseType;
}
