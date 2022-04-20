package org.swasth.apigateway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.swasth.apigateway.exception.ErrorCodes;
import org.swasth.apigateway.exception.ServerException;
import org.swasth.apigateway.models.BaseRequest;
import org.swasth.apigateway.utils.HttpUtils;
import org.swasth.apigateway.utils.JSONUtils;
import org.swasth.apigateway.utils.Utils;
import org.swasth.auditindexer.function.AuditIndexer;

import java.util.*;

import static org.swasth.apigateway.constants.Constants.*;


@Service
public class AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);

    @Value("${hcx-api.basePath}")
    private String hcxApiUrl;

    @Autowired
    private AuditIndexer auditIndexer;

    public List<Map<String, Object>> getAuditLogs(Map<String,String> filters) throws Exception {
        String url = hcxApiUrl + "/v1/audit/search";
        HttpResponse response;
        try {
            response = HttpUtils.post(url, JSONUtils.serialize(Collections.singletonMap("filters", filters)));
        } catch (UnirestException e) {
            throw new ServerException(ErrorCodes.SERVICE_UNAVAILABLE, "Error connecting to audit service: " + e.getMessage());
        }
        List<Map<String,Object>> details;
        if (response != null && response.getStatus() == 200) {
            details = JSONUtils.deserialize((String) response.getBody(), ArrayList.class);
            System.out.println("Audit filters: " + filters + " Audit data count: " + details.size() + " Audit data: " + details);
        } else {
            throw new Exception("Error in fetching the audit logs" + response.getStatus());
        }
        return details;
    }

    public void createAuditLog(BaseRequest request) throws Exception {
        auditIndexer.createDocument(createAuditEvent(request));
    }

    private Map<String,Object> createAuditEvent(BaseRequest request) throws JsonProcessingException {
        Map<String,Object> event = new HashMap<>();
        event.put(EID, AUDIT);
        event.put(RECIPIENT_CODE, request.getRecipientCode());
        event.put(SENDER_CODE, request.getSenderCode());
        event.put(API_CALL_ID, request.getApiCallId());
        event.put(CORRELATION_ID, request.getCorrelationId());
        event.put(WORKFLOW_ID, request.getWorkflowId());
        event.put(TIMESTAMP, request.getTimestamp());
        event.put(ERROR_DETAILS, request.getErrorDetails());
        event.put(DEBUG_DETAILS, request.getDebugDetails());
        event.put(MID, Utils.getUUID());
        event.put(ACTION, request.getApiAction());
        event.put(STATUS, ERROR_STATUS);
        event.put(REQUESTED_TIME, System.currentTimeMillis());
        event.put(UPDATED_TIME, System.currentTimeMillis());
        event.put(AUDIT_TIMESTAMP, System.currentTimeMillis());
        event.put(SENDER_ROLE, request.getSenderRole());
        event.put(RECIPIENT_ROLE, request.getRecipientRole());
        event.put(PAYLOAD, removeEncryptionKey(request));
        return  event;
    }

    private String removeEncryptionKey(BaseRequest request) throws JsonProcessingException {
        if(request.isJSONRequest()) {
            return JSONUtils.serialize(request.getPayload());
        } else {
            List<String> modifiedPayload = new ArrayList<>(Arrays.asList(request.getPayload().get(PAYLOAD).toString().split("\\.")));
            modifiedPayload.remove(1);
            String[] payloadValues = modifiedPayload.toArray(new String[modifiedPayload.size()]);
            StringBuilder sb = new StringBuilder();
            for(String value: payloadValues) {
                sb.append(value).append(".");
            }
            return sb.deleteCharAt(sb.length()-1).toString();
        }
    }

}
