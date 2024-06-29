package com.app.project.common;

/**
 * Created by Manjunath Reddy
 */
public class Constants {

    private Constants() {
    }

    public static final String KEY_ACCEPT = "Accept";
    public static final String KEY_CONTENT_TYPE = "Content-Type";
    public static final String KEY_ORIGIN = "Origin";
    public static final String VALUE_CONTENT_TYPE_JSON = "application/json";
    public static final Long MIN_ASSOCIATED_YRS_CRITERIA = 3L;
    public static final int RETRY_MAX_ATTEMPTS_EMAIL = 3;
    public static final int EXPIRY_HOURS_PACK_INVITATION = 0;
    public static final int EXPIRY_HOURS_PACK_CALL = 1;

    public static final String SOURCE_SYSTEM = "System";
    public static final String STATUS_REGISTERED = "Registered";
    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_EXIT = "Exit";
    public static final String STATUS_INACTIVE = "Inactive";
    public static final String STATUS_INVITED = "Invited";
    public static final String STATUS_SCHEDULED = "Scheduled";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_OVERRIDDEN = "Overridden";
    public static final String STATUS_ACCEPTED = "Accepted";
    public static final String STATUS_RECEIVED = "Received";
    public static final String STATUS_SUCCESS = "Success";
    public static final String STATUS_FAILURE = "Failure";
    public static final String STATUS_EXPIRED = "Expired";
    public static final String STATUS_YES = "Yes";
    public static final String STATUS_NO = "No";
    public static final String USER_TYPE_USER = "User";
    public static final String USER_TYPE_ADMIN = "Admin";
    public static final String USER_LOGOUT_MESSAGE = "You have successfully logged out.";
    public static final String USER_THANKS_RESPONSE_MESSAGE = "Thanks for encouraging us.";
    public static final String ENHANCE_FEATURE_RESPONSE_MESSAGE = "Thank you for helping us with new feature request. We will get back to you soon. Please let us know if you have any more questions. We will be happy to help";
    public static final int ENHANCE_FEATURE_PRIORITY_LOW = 3;
    public static final int ENHANCE_FEATURE_PRIORITY_MEDIUM = 2;
    public static final int ENHANCE_FEATURE_PRIORITY_HIGH = 1;
    public static final String PACK_USER_TYPE_OWNER = "Owner";
    public static final String PACK_USER_TYPE_MEMBER = "Member";
    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_UNDERSCORE = "_";
    public static final String DELIMITER_NEW_LINE = " \n ";
    public static final String DELIMITER_PERIOD = ".";
    public static final String UNITS_MONTHS = "Month(s)";
    public static final String KEY_PACK_NAME = "Pack";
    public static final String REASON_USER_ACCOUNT_COMPANY_DELETE = "Account company deleted by User";
    public static final int MINUTES_ONE = 1;
    public static final int LIMIT_RECORDS = 100;
    public static final int THRESHOLD_DAYS_LOGIN_ISSUES = 180;
    public static final int THRESHOLD_DAYS_USER_RATiNG = 30;

    public static final String SALES_TYPE_SW = "Software Sales";
    public static final String SALES_TYPE_MC = "Management Consulting Sales";
    public static final String SALES_TYPE_SI = "System Integrator Sales";
    public static final String SALES_TYPE_CL = "Cloud Sales";
    public static final String SALES_TYPE_MAPPING_SW = "SW";
    public static final String SALES_TYPE_MAPPING_MC = "MC";
    public static final String SALES_TYPE_MAPPING_SI = "SI";
    public static final String SALES_TYPE_MAPPING_CL = "CL";
    public static final Integer PACKS_ALLOWED_FOR_SALES_TYPE_SW = 1;
    public static final Integer PACKS_ALLOWED_FOR_SALES_TYPE_MC = 1;
    public static final Integer PACKS_ALLOWED_FOR_SALES_TYPE_SI = 1;
    public static final Integer PACKS_ALLOWED_FOR_ALES_TYPE_CL = 3;

    public static final Integer AE_ASSIGNMENT_LOGIC_PACK_USERS_MAX = 8;
    public static final Integer AE_ASSIGNMENT_LOGIC_PACK_USERS_MAX_ALLOWED_SW_LT_3_YRS = 3;
    public static final Integer AE_ASSIGNMENT_LOGIC_PACK_USERS_MAX_ALLOWED_SW_GT_3_YRS = 3;

    //REGEX
    public static final String REGEX_USER = "$user$";
    public static final String REGEX_HOURS = "$hours$";

    //Message Center Templates
    public static final String MESSAGE_WELCOME = "Thanks for registering with us. Your account is now active.";
    public static final String MESSAGE_SUBSCRIPTION_PLAN_ACTIVATED_FT = "Your free subscription plan is activated.";
    public static final String MESSAGE_ENHANCE_FEATURE_REQUEST_RECEIVED = ENHANCE_FEATURE_RESPONSE_MESSAGE;
    public static final String MESSAGE_PASSWORD_UPDATED_SUCCESS = "You have updated your password successfully. Report us immediately if it it not you.";
    public static final String MESSAGE_PACK_CREATED_SUCCESS = "Pack is created";
    public static final String MESSAGE_PACK_USER_ADDED_SUCCESS = "You have been added to pack successfully.";
    public static final String MESSAGE_PACK_USER_EXIT_SUCCESS = "You have exited Pack_";
    public static final String MESSAGE_PACK_USERS_ABOUT_EXIT = " has left Pack_";
    public static final String MESSAGE_PACK_USERS_ABOUT_USER_INVITATION_EXPIRY = "User should register within $hours$ hours after they are being invited. User $user$ has not completed the sign up process on time. Hence deleted the record.";
    public static final String MESSAGE_PACK_USERS_ABOUT_COMPLIMENT = "received a compliment from";
    public static final String MESSAGE_PACK_USERS_COMPLIMENT_TO_SUCCESS = "You received a compliment from ";
    public static final String MESSAGE_PACK_USERS_ABOUT_COMPLAINT = "received a complaint in the pack";
    public static final String MESSAGE_PACK_USERS_ABOUT_WARM_INTRO = "received a warm intro from";
    public static final String MESSAGE_PACK_USER_COMPLAINT_SUCCESS = "Your complaint is registered successfully.";
    public static final String MESSAGE_PACK_USERS_COMPLIMENT_BY_SUCCESS = "You gave a compliment to";
    public static final String MESSAGE_PACK_USER_WARM_INTRO_TO_SUCCESS = "You received a warm intro from";
    public static final String MESSAGE_PACK_USER_WARM_INTRO_BY_SUCCESS = "You gave a warm intro to";
    public static final String MESSAGE_PACK_USER_OPPORTUNITY_SUCCESS = "Your have a new opportunity available.";
    public static final String MESSAGE_PACK_USER_KEYWORD_IN = "in";

    //Ratings not done
    public static final String MESSAGE_NO_RATE_0_KEY = "No_Rate_0";
    public static final String MESSAGE_NO_RATE_0_VALUE = "Hello. A key feature of this platform is the ability of pack members to rate each other. We mandate this be done twice a year. This encourages members to be helpful. The exercise takes approximately 30seconds per pack. Please do this. Thanks!  Happy Hunting!";
    public static final String MESSAGE_NO_RATE_1W_KEY = "No_Rate_1w";
    public static final String MESSAGE_NO_RATE_1W_VALUE = "Hello. This notice is a reminder that you have some packs with members that you have not yet rated. The exercise takes approximately 30seconds per pack. Here are the packs you are in that have members unrated by you: ";
    public static final String MESSAGE_NO_RATE_2W_KEY = "No_Rate_2w";
    public static final String MESSAGE_NO_RATE_2W_VALUE = "Hello. It has been 2 weeks since we asked for pack ratings to be done. Thank you for the ratings you have done so far. However there remain some member you have not yet rated. The exercise takes approximately 30seconds per pack. The terms of service state we have the right to restrict or remove access. Here are the packs you are in that have members unrated by you: ";
    public static final String MESSAGE_NO_RATE_3W_KEY = "No_Rate_3w";
    public static final String MESSAGE_NO_RATE_3W_VALUE = "Hello. It has been 3 weeks since we asked for pack ratings to be done. Thank you for the ratings you have done so far. However there remain some member you have not yet rated. You have 5 more days until we restrict your account access to only review pack members. This is in the terms of service. Here are the packs you are in that have members unrated by you: ";
    public static final String MESSAGE_NO_RATE_1M_KEY = "No_Rate_1m";
    public static final String MESSAGE_NO_RATE_1M_VALUE = "Hello. It has been a month since we asked for pack ratings to be done. Thank you for the ratings you have done so far. However there remain some member you have not yet rated. Today is your last day with full access to softsellr. Tomorrow we will restrict your account access to only rate pack members. This is in the terms of service. Here are the packs you are in that have members unrated by you: ";
    public static final String MESSAGE_NO_RATE_LOCK_KEY = "No_Rate_lock";
    public static final String MESSAGE_NO_RATE_LOCK_VALUE = "Hello. We regret to inform you that we have locked your account. It has been a month since we asked for pack ratings to be done. We have given you 5 previous requests. However there remain some members you have not yet rated. This right is documented in the terms of service. Here are the packs you are in that have members unrated by you: ";
    public static final String MESSAGE_NO_RATE_HAPPY_HUNTING = "\n Thanks!  Happy Hunting!";

}
