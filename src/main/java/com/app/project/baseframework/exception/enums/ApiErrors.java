package com.app.project.baseframework.exception.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Manjunath Reddy
 */
public enum ApiErrors {

    BLOCKED_PROTOCOL(HttpStatus.UPGRADE_REQUIRED.value(), 1000, "Protocol Upgrade required"),
    JWT_TOKEN_INVALID_OR_EXPIRED(HttpStatus.UNAUTHORIZED.value(), 1001, "Provided JWT token is Invalid or Expired"),

    /*BusinessExceptions*/
    MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES(HttpStatus.BAD_REQUEST.value(), 4000, "Missing mandatory data"),
    INVALID_REQUEST(HttpStatus.NOT_ACCEPTABLE.value(), 4008, "Invalid request"),

    /*DataFormatExceptions*/
    INCORRECT_DATE_INTERVAL(HttpStatus.BAD_REQUEST.value(), 5000, "Start date cannot be greater than End date"),
    INVALID_DATE_PARAMETER(HttpStatus.BAD_REQUEST.value(), 5001, "Invalid date combination"),
    GENERIC_INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), 5002, "Invalid Request"),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST.value(), 5003, "Invalid date format"),
    INVALID_DATA(HttpStatus.BAD_REQUEST.value(), 5004, "Invalid data combination"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), 5405, "Method Not Allowed"),

    /*PersistenceExceptions*/
    ERROR_WHILE_SAVING(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6000, "Error while saving"),
    ERROR_WHILE_RETRIEVAL(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6001, "Error communicating with database"),
    PARTIALLY_UPDATED(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6002, "Partially updated"),
    ERROR_WHILE_DELETION(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6003, "Error while deletion"),
    NO_RECORD_FOUND(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6004, "No records found"),
    USER_ALREADY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6005, "User account already exist"),
    USER_DOESNOT_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6006, "User account doesn't exist"),
    USER_ACCOUNT_INACTIVE(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6007, "User account is Inactive"),
    USER_ACCOUNT_PASSWORD_MISMATCH(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6008, "Login ID & password combination does not exist"),
    RECORD_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6011, "Record already exists"),
    MAX_ALLOWED_ACCOUNTS_LIMIT_REACHED(HttpStatus.INTERNAL_SERVER_ERROR.value(), 6012, "Max allowed account's limit is reached"),
    INTERNAL_PROCESSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 7004, "Internal processing error"),

    //Pack Error codes
    PURGE_AE_INVITED_TO_SYSTEM(HttpStatus.INTERNAL_SERVER_ERROR.value(), 8000, "User account doesn't exist and is Invited to System"),
    MAX_ALLOWED_PACKS_LIMIT_REACHED(HttpStatus.INTERNAL_SERVER_ERROR.value(), 8001, "Max allowed pack's limit is reached"),
    MIN_ASSOCIATED_YRS_CRITERIA_NOT_MET(HttpStatus.BAD_REQUEST.value(), 8002, "Minimum associated years (3) for the account criteria is not met"),
    PURGE_AE_ALREADY_EXISTS_IN_PACK(HttpStatus.BAD_REQUEST.value(), 8004, "Purg AE is in this pack already"),
    PURGE_AE_EXITED_FROM_PACK_EARLIER(HttpStatus.BAD_REQUEST.value(), 8005, "Purg AE was in this pack previously and Exited"),
    PURGE_AE_HAS_COMPLAINT(HttpStatus.BAD_REQUEST.value(), 8006, "Purg AE has been involved in a complaint (give or receive) "),
    AE_COMP_INPACK_EXISTS_SAME_AS_PURGE_AE_COMP(HttpStatus.BAD_REQUEST.value(), 8007, "There already is an AE in the pack that works at the same company as the Purg AE"),
    AE_IN_PACK_WORK_AT_COMPETITOR_COMPANY_PURGE_AE(HttpStatus.BAD_REQUEST.value(), 8008, "Co-Co competitor. There is an AE in the pack that works at a company that is a competitor to the Purg AE company"),
    PURGEAE_SALES_RANK_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), 8009, "This Purg AE is a CL,SI or MC but there is already a CL, SI or MC AE in the pack."),
    PURGEAE_NOT_IN_SAME_TIMEZONE_AS_AE(HttpStatus.BAD_REQUEST.value(), 8010, "Purg AE is not in same time zone as pack considered"),
    PURGE_AE_EMPLOYER_DO_NOT_RUN_ON_THE_SAME_CL_AS_AE(HttpStatus.BAD_REQUEST.value(), 8011, "Purg AE employer Co does not run on this CL"),
    PURGE_AE_EMPLOYER_CL_NOT_IN_COMMON_WITH_EVERY_PACK_MEMBER(HttpStatus.BAD_REQUEST.value(), 8012, "none of those CL vendors are in common with every pack member"),
    PURGE_AE_JOINING_PACK_HAVE_ATLEASTONE_CL_IN_COMMON(HttpStatus.BAD_REQUEST.value(), 8013, "AEs that join must always have at least a CL in common with every existing member of the considered pack"),
    PACK_SIZE_EXCEEDS_THE_MAX_ALLOWED_LIMIT(HttpStatus.BAD_REQUEST.value(), 8014, "Pack size exceeds the max allowed limit"),

    PACK_FOUNDER_DETAILS_NOT_AVAILABLE(HttpStatus.BAD_REQUEST.value(), 8015, "Pack founder details are not available"),
    PURGEAE_SALES_SI_RANK_ALREADY_EXISTS_IN_ANOTHER_PACK(HttpStatus.BAD_REQUEST.value(), 8016, "This Purg AE is a SI and is already present in another pack."),
    PURGE_AE_TIMEZONE_NOT_IN_COMMON_WITH_EVERY_PACK_MEMBER(HttpStatus.BAD_REQUEST.value(), 8017, "Purge AE Timezone not in the same Timezone as pack members"),
    PURGE_AE_EXP_LESSTHAN_THREE_AND_PACK_DOES_NOT_HAVE_FOUR_AE_WITH_EXP_GTTHAN_THREE(HttpStatus.BAD_REQUEST.value(), 8018, "The Pack should have Four AE with Three Plus years of experience"),
    PURGE_AE_EXP_LESSTHAN_THREE_AND_PACK_HAS_MAX_ALLOWED_SW_AE_WITH_EXP_LTTHAN_THREE(HttpStatus.BAD_REQUEST.value(), 8019, "The Pack has max allowed SW AEs with less than Three years of experience"),
    PURGE_AE_EXP_GREATERTHAN_THREE_AND_PACK_HAS_MAX_ALLOWED_SW_AE_WITH_EXP_GTTHAN_THREE(HttpStatus.BAD_REQUEST.value(), 8020, "The Pack has max allowed SW AEs with Three or more years of experience");

    public static final Map<String, ApiErrors> LOOKUP_BY_ERROR_DESCRIPTION = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    public static final Map<Integer, ApiErrors> LOOKUP_BY_ERROR_CODE = new TreeMap<>();

    static {
        for (ApiErrors s : values()) {
            LOOKUP_BY_ERROR_DESCRIPTION.put(s.getErrorDescription(), s);
            LOOKUP_BY_ERROR_CODE.put(s.getErrorCode(), s);
        }
    }

    private final Integer httpStatus;
    private final Integer errorCode;
    private final String errorDescription;

    private ApiErrors(Integer httpStatus, Integer errorCode, String errorDescription) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
