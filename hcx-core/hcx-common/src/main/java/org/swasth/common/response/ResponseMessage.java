package org.swasth.common.response;

public class ResponseMessage {
    public static final String INVALID_TOPIC_CODE = "Topic code is empty or invalid";
    public static final String NOTHING_TO_UPDATE = "Nothing to update";
    public static final String INVALID_NOTIFICATION_FILTERS = "Invalid notifications filters, allowed properties are: {0}";
    public static final String EXPIRY_CANNOT_BE_PAST_DATE = "Expiry cannot be past date";
    public static final String SUBSCRIPTION_STATUS_VALUE_IS_INVALID = "Subscription status value is invalid";
    public static final String IS_DELEGATED_VALUE_IS_INVALID = "Is delegated value is invalid";
    public static final String SUBSCRIPTION_DOES_NOT_EXIST = "Subscription does not exist";
    public static final String INVALID_SUBSCRIPTION_FILTERS = "Invalid notification subscription filters, allowed properties are: {0}";
    public static final String UPDATE_MESSAGE_SUBSCRIPTION_ID = "Unable to update record with subscription id: {0}";
    public static final String INVALID_SUBSCRIPTION_ID = "Invalid subscription id: {0}";
    public static final String INVALID_SUBSCRIPTION_LIST = "Invalid subscriptions list: {0}";
    public static final String INVALID_STATUS_SEARCH_ENTITY = "Invalid entity, status search allowed only for entities: {0}";
    public static final String CORRELATION_ID_MISSING = "Invalid correlation id, details do not exist";
    public static final String SERVICE_UNAVAILABLE = "The server is temporarily unable to service your request. Please try again later.";
    public static final String INVALID_CORRELATION_ID = "Invalid Correlation Id";
    public static final String INVALID_WORKFLOW_ID = "The request contains invalid workflow id";
    public static final String INVALID_PARTICIPANT_CODE = "Please provide valid participant code";
    public static final String PARTICIPANT_CODE_MSG = "Please provide valid participant code";
    public static final String PARTICIPANT_ERROR_MSG = "Error in creating participant :: Exception: {0}";
    public static final String INVALID_REGISTRY_RESPONSE = "Error in creating participant, invalid response from registry";
    public static final String INVALID_ROLES_PROPERTY = "roles property cannot be null, empty or other than 'ArrayList'";
    public static final String MISSING_SCHEME_CODE = "scheme_code property is missing";
    public static final String UNKNOWN_PROPERTY = "unknown property, 'scheme_code' is not allowed";
    public static final String INVALID_END_POINT = "end point url should not be the HCX Gateway/APIs URL";
    public static final String INVALID_EMAIL = "primary_email is missing or invalid";
    public static final String INVALID_ENCRYPTION_CERT = "encryption_cert is missing or invalid";
    public static final String VALID_SEARCH_OBJ_MSG = "Search details cannot be null, empty and should be 'JSON Object'";
    public static final String VALID_SEARCH_MSG = "Search details should contain only: ";
    public static final String VALID_SEARCH_RESP_OBJ_MSG = "Search response details cannot be null, empty and should be 'JSON Object'";
    public static final String VALID_SEARCH_RESP_MSG = "Search response details should contain only: ";
    public static final String VALID_SEARCH_REGISTRY_CODE = "Search recipient code must be hcx registry code";
    public static final String VALID_SEARCH_FILTERS_MSG = "Search Filters should contain only: {0}";
    public static final String VALID_SEARCH_FILTERS_OBJ_MSG = "Search filters cannot be null and should be 'JSON Object'";
    public static final String INVALID_SENDER = "Sender does not exist in registry";
    public static final String ACCESS_DENIED_MSG = "Does not have access to the called API";
    public static final String AUTH_HEADER_MISSING = "Authorization header is missing";
    public static final String AUTH_HEADER_EMPTY = "Authorization header is empty";
    public static final String AUTH_MALFORMED = "Malformed Authorization content";
    public static final String BEARER_MISSING = "Bearer is needed";
    public static final String AUDIT_LOG_MSG = "Error while creating audit log :: Exception : {0}";
    public static final String INVALID_KEY_HS_MSG = "Invalid key type, supported HMAC methods are HS256, HS384 & HS512!";
    public static final String INVALID_KEY_RS_MSG = "Invalid key type, supported RSA methods are RS256, RS384 & RS512!";
    public static final String INVALID_JWT_MSG = "Invalid JWT configuration.";
    public static final String AUDIT_SERVICE_ERROR = "Error connecting to audit service: {0}";
    public static final String AUDIT_LOG_FETCH_MSG = "Error in fetching the audit logs. Status Code is {0}";
    public static final String REGISTRY_SERVICE_ERROR = "Error connecting to registry service: {0}";
    public static final String REGISTRY_SERVICE_FETCH_MSG = "Error in fetching the participant details. Status Code is {0}";
    public static final String INVALID_TIMESTAMP_MSG = "Timestamp should be a valid ISO-8061 format, Exception msg: {0}";
    public static final String INVALID_JWE_MSG = "Request body should be a proper JWE object for action API calls";
    public static final String INVALID_STATUS_REDIRECT = "Invalid redirect request,{0} status is not allowed for redirect, Allowed status is {1}";
    public static final String INVALID_ACTION_REDIRECT = "Invalid redirect request,{0} is not allowed for redirect, Allowed APIs are: {1}";
    public static final String CORRELATION_ERR_MSG = "Exception occurred for request with correlationId: {0} :: error message: {1}";
    public static final String PAYLOAD_PARSE_ERR = "Error while parsing the payload";
    public static final String INVALID_API_CALL_UUID = "API call id should be a valid UUID";
    public static final String INVALID_CORRELATION_UUID = "Correlation id should be a valid UUID";
    public static final String MISSING_MANDATORY_HEADERS = "Mandatory headers are missing: {0}";
    public static final String TIMESTAMP_FUTURE_MSG = "Timestamp cannot be in future or cross more than {0} hours in past";
    public static final String INVALID_WORKFLOW_UUID = "Workflow id should be a valid UUID";
    public static final String SENDER_RECIPIENT_SAME_MSG = "sender and recipient code cannot be the same";
    public static final String CALLER_MISMATCH_MSG = "Caller id and sender code is not matched";
    public static final String DEBUG_FLAG_ERR = "Debug flag cannot be null, empty and other than 'String'";
    public static final String DEBUG_FLAG_VALUES_ERR = "Debug flag cannot be other than Error, Info or Debug";
    public static final String MANDATORY_STATUS_MISSING = "Mandatory headers are missing: {0}";
    public static final String STATUS_ERR = "Status cannot be null, empty and other than 'String'";
    public static final String STATUS_VALUES = "Status value for on_* API calls can be only: ";
    public static final String STATUS_ON_ACTION_VALUES = "Status value for *action API calls can be only: ";
    public static final String ERROR_DETAILS_MSG = "Error details cannot be null, empty and other than 'JSON Object'";
    public static final String ERROR_VALUES = "Error details should contain only: ";
    public static final String INVALID_ERROR_CODE = "Invalid Error Code";
    public static final String DEBUG_DETAILS_ERR = "Debug details cannot be null, empty and other than 'JSON Object'";
    public static final String DEBUG_DETAILS_VALUES_ERR = "Debug details should contain only: ";
    public static final String MISSING_PARTICIPANT = "{0} does not exist in the registry";
    public static final String INVALID_REGISTRY_STATUS = "{0}  is blocked or inactive as per the registry";
    public static final String HCX_CODE_ERR = "{0} should not be sent as sender/recipient in the incoming requests";
    public static final String HCX_ROLES_ERR = "{0} role is not be sent as sender/recipient in the incoming requests";
    public static final String MANDATORY_CODE_MSG = "Mandatory fields code or message is missing";
    public static final String SENDER_CODE_ERR_MSG = "Sender code cannot be null, empty and other than 'String'";
    public static final String RECIPIENT_CODE_ERR_MSG = "Recipient code cannot be null, empty and other than 'String'";
    public static final String TIMESTAMP_INVALID_MSG = "Invalid timestamp";
    public static final String INVALID_WORKFLOW_ID_ERR_MSG = "Workflow id cannot be null, empty and other than 'String'";
    public static final String INVALID_CORRELATION_ID_ERR_MSG = "Correlation id cannot be null, empty and other than 'String'";
    public static final String INVALID_API_CALL_ID_ERR_MSG = "Api call id cannot be null, empty and other than 'String'";
    public static final String API_CALL_SAME_MSG = "Request exist with same api call id";
    public static final String CLOSED_CYCLE_MSG = "Invalid request, cycle is closed for correlation id";
    public static final String INVALID_ON_ACTION = "Invalid on_action request, corresponding action request does not exist";
    public static final String INVALID_FORWARD = "Entity is not allowed for forwarding";
    public static final String FORWARD_REQ_ERR_MSG = "Request cannot be forwarded to the forward initiators";
    public static final String INVALID_RECIPIENT = "Invalid recipient";
    public static final String MALFORMED_PAYLOAD = "Malformed payload";
    public static final String INVALID_PAYLOAD = "Payload contains null or empty values";
    public static final String INVALID_FWD_CORRELATION_ID = "The request contains invalid correlation id";
    public static final String INVALID_REDIRECT_MSG = "Redirect requests must have valid participant code for field {0}";
    public static final String INVALID_REDIRECT_SELF = "Sender can not redirect request to self";
    public static final String INVALID_REDIRECT_PARTICIPANT = "Redirected participant do not have access to send across callbacks (on_* API calls)";
    public static final String INVALID_API_CALL = "Already request exists with same api call id:{0}";
    public static final String ON_ACTION_CORRELATION_ERR_MSG = "The on_action request should contain the same correlation id as in corresponding action request";
    public static final String ON_ACTION_WORKFLOW_ID = "The on_action request should contain the same workflow id as in corresponding action request";
    public static final String CLOSED_REDIRECT_MSG = "The redirected request has been closed with status as response.complete";
    public static final String REDIRECT_INITIATOR_MSG = "Redirect request can not be redirected to one of the initiators";
    public static final String INVALID_ALGO = "Algorithm is missing or invalid";
    public static final String INVALID_JWS = "JWS payload is not signed by the request initiator";
    public static final String NOTIFICATION_TS_MSG = "Notification timestamp is missing or empty";
    public static final String NOTIFICATION_HEADER_ERR_MSG = "Notification headers is missing or empty";
    public static final String NOTIFICATION_EXPIRY = "Notification expiry cannot be past date";
    public static final String NOTIFICATION_RECIPIENT_ERR_MSG = "Recipient type is missing or empty";
    public static final String NOTIFICATION_RECIPIENT_LIST = "Recipients list is empty";
    public static final String NOTIFICATION_MESSAGE_ERR = "Notification message is missing or empty";
    public static final String NOTIFICATION_TOPIC_ERR = "Notification topic code cannot be null, empty and other than 'String'";
    public static final String NOTIFICATION_INVALID_TOPIC = "Invalid topic code({0}) is not present in the master list of notifications";
    public static final String NOTIFICATION_STATUS = "Notification status is inactive";
    public static final String NOTIFICATION_TRIGGER_ERR = "Participant is not authorized to trigger this notification: {0}";
    public static final String NOTIFICATION_RECIPIENT_ERR = "{0} is not a allowed recipient of this notification: {1}";
    public static final String NOTIFICATION_NOT_ALLOWED_RECIPIENTS = "Recipient roles are out of range, allowed recipients for this notification: {0}";
    public static final String NOTIFICATION_MANDATORY_HEADERS = "Notification request does not have mandatory headers: {0} , {1}";
    public static final String NOTIFICATION_NOT_ALLOWED = "Participant is not allowed to receive this notification: {0}";
    public static final String NOTIFICATION_TRIGGER_ERR_MSG = "{0} is not allowed to trigger this notification: {1}";
    public static final String RECIPIENT_NOT_ALLOWED = "Recipient type is invalid, allowed type are: {0}";
    public static final String UNSUBSCRIBE_ERR_MSG = "Provide proper senders as * is not allowed for unsubscribe";
    
}
